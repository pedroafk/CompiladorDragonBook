package parser;
import java.io.IOException;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;
import lexer.Word;
import symbols.*;

public class Parser {
  private Lexer lexer; // the scanner
  private Token lookahead; // the next token
  private boolean parsingOK;
  Env top = null;

  /**
   * Constructor class
   * 
   * @param lexer
   *              The scanner
   */
  public Parser(Lexer lexer) {
    this.lexer = lexer;
    parsingOK = false;
  }

  /**
   * Returns if parsing was successful
   * 
   * @return true if parsing was successful.
   */
  public boolean isParsingSuccessful() {
    return parsingOK;
  }

  /**
   * Match the current token
   * 
   * @throws IOException
   */
  private void nextToken () {
    try {
      lookahead = lexer.scanner();
    } catch (IOException ioException) {
      System.err.println(ioException.toString());
    }
  }

  /**
   * Prints the error message
   * 
   * @param errorMessage
   *                     The error message
   */
  private void error(String errorMessage) {
    throw new Error("line " + Lexer.line + ": " + errorMessage);
  }

  /**
   * Match the current token with the t token expected
   * 
   * @param t
   *          The token expected
   * @throws IOException
   */
  private void match(Token t) {
    if (lookahead.getTag() == t.getTag()) {
      System.out.println(Lexer.line+": "+lookahead.toString());
      System.out.printf("Token " + t.toString() + "\n"+"\n");
      nextToken();
    } else 
      error("syntax error, "+t.toString()+" was expected!");
  }

  //Sempre tratar as recursividades a esquerda
  public void program(){
    nextToken();
    block();
  }
  
  private void block(){
    if(lookahead.getTag() == 255)
      return;
    if(lookahead.getTag() == '{'){
      System.out.printf("Abriu Block" + "\n");
      match(new Token('{'));
      decls();
      stmts();
      System.out.printf("Fechou Block" + "\n");
      match(new Token('}'));
    } 
  }

  private void decls(){
    decls_();
  }

  private void decls_(){
    if(lookahead.getTag() == Tag.BASIC){
      decl();
      decls_();
    }
  }

  private void decl(){
    type();
    match(new Token(Tag.ID));
    match(new Token(';'));
  }

  private void type(){
    match(new Token(Tag.BASIC));
    type_();
  }

  private void type_(){
    if(lookahead.getTag() == '['){
      match(new Token('['));
      match(new Token(Tag.NUM));
      match(new Token(']'));
      type_();
    }
  }

  private void stmts(){
    stmts_();
  }

  private void stmts_(){
    stmt();
  }

  private void stmt(){        
    //Verifica atribuições
    if(lookahead.getTag() == Tag.ID){
      loc();
      match(new Token(Tag.ATR));
      bool();
      match(new Token(';'));
      
    //Verifica se IF ELSE
    }else if(lookahead.getTag() == Tag.IF){ 
      match(new Token(Tag.IF));
      match(new Token('('));
      bool();
      match(new Token(')'));
      stmt();
      stmt_(); //Verifica Else
      
    //Verifica se Do While
    } else if(lookahead.getTag() == Tag.DO){
      match(new Token(Tag.DO));
      stmt();
      if(lookahead.getTag() == Tag.WHILE){
        match(new Token(Tag.WHILE));
      match(new Token('('));
      bool();
      match(new Token(')'));
      match(new Token(';'));
      stmt();
      stmts_();
      }
      
    //Verifica se while
    }else if(lookahead.getTag() == Tag.WHILE){
      match(new Token(Tag.WHILE));
      match(new Token('('));
      bool();
      match(new Token(')'));
      stmts_();

    //Verifica se Break
    }else if(lookahead.getTag() == Tag.BREAK){
      match(new Token(Tag.BREAK));
      match(new Token(';'));
      stmts_();
      
    //Verifica se Print
    }else if(lookahead.getTag() == Tag.PRINT){
      match(new Token(Tag.PRINT));
      match(new Token('('));
      bool();
      match(new Token(')'));
      match(new Token(';'));
      stmts_();

    //Verifica se Read
    }else if(lookahead.getTag() == Tag.READ){
      match(new Token(Tag.READ));
      match(new Token('('));
      loc();
      match(new Token(')'));
      match(new Token(';'));
      stmts_();

    //Verifica se block
    }else{
      block();
    }  
  }//Fim da stmt()

  private void stmt_(){
    if(lookahead.getTag() == Tag.ELSE){
      match(new Token(Tag.ELSE));
      stmt();
    }
  }

  private void loc(){
    match(new Token(Tag.ID));
    loc_();
  }

  private void loc_(){
    if(lookahead.getTag() == '['){
      match(new Token('['));
      bool();
      match(new Token(']'));
      loc_();
    }
  }


  private void bool(){
    join();
    bool_();
  }

  private void bool_(){
    if(lookahead.getTag() == Tag.OR){
      match(new Token(Tag.OR));
      join();
      bool_();
    }
    
  }

    private void join(){
      equality();
      join_();
      
    }
  private void join_(){
    if(lookahead.getTag() == Tag.AND){
      match(new Token(Tag.AND));
      equality();
      join_();
    }
  }
  
  private void equality(){
      rel();
      equality_();
    }
    

    private void equality_(){
      if(lookahead.getTag() == Tag.EQ){
        match(new Token(Tag.EQ));
        rel();
        equality_();
      } else if(lookahead.getTag() == Tag.NE){
        match(new Token(Tag.NE));
        rel();
        equality_();
      }
      
    }
    private void rel(){
      expr();
      if(lookahead.getTag() == Tag.LT){
        match(new Token(Tag.LT));
        expr();
      }else if (lookahead.getTag() == Tag.LE){
        match(new Token(Tag.LE));
        expr();
        
      }else if(lookahead.getTag() == Tag.GT){
        match(new Token(Tag.GT));
        expr();
      }else if (lookahead.getTag() == Tag.GE){
        match(new Token(Tag.GE));
        expr();
      }
    }

    private void expr(){
      term();
      expr_();      
    }

    private void expr_(){
      if(lookahead.getTag() == '-'){
        match(new Token('-'));
        term();
        expr_();
        
      }else if (lookahead.getTag() == '+'){
        match(new Token('+'));
        term();
        expr_();
      }
    }

    private void term(){
      unary();
      term_();
    }
    private void term_(){
      if(lookahead.getTag() == '*'){
        match(new Token('*'));
        unary();
        term_();
        
        
      }else if (lookahead.getTag() == '/'){
        match(new Token('/'));
        unary();
        term_();
      }
    }

    private void unary (){
      if(lookahead.getTag() == '!'){
        match(new Token(Tag.NE));
        unary();        
      }else if (lookahead.getTag() == '-'){
        match(new Token('-'));
        unary();
      }else{
        factor();
      }
    }

  private void factor(){
    if(lookahead.getTag() == '('){
      match(new Token('('));
      bool();
      match(new Token(')'));
    }else if(lookahead.getTag() == Tag.FALSE){// ???
      match(new Token(Tag.FALSE));
    }else if(lookahead.getTag() == Tag.TRUE){// ???
      match(new Token(Tag.TRUE));
    }else if(lookahead.getTag() == Tag.NUM){// ???
      match(new Token(Tag.NUM));
    }else if(lookahead.getTag() == Tag.REAL){// ???
      match(new Token(Tag.REAL));
    }else{
      loc();
    }
  }

  
  
}