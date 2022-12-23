import io.ReadFile;
import lexer.Lexer;
import lexer.Token;
import parser.Parser;

/**

**/
public class Main {
  public static void main(String[] args) throws Exception {
    String inputText;
    Lexer lexer;
    Token token;
    Parser parser;
  
    inputText = ReadFile.read("./input.txt");

    lexer = new Lexer();
    lexer.setInputText(inputText);
    parser = new Parser(lexer);
    parser.program();
  }
}