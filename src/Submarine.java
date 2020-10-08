
public class Submarine extends Ship {

 // constructor
    public Submarine() {
	this.setLength(3);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "submarine";
    }

}
