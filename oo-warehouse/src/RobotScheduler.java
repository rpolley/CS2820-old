import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class RobotScheduler {// implements Time
	// So this is where we have to plan out a robot's path
	// which is gonna be a bit tricky
public RobotScheduler(){
	}
	int ordernumber;
	// this is gonna be robots, and their locations
	// robots will each be a assigned an integer
	Queue<Integer> RequestQueue = new LinkedList<Integer>();
	// this is an list of locations of shelves
	// Add all the shelve locations to this hashmap, because it will be easier
	// if we label each shelve
	// with [x,y] location as the int array.
	HashMap<Integer, int[]> ShelvesLocs;
	HashMap<Robot, int[]> RobotLocs;
	/*
	 * public void addRobot() { // whenever we have to add a robot Robot robo1 =
	 * new Robot(5, 5); int[] loc1 = new int[2]; loc1[0] = robo1.x; loc1[1] =
	 * robo1.y; RobotLocs.put(robo1, loc1); }
	 */
	public void RecieveShelveRequest(int shelveid) {
		RequestQueue.add(shelveid);
	}

	public void MovingRobots() {
		int shelveid = RequestQueue.remove();
		Robot i = closestRobot(shelveid);
		// while shelvex doesnt equal robot x etc. with y.
		int shelvex = ShelvesLocs.get(shelveid)[0];
		int shelvey = ShelvesLocs.get(shelveid)[1];
		int robotx = RobotLocs.get(i)[0];
		int roboty = RobotLocs.get(i)[1];
		while (shelvex != robotx && shelvey != roboty) {
			// where we move the one robot to the shelve.
			// for each tick of the interface
			// trying to figure out a way to get around
			int check = 0;
			if (shelvex - robotx < 0) {
				i.move(robotx - 1, roboty);
				// if it isnt a legal move, it wont move that means we can do
				// something else
				if (i.isLegalMove(robotx - 1, roboty) == false) {

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
			System.out.println("Robot x loc " +robotx + "Robot y loc "+roboty);
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