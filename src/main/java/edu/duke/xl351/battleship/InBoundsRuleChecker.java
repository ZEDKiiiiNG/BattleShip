package edu.duke.xl351.battleship;

public class InBoundsRuleChecker<T> extends PlacementRuleChecker<T> {

  /** check all the coordinates of the ship is in bound
   * @param theShip and the board of the examed ship and board
   * return ture when all the ship's coordinates in bound
   */
	@Override
	protected boolean checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()){
      if(c.getColumn() < 0 || c.getColumn() >= theBoard.getWidth()){
        return false;
      }
      if(c.getRow() < 0 || c.getRow() >= theBoard.getHeight()){
        return false;
      }
    }
		return true;
	}

  /** pass the next to the next checker to the super constructor
   */
  public InBoundsRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }



}












