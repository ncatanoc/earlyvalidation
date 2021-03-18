package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.*;

public class SET_ZERO_VELTest {
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
	public void SET_ZERO_VEL_01() {
		Integer Vehicle = 11;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		SET_ZERO_VEL szv = new SET_ZERO_VEL(machine);
		szv.run_SET_ZERO_VEL(Vehicle);

		Integer newV = machine.get_acc().elementImage(Vehicle).choose();
		assertTrue(newV==0);
	}

}
