package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_MAXVEL extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_MAXVEL(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && NAT1.instance.has(M) && (M).compareTo(machine.get_vel().apply(Vehicle)) >= 0); */
	public /*@ pure */ boolean guard_SET_MAXVEL( Integer M, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && NAT1.instance.has(M) && (M).compareTo(machine.get_vel().apply(Vehicle)) >= 0);
	}

	/*@ public normal_behavior
		requires guard_SET_MAXVEL(M,Vehicle);
		assignable machine.maxvel;
		ensures guard_SET_MAXVEL(M,Vehicle) &&  machine.get_maxvel().equals(\old((machine.get_maxvel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,M)))))); 
	 also
		requires !guard_SET_MAXVEL(M,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_SET_MAXVEL( Integer M, Integer Vehicle){
		if(guard_SET_MAXVEL(M,Vehicle)) {
			BRelation<Integer,Integer> maxvel_tmp = machine.get_maxvel();

			machine.set_maxvel((maxvel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,M)))));

			System.out.println("SET_MAXVEL executed M: " + M + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer M = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_MAXVEL(M,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
