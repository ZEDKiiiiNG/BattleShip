package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PlacementTest {
  @Test
  public void test_where_and_orientation() {
    Coordinate c1 = new Coordinate(10, 20);
    Placement p1 = new Placement(c1, 'v');
    assertEquals(c1, p1.getWhere());
    assertEquals('V', p1.getOrientation());
    
  }
  @Test
  public void test_equals() {
    Coordinate c1 = new Coordinate(1, 2);
    Placement p1 = new Placement(c1, 'v');
    Coordinate c2 = new Coordinate(1, 2);
    Placement p2 = new Placement(c2, 'v');
    Coordinate c3 = new Coordinate(1, 3);
    Placement p3 = new Placement(c3, 'v');
    Coordinate c4 = new Coordinate(3, 2);
    Placement p4 = new Placement(c4, 'v');
    Coordinate c5 = new Coordinate(1, 2);
    Placement p5 = new Placement(c5, 'h');
    assertEquals(p1, p1);   //equals should be reflexsive
    assertEquals(p1, p2);   //different objects bu same contents
    assertNotEquals(p1, p3);  //different contents
    assertNotEquals(p1, p4);
    assertNotEquals(p3, p4);
    assertNotEquals(p1, p5); // different orientation with same where
    assertNotEquals(p1, "(1, 2)V"); //different types which c1 has to be the first
  }

  @Test
  public void test_hashCode() {
    Coordinate c1 = new Coordinate(1, 2);
    Placement p1 = new Placement(c1, 'v');
    Coordinate c2 = new Coordinate(1, 2);
    Placement p2 = new Placement(c2, 'v');
    Coordinate c3 = new Coordinate(1, 3);
    Placement p3 = new Placement(c3, 'v');
    Coordinate c4 = new Coordinate(2, 1);
    Placement p4 = new Placement(c4, 'v');
    Coordinate c5 = new Coordinate(1, 2);
    Placement p5 = new Placement(c5, 'h');
    assertEquals(p1.hashCode(), p2.hashCode());
    assertNotEquals(p1.hashCode(), p3.hashCode());
    assertNotEquals(p1.hashCode(), p4.hashCode());
    assertNotEquals(p1.hashCode(), p5.hashCode());
  }


  @Test
  void test_string_constructor_valid_cases() {
    Coordinate c1 = new Coordinate("B3");
    Placement p1 = new Placement("B3v");
    assertEquals(c1, p1.getWhere());
    assertEquals('V', p1.getOrientation());
    Coordinate c2 = new Coordinate("D5");
    Placement p2 = new Placement("D5v");
    assertEquals(c2, p2.getWhere());
    assertEquals('V', p2.getOrientation());
    Coordinate c3 = new Coordinate("A9");
    Placement p3 = new Placement("A9V");
    assertEquals(c3, p3.getWhere());
    assertEquals('V', p3.getOrientation());
    Coordinate c4 = new Coordinate("Z0");
    Placement p4 = new Placement("Z0v");
    assertEquals(c4, p4.getWhere());
    assertEquals('V', p4.getOrientation());
    Coordinate c5 = new Coordinate("B3");
    Placement p5 = new Placement("B3h");
    assertEquals(c5, p5.getWhere());
    assertEquals('H', p5.getOrientation());

  }
  @Test
  public void test_string_constructor_error_cases() {
    assertThrows(IllegalArgumentException.class, () -> new Placement("00v"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("AAv"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("@0v"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("[0v"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A/v"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A:v"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1K"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1k"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1]"));
    assertThrows(IllegalArgumentException.class, () -> new Placement("A1hh"));
  }

}







