package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.*;

public class SET_POSTest {
	private ref1_kinectic machine;
	
	@Before
	public void setUp() {
		machine = new ref1_kinectic();	
	}

    @After
    public void tearDown() {
            //
    }

	@Test
	public void SET_POS_01() {
		Integer Vehicle = 11;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		
		SET_POS sp = new SET_POS(machine);
		sp.run_SET_POS(Vehicle, X+10, Y+20);
		assertTrue(machine.get_posX().apply(Vehicle) == 10);
		assertTrue(machine.get_posY().apply(Vehicle) == 20);
	}

}
