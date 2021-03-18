package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class WALL_COLLISION extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public WALL_COLLISION(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_vehicles().has(Vehicle) && machine.get_lanes().has(Lane) && !machine.get_collided().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle) && machine.get_width().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle) && machine.get_left_border().domain().has(Lane) && machine.get_right_border().domain().has(Lane) && machine.get_width().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle)); */
	public /*@ pure */ boolean guard_WALL_COLLISION( Integer Lane, Integer Vehicle) {
		return (machine.get_vehicles().has(Vehicle) && machine.get_lanes().has(Lane) && !machine.get_collided().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle) && machine.get_width().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle) && machine.get_left_border().domain().has(Lane) && machine.get_right_border().domain().has(Lane) && machine.get_width().domain().has(Vehicle) && machine.get_posX().domain().has(Vehicle));
	}

	/*@ public normal_behavior
		requires guard_WALL_COLLISION(Lane,Vehicle);
		assignable machine.collided;
		ensures guard_WALL_COLLISION(Lane,Vehicle) &&  machine.get_collided().equals(\old((machine.get_collided().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,(!((machine.get_left_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Vehicle) - new Integer(machine.get_width().apply(Vehicle) / 2))) <= 0 && (machine.get_right_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Vehicle) + new Integer(machine.get_width().apply(Vehicle) / 2))) >= 0)))))))); 
	 also
		requires !guard_WALL_COLLISION(Lane,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_WALL_COLLISION( Integer Lane, Integer Vehicle){
		if(guard_WALL_COLLISION(Lane,Vehicle)) {
			BRelation<Integer,Boolean> collided_tmp = machine.get_collided();

			machine.set_collided((collided_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,(!((machine.get_left_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Vehicle) - new Integer(machine.get_width().apply(Vehicle) / 2))) <= 0 && (machine.get_right_border().apply(Lane)).compareTo(new Integer(machine.get_posX().apply(Vehicle) + new Integer(machine.get_width().apply(Vehicle) / 2))) >= 0)))))));

			System.out.println("WALL_COLLISION executed Lane: " + Lane + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Lane = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_WALL_COLLISION(Lane,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
