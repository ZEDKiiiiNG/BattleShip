package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class BattleShipBoardTest {
  //private void checkWhatIsAtBoard(BattleShipBoard<Character> b, Character[][] expect){
  private <T> void checkWhatIsAtBoard(BattleShipBoard<T> b, T[][] expect){
    for(int i=0; i < b.getWidth(); i++){
      for(int j = 0; j< b.getHeight(); j++ ){
        Coordinate c1 = new Coordinate(j,i);
        assertEquals(b.whatIsAt(c1),expect[i][j]); // the i represent for width and j for Height
      }
    }
  }
  @Test
  public void test_width_and_height() {
    Board<Character> b1 = new BattleShipBoard<Character>(10, 20);
    assertEquals(10, b1.getWidth());
    assertEquals(20, b1.getHeight());
    
  }
  @Test
  public void test_invalid_dimensions() {
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, 0));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(0, 20));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(10, -5));
    assertThrows(IllegalArgumentException.class, () -> new BattleShipBoard<Character>(-8, 20));
  }

  @Test
  public void test_add_ship(){
    BattleShipBoard<Character> b1 = new BattleShipBoard<Character>(2, 2);
    Character[][] expect = new Character[2][2];
    checkWhatIsAtBoard(b1,expect); // check all is null
    Coordinate c1 = new Coordinate("B0");
    Coordinate c2 = new Coordinate("C0");
    Coordinate c3 = new Coordinate("B3");
    Ship<Character> s1 = new BasicShip(c1);
    assertEquals(b1.tryAddShip(s1),true);
    expect[0][1] = 's';
    checkWhatIsAtBoard(b1,expect); // check only the C1 is 's'
    //check the out of bounds
    assertThrows(IllegalArgumentException.class, () -> b1.whatIsAt(c2));
    assertThrows(IllegalArgumentException.class, () -> b1.whatIsAt(c3));
   
  }



}










