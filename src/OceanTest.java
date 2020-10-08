import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {

    private Ocean ocean;
    @Before
    public void setUp() throws Exception {
	ocean = new Ocean();
    }

    @Test
    public void testPlaceAllShipsRandomly() {
	ocean.placeAllShipsRandomly();
	
    }
    
    @Test
    public void testPlaceBattleShip() {
	Ship battleShip = new BattleShip();
	
	battleShip.placeShipAt(1, 4, true, ocean);
	ocean.print();
	assertEquals(true, ocean.shootAt(1, 5));
	
	assertEquals(true, ocean.shootAt(1,4));
	assertEquals(true, ocean.shootAt(1,6));
	assertEquals(true, ocean.shootAt(1,7));
	assertEquals(true, ocean.shootAt(1,8));
	assertEquals(true, ocean.shootAt(1,9));
	assertEquals(true, ocean.shootAt(1,10));
	assertEquals(true, ocean.shootAt(1,11));
	assertEquals(true,battleShip.isSunk());
	System.out.println(ocean.getShips()[1][4].isSunk());
	System.out.println(ocean.getShips()[1][5].isSunk());
	
    }

}
