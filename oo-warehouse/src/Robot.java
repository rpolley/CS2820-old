


public class Robot extends RobotScheduler {
	int x;
	int y;
	boolean hasShelves = false;
	boolean inUse = false;
	double batterylife = 1.0;

	public Robot(int startingx, int startingy) {
		this.x = startingx;
		this.y = startingy;
		hasShelves = false;
		inUse=false;
		int[] temp = new int[2];
		temp[0]=startingx;
		temp[1]=startingy;
		//RobotLocs.put(this, temp);
	}

	public boolean isCarryingShelves(){
		if (this.hasShelves) {
			return true;
		}
		return false;
	}

	// Makes sure to only move the robot 1 space at a time, and if it does only
	// move one space go ahead and do it.
	public void move(int toX, int toY) {
		if (((x - toX) + (y - toY) == 1) || ((x - toX) + (y - toY) == -1)) {
			if (isLegalMove(toX, toY)) {
				// unfinished
				int[] newloc = new int[2];
				newloc[0] = toX;
				newloc[1] = toY;
				this.x=toX;
				this.y=toY;
				RobotLocs.remove(this);
				RobotLocs.put(this, newloc);
				//System.out.println(this.x);
				//System.out.println(RobotLocs.get(this)[0]);
				//System.out.println(newloc[0]+ "  "+newloc[1]);
				batterylife = batterylife - .001;

			}

		} else {
			System.out.println("Too big of a step, the robot has short feet");
		}
	}

	// Will check if there is something that is in the way, will see if it has
	// shelves, if so can't run into a shelve.
	public boolean isLegalMove(int toX, int toY) {
		int[] checkxandy = new int[2];
		checkxandy[0] = toX;
		checkxandy[1] = toY;
		// for right now the only illegal move is when something with a shelf,
		// is runnign into a shelf.
		if (this.hasShelves == true) {
			for (int q : ShelvesLocs.keySet()) {
				if (ShelvesLocs.get(q)[0] == toX && ShelvesLocs.get(q)[1] == toY) {
					return false;
				}
			}
		}
		return true;
	}

	// gets the batteryLife
	public double getBatteryLife(Robot i) {
		return i.batterylife;
	}
	//
}