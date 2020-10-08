
public class Destroyer extends Ship {

 // constructor
    public Destroyer() {
	this.setLength(4);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "destroyer";
    }

}
