import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

// test comment

public class RobotScheduler {// implements Time
	// So this is where we have to plan out a robot's path
	// which is gonna be a bit tricky
	public RobotScheduler() {
		RobotLocs = new HashMap<Robot, int[]>();
		RequestQueue = new LinkedList<Integer>();
		ShelvesLocs = new HashMap<Integer, int[]>();
	}

	public int ordernumber;
	// this is gonna be robots, and their locations
	// robots will each be a assigned an integer
	public Queue<Integer> RequestQueue;
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
	public void RecieveShelveRequest(int shelveid) {
		RequestQueue.add(shelveid);
	}
	public void UpdateRobotLocs(Robot i,int x, int y){
		RobotLocs.get(i)[0]=x;
		RobotLocs.get(i)[1]=y;
	}
	public void MovingRobotstoShelf() {
		int shelveid = RequestQueue.remove();
		Robot i = closestRobot(shelveid);
		// while shelvex doesnt equal robot x etc. with y.
		int shelvex = ShelvesLocs.get(shelveid)[0];
		int shelvey = ShelvesLocs.get(shelveid)[1];
		//System.out.println(i.batterylife);
		int robotx = i.x;
		int roboty = i.y;
		while (shelvex != robotx || shelvey != roboty) {
			robotx = i.x;
			roboty = i.y;
			// where we move the one robot to the shelve.
			// for each tick of the interface
			// trying to figure out a way to get around
			int check = 0;
			if (shelvex - robotx < 0) {
				i.move(robotx - 1, roboty);
				// if it isnt a legal move, it wont move that means we can do
				// something else
				//System.out.println(RobotLocs.get(i)[0]+"yolo");
				if (i.isLegalMove(robotx - 1, roboty) == false) {
					check = 0;
				} else {
					check = 1;
				}
			} else if (shelvex - robotx > 0) {
				i.move(robotx + 1, roboty);
				if (i.isLegalMove(robotx + 1, roboty) == false) {
				} else {
					check = 1;
				}
			}

			if (check == 0 && shelvey - roboty < 0) {
				i.move(robotx, roboty - 1);
			} else if (check == 0 && shelvey - roboty > 0) {
				i.move(robotx, roboty + 1);
			}
			check = 0;
			System.out.println("Robot x loc " + robotx + " Robot y loc " + roboty);
			System.out.println("Shelf x loc " + shelvex + " Shelf y loc " + shelvey);

		}

	}

	public Robot closestRobot(int shelveid) {
		for (Robot i : RobotLocs.keySet()) {
			if ((i.inUse == false)) {
				return i;
			}
		}
		return null;
	}
}
