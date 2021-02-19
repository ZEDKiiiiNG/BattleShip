package edu.duke.xl351.battleship;

import java.util.HashSet;
import java.util.LinkedHashSet;


public class OtherShip<T> extends BasicShip<T> {
/** The HashSet is usded to judge the shape of ship
   * @param upperleft is the start coordinate of the upperleft of ship
   * @param width and height represent the shape of ship
   * @return the hash set contain all ship body coordinate
   */
  final String name;
  
  static LinkedHashSet<Coordinate> makeCoords(Coordinate upperLeft, String name ,char orientation){
    LinkedHashSet<Coordinate> h1 = new LinkedHashSet<Coordinate>();
    /*
    if(name != "Battleship" && name != "Carrier"){
       throw new IllegalArgumentException("Other ship must be Carrier or Battleship but is " + name);
    }
    
    else{
    */
      //for BattleShip 
      if(name == "Battleship"){
        // for UP
        if(orientation == 'U'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i, j + 1));
          h1.add(new Coordinate(i + 1, j));
          h1.add(new Coordinate(i + 1, j + 1));
          h1.add(new Coordinate(i + 1, j + 2));
          return h1;
        }
        //Right
        else if(orientation == 'R'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 1, j + 1)); //1
          h1.add(new Coordinate(i, j));
          h1.add(new Coordinate(i + 1, j));
          h1.add(new Coordinate(i + 2, j));
          return h1;
        }
        //Down 
        else if(orientation == 'D'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 1, j + 1)); //1
          h1.add(new Coordinate(i, j + 2 ));
          h1.add(new Coordinate(i , j + 1));
          h1.add(new Coordinate(i , j));
          return h1;
        }
        //Left
        else{
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 1, j));
          h1.add(new Coordinate(i + 2, j +1));
          h1.add(new Coordinate(i + 1 , j + 1));
          h1.add(new Coordinate(i , j + 1));
          return h1;
        }
      }
      else{
        // For Carrier
        // For up
        if(orientation == 'U'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i, j));
          h1.add(new Coordinate(i + 1, j));
          h1.add(new Coordinate(i + 2, j));
          h1.add(new Coordinate(i + 2, j + 1));
          h1.add(new Coordinate(i + 3, j + 1));
          h1.add(new Coordinate(i + 4, j + 1));
          
          return h1;
        }
        // For Right
        else if(orientation == 'R'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 1, j));
          h1.add(new Coordinate(i + 1, j + 1));
          h1.add(new Coordinate(i + 1, j + 2));
          h1.add(new Coordinate(i , j + 2));
          h1.add(new Coordinate(i , j + 3));
          h1.add(new Coordinate(i , j + 4));
          
          return h1;
        }
        // Down
        else if(orientation == 'D'){
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 4, j));
          h1.add(new Coordinate(i + 3, j));
          h1.add(new Coordinate(i + 2, j));
          h1.add(new Coordinate(i + 2, j + 1));
          h1.add(new Coordinate(i + 1, j + 1));
          h1.add(new Coordinate(i , j + 1));
          return h1;
        }

        //Left
        else {
          int i = upperLeft.getRow();
          int j = upperLeft.getColumn();
          h1.add(new Coordinate(i + 1, j + 4));
          h1.add(new Coordinate(i + 1, j + 3));
          h1.add(new Coordinate(i + 1, j + 2));
          h1.add(new Coordinate(i , j + 2));
          h1.add(new Coordinate(i , j + 1));
          h1.add(new Coordinate(i , j));
          return h1;
        }
      }
      //}
  }

  public String getName(){
    return name;
  }

  // pass the needed field to the super which is basic ship
  public OtherShip(String name, Coordinate upperLeft, char orientation , ShipDisplayInfo<T> myDisplayInfo,ShipDisplayInfo<T> enemyDisplayInfo) {
    super(makeCoords(upperLeft, name , orientation ), myDisplayInfo,enemyDisplayInfo);
    this.name = name;
  }

  /* that is, we will tell the paraent constructor that for my own view display
    data if not hit, onHit if hit

   And for the enemy view, nothing if not hit, data if hit

  */
  public OtherShip(String name, Coordinate upperLeft, char orientaion, T data, T onHit) {
    this(name, upperLeft, orientaion, new SimpleShipDisplayInfo<T>(data, onHit),
        new SimpleShipDisplayInfo<T>(null, data));
  }



}












