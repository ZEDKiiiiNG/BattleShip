package edu.duke.xl351.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.function.Function;

public class TextPlayer {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;

  final AbstractShipFactory<Character> shipFactory;

  final String name;

  final ArrayList<String> shipsToPlace;
  final HashMap<String, Function<Placement, Ship<Character>>> shipCreationFns;


  /**
   * Constructs a TextPlayer  with the borad, input source reader and ouput printstream
   * @param theBoard is the {@link Board}.
   * @param view is the text view of the board to display.
   * @param input source reader and ouput printstream are the remaining two parameters
   */
  public TextPlayer(String name,Board<Character> theBoard, BufferedReader inputSource, PrintStream out, AbstractShipFactory<Character> shipFactory) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = inputSource;
    this.out = out;
    this.shipFactory = shipFactory;
    this.name = name;
    this.shipsToPlace = new ArrayList<String>();
    this.shipCreationFns = new HashMap<String, Function<Placement, Ship<Character>>>();
    setupShipCreationMap();
    setupShipCreationList();
  }

  /**setup function for ship an ArrayList of the ship names that we want to work from.  
   *Then we want a map from ship name to the lambda to create it.
   */  
  protected void setupShipCreationMap(){
    shipCreationFns.put("Submarine", (p) -> shipFactory.makeSubmarine(p));
    shipCreationFns.put("Battleship", (p) -> shipFactory.makeBattleship(p));
    shipCreationFns.put("Carrier", (p) -> shipFactory.makeCarrier(p));
    shipCreationFns.put("Destroyer", (p) -> shipFactory.makeDestroyer(p));
  }
  
  protected void setupShipCreationList(){
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }
  
  
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }
  
  
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    out.print(view.displayMyOwnBoard());
    out.print("\n");
  }


  
  /**  display the starting (empty) board
   *   print the instructions message (from the README,
   *   but also shown again near the top of this file)
   *   call doOnePlacement to place one ship
  
   */
  public void doPlacementPhase() throws IOException{
    out.println(view.displayMyOwnBoard());
    out.println("Player "+name+": you are going to place the following ships (which are all\nrectangular). For each ship, type the coordinate of the upper left\n"+"side of the ship, followed by either H (for horizontal) or V (for\n"+"vertical).  For example M4H would place a ship horizontally starting\n"+"at M4 and going to the right.  You have\n"+"\n"+"2 \"Submarines\" ships that are 1x2 \n"+"3 \"Destroyers\" that are 1x3\n"+"3 \"Battleships\" that are 1x4\n"+"2 \"Carriers\" that are 1x6\n");
    for(String sn : shipsToPlace){
      Function<Placement, Ship<Character>> createFn = shipCreationFns.get(sn);
      doOnePlacement(sn, createFn);
      //out.reset();
    }
    
  }

}











