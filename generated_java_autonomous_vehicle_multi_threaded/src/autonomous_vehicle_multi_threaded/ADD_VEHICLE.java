package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class ADD_VEHICLE extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_VEHICLE(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.has(Vehicle) && !machine.get_objects().has(Vehicle) && !machine.get_obstacles().has(Vehicle) && !machine.get_vehicles().has(Vehicle) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT1.instance.has(Max)); */
	public /*@ pure */ boolean guard_ADD_VEHICLE( Integer H, Integer Vehicle, Integer W, Integer X, Integer Y, Integer Max) {
		return (machine.OBJECTS.has(Vehicle) && !machine.get_objects().has(Vehicle) && !machine.get_obstacles().has(Vehicle) && !machine.get_vehicles().has(Vehicle) && NAT.instance.has(W) && NAT.instance.has(H) && INT.instance.has(X) && INT.instance.has(Y) && NAT1.instance.has(Max));
	}

	/*@ public normal_behavior
		requires guard_ADD_VEHICLE(H,Vehicle,W,X,Y,Max);
		assignable machine.vehicles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.drift, machine.vel, machine.acc, machine.maxvel, machine.finished, machine.active, machine.collided;
		ensures guard_ADD_VEHICLE(H,Vehicle,W,X,Y,Max) &&  machine.get_vehicles().equals(\old((machine.get_vehicles().union(new BSet<Integer>(Vehicle))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Vehicle))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Y)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,W)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,H)))))) &&  machine.get_drift().equals(\old((machine.get_drift().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))))) &&  machine.get_vel().equals(\old((machine.get_vel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))))) &&  machine.get_acc().equals(\old((machine.get_acc().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))))) &&  machine.get_maxvel().equals(\old((machine.get_maxvel().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Max)))))) &&  machine.get_finished().equals(\old((machine.get_finished().union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,false)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,true)))))) &&  machine.get_collided().equals(\old((machine.get_collided().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,false)))))); 
	 also
		requires !guard_ADD_VEHICLE(H,Vehicle,W,X,Y,Max);
		assignable \nothing;
		ensures true; */
	public void run_ADD_VEHICLE( Integer H, Integer Vehicle, Integer W, Integer X, Integer Y, Integer Max){
		if(guard_ADD_VEHICLE(H,Vehicle,W,X,Y,Max)) {
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

			machine.set_vehicles((vehicles_tmp.union(new BSet<Integer>(Vehicle))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Vehicle))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,X)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Y)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,W)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,H)))));
			machine.set_drift((drift_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))));
			machine.set_vel((vel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))));
			machine.set_acc((acc_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,0)))));
			machine.set_maxvel((maxvel_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Vehicle,Max)))));
			machine.set_finished((finished_tmp.union(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,false)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,true)))));
			machine.set_collided((collided_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Vehicle,false)))));

			System.out.println("ADD_VEHICLE executed H: " + H + " Vehicle: " + Vehicle + " W: " + W + " X: " + X + " Y: " + Y + " Max: " + Max + " ");
		}
	}

	public void run() {
		while(true) {
			Integer H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Vehicle = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Max = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_ADD_VEHICLE(H,Vehicle,W,X,Y,Max);
			machine.lock.unlock(); // end of critical section
		}
	}
}
