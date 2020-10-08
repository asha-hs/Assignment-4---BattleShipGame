
public class BattleCruiser extends Ship {

    // constructor
    public BattleCruiser() {
	this.setLength(7);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "battlecruiser";
    }

}
