package edu.duke.xl351.battleship;

import java.util.ArrayList;
import java.util.HashSet;

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
  private final int height;

  final T missInfo;

  private final PlacementRuleChecker<T> placementChecker;
  final ArrayList<Ship<T>> myShips;

  
  final HashSet<Coordinate> enemyMisses;

  
  public int getWidth() {
    return width;
  }
  
  
  public int getHeight() {
    return height;
  }

  /* default consturctor
   */
  public BattleShipBoard(int w, int h, T missInfo) {
    this(w, h, new InBoundsRuleChecker<T>(new NoCollisionRuleChecker<>(null)), missInfo);
  }

  /*construtor to have InBoundsRuleChecker
   */
  public BattleShipBoard(int w, int h, PlacementRuleChecker<T> pc, T missInfo) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
    this.myShips = new ArrayList<Ship<T>>();
    this.placementChecker = pc;
    this.enemyMisses = new HashSet<Coordinate>();
    this.missInfo = missInfo;
  }

  /** this function will add ship to the board based on the checkrule
   *  @param toAdd is the added {@link Ship}
   *  the return results shows whether the ship is added or not

   */
  
  public String tryAddShip(Ship<T> toAdd){
    if (placementChecker.checkPlacement(toAdd,this) == null) {
      myShips.add(toAdd);
      return null;
    }
    else{
      return placementChecker.checkPlacement(toAdd,this);
    }
  }
  
  public T whatIsAtForSelf(Coordinate where) {
   return whatIsAt(where, true);
  }

  public T whatIsAtForEnemy(Coordinate where) {
    return whatIsAt(where, false);
  }


  protected T whatIsAt(Coordinate where, boolean isSelf){
     //if where out of bounds, fall as quickly as they can
    if (where.getRow()+1 >  height) {
      throw new IllegalArgumentException("The coordinate row should be less than" + (height-1)+"but is "+where.getRow());
    }
    if (where.getColumn()+1 >  width) {
      throw new IllegalArgumentException("The coordinate row should be less than" + (width-1)+"but is "+where.getColumn());
    }
    for (Ship<T> s: myShips) {
      if (s.occupiesCoordinates(where)){
        return s.getDisplayInfoAt(where, isSelf);
      }
    }
    if(enemyMisses.contains(where) && !isSelf){
      return missInfo;
    }
    return null;
  }

  public Ship<T> fireAt(Coordinate c){
    for (Ship<T> s: myShips) {
      if(s.occupiesCoordinates(c)){
        s.recordHitAt(c);
        return s;
      }
    }
      enemyMisses.add(c);
      return null;
  }

  /** function to check win or loose condition 
   */
  public boolean check_win(){
    for (Ship<T> s: myShips) {
      if(s.isSunk() == false){
        return false;
      }
    }
      return true;
  }
  
}














