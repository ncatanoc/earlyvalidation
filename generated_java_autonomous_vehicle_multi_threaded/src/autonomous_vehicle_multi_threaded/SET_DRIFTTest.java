package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SET_DRIFTTest {
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
	public void SET_DRIFTT_01() {
		Integer Vehicle = 11;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		Integer D = 0;
		SET_DRIFT sd = new SET_DRIFT(machine);
		sd.run_SET_DRIFT(Vehicle, D);
		assertTrue(machine.get_drift().elementImage(Vehicle).choose() == 0);
		//
		D = 1;
		sd.run_SET_DRIFT(D, Vehicle);
		assertTrue(machine.get_drift().elementImage(Vehicle).choose() == D);
		//
		D = -1;
		sd.run_SET_DRIFT(D, Vehicle);
		assertTrue(machine.get_drift().elementImage(Vehicle).choose() == D);
	}

}
