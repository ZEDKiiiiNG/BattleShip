package edu.duke.xl351.battleship;

public class Coordinate {
  private final int row; // the row cordinate
  
  public int getRow() {
    return row;
  }
  private final int column; // the row cordinate

  public int getColumn() {
    return column;
  }
  public Coordinate(int r, int c){
    this.row = r;
    this.column = c;
  }

  @Override
  public boolean equals(Object o){
    if (o.getClass().equals(getClass())) {
      Coordinate c = (Coordinate) o;
      return row == c.row && column == c.column;
    }
    return false;

  }

  @Override
  public String toString() {
    return "("+row+", " + column+")";
  }
  @Override
  public int hashCode() {
    return toString().hashCode();
  }

  public Coordinate(String descr) {
    if(descr.length() != 2){ // when the size of descr is not 2
      throw new IllegalArgumentException("the length of size must be 2, but is"+descr.length());
    }
    char rowLetter = descr.toUpperCase().charAt(0); // the first letter in upper case
    char colNumber = descr.charAt(1); // the second number
    if (rowLetter < 'A' || rowLetter > 'Z'){
     throw new IllegalArgumentException("the first letter must be A-Z, but is"+rowLetter); 
    }
    row = rowLetter - 'A';
    if (colNumber < '0' || colNumber > '9'){
      throw new IllegalArgumentException("the second number  must be 0-9, but is"+colNumber);
    }
    column = colNumber - '0';
  }
}













