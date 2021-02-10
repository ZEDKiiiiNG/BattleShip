package edu.duke.xl351.battleship;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.StringReader;
import java.util.function.Function;

import org.junit.jupiter.api.Test;

public class TextPlayerTest {
  /**
     we need to access bytes in the test code, and can't return two values
   */
   private TextPlayer createTextPlayer(int w, int h, String inputData, OutputStream bytes) {
    BufferedReader input = new BufferedReader(new StringReader(inputData));
    PrintStream output = new PrintStream(bytes, true);
    Board<Character> board = new BattleShipBoard<Character>(w, h);
    V1ShipFactory shipFactory = new V1ShipFactory();
    return new TextPlayer("A", board, input, output, shipFactory);
  }

  
  @Test
  public void test_read_placement() throws IOException{
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(10, 20, "B2V\nC8H\na4v\n", bytes);


    String prompt = "Please enter a location for a ship:";
    Placement[] expected = new Placement[3];
    expected[0] = new Placement(new Coordinate(1, 2), 'V');
    expected[1] = new Placement(new Coordinate(2, 8), 'H');
    expected[2] = new Placement(new Coordinate(0, 4), 'V');
    
    for (int i = 0; i < expected.length; i++) {
        Placement p = player.readPlacement(prompt);
        assertEquals(p, expected[i]); //did we get the right Placement back
        assertEquals(prompt + "\n", bytes.toString()); //should have printed prompt and newline
        bytes.reset(); //clear out bytes for next time around
      }
  }

  @Test
   public void test_doOne_placement() throws IOException{
    
    String expectedHeader= "  0|1|2\n";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 5, "B0V\nC1v\nb2v\n", bytes);

    String sn1 = "Destroyer";
    Function<Placement, Ship<Character>> createFn1 = player.shipCreationFns.get(sn1);
    player.doOnePlacement(sn1,createFn1);
    
    String expected1=
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A  | |  A\n"+
      "B d| |  B\n"+
      "C d| |  C\n"+
      "D d| |  D\n"+
      "E  | |  E\n"+
      expectedHeader+"\n";
    assertEquals(expected1, bytes.toString());
    bytes.reset(); //clear out bytes for next time around

    String sn2 = "Destroyer";
    Function<Placement, Ship<Character>> createFn2 = player.shipCreationFns.get(sn2);
    player.doOnePlacement(sn2,createFn2);
    
    
    String expected2=
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A  | |  A\n"+
      "B d| |  B\n"+
      "C d|d|  C\n"+
      "D d|d|  D\n"+
      "E  |d|  E\n"+
      expectedHeader+"\n";
    assertEquals(expected2, bytes.toString());
    bytes.reset(); //clear out bytes for next time around

