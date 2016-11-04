import java.util.HashMap;

public class RobotScheduler {// implements Time
	// So this is where we have to plan out a robot's path
	// which is gonna be a bit tricky
	int ordernumber;
	// this is gonna be robots, and their locations
	// robots will each be a assigned an integer
	public HashMap<Robot, int[]> RobotLocs;

	public void addRobot(){
		//whenever we have to add a robot
		Robot robo1 = new Robot(5,5);
		int[] loc1= new int[2];
		loc1[0]=robo1.x;
		loc1[1]=robo1.y;
	RobotLocs.put(robo1,loc1);
	}
	public void RecieveRequest(int finalX, int finalY){
		//first find the closest robot to the shelve #
		Robot close= closestRobot(finalX,finalY);
		while(close.x!=finalX && close.y!=finalY){
			
		}
	}
	public Robot closestRobot(int shelvex, int shelvey){
		for(Robot i: RobotLocs.keySet()){
			if((i.inUse == false)){
				return i;
			}
		}
		return null;
	}
}
  