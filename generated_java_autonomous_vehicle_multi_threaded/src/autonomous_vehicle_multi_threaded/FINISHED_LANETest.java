package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import eventb_prelude.Pair;

public class FINISHED_LANETest {
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
	public void FINISHED_LANE_01() {
		Integer Vehicle = 11;
		Integer H = 1; // car's height
		Integer W = 1; // car's width
		Integer X = 55; // car's position, x-coordinate
		Integer Y = 66; // car's position, y-coordinate
		Integer Max = 1; // car's maximum velocity
		
		ADD_VEHICLE ac = new ADD_VEHICLE(machine);
		ac.run_ADD_VEHICLE(H, Vehicle, W, X, Y, Max);
		////////

		Integer Finish = 11;
		Integer Lane = 12; // 
		Integer Left = 1; // 
		Integer Right = 5; // 
		Integer F = 1; // 
		
		ADD_LANE al = new ADD_LANE(machine);


		al.run_ADD_LANE(  Finish,  Lane, Left, Right, F);
		assertTrue(machine.get_lanes().has(Lane));
		assertTrue(machine.get_finish_line().has(new Pair<Integer,Integer>(Lane,Finish)));
		assertTrue(machine.get_left_border().has(new Pair<Integer,Integer>(Lane,Left)));
		assertTrue(machine.get_right_border().has(new Pair<Integer,Integer>(Lane,Right)));
		assertTrue(machine.get_friction().has(new Pair<Integer,Integer>(Lane,F)));	
		//////
		FINISHED_LANE fl = new FINISHED_LANE(machine);
		fl.run_FINISHED_LANE(Lane,Vehicle);
		assertTrue(machine.get_finished().elementImage(Vehicle).choose() == true);
	}

}
