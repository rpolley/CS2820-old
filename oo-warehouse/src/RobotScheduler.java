import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RobotScheduler implements FrameListener {// implements Time
	/**
	 * @author Jonathan Reinhart
	 */
	public RobotScheduler() {
		Master.master.subscribe(this);
		RobotLocs = new HashMap<Robot, int[]>();
		RequestQueue = new LinkedList<int[]>();
		ShelvesLocs = new HashMap<Integer, int[]>();
	}

	/**
	 * @param doesn't
	 *            not take in anything, this is where we create the select
	 *            number of robots needed
	 * @return doesn't return anything, just creates the robots and sets them to
	 *         initial locations so there is a void return.
	 */
	public void addRobots() {
		Robot robo1 = new Robot(5, 5);
		int[] temp = new int[2];
		temp[0] = robo1.x;
		temp[1] = robo1.y;
		RobotLocs.put(robo1, temp);
	}

	public int part1 = 0;
	public int ordernumber;
	// this is gonna be robots, and their locations
	// robots will each be a assigned an integer
	public Queue<int[]> RequestQueue;
	// this is an list of locations of shelves
	// Add all the shelve locations to this hashmap, because it will be easier
	// if we label each shelve
	// with [x,y] location as the int array.
	public HashMap<Integer, int[]> ShelvesLocs;
	public HashMap<Robot, int[]> RobotLocs;

	public void addRequest(int[] locations) { // or an int as to where the
												// picker/packer is or where
												// loading dock
		RequestQueue.add(locations);
	}

	/**
	 * 
	 * @param requestid
	 *            this id, is up to change but as of right now, i take an order
	 *            request like, item on shelf 2 move to the picker, or pick up
	 *            new inventory at the shipping docks and use shelf 4
	 * @return I don't return anything I update RobotLocs, which is robot
	 *         locations and go off whatever the queue is.
	 * 
	 *         this method is the one moving the robots from their original x,y
	 *         coordinates to pick up a shelf then from that shelf take it
	 *         wherever it needs to go, then return that shelf to its orginal
	 *         location.
	 */
	public void onFrame() {
		MovingRobots();
	}

	public void moveRobot(Robot i, int toX, int toY) {
		int toLocationX = toX;
		int toLocationY = toY;
		// System.out.println(i.batterylife);
		int robotx = i.x;
		int roboty = i.y;
		robotx = i.x;
		roboty = i.y;
		// where we move the one robot to the shelve.
		// for each tick of the interface
		// trying to figure out a way to get around
		int check = 0;
		if(toLocationX=robotx && toLocationY=roboty){
			part1++;
		}
		if (toLocationX - robotx < 0) {
			i.move(robotx - 1, roboty);
			// if it isnt a legal move, it wont move that means we can
			// do
			// something else
			// System.out.println(RobotLocs.get(i)[0]+"yolo");
			if (i.isLegalMove(robotx - 1, roboty) == false) {
				check = 0;
			} else {
				check = 1;
			}
		} else if (toLocationX - robotx > 0) {
			i.move(robotx + 1, roboty);
			if (i.isLegalMove(robotx + 1, roboty) == false) {
			} else {
				check = 1;
			}
		}

		if (check == 0 && toLocationY - roboty < 0) {
			i.move(robotx, roboty - 1);
		} else if (check == 0 && toLocationY - roboty > 0) {
			i.move(robotx, roboty + 1);
		}
		check = 0;
		System.out.println("Robot x loc " + robotx + " Robot y loc " + roboty);
		System.out.println("Shelf x loc " + toLocationX + " Shelf y loc " + toLocationY);
	}

	public void MovingRobots() {
		int[] locid = RequestQueue.remove();
		Robot i = closestRobot(locid[0], locid[1]);
		// while shelvex doesnt equal robot x etc. with y.
		if (part1 == 0) {
			moveRobot(i, locid[0], locid[1]);
			// or onFrame();
			// if (requestid == 0) {
			i.hasShelves==true;
		}
		if (part1 == 1) {
			moveRobot(i, locid[2], locid[3]);
		}
		if(part2==2){
			moveRobot(i, locid[0], locid[1])
		}

		// }

	}

	/**
	 * 
	 * @param x
	 *            closest robot to this combination of x,y coordinates, might be
	 *            changed to accept an int array to make life a bit easier
	 * @param y
	 * @return closest robot to the given x and y.
	 */
	public Robot closestRobot(int x, int y) {
		for (Robot i : RobotLocs.keySet()) {
			if ((i.inUse == false)) {
				return i;
			}
		}
		return null;
	}
}