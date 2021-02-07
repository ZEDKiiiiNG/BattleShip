package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class InBoundsRuleCheckerTest {
  @Test
  public void test_Inbound_checker() {
    PlacementRuleChecker<Character> Ibc = new InBoundsRuleChecker<>(null);
    Board<Character> b = new BattleShipBoard<>(4,4, Ibc);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    Placement v1_3 = new Placement(new Coordinate(1, 2), 'H');
    Placement v1_4 = new Placement(new Coordinate(2, 0), 'V');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(v1_3);
    Ship<Character> dst3 = f.makeDestroyer(v1_4);
    
    assertEquals(Ibc.checkPlacement(dst1, b), true);
    assertEquals(Ibc.checkPlacement(dst2, b), false);
    assertEquals(Ibc.checkPlacement(dst3, b), false);
  }

}











