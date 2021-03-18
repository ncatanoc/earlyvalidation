package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_RIGHT_BORDER extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_RIGHT_BORDER(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_lanes().has(Lane) && INT.instance.has(B)); */
	public /*@ pure */ boolean guard_SET_RIGHT_BORDER( Integer B, Integer Lane) {
		return (machine.get_lanes().has(Lane) && INT.instance.has(B));
	}

	/*@ public normal_behavior
		requires guard_SET_RIGHT_BORDER(B,Lane);
		assignable machine.right_border;
		ensures guard_SET_RIGHT_BORDER(B,Lane) &&  machine.get_right_border().equals(\old((machine.get_right_border().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,B)))))); 
	 also
		requires !guard_SET_RIGHT_BORDER(B,Lane);
		assignable \nothing;
		ensures true; */
	public void run_SET_RIGHT_BORDER( Integer B, Integer Lane){
		if(guard_SET_RIGHT_BORDER(B,Lane)) {
			BRelation<Integer,Integer> right_border_tmp = machine.get_right_border();

			machine.set_right_border((right_border_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Lane,B)))));

			System.out.println("SET_RIGHT_BORDER executed B: " + B + " Lane: " + Lane + " ");
		}
	}

	public void run() {
		while(true) {
			Integer B = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_RIGHT_BORDER(B,Lane);
			machine.lock.unlock(); // end of critical section
		}
	}
}
