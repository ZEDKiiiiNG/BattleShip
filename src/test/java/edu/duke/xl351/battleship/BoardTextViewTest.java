package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2);
    BoardTextView view = new BoardTextView(b1);
    String expectedHeader= "  0|1\n";
    assertEquals(expectedHeader, view.makeHeader());
    String expected=
      expectedHeader+
      "A  |  A\n"+
      "B  |  B\n"+
      expectedHeader;
    assertEquals(expected, view.displayMyOwnBoard());
  }
  @Test
  public void test_invalid_board_size() {
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20);
    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27);
    //you should write two assertThrows here
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(wideBoard));
    assertThrows(IllegalArgumentException.class, () -> new BoardTextView(tallBoard));

  }
     /**
    * Build the testcase for test with width > height and height > width to rule out a lot of potential
   * mistakes in that area
   * 
   *  
   * 
   */

  @Test
  public void test_display_empty_3by2(){
    Board<Character> b2 = new BattleShipBoard<Character>(3, 2);
    BoardTextView view2 = new BoardTextView(b2);
    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view2.makeHeader());
    String expected=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      expectedHeader;
    assertEquals(expected, view2.displayMyOwnBoard());

  }


  @Test
  public void test_display_empty_3by5(){
    Board<Character> b3 = new BattleShipBoard<Character>(3, 5);
    BoardTextView view3 = new BoardTextView(b3);
    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view3.makeHeader());
    String expected=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected, view3.displayMyOwnBoard());

  }

  @Test
  public void test_display_add_3by5(){
    Board<Character> b3 = new BattleShipBoard<Character>(3, 5);
    BoardTextView view3 = new BoardTextView(b3);
    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view3.makeHeader());
    String expected=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected, view3.displayMyOwnBoard());
    Coordinate c1 = new Coordinate("B0");
    Ship<Character> s1 = new BasicShip(c1);
    assertEquals(b3.tryAddShip(s1),true);
    String expected2=
      expectedHeader+
      "A  | |  A\n"+
      "B s| |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected2, view3.displayMyOwnBoard());
  }




}









