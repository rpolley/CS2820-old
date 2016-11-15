import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.List;

public class Master {
	protected Master(){
		subscribedListeners = new LinkedList<FrameListener>();
		this.speed = 1;
		this.time = 0;
		this.stopped = true;
	}
	//singleton
	public static final Master master = new Master();
	
	private Collection<FrameListener> subscribedListeners;
	private RobotScheduler robots;
	private Belts belts;
	private Inventory inventory;
	private Orders orders;
	private Floor floor;
	private Visualizer visualizer;
	private FrameListener visualizerUpdater;//used to make sure that visualizer draws at the end of the frame
	private List<Map<String,Object>> initialInventory;
	//speed of the simulation relative to real time
	//0 indicates as fast as the computer can go
	private int speed;
	
	/*
	 * @author rpolley
	 */
	public RobotScheduler getRobotScheduler() {
		return robots;
	}

	/*
	 * @author rpolley
	 */
	public Belts getBelts() {
		return belts;
	}
	
	/*
	 * @author rpolley
	 */
	public Inventory getInventory() {
		return inventory;
	}

	/*
	 * @author rpolley
	 */
	public Orders getOrders() {
		return orders;
	}
	
	/*
	 * @author rpolley
	 */
	public Floor getFloor() {
		return floor;
	}
	
	/*
	 * @author rpolley
	 */
	public Visualizer getVisualizer() {
		return visualizer;
	}
	private int time;

	private boolean stopped;
	
	/*
	 * @author rpolley
	 * @return the current number of frames the simulation has been running
	 */
	public int getTime(){
		return time;
	}
	/*
	 * @author rpolley
	 * used if visualizer wants to implement a way to stop the simulation from the ui
	 * this will cause the main loop to terminate after the call stack containing it resolves
	 */
	public void stop(){
		stopped = true;
		cleanupAll();
	}
	
	/*
	 * @author rpolley
	 * add a frame listener, so that every time a quantum of time passes it's
	 * onFrame method is called
	 */
	public void subscribe(FrameListener subscriber){
		subscribedListeners.add(subscriber);
	}
	/*
	 * @author rpolley
	 * simulate a quantum/frame of time
	 */
	private void runFrame(){
		for(FrameListener l : subscribedListeners){
			if(stopped)break;
			l.onFrame();
		}
		//update the visualizer after everything else has run
		visualizerUpdater.onFrame();
		time++;
	}
	/*
	 * @author rpolley
	 * allow modules to do one last thing before they exit
	 */
	private void cleanupAll(){
		for(FrameListener l : subscribedListeners){
			l.cleanup();
		}
	}
	/*
	 * @author rpolley
	 * initialize the simulation, most of the stuff here can't be done in the constructor,
	 * since some of the classes depend on an extant master object
	 */
	public void initializeSimulation(){
		this.initialInventory = new ArrayList();
		this.robots = new RobotScheduler();
		this.belts = new Belts();
		this.inventory = new Inventory(initialInventory);
		this.orders = new Orders();
		this.floor = new Floor(10,10,1);
		this.visualizer = new Visualizer();
		this.visualizerUpdater = this.visualizer;
	}
	/*
	 * @author rpolley
	 * start the main simulation loop 
	 */
	public void startSimulation(){
		stopped = false;
		while(!stopped){
			runFrame();
			try {
				if(speed!=0)
					Thread.sleep(1000/speed);
			} catch (InterruptedException e) {
				//ignore the exception
			}
		}
		
	}
	/*
	 * @author rpolley
	 * entry point for running the simulation
	 */
	public static void main(String[] args){
		Master.master.initializeSimulation();
		Master.master.startSimulation();
	}
}
