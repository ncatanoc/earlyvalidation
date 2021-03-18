package autonomous_vehicle_multi_threaded;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ ADD_LANETest.class, ADD_OBSTACLETest.class,
		ADD_VEHICLETest.class, APPLY_FRICTIONTest.class, DELETE_LANETest.class,
		DELETE_OBSTACLETest.class, DELETE_VEHICLETest.class,
		FINISHED_LANETest.class, OBJECT_COLLISIONTest.class, SET_ACCTest.class,
		SET_DRIFTTest.class, SET_FINISH_LINETest.class,
		SET_LEFT_BORDERTest.class, SET_MAXVELTest.class, SET_POSTest.class,
		SET_RIGHT_BORDERTest.class, SET_VELTest.class, SET_ZERO_VELTest.class,
		UPDATE_POSTest.class, UPDATE_VELTest.class })
public class AllTests {

}
