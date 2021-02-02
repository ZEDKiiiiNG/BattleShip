package edu.duke.xl351.battleship;

/**
 * This class handles textual display of
 * a Board (i.e., converting it to a string to show
 * to the user).
 * It supports two ways to display the Board:
 * one for the player's own board, and one for the 
 * enemy's board.
 */

public class BoardTextView {
  /**
   * The Board to display
   */
  private final Board toDisplay;
  /**
   * Constructs a BoardView, given the board it will display.
   * @param toDisplay is the Board to display
   */
  public BoardTextView(Board toDisplay) {
    this.toDisplay = toDisplay;
    if (toDisplay.getWidth() > 10 || toDisplay.getHeight() > 26) {
      throw new IllegalArgumentException(
          "Board must be no larger than 10x26, but is " + toDisplay.getWidth() + "x" + toDisplay.getHeight());
    }

  }

  public String displayMyOwnBoard() {
    StringBuilder ans = new StringBuilder();
    ans.append(makeHeader());   // create the header
    for (int j = 0; j < toDisplay.getHeight(); j++) {

      ans.append(makeBody(j));
    }
    ans.append(makeHeader());
    
    return ans.toString();
 }
  /**
   * This makes the header line, e.g. 0|1|2|3|4\n
   * 
   * @return the String that is the header line for the given board
   */
  String makeHeader() {
    StringBuilder ans = new StringBuilder("  "); // README shows two spaces at
    String sep=""; //start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(i);
      sep = "|";
    }
    ans.append("\n");
    return ans.toString();
  }
  /**
   * This makes the body  line, e.g."A  |  A\n"
   * 
   * @return the String that is the headerbody line for the given board
   */
  
  String makeBody(int a){
    int A_int = (int) 'A'; // convert A in to int type
    char sta = (char)(A_int+a); // to get the start character of the line
    StringBuilder ans = new StringBuilder(sta+" "); //"A  |  A\n"
    String sep=""; //start with nothing to separate, then switch to | to separate
    for (int i = 0; i < toDisplay.getWidth(); i++) {
      ans.append(sep);
      ans.append(" ");
      sep = "|";
    }
    ans.append(" "+sta+"\n");
    return ans.toString();
    
  }

  
}













