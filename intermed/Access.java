package intermed;
import lexer.*;
import symbols.*;

public class Access extends Op{
  public Ident array;
  public Expr index;
  public Access(Ident a, Expr i, Type p){
    super(new Word("[]", Tag.INDEX), p);
    array = a;
    index = i;
  }

  public Expr gen(){
    return new Access(array, index.reduce(), type);
  }

  public void jumping(int t, int f){
    emitjumps(reduce().toString(),t,f);
  }

  public String toString(){
    return array.toString() + " [ " + index.toString() + " ] "; 
  }
}