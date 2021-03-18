package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.*;

public class SET_MAXVELTest {
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
	public void SET_MAXVEL_01() {
		Integer Vehicle = 33;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 55; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		Integer MAX = 100;
		SET_MAXVEL smv = new SET_MAXVEL(machine);
		smv.run_SET_MAXVEL(MAX, Vehicle);
		assertTrue(machine.get_maxvel().elementImage(Vehicle).choose() == MAX);
	}

}
