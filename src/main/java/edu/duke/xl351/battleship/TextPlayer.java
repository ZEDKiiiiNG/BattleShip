package edu.duke.xl351.battleship;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
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

  protected int moveNum;
  protected int sonarNum;

  protected boolean Ai;
  protected Random rand;


  /**
   * Constructs a TextPlayer  with the borad, input source reader and ouput printstream
   * @param theBoard is the {@link Board}.
   * @param view is the text view of the board to display.
   * @param input source reader and ouput printstream are the remaining two parameters
   */
  public TextPlayer(String name,Board<Character> theBoard, BufferedReader inputSource, PrintStream out, AbstractShipFactory<Character> shipFactory, boolean Ai) {
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
    this.sonarNum = 3;
    this.moveNum = 3;
    this.Ai = Ai;
    this.rand = new Random(1);
    
  }

  public TextPlayer(String name,Board<Character> theBoard, BufferedReader inputSource, PrintStream out, AbstractShipFactory<Character> shipFactory){
    this(name, theBoard, inputSource, out, shipFactory, false);
  }

  /**
   * @param b and the view are nemy's Board and BoardTextView
   * this function will let the player play one turn
   */  
  public void playOneTurn(Board<Character> b , BoardTextView enemy_view) throws IOException{
    out.println(view.displayMyBoardWithEnemyNextToIt(enemy_view,"Your ocean","Enemy's ocean"));
   
    boolean valid = false;
    while(!valid){
      try{
          out.println("player " + name + " where you want to fire at?");
          String s = readhelp();
          Coordinate c = new Coordinate(s);
          Ship<Character> sp = b.fireAt(c);
          if (sp != null) {
            out.println("You hit a " + sp.getName() + "!");
          } else {
            out.println("You missed!");
            //out.print("\n");
          }
          valid = true;
          if (b.check_win()) {
            out.println("player " + name + " has won!!!");
          }
        
      }
      catch(IllegalArgumentException e){
        out.println(e.getMessage());
      }
    }
    
  }

  /* a heler to readline and check if null
   */  
  protected String readhelp() throws EOFException,IOException{
    String s1 = inputReader.readLine();
    if(s1 == null){
      throw new EOFException("That option is invalid:the input is empty");
    }
    return s1;
  }

  /* a heler to generate random Coordinate
   * @return is the generated Coordinate
   */
  public Coordinate randomCoordinate(){
    
    int row = rand.nextInt(theBoard.getHeight());
    int col = rand.nextInt(theBoard.getWidth());
    return new Coordinate(row,col);
  }

  /* a heler to generate random Coordinate
   * @param simple means whether VH or URDL
   * @return is the generated Coordinate
   */
  public Placement randomPlacement(boolean simple){
    
    char[] range1 = {'V','H'};
    char[] range2 = {'U','R', 'D', 'L'};
    int index1 = rand.nextInt(2);
    int index2 = rand.nextInt(4);
    if(simple){
      return new Placement(randomCoordinate(), range1[index1]);
    }
    else {
      return new Placement(randomCoordinate(), range2[index2]);
    }
  }

  /* a heler to generate random Coordinate
   * @param simple means whether VH or URDL
   * @return is the generated Coordinate
   */
  public String randomOption(){
    
    List range1 =  new ArrayList();
    int num = 1;
    range1.add("F");
    if(sonarNum > 0){
      range1.add("S");
      num++;
    }
    if(moveNum > 0){
      range1.add("M");
      num++;
    }
    int index1 = rand.nextInt(num);
    return (String)range1.get(index1);
    
  }
  
  /**
   * @param b and the view are nemy's Board and BoardTextView
   * this function will let the player play one turn
   */
  
  public void playOneTurnV2(Board<Character> b , BoardTextView enemy_view) throws IOException{
    if (!Ai) {
      out.println(view.displayMyBoardWithEnemyNextToIt(enemy_view, "Your ocean", "Enemy's ocean"));
    }
      String options = "Possible actions for Player A:\n\n" + "F Fire at a square\n"
          + "M Move a ship to another square (" + moveNum + " remaining)\n" + "S Sonar scan (" + sonarNum
          + " remaining)\n\n" + "Player " + name + ", what would you like to do?\n";
    
    boolean valid = false;
    while(!valid){
      try{
        String s1 = null;
        if (!Ai) {
          out.print(options);
          s1 = readhelp();
        }
        else{
          s1 = randomOption();
        }
        if (s1.equals("F")) {
          if (!Ai) {
            out.println("player " + name + " where you want to fire at?");
            String s = readhelp();
            Coordinate c = new Coordinate(s);
            Ship<Character> sp = b.fireAt(c);
            if (sp != null) {
              out.println("You hit a " + sp.getName() + "!");
            } else {
              out.println("You missed!");
              //out.print("\n");
            }
          }
          else{
            Coordinate c = randomCoordinate();
            Ship<Character> sp = b.fireAt(c);
            if (sp != null) {
              out.println("Player "+ name +" hit your " + sp.getName() +"at "+c.toString()+ "!");
            } else {
              out.println("Player "+ name +"missed!");
              //out.print("\n");
            }
          }
          valid = true;
          if (b.check_win()) {
            out.println("player " + name + " has won!!!");
          }
          
        }
        else if(s1.equals("M") && moveNum > 0){
          Coordinate c = null;
          Placement p = null;
          if (!Ai) {
            out.println("player " + name + " which ship you want to move?");
            String s = readhelp();
            out.println("player " + name + " where you want to move to?");
            String s2 = readhelp();
            c = new Coordinate(s);
            p = new Placement(s2);
          }
          else{
            c = randomCoordinate();
            if(theBoard.selectShip(c) == null){          
              throw new IllegalArgumentException("No ships contains coordinate " + c.toString());
            }
            p = randomPlacement(theBoard.selectShip(c).getName() == "Submarine" || theBoard.selectShip(c).getName() == "Destroyer");
            out.println("Player "+ name +" used a special action");
          }
          
          String info = theBoard.moveShip(c, p);
          if(info != null){
            throw new IllegalArgumentException(info);
          }
          moveNum--;
          valid = true;
        }
        else if(s1.equals("S") && sonarNum > 0){
          Coordinate c = null;
          if (!Ai) {
            out.println("player " + name + " where you want to use sonar?");
            String s = readhelp();
            c = new Coordinate(s); // check in board or not?
            out.print(theBoard.sonarScan(c));
          }
          else{
            c = randomCoordinate();
            out.println("Player "+ name +" used a special action");
          }
          
          sonarNum--;
          valid = true;
        }
        else{
          throw new IllegalArgumentException("That option is invalid:the input should be F S or M, which is "+s1+" the reamaning sonar is "+sonarNum+" and the remaining move num is "+moveNum);
        }
        
      }
      catch(IllegalArgumentException e){
        out.println(e.getMessage());
      }
    }
    
  }
  
  
  
  /** the helper function to return checkwin status
   */
  public boolean check_win() {
    return theBoard.check_win();
  }

  /** the helper function to return board
   */
  public Board<Character> get_Board(){
    return theBoard;
  }

  /** the helper function to return boardview
   */
  public BoardTextView get_View(){
    return view;
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
  /**setup function for ship an ArrayList of the ship names that we want to work from.  
   *Then we want a map from ship name to the lambda to create it.
   */  
  protected void setupShipCreationList(){
    shipsToPlace.addAll(Collections.nCopies(2, "Submarine"));
    shipsToPlace.addAll(Collections.nCopies(3, "Destroyer"));
    shipsToPlace.addAll(Collections.nCopies(3, "Battleship"));
    shipsToPlace.addAll(Collections.nCopies(2, "Carrier"));
  }
  
  /**readplacement function for ship an ArrayList of the ship names that we want to work from.  
   *Then we want a map from ship name to the lambda to create it.
   */  
  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = readhelp();
    return new Placement(s);
  }
  
  /** This function is to iterate the input line make sure all the input is valid .
   *  if is not valid, the corresoponding String will print to show the first excpetion it break 
   * @param valid is a maker to show which the input is valid or not
   * @param shipName represents the name of {@link Ship}
   * @param createFn represents the correspoding ship function
   */  
  public void doOnePlacement(String shipName, Function<Placement, Ship<Character>> createFn) throws IOException {
    boolean valid = false;
    while(!valid){
      try{
        Placement p = null;
        if (!Ai) {
          p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
        }
        else{
          p = randomPlacement(shipName == "Submarine" || shipName == "Destroyer");
        }
        Ship<Character> s = createFn.apply(p);
        String err = theBoard.tryAddShip(s);
        if(err != null){
          throw new IllegalArgumentException(err);
        }
        valid = true;
      }
      catch(IllegalArgumentException e){
        out.println(e.getMessage());
      }
      }
    /*
    Placement p = readPlacement("Player " + name + " where do you want to place a " + shipName + "?");
    Ship<Character> s = createFn.apply(p);
    theBoard.tryAddShip(s);
    */
    if (!Ai) {
      out.print(view.displayMyOwnBoard());
      out.print("\n");
    }
    }
    
    
    
    /**  display the starting (empty) board
    *   print the instructions message (from the README,
    *   but also shown again near the top of this file)
    *   call doOnePlacement to place one ship
    
    */
  public void doPlacementPhase() throws IOException{
    if (!Ai) {
      out.println(view.displayMyOwnBoard());
      out.println("Player " + name
          + ": you are going to place the following ships (which are all\nrectangular). For each ship, type the coordinate of the upper left\n"
          + "side of the ship, followed by either H (for horizontal) or V (for\n"
          + "vertical).  For example M4H would place a ship horizontally starting\n"
          + "at M4 and going to the right.  You have\n" + "\n" + "2 \"Submarines\" ships that are 1x2 \n"
          + "3 \"Destroyers\" that are 1x3\n" + "3 \"Battleships\" that are 1x4\n" + "2 \"Carriers\" that are 1x6\n");
    }
    for(String sn : shipsToPlace){
      Function<Placement, Ship<Character>> createFn = shipCreationFns.get(sn);
      doOnePlacement(sn, createFn);
      //out.reset();
    }
    
  }

}











