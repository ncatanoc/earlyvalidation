package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class DELETE_VEHICLE extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public DELETE_VEHICLE(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> machine.get_vehicles().has(Vehicle) && !machine.get_obstacles().has(Vehicle); */
	public /*@ pure */ boolean guard_DELETE_VEHICLE( Integer Vehicle) {
		return machine.get_vehicles().has(Vehicle) && !machine.get_obstacles().has(Vehicle);
	}

	/*@ public normal_behavior
		requires guard_DELETE_VEHICLE(Vehicle);
		assignable machine.vehicles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.drift, machine.vel, machine.acc, machine.maxvel, machine.finished, machine.active, machine.collided;
		ensures guard_DELETE_VEHICLE(Vehicle) &&  machine.get_vehicles().equals(\old(machine.get_vehicles().difference(new BSet<Integer>(Vehicle)))) &&  machine.get_objects().equals(\old(machine.get_objects().difference(new BSet<Integer>(Vehicle)))) &&  machine.get_posX().equals(\old(machine.get_posX().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_posY().equals(\old(machine.get_posY().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_width().equals(\old(machine.get_width().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_height().equals(\old(machine.get_height().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_drift().equals(\old(machine.get_drift().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_vel().equals(\old(machine.get_vel().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_acc().equals(\old(machine.get_acc().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_maxvel().equals(\old(machine.get_maxvel().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_finished().equals(\old(machine.get_finished().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_active().equals(\old(machine.get_active().domainSubtraction(new BSet<Integer>(Vehicle)))) &&  machine.get_collided().equals(\old(machine.get_collided().domainSubtraction(new BSet<Integer>(Vehicle)))); 
	 also
		requires !guard_DELETE_VEHICLE(Vehicle);
		assignable \nothing;
		ensures true; */
	public void run_DELETE_VEHICLE( Integer Vehicle){
		if(guard_DELETE_VEHICLE(Vehicle)) {
			BSet<Integer> vehicles_tmp = machine.get_vehicles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Integer> drift_tmp = machine.get_drift();
			BRelation<Integer,Integer> vel_tmp = machine.get_vel();
			BRelation<Integer,Integer> acc_tmp = machine.get_acc();
			BRelation<Integer,Integer> maxvel_tmp = machine.get_maxvel();
			BRelation<Integer,Boolean> finished_tmp = machine.get_finished();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();
			BRelation<Integer,Boolean> collided_tmp = machine.get_collided();

			machine.set_vehicles(vehicles_tmp.difference(new BSet<Integer>(Vehicle)));
			machine.set_objects(objects_tmp.difference(new BSet<Integer>(Vehicle)));
			machine.set_posX(posX_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_posY(posY_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_width(width_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_height(height_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_drift(drift_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_vel(vel_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_acc(acc_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_maxvel(maxvel_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_finished(finished_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_active(active_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));
			machine.set_collided(collided_tmp.domainSubtraction(new BSet<Integer>(Vehicle)));

			System.out.println("DELETE_VEHICLE executed Vehicle: " + Vehicle + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_DELETE_VEHICLE(Vehicle);
			machine.lock.unlock(); // end of critical section
		}
	}
}
