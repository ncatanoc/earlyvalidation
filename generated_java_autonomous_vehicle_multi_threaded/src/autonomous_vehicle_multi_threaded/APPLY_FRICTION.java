package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class APPLY_FRICTION extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public APPLY_FRICTION(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Obj) && NAT.instance.has(Elapsed) && machine.get_lanes().has(Lane) && machine.get_acc().apply(Obj).equals(new Integer(0)) && (new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed))).compareTo(new Integer(0)) >= 0 && (machine.get_vel().apply(Obj)).compareTo(new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed)) > 0); */
	public /*@ pure */ boolean guard_APPLY_FRICTION( Integer Elapsed, Integer Lane, Integer Obj) {
		return (machine.get_vehicles().has(Obj) && NAT.instance.has(Elapsed) && machine.get_lanes().has(Lane) && machine.get_acc().apply(Obj).equals(new Integer(0)) && (new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed))).compareTo(new Integer(0)) >= 0 && (machine.get_vel().apply(Obj)).compareTo(new Integer(machine.get_friction().apply(Lane) * new Integer(5) * Elapsed)) > 0);
	}

	/*@ public normal_behavior
		requires guard_APPLY_FRICTION(Elapsed,Lane,Obj);
		assignable machine.vel;
		ensures guard_APPLY_FRICTION(Elapsed,Lane,Obj) &&  machine.get_vel().equals(\old((machine.get_vel().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(machine.get_vel().apply(Obj) - new Integer(machine.get_friction().apply(Lane) * 5 * Elapsed)))))))); 
	 also
		requires !guard_APPLY_FRICTION(Elapsed,Lane,Obj);
		assignable \nothing;
		ensures true; */
	public void run_APPLY_FRICTION( Integer Elapsed, Integer Lane, Integer Obj){
		if(guard_APPLY_FRICTION(Elapsed,Lane,Obj)) {
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();

			machine.set_vel((vel_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,new Integer(vel_tmp.apply(Obj) - new Integer(machine.get_friction().apply(Lane) * 5 * Elapsed)))))));

			System.out.println("APPLY_FRICTION executed Elapsed: " + Elapsed + " Lane: " + Lane + " Obj: " + Obj + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Elapsed = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_APPLY_FRICTION(Elapsed,Lane,Obj);
			machine.lock.unlock(); // end of critical section
		}
	}
}
