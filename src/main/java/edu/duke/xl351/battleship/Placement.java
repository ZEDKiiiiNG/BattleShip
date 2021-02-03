package edu.duke.xl351.battleship;

public class Placement {
  private final Coordinate where;
  private final char orientation;
  public char getOrientation() {
    return orientation;
  }
  public Coordinate getWhere() {
    return where;
  }
  public Placement(Coordinate w, char o){ // default constructor
    this.where = w;
    String O =String.valueOf(o);
    O = O.toUpperCase();  // convert to Upper case
    o = O.charAt(0);
    this.orientation = o;
  }


  @Override
  public boolean equals(Object o){
    if (o.getClass().equals(getClass())) {
      Placement p = (Placement) o;
      return  (where.equals(p.where)) && orientation == p.orientation;
    }
    return false;

  }

  @Override
  public String toString() {
    return where.toString()+orientation;
  }
  @Override
  public int hashCode() {
    return toString().hashCode();
  }
  // the constructor with input of String
  public Placement(String descr){
    if(descr.length() != 3){
      throw new IllegalArgumentException("the length of size must be 3, but is"+descr.length());
    }
    // the input for coordinate
    String w = descr.substring(0,2);
    where = new Coordinate(w);
    char ori = descr.toUpperCase().charAt(2);
    if (ori != 'V' && ori != 'H'){
      throw new IllegalArgumentException("the orientation must be V or H, but is"+ori);
    }
    orientation = ori;
    
  }
}












