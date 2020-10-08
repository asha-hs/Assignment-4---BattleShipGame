
public class Cruiser extends Ship {

 // constructor
    public Cruiser() {
	this.setLength(6);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "cruiser";
    }

}
