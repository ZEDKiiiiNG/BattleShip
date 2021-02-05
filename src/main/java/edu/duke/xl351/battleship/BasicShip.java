package edu.duke.xl351.battleship;

import java.util.HashMap;

//public class BasicShip implements Ship<Character>{
public abstract class BasicShip<T> implements Ship<T> {
  //private final Coordinate myLocation;
  protected HashMap<Coordinate, Boolean> myPieces;
  protected ShipDisplayInfo<T> myDisplayInfo;
  

  public BasicShip(Iterable<Coordinate> where, ShipDisplayInfo<T> myDisplayInfo){
    this.myDisplayInfo = myDisplayInfo;
    myPieces = new HashMap<Coordinate, Boolean>();
    for (Coordinate c : where){
      myPieces.put(c, false);
    }
  }
  
  /**
   *check if the coordinate is in ship or not
   * @param c is the check {@link Coordinate}
   * @throws IllegalArgumentException if where is not part of the Ship
   */
  protected void checkCoordinateInThisShip(Coordinate c){
    if(myPieces.get(c) == null){
      throw new IllegalArgumentException("The body of ship do not contain corrdinate" + c.toString());
    }
  }
  
	@Override
	public boolean occupiesCoordinates(Coordinate where) {
		// TODO Auto-generated method stub
    boolean res;
    if(myPieces.get(where) != null){
      res = true;
    }
    else{
      res = false;
    }
		return res;
	}

	@Override
	public boolean isSunk() {
		// TODO Auto-generated method stub
    
    for (Coordinate key : myPieces.keySet()) {
      if (myPieces.get(key) == false){
        return false;
      }
    }
        return true;
	}

	@Override
	public void recordHitAt(Coordinate where) {
		// TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
    myPieces.put(where, true);
	}

	@Override
	public boolean wasHitAt(Coordinate where) {
		// TODO Auto-generated method stub
    checkCoordinateInThisShip(where);
		return myPieces.get(where);
	}

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
    checkCoordinateInThisShip(where);
    return myDisplayInfo.getInfo(where, wasHitAt(where));
  }



}









