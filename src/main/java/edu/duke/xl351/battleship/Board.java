package edu.duke.xl351.battleship;

//public  interface Board {
public interface Board<T>{
  public int getWidth();

  public int getHeight();

  public boolean tryAddShip(Ship<T> toAdd);

  public T whatIsAt(Coordinate where);
}









