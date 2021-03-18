package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_DRIFT extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_DRIFT(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D)); */
	public /*@ pure */ boolean guard_SET_DRIFT( Integer D, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && new BSet<Integer>(new Integer(0),new Integer(-1),new Integer(1)).has(D));
	}

	/*@ public normal_behavior
		requires guard_SET_DRIFT(D,Vehicle);
		assignable machine.drift;
		ensures guard_SET_DRIFT(D,Vehicle) &&  machine.get_drift().equals(\old((machine.get_drift().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,D)))))); 
	 also
		requires !guard_SET_DRIFT(D,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_SET_DRIFT( Integer D, Integer Vehicle){
		if(guard_SET_DRIFT(D,Vehicle)) {
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();

			machine.set_drift((drift_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,D)))));

			System.out.println("SET_DRIFT executed D: " + D + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer D = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_DRIFT(D,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
