package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_ZERO_VEL extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_ZERO_VEL(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && (machine.get_vel().apply(Vehicle)).compareTo(new Integer(0)) < 0); */
	public /*@ pure */ boolean guard_SET_ZERO_VEL( Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && (machine.get_vel().apply(Vehicle)).compareTo(new Integer(0)) < 0);
	}

	/*@ public normal_behavior
		requires guard_SET_ZERO_VEL(Vehicle);
		assignable machine.vel;
		ensures guard_SET_ZERO_VEL(Vehicle) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))))); 
	 also
		requires !guard_SET_ZERO_VEL(Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_SET_ZERO_VEL( Integer Vehicle){
		if(guard_SET_ZERO_VEL(Vehicle)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))));

			System.out.println("SET_ZERO_VEL executed Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_ZERO_VEL(Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
