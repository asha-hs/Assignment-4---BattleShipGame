
public class LightCruiser extends Ship {

    // constructor
    public LightCruiser() {
	this.setLength(5);
	this.setHit(new boolean[8]);
    }
    @Override
    String getShipType() {
	return "light cruiser";
    }

}
