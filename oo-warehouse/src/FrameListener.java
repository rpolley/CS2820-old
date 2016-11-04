/*
 * this interface should be implemented by any class wanting to keep track of the passage of time
 */
public interface FrameListener {
	/*
	 * called every time a amount of time equal to the amount of time a robot
	 * takes to move one grid square passes inside the warehouse simulation
	 * which will be referred to from now on as one quantum of time
	 */
	public void onFrame();
}
