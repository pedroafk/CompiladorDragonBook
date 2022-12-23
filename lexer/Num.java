/**
 * @author Walter Aoiama Nagai
 */
package lexer;

/**
 * The class Num
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Num extends Token {
  private final int value;

  /**
   * The constructor method
   * 
   * @param value
   *              The int value of number
   */
  public Num(int value) {
    super(Tag.NUM);
    this.value = value;
  }

  /**
   * Returns of integer value
   * 
   * @return the int value of number
   */
  public int getValue() {
    return value;
  }

  /**
   * Returns of numeric string
   */
  @Override
  public String toString() {
    return "" + value;
  }
}
