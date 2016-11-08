
public class Test {
	public static void main(String[] args) {
		RobotScheduler test = new RobotScheduler();
		Robot testy = new Robot(5, 5);
		System.out.println(testy.batterylife);
		int[] temp = new int[2];
		temp[0]=testy.x;
		temp[1]=testy.y;
		test.RobotLocs.put(testy, temp);
		temp[0]=3;
		temp[1]=3;
		test.ShelvesLocs.put(0, temp);
		test.RequestQueue.add(0);
		test.MovingRobots();
	}
}
