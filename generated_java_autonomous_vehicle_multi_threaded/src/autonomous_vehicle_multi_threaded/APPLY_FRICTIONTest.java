package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;
import org.junit.*; 


import eventb_prelude.BSet;

public class APPLY_FRICTIONTest {
	private ref1_kinectic machine;
	
	@Before
	public void setUp() {
		machine = new ref1_kinectic();	
	}

	@After
	public void tearDown() throws Exception {
		
        Integer initobjs[] = {1,}; 
		BSet<Integer> objects = new BSet<Integer>(initobjs);
		machine.set_objects(objects);
	}
	
	@Test
	public void APPLY_FRICTION_01() {
		Integer Vehicle = 33;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 0; // car's position, x-coordinate
		Integer Y = 0; // car's position, y-coordinate
		Integer Max = 17; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);		
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);		
		assertTrue(machine.get_vehicles().has(Vehicle));
		//
		Integer MAX = 46;
		SET_MAXVEL smv = new SET_MAXVEL(machine);
		
		smv.run_SET_MAXVEL(MAX, Vehicle);
		assertTrue(machine.get_maxvel().elementImage(Vehicle).choose() == MAX);
		
		Integer V = 33;
		SET_VEL sv = new SET_VEL(machine);
		sv.run_SET_VEL(Vehicle, V);

		Integer newV = machine.get_vel().apply(Vehicle);
		assertTrue(V==newV);
		//
		Integer Elapsed = 666;
		Integer Lane = 1;
		APPLY_FRICTION af = new APPLY_FRICTION(machine);
		af.run_APPLY_FRICTION(Elapsed, Lane, Vehicle);
		//
	}

}
