package edu.duke.xl351.battleship;

//public  interface Board {
public interface Board<T>{
  public int getWidth();

  public int getHeight();

  public String tryAddShip(Ship<T> toAdd);

  public T whatIsAt(Coordinate where);
}









