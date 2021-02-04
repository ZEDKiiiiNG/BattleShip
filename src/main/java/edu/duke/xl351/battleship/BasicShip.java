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
		return false;
	}

	@Override
	public void recordHitAt(Coordinate where) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean wasHitAt(Coordinate where) {
		// TODO Auto-generated method stub
		return false;
	}

  @Override
  public T getDisplayInfoAt(Coordinate where) {
    //TODO this is not right.  We need to
    //look up the hit status of this coordinate
    return myDisplayInfo.getInfo(where, false);
  }



}
