package symbols;
import java.util.*;
import lexer.*;
import intermed.*;

public class Env{
  private Hashtable table;
  protected Env prev;

  public Env(Env n){
    table = new Hashtable();
    prev = n;
  }

  public void put(Token w, Ident i){
    table.put(w,i);
  }

  public Ident get(Token w){
    for(Env e = this; e != null; e = e.prev){
      Ident found = (Ident)(e.table.get(w));
      if(found != null){
        return found;
      }
    }
    return null;
  }
}