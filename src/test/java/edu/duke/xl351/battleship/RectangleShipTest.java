package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

public class RectangleShipTest {
  @Test
  public void test_makeCoords() {
    Coordinate c1 = new Coordinate(1,2);
    Coordinate c2 = new Coordinate(1,3);
    Coordinate c3 = new Coordinate(2,2);
    Coordinate c4 = new Coordinate(2,3);
    //Coordinate c5 = new Coordinate(2,4);
    
    HashSet<Coordinate> h1 = RectangleShip.makeCoords(c1, 2,2);
    HashSet<Coordinate> h2  = new HashSet<Coordinate>();
    h2.add(c1);
    h2.add(c2);
    h2.add(c3);
    h2.add(c4);
    assertEquals(h1,h2);
    
  }

  @Test
  public void test_occupy(){
    Coordinate c1 = new Coordinate(1,2);
    Coordinate c2 = new Coordinate(2,2);
    Coordinate c3 = new Coordinate(1,3);
    Coordinate c4 = new Coordinate(2,3);
    Coordinate c5 = new Coordinate(2,4);
    //s1 = new RectangleShip(c1, 2, 2);
    RectangleShip<Character> s1 = new RectangleShip<Character>("testship",c1, 2, 2,'s', '*');

    assertEquals(s1.occupiesCoordinates(c1),true);
    assertEquals(s1.occupiesCoordinates(c2),true);
    assertEquals(s1.occupiesCoordinates(c3),true);
    assertEquals(s1.occupiesCoordinates(c4),true);
    assertEquals(s1.occupiesCoordinates(c5),false);
  }

  @Test
  public void test_hit_record_testname(){
    Coordinate c1 = new Coordinate(1,2);
    Coordinate c2 = new Coordinate(2,2);
    Coordinate c3 = new Coordinate(1,3);
    Coordinate c4 = new Coordinate(2,3);
    Coordinate c5 = new Coordinate(2,4);
    RectangleShip<Character> s1 = new RectangleShip<Character>("testship",c1, 2, 2,'s', '*');
    s1.recordHitAt(c1);

    assertEquals(s1.getName(),"testship");
    
    assertEquals(s1.getDisplayInfoAt(c1,true),'*');
    assertEquals(s1.getDisplayInfoAt(c2,true),'s');

    assertEquals(s1.getDisplayInfoAt(c1,false),'s');
    assertEquals(s1.getDisplayInfoAt(c2,false),null);
    
    assertEquals(s1.wasHitAt(c1),true);
    assertEquals(s1.wasHitAt(c2),false);
    assertThrows(IllegalArgumentException.class, () -> s1.recordHitAt(c5));
    assertThrows(IllegalArgumentException.class, () -> s1.wasHitAt(c5));
    assertEquals(s1.isSunk(),false);
    s1.recordHitAt(c2);
    s1.recordHitAt(c3);
    s1.recordHitAt(c4);
    assertEquals(s1.isSunk(),true);
    
  }

  @Test
  public void test_check_rule(){
    Coordinate c1 = new Coordinate(1,2);
    RectangleShip<Character> s1 = new RectangleShip<Character>("testship",c1, 2, 2,'s', '*');
    
    Iterable<Coordinate> set1 =  s1.getCoordinates();
    for(Coordinate c : set1){
      assertEquals(s1.wasHitAt(c), false);
    }
    
  }
}