    String sn3 = "Destroyer";
    Function<Placement, Ship<Character>> createFn3 = player.shipCreationFns.get(sn3);
    player.doOnePlacement(sn3,createFn3);
    
    
    String expected3=
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A  | |  A\n"+
      "B d| |d B\n"+
      "C d|d|d C\n"+
      "D d|d|d D\n"+
      "E  |d|  E\n"+
      expectedHeader+"\n";
    assertEquals(expected3, bytes.toString());
    bytes.reset(); //clear out bytes for next time around
  }

  @Test
   public void test_doOne_placement_throw_illegelplace() throws IOException{
    
    String expectedHeader= "  0|1|2\n";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 5, "Z2V\n8ZH\naav\nA0Q\n\nB0V\n", bytes);

    String sn1 = "Destroyer";
    Function<Placement, Ship<Character>> createFn1 = player.shipCreationFns.get(sn1);
    player.doOnePlacement(sn1,createFn1);
    
    String expected1 = "Player A where do you want to place a Destroyer?\n"+"That placement is invalid: the ship goes off the bottom of the board.\n";
    
    String expected2 = "Player A where do you want to place a Destroyer?\n"+"the first letter must be A-Z, but is 8\n";
    
    String expected3 = "Player A where do you want to place a Destroyer?\n"+"the second number  must be 0-9, but is a\n";

    
    String expected4 = "Player A where do you want to place a Destroyer?\n"+"the orientation must be V or H, but is Q\n";
    
    String expected5 = "Player A where do you want to place a Destroyer?\n"+"the length of size must be 3, but is 0\n";

    String expected = expected1+expected2+expected3+expected4+expected5+"Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A  | |  A\n"+
      "B d| |  B\n"+
      "C d| |  C\n"+
      "D d| |  D\n"+
      "E  | |  E\n"+
      expectedHeader+"\n";
    assertEquals(expected, bytes.toString());
    bytes.reset();
  }

  /*
  @Test
   public void test_doOne_placement_throw_nullinput() throws IOException{
    
    String expectedHeader= "  0|1|2\n";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 5, "", bytes);

    String sn1 = "Destroyer";
    Function<Placement, Ship<Character>> createFn1 = player.shipCreationFns.get(sn1);
    player.doOnePlacement(sn1,createFn1);
  }
  */
  
  @Test
   public void test_doPlacementPhase() throws IOException{

    String expectedHeader= "  0|1|2\n";
    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
    TextPlayer player = createTextPlayer(3, 15, "A0V\nA1v\na2v\nc0v\nc1v\nd2v\nh2v\nl2v\nf0v\nf1v\n", bytes);

    player.doPlacementPhase();
    String expected1=
      expectedHeader+
      "A  | |  A\n"+
      "B  | |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+"Player A: you are going to place the following ships (which are all\nrectangular). For each ship, type the coordinate of the upper left\n"+"side of the ship, followed by either H (for horizontal) or V (for\n"+"vertical).  For example M4H would place a ship horizontally starting\n"+"at M4 and going to the right.  You have\n"+"\n"+"2 \"Submarines\" ships that are 1x2 \n"+"3 \"Destroyers\" that are 1x3\n"+"3 \"Battleships\" that are 1x4\n"+"2 \"Carriers\" that are 1x6\n"+"\n"+
      "Player A where do you want to place a Submarine?\n"+
      expectedHeader+
      "A s| |  A\n"+
      "B s| |  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Submarine?\n"+
      expectedHeader+
      "A s|s|  A\n"+
      "B s|s|  B\n"+
      "C  | |  C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C  | |d C\n"+
      "D  | |  D\n"+
      "E  | |  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d| |d C\n"+
      "D d| |  D\n"+
      "E d| |  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Destroyer?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|  D\n"+
      "E d|d|  E\n"+
      "F  | |  F\n"+
      "G  | |  G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Battleship?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|b D\n"+
      "E d|d|b E\n"+
      "F  | |b F\n"+
      "G  | |b G\n"+
      "H  | |  H\n"+
      "I  | |  I\n"+
      "J  | |  J\n"+
      "K  | |  K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Battleship?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|b D\n"+
      "E d|d|b E\n"+
      "F  | |b F\n"+
      "G  | |b G\n"+
      "H  | |b H\n"+
      "I  | |b I\n"+
      "J  | |b J\n"+
      "K  | |b K\n"+
      "L  | |  L\n"+
      "M  | |  M\n"+
      "N  | |  N\n"+
      "O  | |  O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Battleship?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|b D\n"+
      "E d|d|b E\n"+
      "F  | |b F\n"+
      "G  | |b G\n"+
      "H  | |b H\n"+
      "I  | |b I\n"+
      "J  | |b J\n"+
      "K  | |b K\n"+
      "L  | |b L\n"+
      "M  | |b M\n"+
      "N  | |b N\n"+
      "O  | |b O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Carrier?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|b D\n"+
      "E d|d|b E\n"+
      "F c| |b F\n"+
      "G c| |b G\n"+
      "H c| |b H\n"+
      "I c| |b I\n"+
      "J c| |b J\n"+
      "K c| |b K\n"+
      "L  | |b L\n"+
      "M  | |b M\n"+
      "N  | |b N\n"+
      "O  | |b O\n"+
      expectedHeader+"\n"+
      "Player A where do you want to place a Carrier?\n"+
      expectedHeader+
      "A s|s|d A\n"+
      "B s|s|d B\n"+
      "C d|d|d C\n"+
      "D d|d|b D\n"+
      "E d|d|b E\n"+
      "F c|c|b F\n"+
      "G c|c|b G\n"+
      "H c|c|b H\n"+
      "I c|c|b I\n"+
      "J c|c|b J\n"+
      "K c|c|b K\n"+
      "L  | |b L\n"+
      "M  | |b M\n"+
      "N  | |b N\n"+
      "O  | |b O\n"+
      expectedHeader+"\n";
    assertEquals(expected1, bytes.toString());
  }
  

}









