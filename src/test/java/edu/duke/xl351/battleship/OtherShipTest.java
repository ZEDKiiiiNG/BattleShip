package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.LinkedHashSet;

import org.junit.jupiter.api.Test;

public class OtherShipTest {
  @Test
  public void test_make_coords() {
    Coordinate[][] c = new Coordinate[5][5];
    for (int i = 0; i < 5; i++) {
      for (int j = 0; j < 5; j++) {
        c[i][j] = new Coordinate(i, j);
      }

    }
    
    //test Battleship up
    LinkedHashSet<Coordinate> hbu = OtherShip.makeCoords(c[0][0], "Battleship", 'U');
    LinkedHashSet<Coordinate> hbu1  = new LinkedHashSet<Coordinate>();
    hbu1.add(c[0][1]);
    hbu1.add(c[1][0]);
    hbu1.add(c[1][1]);
    hbu1.add(c[1][2]);
    assertEquals(hbu,hbu1);

    //test Battleship Right
    LinkedHashSet<Coordinate> hbr = OtherShip.makeCoords(c[0][0], "Battleship", 'R');
    LinkedHashSet<Coordinate> hbr1  = new LinkedHashSet<Coordinate>();
    hbr1.add(c[1][1]);
    hbr1.add(c[0][0]);
    hbr1.add(c[1][0]);
    hbr1.add(c[2][0]);
    assertEquals(hbr,hbr1);

    //test Battleship Down
    LinkedHashSet<Coordinate> hbd = OtherShip.makeCoords(c[0][0], "Battleship", 'D');
    LinkedHashSet<Coordinate> hbd1  = new LinkedHashSet<Coordinate>();
    hbd1.add(c[1][1]);
    hbd1.add(c[0][2]);
    hbd1.add(c[0][1]);
    hbd1.add(c[0][0]);
    assertEquals(hbd,hbd1);

    //test Battleship Left
    LinkedHashSet<Coordinate> hbl = OtherShip.makeCoords(c[0][0], "Battleship", 'L');
    LinkedHashSet<Coordinate> hbl1  = new LinkedHashSet<Coordinate>();
    hbl1.add(c[1][0]);
    hbl1.add(c[2][1]);
    hbl1.add(c[1][1]);
    hbl1.add(c[0][1]);
    assertEquals(hbl,hbl1);

    //test Carrier UP
    LinkedHashSet<Coordinate> hcu = OtherShip.makeCoords(c[0][0], "Carrier", 'U');
    LinkedHashSet<Coordinate> hcu1  = new LinkedHashSet<Coordinate>();
    hcu1.add(c[0][0]);
    hcu1.add(c[1][0]);
    hcu1.add(c[2][0]);
    hcu1.add(c[2][1]);
    hcu1.add(c[3][1]);
    hcu1.add(c[4][1]);
    assertEquals(hcu,hcu1);


    //test Carrier Right
    LinkedHashSet<Coordinate> hcr = OtherShip.makeCoords(c[0][0], "Carrier", 'R');
    LinkedHashSet<Coordinate> hcr1  = new LinkedHashSet<Coordinate>();
    hcr1.add(c[1][0]);
    hcr1.add(c[1][1]);
    hcr1.add(c[1][2]);
    hcr1.add(c[0][2]);
    hcr1.add(c[0][3]);
    hcr1.add(c[0][4]);
    assertEquals(hcr,hcr1);

    //test Carrier Down
    LinkedHashSet<Coordinate> hcd = OtherShip.makeCoords(c[0][0], "Carrier", 'D');
    LinkedHashSet<Coordinate> hcd1  = new LinkedHashSet<Coordinate>();
    hcd1.add(c[4][0]);
    hcd1.add(c[3][0]);
    hcd1.add(c[2][0]);
    hcd1.add(c[2][1]);
    hcd1.add(c[1][1]);
    hcd1.add(c[0][1]);
    assertEquals(hcd,hcd1);

    //test Carrier Left
    LinkedHashSet<Coordinate> hcl = OtherShip.makeCoords(c[0][0], "Carrier", 'L');
    LinkedHashSet<Coordinate> hcl1  = new LinkedHashSet<Coordinate>();
    hcl1.add(c[1][4]);
    hcl1.add(c[1][3]);
    hcl1.add(c[1][2]);
    hcl1.add(c[0][2]);
    hcl1.add(c[0][1]);
    hcl1.add(c[0][0]);
    assertEquals(hcl,hcl1);

    //assertEquals("Carrier", null);
  }

}














