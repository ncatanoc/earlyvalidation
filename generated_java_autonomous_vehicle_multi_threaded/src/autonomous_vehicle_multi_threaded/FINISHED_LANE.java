package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class FINISHED_LANE extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public FINISHED_LANE(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && machine.get_posY().domain().has(Vehicle) && machine.get_lanes().has(Lane) && machine.get_finish_line().domain().has(Lane)); */
	public /*@ pure */ boolean guard_FINISHED_LANE( Integer Lane, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && machine.get_posY().domain().has(Vehicle) && machine.get_lanes().has(Lane) && machine.get_finish_line().domain().has(Lane));
	}

	/*@ public normal_behavior
		requires guard_FINISHED_LANE(Lane,Vehicle);
		assignable machine.finished;
		ensures guard_FINISHED_LANE(Lane,Vehicle) &&  machine.get_finished().equals(\old((machine.get_finished().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,((machine.get_posY().apply(Vehicle)).compareTo(machine.get_finish_line().apply(Lane)) > 0))))))); 
	 also
		requires !guard_FINISHED_LANE(Lane,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_FINISHED_LANE( Integer Lane, Integer Vehicle){
		if(guard_FINISHED_LANE(Lane,Vehicle)) {
			BRelation<Integer,Boolean> finished_tmp = machine.get_finished();

			machine.set_finished((finished_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,((machine.get_posY().apply(Vehicle)).compareTo(machine.get_finish_line().apply(Lane)) > 0))))));

			System.out.println("FINISHED_LANE executed Lane: " + Lane + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_FINISHED_LANE(Lane,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
