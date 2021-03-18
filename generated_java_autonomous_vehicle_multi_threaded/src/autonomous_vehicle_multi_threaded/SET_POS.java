package autonomous_vehicle_multi_threaded; 

import eventb_prelude.*;
import Util.Utilities;

public class SET_POS extends Thread{
	/*@ spec_public */ private ref1_kinectic machine; // reference to the machine 

	/*@ public normal_behavior
		requires true;
		assignable \everything;
		ensures this.machine == m; */
	public SET_POS(ref1_kinectic m) {
		this.machine = m;
	}

	/*@ public normal_behavior
		requires true;
 		assignable \nothing;
		ensures \result <==> (machine.get_objects().has(Obj) && INT.instance.has(X) && INT.instance.has(Y)); */
	public /*@ pure */ boolean guard_SET_POS( Integer Obj, Integer X, Integer Y) {
		return (machine.get_objects().has(Obj) && INT.instance.has(X) && INT.instance.has(Y));
	}

	/*@ public normal_behavior
		requires guard_SET_POS(Obj,X,Y);
		assignable machine.posX, machine.posY;
		ensures guard_SET_POS(Obj,X,Y) &&  machine.get_posX().equals(\old((machine.get_posX().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))))) &&  machine.get_posY().equals(\old((machine.get_posY().override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))))); 
	 also
		requires !guard_SET_POS(Obj,X,Y);
		assignable \nothing;
		ensures true; */
	public void run_SET_POS( Integer Obj, Integer X, Integer Y){
		if(guard_SET_POS(Obj,X,Y)) {
			BRelation<Integer,Integer> posX_tmp = machine.get_posX();
			BRelation<Integer,Integer> posY_tmp = machine.get_posY();

			machine.set_posX((posX_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,X)))));
			machine.set_posY((posY_tmp.override(new BRelation<Integer,Integer>(new Pair<Integer,Integer>(Obj,Y)))));

			System.out.println("SET_POS executed Obj: " + Obj + " X: " + X + " Y: " + Y + " ");
		}
	}

	public void run() {
		while(true) {
			Integer Obj = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer X = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			Integer Y = Utilities.someVal(new BSet<Integer>((new Enumerated(1,Utilities.max_integer))));
			machine.lock.lock(); // start of critical section
			run_SET_POS(Obj,X,Y);
			machine.lock.unlock(); // end of critical section
		}
	}
}
