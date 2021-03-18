package autonomous_vehicle_multi_threaded;

import static org.junit.Assert.*;
import org.junit.*; 

import eventb_prelude.Pair;

public class ADD_OBSTACLETest {
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
	public void ADD_OBSTACLE_01() {

		ADD_OBSTACLE ao = new ADD_OBSTACLE(machine);

		Integer H = 3;
		Integer W = 6;
		Integer X = 10;
		Integer Y = 20;
		Integer Obs = 11;
		
		ao.run_ADD_OBSTACLE(H, Obs, W, X, Y);
		assertTrue(machine.get_obstacles().has(Obs));
		assertTrue(machine.get_height().has(new Pair<Integer,Integer>(Obs,H)));
		assertTrue(machine.get_posX().has(new Pair<Integer,Integer>(Obs,X)));
		assertTrue(machine.get_posY().has(new Pair<Integer,Integer>(Obs,Y)));	
	}

}
