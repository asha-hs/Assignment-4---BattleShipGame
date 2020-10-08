
public abstract class Ship {

    private int bowRow;		// the row(0 - 19) which contains the bow(front) of the ship
    private int bowColumn;	// the column which contains bow of the ship
    private int length;		// the number of squares occupied by the ship. An "empty sea" location has length 1
    private boolean horizontal; //  true if the ship occupies a single row, false otherwise
    			// ships will either be placed horizontally or vertically in the ocean
    private boolean[] hit;	// this is a boolean array of size 8 that records hits. Only battleship use all the locations. The others will use fewer
    
    private boolean isFired;
    
    
    public boolean getIsFired() {
        return isFired;
    }

    public void setIsFired(boolean isFired) {
        this.isFired = isFired;
    }
    
    public int getBowRow() {
        return bowRow;
    }
    public void setBowRow(int bowRow) {
        this.bowRow = bowRow;
    }
    public int getBowColumn() {
        return bowColumn;
    }
    public void setBowColumn(int bowColumn) {
        this.bowColumn = bowColumn;
    }
    public int getLength() {
        return length;
    }
    public void setLength(int length) {
        this.length = length;
    }
    public boolean isHorizontal() {
        return horizontal;
    }
    public void setHorizontal(boolean horizontal) {
        this.horizontal = horizontal;
    }
    public boolean[] getHit() {
        return hit;
    }
    public void setHit(boolean[] hit) {
        this.hit = hit;
    }
    
    abstract String getShipType();
    /**
     * Returns true if it is okay to put a ship of this length with its bow in this location, 
     * with the given orientation, and returns false otherwise. The ship must not overlap another ship, 
     * or touch another ship (vertically, horizontally, or diagonally), and it must not ”stick out” beyond 
     * the array. Do not actually change either the ship or the Ocean, just says whether it is legal to do so.
     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     * @return
     */
    public boolean okToPlaceShip(int row, int column, boolean horizontal, Ocean ocean) {
	boolean isEmptySea = true;
	if(row < 0 || row > 20 || column < 0 || column > 20) {
	    return false;
	}
	if(horizontal && (column+length > 19)) {
	    return false;
	}
	if(!horizontal && (row + length > 19)) {
	    return false;
	}
	if(column > 0 && !ocean.getShips()[row][column-1].getShipType().equals("")) {
	    return false;
	}
	if(column < 19 && !ocean.getShips()[row][column+1].getShipType().equals("")) {
	    return false;
	}
	if(row < 19 && !ocean.getShips()[row+1][column].getShipType().equals("")) {
	    return false;
	}
	if(column > 0 && row < 19 && !ocean.getShips()[row+1][column-1].getShipType().equals("")) {
	    return false;
	}
	if(column < 19 && row < 19 && !ocean.getShips()[row+1][column+1].getShipType().equals("")) {
	    return false;
	}
	if(row > 0 && !ocean.getShips()[row-1][column].getShipType().equals("")) {
	    return false;
	}
	if(row > 0 && column > 0 && !ocean.getShips()[row-1][column-1].getShipType().equals("")) {
	    return false;
	}
	if(row > 0 && column <19 && !ocean.getShips()[row-1][column+1].getShipType().equals("")) {
	    return false;
	}
	
	if(horizontal) {
	    if(column + length < 20) {
				
		for(int j = column; j < column + length; j++) {
		    if(!ocean.getShips()[row][j].getShipType().equals("")) {
			isEmptySea = false;
			break;
		    }
		}
		
	    }
	    else {
		    isEmptySea = false;
		}

	}
	else {
	    if(row + length < 20) {
		for(int i = row; i < row + length; i++) {
		    if(!ocean.getShips()[i][column].getShipType().equals("")) {
			isEmptySea = false;
			break;
		    }
		}
	    }
	    else {
		    isEmptySea = false;
		}
	    
	}
	return isEmptySea;
    }
    
    /**
     * ”Puts” the ship in the ocean. This involves giving values to the bowRow, bowColumn, 
     * and horizontal instance variables in the ship, and it also involves putting a reference 
     * to the ship in each of 1 or more locations (up to 8) in the ships array in the Ocean object. 
     * (Note: This will be as many as eight identical references; 
     * you can’t refer to a ”part” of a ship, only to the whole ship.)

     * @param row
     * @param column
     * @param horizontal
     * @param ocean
     */
    public void placeShipAt(int row, int column, boolean horizontal, Ocean ocean) {
	this.bowRow = row;
	this.bowColumn = column;
	this.horizontal = horizontal;
	//Ship[][] ships = ocean.getShips();
	
	    if(horizontal) {
		while(column < bowColumn + length )
		    ocean.getShips()[row][column++] = this;
	    }
	    else {
		while(row < bowRow + length)
		    ocean.getShips()[row++][column] = this;
	    }
	    
    }
    /**
     * If a part of the ship occupies the given row and column, and the ship hasn’t been sunk, 
     * mark that part of the ship as ”hit” (in the hit array, 0 indicates the bow) and return true, 
     * otherwise return false.
     * @param row
     * @param column
     */
    public boolean shootAt(int row, int column) {
	if(!isSunk()) {
	    if(horizontal) {
		if (row == this.bowRow && column < this.bowColumn + length) {
		    hit[column - bowColumn] = true;
		    setIsFired(true);
		}
	    }
	    else {
		if (column == this.bowColumn && row < this.bowRow + length) {
		    hit[row - bowRow] = true;
		    setIsFired(true);
		}
	    }
	    return true;
	}
	return false;
    }
    
    /**
     * Return true if every part of the ship has been hit, false otherwise.
     * @return
     */
    public boolean isSunk() {
	boolean isAllPartsOfShipHit = true;
	for(int i = 0; i < length; i++) {
	    if(!hit[i]) {
		isAllPartsOfShipHit = false;
		break;
	    }
	}
	return isAllPartsOfShipHit;
    }
    
    @Override
    public String toString() {
        if(isSunk()) return "x";
        else return "S";
    }
    
}
