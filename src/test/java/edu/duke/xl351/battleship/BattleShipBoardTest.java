package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  //private void checkWhatIsAtBoard(BattleShipBoard<Character> b, Character[][] expect){
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expect){
    for(int i=0; i < b.getWidth(); i++){
      for(int j = 0; j< b.getHeight(); j++ ){
        Coordinate c1 = new Coordinate(j,i);
        assertEquals(b.whatIsAtForSelf(c1),expect[i][j]); // the i represent for width and j for Height
      }
    }
  }
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20,'X');
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
    
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5,'X'));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20,'X'));
  }

  @Test
  public void test_add_ship(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(2, 2,'X');
    Character[][] expect = new Character[2][2];
    checkWhatIsAtBoard(b1,expect); // check all is null
    Coordinate c1 = new Coordinate("B0");
    Coordinate c2 = new Coordinate("C0");
    Coordinate c3 = new Coordinate("B3");
    Ship<Character> s1 = new RectangleShip<Character>(c1, 's', '*');;
    assertEquals(b1.tryAddShip(s1),null);
    expect[0][1] = 's';
    checkWhatIsAtBoard(b1,expect); // check only the C1 is 's'
    //check the out of bounds
    assertThrows(IllegalArgumentException.class, () -> b1.whatIsAtForSelf(c2));
    assertThrows(IllegalArgumentException.class, () -> b1.whatIsAtForSelf(c3));
   
  }
  @Test
  public void test_add_ship_withchecker(){
    Board<Character> b = new BattleShipBoard<Character>(4, 4,'X');
    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    Placement v1_3 = new Placement(new Coordinate(1, 2), 'H');
    Placement v1_4 = new Placement(new Coordinate(2, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(v1_3);
    Ship<Character> dst3 = f.makeDestroyer(v1_4);

    assertEquals(b.tryAddShip(dst1), null);
    assertEquals(b.tryAddShip(dst2), "That placement is invalid: the ship goes off the right of the board.");
    assertEquals(b.tryAddShip(dst3), "That placement is invalid: the ship goes off the bottom of the board.");
    
  }

  @Test
  public void test_add_fireat(){
    Board<Character> b = new BattleShipBoard<Character>(4, 4,'X');
    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    
    assertEquals(b.tryAddShip(dst1), null);

    assertSame(b.fireAt(new Coordinate(0, 0)), dst1);

    assertFalse(dst1.isSunk());

    assertSame(b.fireAt(new Coordinate(1, 0)), dst1);

    assertFalse(dst1.isSunk());

    assertSame(b.fireAt(new Coordinate(2, 0)), dst1);

    assertTrue(dst1.isSunk());

    assertSame(b.fireAt(new Coordinate(0, 1)), null);

    assertEquals(b.whatIsAtForEnemy(new Coordinate(0, 1)), 'X');

    assertEquals(b.whatIsAtForEnemy(new Coordinate(1, 0)), 'd');
    
    
  }

  @Test
  public void test_check_win(){
    Board<Character> b = new BattleShipBoard<Character>(4, 4,'X');
    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    
    assertEquals(b.tryAddShip(dst1), null);

    assertSame(b.fireAt(new Coordinate(0, 0)), dst1);

    assertFalse(dst1.isSunk());

    assertSame(b.fireAt(new Coordinate(1, 0)), dst1);

    assertFalse(dst1.isSunk());

    assertFalse(b.check_win());

    assertSame(b.fireAt(new Coordinate(2, 0)), dst1);

    assertTrue(dst1.isSunk());

    assertTrue(b.check_win());
    
  }


}










