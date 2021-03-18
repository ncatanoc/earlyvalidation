package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class OBJECT_COLLISION extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public OBJECT_COLLISION(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_objects().has(Obj) && machine.get_vehicles().has(Vehicle) && machine.get_posX().domain().has(Obj) && machine.get_posY().domain().has(Obj) && machine.get_posX().domain().has(Obj) && machine.get_width().domain().has(Obj) && machine.get_height().domain().has(Obj) && machine.get_active().domain().has(Obj) && machine.get_posX().domain().has(Vehicle) && machine.get_posY().domain().has(Vehicle) && machine.get_width().domain().has(Vehicle) && machine.get_height().domain().has(Vehicle) && machine.get_collided().domain().has(Vehicle)); */
	public /*@ pure */ boolean guard_OBJECT_COLLISION( Integer Obj, Integer Vehicle) {
		return (machine.get_objects().has(Obj) && machine.get_vehicles().has(Vehicle) && machine.get_posX().domain().has(Obj) && machine.get_posY().domain().has(Obj) && machine.get_posX().domain().has(Obj) && machine.get_width().domain().has(Obj) && machine.get_height().domain().has(Obj) && machine.get_active().domain().has(Obj) && machine.get_posX().domain().has(Vehicle) && machine.get_posY().domain().has(Vehicle) && machine.get_width().domain().has(Vehicle) && machine.get_height().domain().has(Vehicle) && machine.get_collided().domain().has(Vehicle));
	}

	/*@ public normal_behavior
		requires guard_OBJECT_COLLISION(Obj,Vehicle);
		assignable machine.collided, machine.active;
		ensures guard_OBJECT_COLLISION(Obj,Vehicle) &&  machine.get_collided().equals(\old((machine.get_collided().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(Vehicle) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(Vehicle) / 2))) < 0))))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj,(!((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(Vehicle) / 2))) < 0) && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(Vehicle) / 2))) < 0))))))); 
	 also
		requires !guard_OBJECT_COLLISION(Obj,Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_OBJECT_COLLISION( Integer Obj, Integer Vehicle){
		if(guard_OBJECT_COLLISION(Obj,Vehicle)) {
			BRelation<Integer,Boolean> collided_tmp = machine.get_collided();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_collided((collided_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(Vehicle) / 2))) < 0 && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(Vehicle) / 2))) < 0))))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obj,(!((new Integer(machine.get_posX().apply(Obj) - machine.get_posX().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_width().apply(Obj) / 2) + new Integer(machine.get_width().apply(Vehicle) / 2))) < 0) && (new Integer(machine.get_posY().apply(Obj) - machine.get_posY().apply(Vehicle))).compareTo(new Integer(new Integer(machine.get_height().apply(Obj) / 2) + new Integer(machine.get_height().apply(Vehicle) / 2))) < 0))))));

			System.out.println("OBJECT_COLLISION executed Obj: " + Obj + " Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_OBJECT_COLLISION(Obj,Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
