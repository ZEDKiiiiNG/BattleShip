package edu.duke.xl351.battleship;

//public  interface Board {
public interface Board<T>{
  public int getWidth();

  public int getHeight();

  public String tryAddShip(Ship<T> toAdd);

  public T whatIsAtForSelf(Coordinate where);

  public T whatIsAtForEnemy(Coordinate where);
  
  public Ship<T> fireAt(Coordinate c);

  public boolean check_win();

  public Ship<T> selectShip(Coordinate c);

  public String moveShip(Coordinate c,Placement p);

  public String sonarScan(Coordinate c);
  
}












