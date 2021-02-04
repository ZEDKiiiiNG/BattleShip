package edu.duke.xl351.battleship;

public class SimpleShipDisplayInfo<T> implements ShipDisplayInfo<T> {
  private T myData;
  private T onHit;
  public SimpleShipDisplayInfo(T data, T hit){
        this.myData = data;
        this.onHit = hit;
  }

	@Override
	public T getInfo(Coordinate where, boolean hit) {
    if(hit){
      return onHit;
    }
    else{
      return myData;
    }
	}

}











