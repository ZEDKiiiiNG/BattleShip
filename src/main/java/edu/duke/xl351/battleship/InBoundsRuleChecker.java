package edu.duke.xl351.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /** check all the coordinates of the ship is in bound
   * @param theShip and the board of the examed ship and board
   * return ture when all the ship's coordinates in bound
   */
	@Override
	protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getColumn() < 0){
        return "That placement is invalid: the ship goes off the left of the board.";
      }
      if(c.getColumn() >= theBoard.getWidth()){
        return "That placement is invalid: the ship goes off the right of the board.";
      }
      if(c.getRow() < 0){
        return "That placement is invalid: the ship goes off the top of the board.";
      }
      if(c.getRow() >= theBoard.getHeight()){
        return "That placement is invalid: the ship goes off the bottom of the board.";
      }
    }
		return null;
	}

  /** pass the next to the next checker to the super constructor
   */
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }



}












