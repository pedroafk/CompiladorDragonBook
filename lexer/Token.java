/**
 * @author Walter Aoiama Nagai
 */
package lexer;

/**
 * The class Token
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Token {
  private final int tag; // the tag of token

  /**
   * The constructor method
   * 
   * @param id
   *           The int value of token
   */
  public Token(int id) {
    this.tag = id;
  }

  /**
   * Returns the tag of token
   * 
   * @return Tag The int value of token
   */
  public int getTag() {
    return tag;
  }

  /**
   * Returns the tag string
   */
  @Override
  public String toString() {
    switch (tag) {
      case Tag.AND:
        return "&&";
      case Tag.BASIC:
        return "basic type";
      case Tag.BREAK:
        return "break";
      case Tag.DO:
        return "do";
      case Tag.ELSE:
        return "else";
      case Tag.EQ:
        return "==";
      case Tag.FALSE:
        return "false";
      case Tag.GE:
        return ">=";
      case Tag.ID:
        return "(id)";
      case Tag.IF:
        return "if";
      case Tag.INDEX:
        return "[]";
      case Tag.LE:
        return "<=";
      case Tag.NE:
        return "!=";
      case Tag.NUM:
        return "(integer)";
      case Tag.OR:
        return "||";
      case Tag.PRINT:
        return "print";
      case Tag.READ:
        return "read";
      case Tag.REAL:
        return "(real)";
      case Tag.TRUE:
        return "true";
      case Tag.WHILE:
        return "while";
      case Tag.LT:
        return "<";
      case Tag.GT:
        return ">";
      case Tag.ATR:
        return "=";
      default:
        return "" + (char) tag;
    }
  }
}

/*
 * AND = 256, BASIC = 257, BREAK = 258, DO = 259, ELSE = 260, EQ = 261, FALSE =
 * 262, GE = 263, ID = 264, IF = 265, INDEX = 266, LE = 267, MINUS = 268, NE =
 * 269, NUM = 270, OR = 271, PRINT = 272, READ = 273, REAL = 274, TEMP = 275,
 * TRUE = 276, WHILE = 277;
 */