package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class UPDATE_VEL extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public UPDATE_VEL(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && machine.get_vel().domain().has(Vehicle) && NAT.instance.has(Elapsed) && (new Integer(machine.get_vel().apply(Vehicle) + machine.get_acc().apply(Vehicle) + new Integer(Elapsed / new Integer(1000)))).compareTo(machine.get_maxvel().apply(Vehicle)) <= 0); */
	public /*@ pure */ boolean guard_UPDATE_VEL( Integer Elapsed, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && machine.get_vel().domain().has(Vehicle) && NAT.instance.has(Elapsed) && (new Integer(machine.get_vel().apply(Vehicle) + machine.get_acc().apply(Vehicle) + new Integer(Elapsed / new Integer(1000)))).compareTo(machine.get_maxvel().apply(Vehicle)) <= 0);
	}

	/*@ public normal_behavior
		requires guard_UPDATE_VEL(Elapsed,Vehicle);
		assignable machine.vel;
		ensures guard_UPDATE_VEL(Elapsed,Vehicle) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,new Integer(machine.get_vel().apply(Vehicle) + machine.get_acc().apply(Vehicle) + new Integer(Elapsed / 1000)))))))); 
	 also
		requires !guard_UPDATE_VEL(Elapsed,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_UPDATE_VEL( Integer Elapsed, Integer Vehicle){
		if(guard_UPDATE_VEL(Elapsed,Vehicle)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,new Integer(vel_tmp.apply(Vehicle) + machine.get_acc().apply(Vehicle) + new Integer(Elapsed / 1000)))))));

			System.out.println("UPDATE_VEL executed Elapsed: " + Elapsed + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_UPDATE_VEL(Elapsed,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
