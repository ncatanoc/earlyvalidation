package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.*;

public class UPDATE_VELTest {
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
	public void test_update_vel_01() {
		Integer Vehicle = 1;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		
		Integer MAX = 100;
		SET_MAXVEL smv = new SET_MAXVEL(machine);
		smv.run_SET_MAXVEL(MAX,Vehicle);
		assertTrue(machine.get_maxvel().elementImage(Vehicle).choose() == MAX);
		
		Integer V = 55;
		SET_VEL sv = new SET_VEL(machine);
		sv.run_SET_VEL(Vehicle, V);

		Integer newV = machine.get_vel().apply(Vehicle);
		assertTrue(V==newV);
		//
		Integer Elapsed = 666;
		UPDATE_VEL uv = new UPDATE_VEL(machine);
		uv.run_UPDATE_VEL(Vehicle, Elapsed);
		assertTrue(machine.get_vel().apply(Vehicle) >= newV);
	}

}
