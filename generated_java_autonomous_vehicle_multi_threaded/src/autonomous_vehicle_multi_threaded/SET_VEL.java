package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_VEL extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_VEL(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && NAT.instance.has(Velocity) && (Velocity).compareTo(machine.get_maxvel().apply(Vehicle)) <= 0); */
	public /*@ pure */ boolean guard_SET_VEL( Integer Vehicle, Integer Velocity) {
		return (machine.get_vehicles().has(Vehicle) && NAT.instance.has(Velocity) && (Velocity).compareTo(machine.get_maxvel().apply(Vehicle)) <= 0);
	}

	/*@ public normal_behavior
		requires guard_SET_VEL(Vehicle,Velocity);
		assignable machine.vel;
		ensures guard_SET_VEL(Vehicle,Velocity) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Velocity)))))); 
	 also
		requires !guard_SET_VEL(Vehicle,Velocity);
		assignable \nothing;
		ensures true; */
	public void run_SET_VEL( Integer Vehicle, Integer Velocity){
		if(guard_SET_VEL(Vehicle,Velocity)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Velocity)))));

			System.out.println("SET_VEL executed Vehicle: " + Vehicle + " Velocity: " + Velocity + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Velocity = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_VEL(Vehicle,Velocity);
			machine.lock.unlock(); // end of critical section
		}
	}
}
