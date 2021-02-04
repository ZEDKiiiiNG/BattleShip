package edu.duke.xl351.battleship;

public interface ShipDisplayInfo<T> {
       public T getInfo(Coordinate where, boolean hit);
}
