/**
 * @author Walter Aoiama Nagai
 */
package lexer;

/**
 * The class Word
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Word extends Token {
  private String lexeme = ""; // the lexeme of Word

  /* Operators with size greater 1 */
  // the word &&
  public static final Word and = new Word("&&", Tag.AND);

  // the word ||
  public static final Word or = new Word("||", Tag.OR); 

  // the word ==
  public static final Word eq = new Word("==", Tag.EQ); 

  // the word !=
  public static final Word ne = new Word("!=", Tag.NE); 

  // the word <
  public static final Word lt = new Word("<", Tag.LT);

  // the word >
  public static final Word gt = new Word(">", Tag.GT);

  // the word <=
  public static final Word le = new Word("<=", Tag.LE); 

  // the word >=
  public static final Word ge = new Word(">=", Tag.GE); 

  // the word =
  public static final Word atr = new Word("=", Tag.ATR);
  

  /* Constants */
  public static final Word True = new Word("true", Tag.TRUE); // the word true
  public static final Word False = new Word("false", Tag.FALSE); // the word
                                                                 // false
  /* Temporary token */
  public static final Word temp = new Word("t", Tag.TEMP); // the word
                                                           // temporary

  /**
   * The constructor method
   * 
   * @param lexeme
   *               The lexeme of token
   * @param tag
   *               The int value of token
   */
  public Word(String lexeme, int tag) {
    super(tag);
    this.lexeme = lexeme;
  }

  /**
   * Returns the lexeme of Word
   * 
   * @return The lexeme of token
   */
  public String getLexeme() {
    return lexeme;
  }

  /**
   * Returns the lexeme
   */
  @Override
  public String toString() {
    return lexeme;
  }
}
