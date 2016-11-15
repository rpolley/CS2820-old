import javax.swing.*;
import javax.swing.text.html.HTMLDocument.Iterator;

import java.awt.*;
import java.io.*;
import java.util.HashMap;
import java.util.Set;

public class Visualizer implements FrameListner{
    /**
     * @wbp.parser.entryPoint
     */
	//private static final String floorFile = "res/Floor.txt";
	//private static BufferedReader floorReader = readFloor();
	private static Object[][] floor = Master.master.getFloor().layout;
	private static HashMap<Robot,int[]> robots = Master.master.getRobotScheduler.RobotLocs.keySet();
	private static HashMap<Integer,int[]> shelves = Master.master.getRobotScheduler.ShelvesLocs.keySet();
	private HashMap<int[][],JLabel> labelGrid = new HashMap<int[],JLabel>();
	private HashMap<int[][],JLabel> initialSetup = new HashMap<int[],JLabel>();
	private HashMap<Robot, int[]> oldRobots;
	private HashMap<Integer,int[]> oldShelves;
	private static final ImageIcon beltIcon = new ImageIcon("res/Belt.png");
	private static final ImageIcon highwayIcon = new ImageIcon("res/Highway.png");
	private static final ImageIcon pickerIcon = new ImageIcon("res/Picker.png");
	private static final ImageIcon packerIcon = new ImageIcon("res/Packer.png");
	private static final ImageIcon chargerIcon = new ImageIcon("res/Charger.png");
	private static final ImageIcon shelfZoneIcon = new ImageIcon("res/ShelfZone.png");
	private static final ImageIcon robotIcon = new ImageIcon("res/Robot.png");
	private static final ImageIcon robotShelfIcon = new ImageIcon("res/Robot.png");
	private static final ImageIcon shelfIcon = new ImageIcon("res/Shelf.png");
	private JFrame window;
	private JPanel panel; 
	
	public Visualizer(){
		
		oldRobots = robots.clone();
		oldShelves = shelves.clone();
		
		JFrame frame = new JFrame("Warehouse Visualizer");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        
	    JPanel panel = new JPanel();
	    frame.getContentPane().add(panel, BorderLayout.CENTER);
	    panel.setLayout(new GridLayout(floor.length,floor[0].length));
	     
	    initialize();
	     
	    frame.pack();
	    frame.setVisible(true);
	}
	
    private static void initialize() {       
        
        for(int x = 0; x < floor.length; x++){
        	for(int y = 0; y < floor[0].length; y++){
        		Object here = floor[x][y];
        		switch(here.getClass()){
        			case (new Belt().getClass()):
        				labelGrid.put({x,y},newJLabel(beltIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        			case (new Highway().getClass()):
        				labelGrid.put({x,y},newJLabel(highwayIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        			case (new Charger().getClass()):
        				labelGrid.put({x,y},newJLabel(chargerIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        			case (new Pick().getClass()):
        				labelGrid.put({x,y},newJLabel(pickerIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        			case (new Pack().getClass()):
        				labelGrid.put({x,y},newJLabel(packerIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        			case (new ShelfSpace.getClass()):
        				labelGrid.put({x,y},newJLabel(shelfZoneIcon))
        				panel.add(labelGrid.get({x,y}));
        				continue;
        		}
        	}
        }
        
        initalSetup = labelGrid.clone();
    }
    
    private static void placeMovables(){
    	
    	oldRobotIter = oldRobots.keySet().iterator();
    	oldShelfIter = oldShelves.keySet().iterator();
    	while(oldShelfIter.hasNext()){
    		Integer oldShelf = oldShelfIter.next();
    		labelGrid.get(oldShelves.get(oldShelf)).setIcon(initialSetup.get(oldShelf));
    	}
    	while(oldRobotIter.hasNext()){
    		Robot oldRobot = oldRobotIter.next();
    		labelGrid.get({oldRobot.x,oldRobot.y}).setIcon(initialSetup.get({oldRobot.x,oldRobot.y}));    		
    	}
    	
    	Iterator robotIter = robots.keySet().iterator();
    	Iterator shelfIter = shelves.keySet().iterator();
    	while(shelfIter.hasNext()){
    		Integer shelf = shelfIter.next();
    		labelGrid.get(shelves.get(shelf)).setIcon(shelfIcon);
    	}
    	while(robotIter.hasNext()){
    		Robot robot = robotIter.next();
    		if robot.isCarryingShelves(){
    			labelGrid.get({robot.x,robot.y}).setIcon(robotShelfIcon);
    		}else{
    			labelGrid.get({robot.x,robot.y}).setIcon(robotIcon);	
    		}
    	}
    	
    	oldRobots = robots.clone();
    	oldShelves = shelves.clone();
    	
    }
    
    public void onFrame(){
    	placeMovables();
    }
}
