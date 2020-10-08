import java.util.*;
public class Ocean {
    // instance variables
    private Ship[][] ships;
    private int shotsFired;
    private int hitCount;
    private int shipsSunk;
    private String[] sunkShipNames;

    // constructor
    public Ocean() {
	ships = new Ship[20][20];
	//sunkShipNames = new String[13];
	for(int i = 0;i < 20; i++) {
	    for(int j=0; j < 20; j++) {
		ships[i][j] = new EmptySea();
	    }
	}
	shotsFired = 0;
	hitCount = 0;
	shipsSunk = 0;

    }

    public void placeAllShipsRandomly() {
	Random rand = new Random();
	int numOfShips;

	// place Battleship of length 8 
	int rand_i = rand.nextInt(20);
	int rand_j = rand.nextInt(20);
	boolean horizontal = rand.nextBoolean();

	Ship battleship = new BattleShip();
	while(!battleship.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	}
	battleship.placeShipAt(rand_i, rand_j, horizontal, this);

	// place BattleCruiser of length 7
	Ship battleCruiser = new BattleCruiser();
	rand_i = rand.nextInt(20);
	rand_j = rand.nextInt(20);
	horizontal = rand.nextBoolean();
	while(!battleCruiser.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	}

	battleCruiser.placeShipAt(rand_i, rand_j, horizontal, this);

	// place 2 cruisers of length 6
	numOfShips = 2;
	while(numOfShips > 0) {
	    Ship cruiser = new Cruiser();
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	    while(!cruiser.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
		rand_i = rand.nextInt(20);
		rand_j = rand.nextInt(20);
		horizontal = rand.nextBoolean();
	    }

	    cruiser.placeShipAt(rand_i, rand_j, horizontal, this);
	    numOfShips--;
	}

	// place 2 light cruisers of length 5
	numOfShips = 2;
	while(numOfShips > 0) {
	    Ship lightCruiser = new LightCruiser();
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	    while(!lightCruiser.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
		rand_i = rand.nextInt(20);
		rand_j = rand.nextInt(20);
		horizontal = rand.nextBoolean();
	    }

	    lightCruiser.placeShipAt(rand_i, rand_j, horizontal, this);
	    numOfShips--;
	}

	// place 3 destroyers of length 4
	numOfShips = 3;
	while(numOfShips > 0) {
	    Ship destroyer = new Destroyer();
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	    while(!destroyer.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
		rand_i = rand.nextInt(20);
		rand_j = rand.nextInt(20);
		horizontal = rand.nextBoolean();
	    }

	    destroyer.placeShipAt(rand_i, rand_j, horizontal, this);
	    numOfShips--;
	}

	// place 4 submarines of length 3
	numOfShips = 4;
	while(numOfShips > 0) {
	    Ship submarine = new Submarine();
	    rand_i = rand.nextInt(20);
	    rand_j = rand.nextInt(20);
	    horizontal = rand.nextBoolean();
	    while(!submarine.okToPlaceShip(rand_i, rand_j, horizontal, this)) {
		rand_i = rand.nextInt(20);
		rand_j = rand.nextInt(20);
		horizontal = rand.nextBoolean();
	    }

	    submarine.placeShipAt(rand_i, rand_j, horizontal, this);
	    numOfShips--;
	}

    }

    /**
     * Returns true if the given location contains a ship, false if it does not.
     * @param row
     * @param col
     * @return
     */
    public boolean isOccupied(int row, int col) {

	if(ships[row][col].toString().equals("W"))
	    return false;
	else
	    return true;
    }

    /**
     * Returns true if the given location contains a ”real” ship, still afloat, 
     * (not an EmptySea), false if it does not. In addition, this method updates 
     * the number of shots that have been fired, and the number of hits. 
     * Note: If a location contains a ”real” ship, shootAt should return true every time 
     * the user shoots at that same location. Once a ship has been ”sunk”, additional shots 
     * at its location should return false.
     * @param row
     * @param col
     * @return
     */
    public boolean shootAt(int row, int col) {
	shotsFired++;
	if(isOccupied(row, col)) {

	    if(ships[row][col].shootAt(row, col)) {
		if (ships[row][col].isSunk()) {
		    System.out.println("You just sunk a " + ships[row][col].getShipType());
		    shipsSunk++;
		}
		hitCount++;
		return true;
	    }
	    return false;

	}
	else {
	    ships[row][col].shootAt(row, col);

	}
	return false;
    }

    public boolean isGameOver() {

	int sunkShips = 0;
	for(int i = 0; i < 20; i++) {
	    for(int j = 0; j < 20; j++) {
		if(isOccupied(i, j) && ships[i][j].isSunk())  {
		    sunkShips++;
		}
	    }
	}

	if(sunkShips >= 61)
	    return true;
	else
	    return false;
    }
    /**
     * Prints the ocean. To aid the user, row numbers should be displayed along the left edge of the array, 
     * and column numbers should be displayed along the top. Numbers should be 00 to 19, not 1 to 20.
     * The top left corner square should be 0, 0.

     * Use ’S’ to indicate a location that you have fired upon and hit a (real) ship, 
     * ’-’ to indicate a location that you have fired upon and found nothing there, 
     * ’x’ to indicate a location containing a sunken ship,

     * and ’.’ (a period) to indicate a location that you have never fired upon.
     */
    public void print() {
	System.out.format("%2s", " ");
	for(int p = 0; p < 20; p++) {
	    if(p == 0)
		System.out.format("%3d", 00);
	    else
		System.out.format("%3d", p);
	}

	// System.out.print(p + " ");
	System.out.println();

	for(int i = 0; i < 20; i++) {
	    for(int j = 0; j < 20; j++) {

		if(j == 0)
		    System.out.format("%2d", i);
		// System.out.print(i + " ");
		if(ships[i][j].toString().equals("W")) {
		    if(ships[i][j].getIsFired()) {
			System.out.format("%3s", "-");
			// System.out.print("-"+ " ");
		    }
		    else {
			System.out.format("%3s", ".");
			//System.out.print("."+ " ");
		    }
		}
		else if(ships[i][j].toString().equals("S")) {
		    if(ships[i][j].getIsFired()) {
			System.out.format("%3s", "S");
			//System.out.print("S"+ " ");
		    }
		    else {
			System.out.format("%3s", ".");
			//System.out.print("."+ " ");
		    }
		}
		else {
		    System.out.format("%3s", "x");
		    //System.out.print("x"+ " ");
		}

	    }
	    System.out.println();
	}
    }

    // getters and setters
    public Ship[][] getShips() {
	return ships;
    }

    public void setShips(Ship[][] ships) {
	this.ships = ships;
    }

    public int getShotsFired() {
	return shotsFired;
    }

    public void setShotsFired(int shotsFired) {
	this.shotsFired = shotsFired;
    }

    public int getHitCount() {
	return hitCount;
    }

    public void setHitCount(int hitCount) {
	this.hitCount = hitCount;
    }

    public int getShipsSunk() {
	return shipsSunk;
    }

    public void setShipsSunk(int shipsSunk) {
	this.shipsSunk = shipsSunk;
    }

    public String[] getSunkShipNames() {
	return sunkShipNames;
    }



}
