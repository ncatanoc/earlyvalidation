package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class ADD_VEHICLETest {
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
	public void ADD_VEHICLE_01() {
		Integer Vehicle = 1;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);		
		int s1 = machine.get_vehicles().int_size();
		
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		int s2 = machine.get_vehicles().int_size();
		
		assertTrue(machine.get_vehicles().has(Vehicle));
		assertTrue(s2 == s1+1);
	}
	
	@Test
	public void ADD_VEHICLE_02() {
		Integer Vehicle = 1;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		assertTrue(machine.get_vehicles().has(Vehicle));
		
		int s1 = machine.get_vehicles().int_size();
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);		
		int s2 = machine.get_vehicles().int_size();
		assertTrue(s1==s2);
	}
	

}
