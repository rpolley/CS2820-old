import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RobotScheduler {// implements Time
	// So this is where we have to plan out a robot's path
	// which is gonna be a bit tricky
	public RobotScheduler() {
		RobotLocs = new HashMap<Robot, int[]>();
		RequestQueue = new LinkedList<int[]>();
		ShelvesLocs = new HashMap<Integer, int[]>();
	}
	public void addRobots(){
		Robot robo1 = new Robot(5, 5);
		int[] temp = new int[2];
		temp[0]=robo1.x;
		temp[1]=robo1.y;
		RobotLocs.put(robo1,temp);
	}
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

	/*
	 * public void addRobot() { // whenever we have to add a robot Robot robo1 =
	 * new Robot(5, 5); int[] loc1 = new int[2]; loc1[0] = robo1.x; loc1[1] =
	 * robo1.y; RobotLocs.put(robo1, loc1); }
	 */
	

	public void UpdateRobotLocs(Robot i, int x, int y) {
		RobotLocs.get(i)[0] = x;
		RobotLocs.get(i)[1] = y;
	}

	public void MovingRobots(int requestid) {
		int[] locid = RequestQueue.remove();
		Robot i = closestRobot(locid[0], locid[1]);
		// while shelvex doesnt equal robot x etc. with y.
		if (requestid == 0) {
			int toLocationX = locid[0];
			int toLocationY = locid[1];
			// System.out.println(i.batterylife);
			int robotx = i.x;
			int roboty = i.y;
			while (toLocationX != robotx || toLocationY != roboty) {
				robotx = i.x;
				roboty = i.y;
				// where we move the one robot to the shelve.
				// for each tick of the interface
				// trying to figure out a way to get around
				int check = 0;
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
			if (i.isCarryingShelves()) {
				i.hasShelves = false;
			} else {
				i.hasShelves = true;
			}
			
			
		}

	}

	public Robot closestRobot(int x, int y) {
		for (Robot i : RobotLocs.keySet()) {
			if ((i.inUse == false)) {
				return i;
			}
		}
		return null;
	}
}