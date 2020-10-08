
public class EmptySea extends Ship {
    
    
    public EmptySea() {
	this.setLength(1);
	this.setIsFired(false);
	this.setHit(new boolean[1]);
    }

    @Override
    public boolean shootAt(int row, int column) {
	this.setIsFired(true);
	this.getHit()[0] = true;
	return false;
    }
    
    

    @Override
    public boolean isSunk() {
        return false;
    }
    @Override
    public String toString() {
        return "W";
    }
    @Override
    String getShipType() {
	return new String("");
    }

}
