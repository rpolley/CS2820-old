import java.util.Collection;
import java.util.LinkedList;


public class Master {
	private Master(){
		subscribedListeners = new LinkedList<FrameListener>();
	}
	//singleton
	public static final Master master = new Master();
	
	private Collection<FrameListener> subscribedListeners;
	private Robots robots;
	private Belts belts;
	private Inventory inventory;
	private Orders orders;
	private Floor floor;
	private Visualizer visualizer;

	private int speed;
	private int time;
	public int getTime(){
		return time;
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
			l.onFrame();
		}
		time++;
	}
	public void initializeSimulation(){
		this.robots = new Robots();
		this.belts = new Belts();
		this.inventory = new Inventory();
		this.orders = new Orders();
		this.floor = new Floor();
		this.visualizer = new Visualizer();		
		this.speed = 1;
		this.time = 0;
		
	}
	public void startSimulation(){
		while(true){
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
