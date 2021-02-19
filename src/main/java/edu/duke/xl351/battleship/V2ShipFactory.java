package edu.duke.xl351.battleship;

public class V2ShipFactory implements AbstractShipFactory<Character> {
  protected Ship<Character> createShip(Placement where, int w, int h, char letter, String name){
    if(name == "Submarine" || name == "Destroyer"){
      if(where.getOrientation() != 'V' && where.getOrientation() != 'H'){
        throw new IllegalArgumentException("Ship's orientation must be V or H but is " + where.getOrientation());
      }
      else{
        if(where.getOrientation() == 'H'){
          int temp = w;
          w = h;
          h = temp;
        }
      }
      RectangleShip<Character> s1 = new RectangleShip<Character>( name, where.getWhere(), w, h,letter, '*');
      return s1;
    }
    else{
       if(where.getOrientation() != 'U' && where.getOrientation() != 'R' && where.getOrientation() != 'D' && where.getOrientation() != 'L'){
        throw new IllegalArgumentException("Ship's orientation must be U, R, D or L  but is " + where.getOrientation());
      }
       OtherShip<Character> s = new OtherShip<>(name, where.getWhere(), where.getOrientation(), letter, '*');
       return s;
    }
  }

  @Override
  public Ship<Character> makeSubmarine(Placement where) {
    return createShip(where, 1, 2, 's', "Submarine");
  }

	@Override
	public Ship<Character> makeBattleship(Placement where) {
		return createShip(where, 1, 4, 'b', "Battleship");
	}

	@Override
	public Ship<Character> makeCarrier(Placement where) {
		return createShip(where, 1, 6, 'c', "Carrier");
	}

	@Override
	public Ship<Character> makeDestroyer(Placement where) {
		return createShip(where, 1, 3, 'd', "Destroyer");
	}

}
