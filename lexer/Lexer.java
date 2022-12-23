/**
 * @author Walter Aoiama Nagai
 */
package lexer;

import java.io.IOException;
import java.util.Hashtable;
import symbols.Type;

/**
 * The scanner of compiler
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Lexer {
  private Hashtable<String, Word> words; // the hash table of words
  private char peek = ' '; // the actual character of input file
  private String buffer; // the buffer of file
  private int posBuffer = -1; // the position buffer
  public static int line = 1; // the actual line of input file

  /**
   * The constructor method
   * 
   * @throws Exception
   */
  public Lexer() throws Exception {
    words = new Hashtable<String, Word>();
    /* keywords */
    keyWords(new Word("if", Tag.IF));
    keyWords(new Word("else", Tag.ELSE));
    keyWords(new Word("while", Tag.WHILE));
    keyWords(new Word("do", Tag.DO));
    keyWords(new Word("break", Tag.BREAK));
    keyWords(new Word("print", Tag.PRINT));
    keyWords(new Word("read", Tag.READ));
    
    //Operadores de comparação
    keyWords(new Word(">=", Tag.GE));
    keyWords(new Word("<=", Tag.LE));
    keyWords(new Word("<", Tag.LT));
    keyWords(new Word(">", Tag.GT));
    keyWords(new Word("=", Tag.ATR));
    keyWords(Word.ge);
    keyWords(Word.le);
    keyWords(Word.lt);
    keyWords(Word.gt);
    keyWords(Word.atr);
    
    
    /* boolean constants */
    keyWords(Word.True);
    keyWords(Word.False);

    /* basic data types */
    keyWords(Type.Bool);
    keyWords(Type.Int);
    keyWords(Type.Char);
    keyWords(Type.Float);

    
    
  }

  /**
   * Set the buffer of scanner
   * 
   * @param buff The text buffer
   */
  public void setInputText(String buff) {
    buffer = buff;
  }

  /**
   * Read one character of buffer
   */
  public void readChar() {
    if (posBuffer < buffer.length() - 1) {
      ++posBuffer;
      peek = buffer.charAt(posBuffer);
    } else
      peek = 255;
  }

  /**
   * Retract one character of buffer
   */
  public void retract() {
    --posBuffer;
  }

  /**
   * Read one character of buffer and matches the character expected
   * 
   * @param ch
   *           One character to compare with actual character in peek
   * @return True if peek is equal ch
   */
  private boolean readChar(final char ch) {
    readChar();
    if (peek != ch)
      return false;
    peek = ' ';
    return true;
  }

  /**
   * Insert one word (lexeme, tag) in the hash table
   * 
   * @param word
   *             The word to insert in the hash table
   */
  private void keyWords(Word word) {
    words.put(word.getLexeme(), word);
  }

  /**
   * Returns the hash table
   * 
   * @return the hash table
   */
  public Hashtable<String, Word> getWords() {
    return words;
  }

  /**
   * Returns one token by function call
   * 
   * @return One token by function call
   * @throws IOException
   */
  public Token scanner() throws IOException {
    int state = 0;
    boolean finished = false;
    int intValue = 0;
    StringBuffer lexeme = new StringBuffer();
    while (!finished) {
      readChar();
      switch (state) {
        case 0:
          switch (peek) {
            case 255:
              finished = true;
              break;
            case ' ':
            case '\t':
            case '\r':
              break;
            case '\n':
              line++;
              break;
            case '&':
              if (readChar('&'))
                return Word.and;
              else {
                retract();
                return new Token('&');
              }
            case '|':
              break;
            case '=':
              return Word.atr;
            case '!':
              break;
            case '<':
              if(readChar('='))
                return Word.le;
              else {
                retract();
                return new Token(Tag.LT);
              }
            case '>':
              if(readChar('='))
                return Word.ge;
              else {
                retract();
                return new Token(Tag.GT);
              }
            default:
              if (Character.isDigit(peek)) {
                intValue = Character.digit(peek, 10);
                state = 1;
              } else if (Character.isLetter(peek) || peek == '_') {
                lexeme.append(peek);
                state = 2;
              } else
                finished = true;
          }
          break;
        // numeric finite automata
        case 1:
          // integer number
          if (Character.isDigit(peek)) {
            intValue = intValue * 10 + Character.digit(peek, 10);
          }
          // real number
          else if (peek == '.') {
            float realValue = intValue;
            float decimals = 10;
            readChar();
            while (Character.isDigit(peek)) {
              realValue += (Character.digit(peek, 10) / decimals);
              decimals = decimals * 10;
              readChar();
            }
            retract();
            return new Real(realValue);
          }
          // return integer number
          else {
            retract();
            return new Num(intValue);
          }
          break;
        // word finite automata
        case 2:
          if (Character.isLetterOrDigit(peek) || peek == '_') {
            lexeme.append(peek);
          } else {
            retract();
            String s = lexeme.toString();
            Word w = words.get(s);
            if (w == null) {
              w = new Word(s, Tag.ID);
              words.put(s, w);
            }
            return w;
          }
          break;
      }
    }
    Token token = new Token(peek);
    peek = ' ';
    return token;
  }
}
