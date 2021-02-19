package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class V2ShipFactoryTest {
  // check helper 
  private void checkShip(Ship<Character> testShip, String expectedName,char expectedLetter, Coordinate... expectedLocs){
    assertEquals(testShip.getName(), expectedName);
    for(int i =0; i<expectedLocs.length; i++){
      assertEquals(testShip.getDisplayInfoAt(expectedLocs[i],true) ,expectedLetter);
    }
  }
  
  @Test
  public void test_V2shipcreate() {
     AbstractShipFactory<Character> f = new V2ShipFactory(); 
    
    Placement v1_2 = new Placement(new Coordinate(1, 2), 'V');
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    checkShip(dst1, "Destroyer", 'd', new Coordinate(1, 2), new Coordinate(2, 2), new Coordinate(3, 2));
    
    Placement h4_2 = new Placement(new Coordinate(4, 2), 'h');
    Ship<Character> dst2 =  f.makeDestroyer(h4_2);
    checkShip(dst2, "Destroyer", 'd', new Coordinate(4, 2), new Coordinate(4, 3), new Coordinate(4, 4));

    Placement h5_2 = new Placement(new Coordinate(5, 2), 'h');
    Ship<Character> sub =  f.makeSubmarine(h5_2);
    checkShip(sub, "Submarine", 's', new Coordinate(5, 2), new Coordinate(5, 3));

    Placement h6_2 = new Placement(new Coordinate(6, 2), 'u');
    Ship<Character> bat =  f.makeBattleship(h6_2);
    checkShip(bat, "Battleship", 'b', new Coordinate(6, 3), new Coordinate(7, 2), new Coordinate(7, 3),new Coordinate(7, 4));

    Placement h7_2 = new Placement(new Coordinate(7, 2), 'l');
    Ship<Character> car =  f.makeCarrier(h7_2);
    checkShip(car, "Carrier", 'c', new Coordinate(7, 2), new Coordinate(7, 3), new Coordinate(7, 4), new Coordinate(8, 4), new Coordinate(8, 5),new Coordinate(8, 6));



    
    Placement a1_2 = new Placement(new Coordinate(4, 2), 'a');
    assertThrows(IllegalArgumentException.class, () ->  f.makeDestroyer(a1_2));

    
    assertThrows(IllegalArgumentException.class, () ->  f.makeCarrier(a1_2));
  }

}










