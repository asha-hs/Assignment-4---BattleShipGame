
public class BattleShip extends Ship {

    public BattleShip() {
	this.setLength(8);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "battleship";
    }

}
