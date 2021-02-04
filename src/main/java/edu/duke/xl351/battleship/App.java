/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.duke.xl351.battleship;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Reader;
import java.io.InputStreamReader;

public class App {
  final Board<Character> theBoard;
  final BoardTextView view;
  final BufferedReader inputReader;
  final PrintStream out;
  
  /**
   * Constructs a app with the borad, input source reader and ouput printstream
   * @param theBoard is the {@link Board}.
   * @param view is the text view of the board to display.
   * @param input source reader and ouput printstream are the remaining two parameters
   */

  public App(Board<Character> theBoard, Reader inputSource, PrintStream out) {
    this.theBoard = theBoard;
    this.view = new BoardTextView(theBoard);
    this.inputReader = new BufferedReader(inputSource);
    this.out = out;
  }


  public Placement readPlacement(String prompt) throws IOException {
    out.println(prompt);
    String s = inputReader.readLine();
    return new Placement(s);
  }


  public void doOnePlacement() throws IOException{
    
    Placement p = readPlacement("Where would you like to put your ship?");
    //Ship<Character> s = new BasicShip(p.getWhere());
    Ship<Character> s = new RectangleShip<Character>(p.getWhere(), 's', '*');
    theBoard.tryAddShip(s);
    out.println(view.displayMyOwnBoard());
  }

  
 
  public static void main(String[] args) throws IOException{
    Board<Character> b = new BattleShipBoard<Character>(10,20);
    
    App app = new App(b,new InputStreamReader(System.in),System.out);
    app.doOnePlacement();
  }
    
}













