package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_ACC extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_ACC(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && NAT.instance.has(A)); */
	public /*@ pure */ boolean guard_SET_ACC( Integer A, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && NAT.instance.has(A));
	}

	/*@ public normal_behavior
		requires guard_SET_ACC(A,Vehicle);
		assignable machine.acc;
		ensures guard_SET_ACC(A,Vehicle) &&  machine.get_acc().equals(\old((machine.get_acc().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,A)))))); 
	 also
		requires !guard_SET_ACC(A,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_SET_ACC( Integer A, Integer Vehicle){
		if(guard_SET_ACC(A,Vehicle)) {
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();

			machine.set_acc((acc_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,A)))));

			System.out.println("SET_ACC executed A: " + A + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer A = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_ACC(A,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
