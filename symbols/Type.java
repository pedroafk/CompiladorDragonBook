/**
 * @author Walter Aoiama Nagai
 */
package symbols;

import lexer.*;

/**
 * The class Type
 * 
 * @author Walter Aoiama Nagai
 * 
 */
public class Type extends Word {
  private int width; // size of data type
  // the Char type
  public static final Type Char = new Type("char", Tag.BASIC, 1);
  // The Bool type
  public static final Type Bool = new Type("bool", Tag.BASIC, 1);
  // The Int type
  public static final Type Int = new Type("int", Tag.BASIC, 4);
  // The Float type
  public static final Type Float = new Type("float", Tag.BASIC, 8);

  /**
   * Constructor class
   * 
   * @param id
   *              Name of data type
   * @param tag
   *              The token value
   * @param width
   *              Size of data type
   */
  public Type(String id, int tag, int width) {
    super(id, tag);
    this.width = width;
  }

  /**
   * Return the size of identifier type
   * 
   * @return the size of data type
   */
  public int getWidth() {
    return this.width;
  }

  /**
   * Is data type numeric?
   * 
   * @param type
   *             The data type
   * @return boolean value
   */
  public static boolean isNumeric(Type type) {
    return (type == Type.Char || type == Type.Int || type == Type.Float);
  }

  /**
   * Return the maximum data type with 2 data types
   * 
   * @param type1
   *              The first data type
   * @param type2
   *              The second data type
   * @return The max type size
   */
  public static Type max(Type type1, Type type2) {
    if (!isNumeric(type1) || !isNumeric(type2))
      return null;
    else if (type1 == Type.Float || type2 == Type.Float)
      return Type.Float;
    else if (type1 == Type.Int || type2 == Type.Int)
      return Type.Int;
    return Type.Char;
  }
}
