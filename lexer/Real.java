/**
 * @author Walter Aoiama Nagai
 */
package lexer;

/**
 * The class of the real value
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Real extends Token {
  private final float value;

  /**
   * The constructor method
   * 
   * @param value
   *              The real value of number
   */
  public Real(float value) {
    super(Tag.REAL);
    this.value = value;
  }

  /**
   * Returns the real value
   * 
   * @return the real value of number
   */
  public float getValue() {
    return value;
  }

  /**
   * Returns the real value string
   */
  @Override
  public String toString() {
    return "" + value;
  }
}
