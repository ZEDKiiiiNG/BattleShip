package edu.duke.xl351.battleship;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class RectangleShip<T> extends BasicShip<T>{
  /** The HashSet is usded to judge the shape of ship
   * @param upperleft is the start coordinate of the upperleft of ship
   * @param width and height represent the shape of ship
   * @return the hash set contain all ship body coordinate
   */
  //private HashSet<Coordinate> myPecies;
  final String name;
  
  static LinkedHashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
    LinkedHashSet<Coordinate> h1 = new LinkedHashSet<Coordinate>();
    
    for(int i = upperLeft.getRow(); i <upperLeft.getRow()+ height; i++ ){
      for(int j = upperLeft.getColumn();j <  upperLeft.getColumn()+ width; j++ ){
        Coordinate c = new Coordinate(i, j);
        h1.add(c);
      }
    }
    return h1;
  }

  public String getName(){
    return name;
  }

  // pass the needed field to the super which is basic ship
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo,enemyDisplayInfo);
    this.name = name;
  }                                        

  /* that is, we will tell the paraent constructor that for my own view display
    data if not hit, onHit if hit

   And for the enemy view, nothing if not hit, data if hit

  */
  public RectangleShip(String name, Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(name, upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }



  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this("testship", upperLeft, 1, 1, data, onHit);
  }


}












