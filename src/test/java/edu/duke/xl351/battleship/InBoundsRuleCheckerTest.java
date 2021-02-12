package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_Inbound_checker() {
    PlacementRuleChecker<Character> Ibc = new InBoundsRuleChecker<>(null);
    Board<Character> b = new BattleShipBoard<>(4,4, Ibc,'X');

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    Placement v1_3 = new Placement(new Coordinate(1, 2), 'H');
    Placement v1_4 = new Placement(new Coordinate(2, 0), 'V');
    Placement v_1 = new Placement(new Coordinate(0, -2), 'H');
    Placement v_2 = new Placement(new Coordinate(-2, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(v1_3);
    Ship<Character> dst3 = f.makeDestroyer(v1_4);
    Ship<Character> dst4 = f.makeDestroyer(v_1);
    Ship<Character> dst5 = f.makeDestroyer(v_2);
    
    assertEquals(Ibc.checkPlacement(dst1, b), null);
    assertEquals(Ibc.checkPlacement(dst2, b), "That placement is invalid: the ship goes off the right of the board.");
    assertEquals(Ibc.checkPlacement(dst3, b), "That placement is invalid: the ship goes off the bottom of the board.");
    assertEquals(Ibc.checkPlacement(dst4, b), "That placement is invalid: the ship goes off the left of the board.");
    assertEquals(Ibc.checkPlacement(dst5, b), "That placement is invalid: the ship goes off the top of the board.");

  }

}











