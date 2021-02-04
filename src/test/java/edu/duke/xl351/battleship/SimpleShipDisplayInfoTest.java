package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SimpleShipDisplayInfoTest {
  @Test
  public void test_shipDisplay() {
    ShipDisplayInfo<Character> sd = new SimpleShipDisplayInfo<>('a','b');
    Coordinate c1 = new Coordinate(1,2);
    Coordinate c2 = new Coordinate(1,3);
    assertEquals(sd.getInfo(c1, true), 'b'); // if hit get 'b' otherwise 'a'
    assertEquals(sd.getInfo(c2,false),'a');
  }

}










