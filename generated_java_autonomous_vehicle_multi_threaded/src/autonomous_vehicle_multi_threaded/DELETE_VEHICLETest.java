package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class DELETE_VEHICLETest {
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
	public void DELETE_VEHICLE_01() {
		Integer Vehicle = 33;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 5; // car's position, x-coordinate
		Integer Y = 5; // car's position, y-coordinate
		Integer Max = 17; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);	
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		DELETE_VEHICLE dc = new DELETE_VEHICLE(machine);
		dc.run_DELETE_VEHICLE(Vehicle);
		assertFalse(machine.get_vehicles().has(Vehicle));
	}

	@Test
	public void DELETE_VEHICLE_02() {
		Integer Vehicle = 11;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 5; // car's position, x-coordinate
		Integer Y = 5; // car's position, y-coordinate
		Integer F = 1; // not used to create car but to create the lane
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);	
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		DELETE_VEHICLE dc = new DELETE_VEHICLE(machine);
		dc.run_DELETE_VEHICLE(Vehicle);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		assertTrue(machine.get_vehicles().has(Vehicle));
	}

	@Test
	public void DELETE_VEHICLE_3() {
		Integer Vehicle1 = 11, Vehicle2 = 12, Vehicle3 = 13;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 5; // car's position, x-coordinate
		Integer Y = 5; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);	
		ac.run_ADD_VEHICLE(H, Vehicle1, W, X, Y, Max);
		ac.run_ADD_VEHICLE(H, Vehicle2, W, X, Y, Max);
		ac.run_ADD_VEHICLE(H, Vehicle3, W, X, Y, Max);
		
		assertTrue(machine.get_vehicles().has(Vehicle1));
		assertTrue(machine.get_vehicles().has(Vehicle2));
		assertTrue(machine.get_vehicles().has(Vehicle3));

		
		DELETE_VEHICLE dc = new DELETE_VEHICLE(machine);
		dc.run_DELETE_VEHICLE(Vehicle1);
		dc.run_DELETE_VEHICLE(Vehicle2);
		dc.run_DELETE_VEHICLE(Vehicle3);
		assertFalse(machine.get_vehicles().has(Vehicle1));
		assertFalse(machine.get_vehicles().has(Vehicle2));
		assertFalse(machine.get_vehicles().has(Vehicle3));
	}

}
