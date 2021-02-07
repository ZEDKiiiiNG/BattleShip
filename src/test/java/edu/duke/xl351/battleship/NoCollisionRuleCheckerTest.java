package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class NoCollisionRuleCheckerTest {
  @Test
  public void test_Nocollision_checker() {
    NoCollisionRuleChecker<Character> Ncc = new NoCollisionRuleChecker<>(null);
    Board<Character> b = new BattleShipBoard<>(4,4, Ncc);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    Placement v1_3 = new Placement(new Coordinate(1, 0), 'H');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(v1_3);

    
    assertEquals(true, Ncc.checkPlacement(dst1, b));
    b.tryAddShip(dst1);
    assertEquals(false, Ncc.checkPlacement(dst2, b));
  }

   @Test
  public void test_Nocollision_and_inBound_checker() {
    
    PlacementRuleChecker<Character> Ncc = new NoCollisionRuleChecker<>(null);
    PlacementRuleChecker<Character> Ibc = new InBoundsRuleChecker<>(Ncc);
    Board<Character> b = new BattleShipBoard<>(4,4, Ibc);

    AbstractShipFactory<Character> f = new V1ShipFactory();
    Placement v1_2 = new Placement(new Coordinate(0, 0), 'V');
    Placement v1_3 = new Placement(new Coordinate(1, 0), 'H');
    Placement v1_4 = new Placement(new Coordinate(0, 2), 'H');
    
    Ship<Character> dst1 = f.makeDestroyer(v1_2);
    Ship<Character> dst2 = f.makeDestroyer(v1_3);
    Ship<Character> dst3 = f.makeDestroyer(v1_4);

    
    assertEquals(true, Ibc.checkPlacement(dst1, b));
    b.tryAddShip(dst1);
    assertEquals(false, Ibc.checkPlacement(dst2, b));

    assertEquals(Ibc.checkPlacement(dst3, b), false);
  }

}

