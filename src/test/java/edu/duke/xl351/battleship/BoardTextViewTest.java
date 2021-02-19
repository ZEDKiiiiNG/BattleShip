package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BoardTextViewTest {
  @Test
  public void test_display_empty_2by2() {
    Board<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
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
    Board<Character> wideBoard = new BattleShipBoard<Character>(11,20,'X');
    Board<Character> tallBoard = new BattleShipBoard<Character>(10,27,'X');
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
    Board<Character> b2 = new BattleShipBoard<Character>(3, 2,'X');
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
    Board<Character> b3 = new BattleShipBoard<Character>(3, 5,'X');
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
    Board<Character> b3 = new BattleShipBoard<Character>(3, 5,'X');
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
    Ship<Character> s1 = new RectangleShip<Character>(c1, 's', '*');;
    assertEquals(b3.tryAddShip(s1),null);
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

  @Test
  public void test_display_enemy(){
    Board<Character> b4 = new BattleShipBoard<Character>(3, 5,'X');
    BoardTextView view4 = new BoardTextView(b4);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    
    assertEquals(b4.tryAddShip(dst1), null);

    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view4.makeHeader());
    String expected=
      expectedHeader+
      "A d| |  A\n"+
      "B d| |  B\n"+
      "C d| |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected, view4.displayMyOwnBoard());
    assertSame(b4.fireAt(new Coordinate(0, 1)), null);
    assertSame(b4.fireAt(new Coordinate(0, 0)), dst1);
    String expected_enemy=
      expectedHeader+
      "A d|X|  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected_enemy, view4.displayEnemyBoard());
    
  }

  @Test
  public void test_display_both(){
    Board<Character> b4 = new BattleShipBoard<Character>(3, 5,'X');
    BoardTextView view4 = new BoardTextView(b4);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    
    assertEquals(b4.tryAddShip(dst1), null);
    assertSame(b4.fireAt(new Coordinate(0, 1)), null);
    assertSame(b4.fireAt(new Coordinate(0, 0)), dst1);
    
    String expectedHeader= "  0|1|2";
    String my_header = "Your ocean";
    String enemy_header = "Player B's ocean";
    String expected_both=
      "     Your ocean"+"             "+"Player B's ocean\n"+
      expectedHeader+"        "+expectedHeader+"\n"+
      "A *| |  A"+"      "+"A d|X|  A\n"+
      "B d| |  B"+"      "+"B  | |  B\n"+
      "C d| |  C"+"      "+"C  | |  C\n"+
      "D  | |  D"+"      "+"D  | |  D\n"+
      "E  | |  E"+"      "+"E  | |  E\n"+
      expectedHeader+"        "+expectedHeader+"\n";
    assertEquals(expected_both, view4.displayMyBoardWithEnemyNextToIt(view4,my_header,enemy_header));
  }
  
  @Test
  public void test_move(){
    Board<Character> b4 = new BattleShipBoard<Character>(3, 5,'X');
    BoardTextView view4 = new BoardTextView(b4);

    AbstractShipFactory<Character> f = new V2ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    
    assertEquals(b4.tryAddShip(dst1), null);

    String expectedHeader= "  0|1|2\n";
    assertEquals(expectedHeader, view4.makeHeader());
    String expected=
      expectedHeader+
      "A d| |  A\n"+
      "B d| |  B\n"+
      "C d| |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected, view4.displayMyOwnBoard());
    assertSame(b4.fireAt(new Coordinate(0, 1)), null);
    assertSame(b4.fireAt(new Coordinate(0, 0)), dst1);
    String expected_enemy=
      expectedHeader+
      "A d|X|  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected_enemy, view4.displayEnemyBoard());
    String expected1=
      expectedHeader+
      "A *| |  A\n"+
      "B d| |  B\n"+
      "C d| |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      expectedHeader;
    assertEquals(expected1, view4.displayMyOwnBoard());

    Placement h30 = new Placement(new Coordinate(3, 0), 'H');
    b4.moveShip(new Coordinate(0, 0), h30);

    String expected2=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D *|d|d D\n"+
      "E  | |  E\n"+
      expectedHeader;
    // assertEquals(null, b4.selectShip(new Coordinate(2, 0)));
    assertEquals(expected2, view4.displayMyOwnBoard());
    assertThrows(IllegalArgumentException.class, () -> b4.moveShip(new Coordinate(0, 0), h30) );


    Ship<Character> sub1 = f.makeSubmarine(v1_2);
    
    assertEquals(b4.tryAddShip(sub1), null);
    assertSame(b4.fireAt(new Coordinate(0, 0)), sub1);
    String expected3=
      expectedHeader+
      "A *| |  A\n"+
      "B s| |  B\n"+
      "C  | |  C\n"+
      "D *|d|d D\n"+
      "E  | |  E\n"+
      expectedHeader;
    
    assertEquals(expected3, view4.displayMyOwnBoard());

    Placement h20 = new Placement(new Coordinate(0, 1), 'V');
    b4.moveShip(new Coordinate(0, 0), h20);

    String expected4=
      expectedHeader+
      "A  |*|  A\n"+
      "B  |s|  B\n"+
      "C  | |  C\n"+
      "D *|d|d D\n"+
      "E  | |  E\n"+
      expectedHeader;
    
    assertEquals(expected4, view4.displayMyOwnBoard());
    
    Placement u1_2 = new Placement(new Coordinate(0, 0), 'U');
    Board<Character> b1 = new BattleShipBoard<Character>(3, 5,'X');
    BoardTextView view1 = new BoardTextView(b1);

    Ship<Character> car1 = f.makeCarrier(u1_2);
    
    assertEquals(b1.tryAddShip(car1), null);
    assertSame(b1.fireAt(new Coordinate(0, 0)), car1);

    String expected5=
      expectedHeader+
      "A *| |  A\n"+
      "B c| |  B\n"+
      "C c|c|  C\n"+
      "D  |c|  D\n"+
      "E  |c|  E\n"+
      expectedHeader;
    
    assertEquals(expected5, view1.displayMyOwnBoard());

    Placement d20 = new Placement("A1D");
    b1.moveShip(new Coordinate(0, 0), d20);

    String expected6=
      expectedHeader+
      "A  | |c A\n"+
      "B  | |c B\n"+
      "C  |c|c C\n"+
      "D  |c|  D\n"+
      "E  |*|  E\n"+
      expectedHeader;
    
    assertEquals(expected6, view1.displayMyOwnBoard());

  }

}











