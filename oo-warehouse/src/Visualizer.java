import javax.swing.*;
import java.awt.*;
import java.io.*;

public class Visualizer {
    /**
     * @wbp.parser.entryPoint
     */
	private static final String floorFile = "res/Floor.txt";
	private static BufferedReader floorReader = readFloor();
	private static final ImageIcon beltIcon = new ImageIcon("res/Belt.png");
	private static final ImageIcon highwayIcon = new ImageIcon("res/Highway.png");
	private static final ImageIcon pickerIcon = new ImageIcon("res/Picker.png");
	private static final ImageIcon packerIcon = new ImageIcon("res/Packer.png");
	private static final ImageIcon chargerIcon = new ImageIcon("res/Charger.png");
	private static final ImageIcon shelfZoneIcon = new ImageIcon("res/ShelfZone.png");


	
	private static BufferedReader readFloor(){
		try {
			return new BufferedReader(new FileReader(floorFile));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Floor File Not Found");
			return null;
		}
	}
	
    private static void initialize() {
        //Create and set up the window.
        JFrame frame = new JFrame("Warehouse Visualizer");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel panel = new JPanel();
        frame.getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(new GridLayout(10,10));
        
        for(int x = 0; x < 10; x++){
        	String row;
			try {
				row = floorReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Invalid Floor File");
				break;
			}
        	for(int y = 0; y < 10; y++){
        		String floorLocation = String.valueOf(row.charAt(y));
        		switch(floorLocation){
        			case "b":
        				panel.add(new JLabel(beltIcon));
        				continue;
        			case "-":
        				panel.add(new JLabel(highwayIcon));
        				continue;
        			case "c":
        				panel.add(new JLabel(chargerIcon));
        				continue;
        			case "i":
        				panel.add(new JLabel(pickerIcon));
        				continue;
        			case "a":
        				panel.add(new JLabel(packerIcon));
        				continue;
        			case "s":
        				panel.add(new JLabel(shelfZoneIcon));
        				continue;
        		}
        		//panel.add(new JLabel("[" + x + "," + y + "]"));
        	}
        }
        
        //Display the window.
        frame.pack();
        frame.setVisible(true);

    }
    
    public static void main(String[] args){
    	initialize();
    }
}
