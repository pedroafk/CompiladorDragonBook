package intermed;
import lexer.*;
import symbols.*;

public class Ident extends Expr{
  public int offset;
  public Ident(Word id, Type p, int b){
    super(id, p);
    offset = b;
  }
}