package autonomous_vehicle_multi_threaded;
import static org.junit.Assert.*;

import org.junit.*; 
import eventb_prelude.Pair;

public class DELETE_LANETest {
	private ref1_kinectic machine;
	
	@Before
	public void setUp() {
		machine = new ref1_kinectic();	
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void DELETE_LANE_01() {
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
		
		DELETE_LANE dl = new DELETE_LANE(machine);
		dl.run_DELETE_LANE(Lane);
		assertFalse(machine.get_lanes().has(Lane));				
	}

}
