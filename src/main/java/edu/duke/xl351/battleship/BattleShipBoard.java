package edu.duke.xl351.battleship;

import java.util.ArrayList;

/**
   * Constructs a BattleShipBoard which implements the interface board
   * The {@link BattleShipBoard} has three elements which are hieght, width of board and the list of ship 
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */

//public class BattleShipBoard implements Board{
public class BattleShipBoard<T> implements Board<T>{
  private final int width;
  final ArrayList<Ship<T>> myShips;
  
  public int getWidth() {
    return width;
  }
  
  private final int height;
  
  public int getHeight() {
    return height;
  }

  public BattleShipBoard(int w, int h) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
  }
  public boolean tryAddShip(Ship<T> toAdd){
    myShips.add(toAdd);
    return true;
  }

  public T whatIsAt(Coordinate where) {
    //if where out of bounds, fall as quickly as they can
    if (where.getRow()+1 >  height) {
      throw new IllegalArgumentException("The coordinate row should be less than" + (height-1)+"but is "+where.getRow());
    }
    if (where.getColumn()+1 >  width) {
      throw new IllegalArgumentException("The coordinate row should be less than" + (width-1)+"but is "+where.getColumn());
    }
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        return s.getDisplayInfoAt(where);
      }
    }
    return null;
  }


}














