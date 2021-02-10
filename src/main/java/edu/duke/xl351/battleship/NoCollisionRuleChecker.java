package edu.duke.xl351.battleship;

public class NoCollisionRuleChecker<T> extends PlacementRuleChecker<T> {
  /** check all the coordinates of the ship is not collides with others
   * @param theShip and the board of the examed ship and board
   * return ture when all the ship's coordinates are empty
   */
	@Override
	protected String checkMyRule(Ship<T> theShip, Board<T> theBoard) {
    for(Coordinate c : theShip.getCoordinates()){
      if(theBoard.whatIsAt(c) != null){
        return "That placement is invalid: the ship overlaps another ship.";
      }
    }
		return null;
	}

  public NoCollisionRuleChecker(PlacementRuleChecker<T> next) {
    super(next);
  }

}












