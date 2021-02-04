package edu.duke.xl351.battleship;

import java.util.HashSet;

public class RectangleShip<T> extends BasicShip<T>{
  /** The HashSet is usded to judge the shape of ship
   * @param upperleft is the start coordinate of the upperleft of ship
   * @param width and height represent the shape of ship
   * @return the hash set contain all ship body coordinate
   */
  //private HashSet<Coordinate> myPecies;
  static HashSet<Coordinate> makeCoords(Coordinate upperLeft, int width, int height){
    HashSet<Coordinate> h1 = new HashSet<Coordinate>();
    for(int i = upperLeft.getRow(); i <upperLeft.getRow()+ height; i++ ){
      for(int j = upperLeft.getColumn();j <  upperLeft.getColumn()+ width; j++ ){
        Coordinate c = new Coordinate(i, j);
        h1.add(c);
      }
    }
    return h1;
  }

  // pass the needed field to the super which is basic ship
  public RectangleShip(Coordinate upperLeft, int width, int height,ShipDisplayInfo<T> myDisplayInfo) {
    super(makeCoords(upperLeft, width, height), myDisplayInfo);
  }

  public RectangleShip(Coordinate upperLeft, int width, int height, T data, T onHit) {
    this(upperLeft, width, height, new SimpleShipDisplayInfo<T>(data, onHit));
  }

  public RectangleShip(Coordinate upperLeft, T data, T onHit) {
    this(upperLeft, 1, 1, data, onHit);
  }


}












