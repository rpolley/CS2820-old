
public class Floor {
	
	public Object[][] layout = new Object[10][10];
	
	public Floor() {
		// Floor layout is hardcoded for now (10x10, can be expanded later).
		// Assuming one robot.
		
		// put belt along left wall
		for(int i = 0; i < 10; i++){
			layout[i][0] = new Belts(i, 0);
		}
		
		// put pick/pack stations at beginning and end of belt
		layout[0][1] = new Pack(0, 1);
		
		layout[9][1] = new Pick(9, 1);
		
		// put 2 3x2 blocks of shelf spaces in middle, separated by highway
		for(int i = 2; i < 4; i++){
			for(int j = 3; j < 6; j++){
				layout[i][j] = new ShelfSpace(i, j);
			}
		}
		
		for(int i = 5; i < 7; i++){
			for(int j = 3; j < 6; j++){
				layout[i][j] = new ShelfSpace(i, j);
			}
		}
		
		// put charger along top wall, in middle
		layout[0][4] = new Charger(0, 4);
		
		// put receiving dock in top right corner
		layout[0][9] = new RecDock(0, 9);
		
		// put highways everywhere else
		for(int i = 0; i < 10; i++){
			for(int j = 0; j < 10; j++){
				if(layout[i][j] == null){
					layout[i][j] = new Highway(i, j);
				}
			}
		}
		
	}
	
}

