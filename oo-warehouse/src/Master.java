import java.util.Collection;
import java.util.LinkedList;


public class Master {
	private Master(){
		subscribedListeners = new LinkedList<FrameListener>();
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

	private int speed;
	public RobotScheduler getRobotScheduler() {
		return robots;
	}


	public Belts getBelts() {
		return belts;
	}

	public Inventory getInventory() {
		return inventory;
	}


	public Orders getOrders() {
		return orders;
	}

	public Floor getFloor() {
		return floor;
	}

	public Visualizer getVisualizer() {
		return visualizer;
	}
	private int time;

	private boolean stopped;

	public int getTime(){
		return time;
	}
	/*
	 * used if visualizer wants to implement a way to stop the simulation from the ui
	 * this will cause the main loop to terminate after the call stack containing it resolves
	 */
	public void stop(){
		stopped = true;
		cleanupAll();
	}
	//speed of the simulation relative to real time
	//0 indicates as fast as the computer can go
	
	/*
	 * add a frame listener, so that every time a quantum of time passes it's
	 * onFrame method is called
	 */
	public void subscribe(FrameListener subscriber){
		subscribedListeners.add(subscriber);
	}
	/*
	 * simulate a quantum of time
	 */
	private void runFrame(){
		for(FrameListener l : subscribedListeners){
			if(stopped)break;
			l.onFrame();
		}
		time++;
	}
	//allow modules to do one last thing before they exit
	private void cleanupAll(){
		for(FrameListener l : subscribedListeners){
			l.cleanup();
		}
	}
	public void initializeSimulation(){
		this.speed = 1;
		this.robots = new RobotScheduler();
		this.belts = new Belts();
		this.inventory = new Inventory();
		this.orders = new Orders();
		this.floor = new Floor();
		this.visualizer = new Visualizer();		
		this.time = 0;
		this.stopped = true;
	}
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
	public static void main(String[] args){
		Master.master.initializeSimulation();
		Master.master.startSimulation();
	}
}
