package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class ADD_OBSTACLE extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public ADD_OBSTACLE(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.OBJECTS.has(Obs) && !machine.get_objects().has(Obs) && !machine.get_vehicles().has(Obs) && !machine.get_obstacles().has(Obs) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(W) && NAT.instance.has(H)); */
	public /*@ pure */ boolean guard_ADD_OBSTACLE( Integer H, Integer Obs, Integer W, Integer X, Integer Y) {
		return (machine.OBJECTS.has(Obs) && !machine.get_objects().has(Obs) && !machine.get_vehicles().has(Obs) && !machine.get_obstacles().has(Obs) && INT.instance.has(X) && INT.instance.has(Y) && NAT.instance.has(W) && NAT.instance.has(H));
	}

	/*@ public normal_behavior
		requires guard_ADD_OBSTACLE(H,Obs,W,X,Y);
		assignable machine.obstacles, machine.objects, machine.posX, machine.posY, machine.width, machine.height, machine.active;
		ensures guard_ADD_OBSTACLE(H,Obs,W,X,Y) &&  machine.get_obstacles().equals(\old((machine.get_obstacles().union(new BSet<Integer>(Obs))))) &&  machine.get_objects().equals(\old((machine.get_objects().union(new BSet<Integer>(Obs))))) &&  machine.get_posX().equals(\old((machine.get_posX().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Y)))))) &&  machine.get_width().equals(\old((machine.get_width().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,W)))))) &&  machine.get_height().equals(\old((machine.get_height().union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,H)))))) &&  machine.get_active().equals(\old((machine.get_active().override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs,true)))))); 
	 also
		requires !guard_ADD_OBSTACLE(H,Obs,W,X,Y);
		assignable \nothing;
		ensures true; */
	public void run_ADD_OBSTACLE( Integer H, Integer Obs, Integer W, Integer X, Integer Y){
		if(guard_ADD_OBSTACLE(H,Obs,W,X,Y)) {
			BSet<Integer> obstacles_tmp = machine.get_obstacles();
			BSet<Integer> objects_tmp = machine.get_objects();
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();
			BRelation<Integer,Integer> width_tmp = machine.get_width();
			BRelation<Integer,Integer> height_tmp = machine.get_height();
			BRelation<Integer,Boolean> active_tmp = machine.get_active();

			machine.set_obstacles((obstacles_tmp.union(new BSet<Integer>(Obs))));
			machine.set_objects((objects_tmp.union(new BSet<Integer>(Obs))));
			machine.set_posX((posX_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,X)))));
			machine.set_posY((posY_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,Y)))));
			machine.set_width((width_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,W)))));
			machine.set_height((height_tmp.union(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obs,H)))));
			machine.set_active((active_tmp.override(new BRelation<Integer,Boolean>(new Pair<Integer,Boolean>(Obs,true)))));

			System.out.println("ADD_OBSTACLE executed H: " + H + " Obs: " + Obs + " W: " + W + " X: " + X + " Y: " + Y + " ");
		}
	}

	public void run() {
		while(true) {
			Integer H = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Obs = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer W = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_ADD_OBSTACLE(H,Obs,W,X,Y);
			machine.lock.unlock(); // end of critical section
		}
	}
}
