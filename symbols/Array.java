/**
 * @author Walter Aoiama Nagai
 */
package symbols;

import lexer.Tag;

/**
 * The class Array
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Array extends Type {
  private int size; // the size of array
  private Type of; // the type of array

  /**
   * Constructor class
   * 
   * @param size
   *             The size of array
   * @param of
   *             The data type of array
   */
  public Array(int size, Type of) // array [size] of Type
  {
    super("[]", Tag.INDEX, size * of.getWidth());
    this.size = size;
    this.of = of;
  }

  /**
   * Returns the type of array
   * 
   * @return the data type of array
   */
  public Type getOf() {
    return this.of;
  }

  /**
   * Returns the size of array
   * 
   * @return the size of array
   */
  public int getSize() {
    return this.size;
  }

  /**
   * Returns the array string
   */
  @Override
  public String toString() {
    return "[" + this.size + "]" + this.of.toString();
  }
}
