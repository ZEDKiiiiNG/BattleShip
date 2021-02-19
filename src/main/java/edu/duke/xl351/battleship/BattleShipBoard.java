package edu.duke.xl351.battleship;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

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

  /** function to check win or loose condition 
   * @param c is the coordinate from the input
   * @return s is the Ship which on that {@link Coordinate}
   */
  public Ship<T> selectShip(Coordinate c){
    for (Ship<T> s: myShips) {
      if(s.occupiesCoordinates(c)){
        return s;
      }
    }
      return null;
  }
  
  /** function to check win or loose condition 
   * @param c is the coordinate from the input
   * @param p is the new placement where the ship will move
   * @return tryadd the ship on the new coordinates.
   */
  public String moveShip(Coordinate c,Placement p){
    if(selectShip(c) == null){
      throw new IllegalArgumentException("No ships contains coordinate " + c.toString());
    }
    Ship<T> s = selectShip(c);
    Iterable<Coordinate> old = s.getshipBody();
    myShips.remove(s);
    
    if(s.getName() == "Battleship" || s.getName() =="Carrier"){
      LinkedHashSet<Coordinate> lc = OtherShip.makeCoords(p.getWhere(), s.getName(), p.getOrientation());
      s.move(lc);
    }
    else{
      int w = 1;
      int h = 3;
      if(s.getName() == "Submarine"){
         w = 1;
         h = 2;
      }

      if(p.getOrientation() == 'H'){
        int temp = w;
        w = h;
        h = temp;
      }
      
       LinkedHashSet<Coordinate> lc = RectangleShip.makeCoords(p.getWhere(), w, h);
      s.move(lc);
    }
    String res = tryAddShip(s); 
    if(res != null){
      //myShips.remove(s);
      s.move(old);
      tryAddShip(s);
    }
    return res;
  }

  /* this function do the sonar scan around a specific Coordinate
   * @param c is the specific Coordinate
   * @return is a string which conatiains the number of specific ship in trarget area
   */
  
  public String sonarScan(Coordinate c){
    
    int num_sub = 0;
    int num_des = 0;
    int num_bat = 0;
    int num_car = 0;

    //Coordinate[][] range = new Coordinate[7][];

    for (int i = 0; i < 7; i++) {
      for (int j =0; j < 7 - 2 * Math.abs(i - 3); j++){
        //range[i][j] = new Coordinate(i + c.getRow() - 3, c.getColumn() + Math.abs(i-3) -3);
        if(selectShip(new Coordinate(i + c.getRow() - 3, j + c.getColumn() + Math.abs(i-3) -3)) != null){
          //Submarine
          if(selectShip(new Coordinate(i + c.getRow() - 3, j + c.getColumn() + Math.abs(i-3) -3)).getName() == "Submarine" ){
            num_sub++;
          }
          //
          if(selectShip(new Coordinate(i + c.getRow() - 3, j + c.getColumn() + Math.abs(i-3) -3)).getName() == "Destroyer" ){
            num_des++;
          }
          //
          if(selectShip(new Coordinate(i + c.getRow() - 3, j + c.getColumn() + Math.abs(i-3) -3)).getName() == "Battleship" ){
            num_bat++;
          }
          //
          if(selectShip(new Coordinate(i + c.getRow() - 3, j + c.getColumn() + Math.abs(i-3) -3)).getName() == "Carrier" ){
            num_car++;
          }
        }
      }
      //range[i] = new Coordinate[7 - 2 * (i % 4)];
    }

    
    String res = "Submarines occupy " + num_sub + " squares\n"+
      "Destroyers occupy " + num_des + " squares\n"+
      "Battleships occupy " + num_bat + " squares\n"+
      "Carriers occupy " + num_car + " square\n";
    return res;
  }
}














