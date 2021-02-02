package edu.duke.xl351.battleship;

/**
   * Constructs a BattleShipBoard which implements the interface board
   * The {@link BattleShipBoard} has two elements which are hieght and width of board
   * @param w is the width of the newly constructed board.
   * @param h is the height of the newly constructed board.
   * @throws IllegalArgumentException if the width or height are less than or equal to zero.
   */

public class BattleShipBoard implements Board{
  private final int width;

  public int getWidth() {
    return width;
  }
  
  private final int height;
  
  public int getHeight() {
    return height;
  }

  public BattleShipBoard(int w, int h) {
    if (w <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's width must be positive but is " + w);
    }
    if (h <= 0) {
      throw new IllegalArgumentException("BattleShipBoard's height must be positive but is " + h);
    }
    this.width = w;
    this.height = h;
  }

}














