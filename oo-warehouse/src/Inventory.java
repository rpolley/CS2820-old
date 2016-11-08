
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Inventory {
	boolean isExist;
	int amount;
	List<Map<String, Object>> inventory = new ArrayList<Map<String, Object>>();
	
    public Inventory(List inventory){
        this.inventory = inventory;
    }
    
    public void Data(){
    	BufferedReader br = null;
		try
		{
			br = new BufferedReader(new FileReader("C:/Users/gaofa/Documents/2016 fall/oop/1Project/atest.txt"));
			//the local path of file
		} catch (FileNotFoundException e)
		{
			e.printStackTrace();
			return;
		}

		String[] columnName =
		{ "Id", "Name", "Amount", "Size"};   //name of columns 
		int i, index;
		String line;
		try
		{
			br.readLine();    //ignore first line
			while ((line = br.readLine()) != null)
			{
				index = 0;
				String[] se = line.split(" ");
				Map<String, Object> item = new HashMap<String, Object>();
				for (i = 0; i < se.length; i++)
				{
					if ("".equals(se[i]))
					{
						continue;
					}
					if (index >= columnName.length)
					{
						continue;
					}
					item.put(columnName[index], se[i]);
					index++;
				}
				
				int amount = Integer.parseInt((String) item
							.get(columnName[2]));
				
				if (amount > 0){
					item.put("InStock", "Yes");
				}
				else{
					item.put("InStock", "No");
				}

				inventory.add(item);
			}
			br.close();
            
			outPutFile();  //wirte the inventory on a file
		} catch (IOException e)
		{
			e.printStackTrace();
		}
    	
        }
	
	
    public void outPutFile(){
    	try
	{
    	   PrintWriter pw = new PrintWriter(new File("C:/Users/gaofa/Documents/2016 fall/oop/1Project/btest.txt"));
    	   pw.println("Id\tName\tAmount\tSize\tInStock");
    	   String[] columnName = { "Id", "Name", "Amount", "Size", "InStock"}; 
			int cIndex;
			for (int i = 0; i < inventory.size(); i++)
			{
				Map<String, Object> st = inventory.get(i);
				cIndex = 0;
				pw.println(st.get(columnName[cIndex++]) + "\t"
					    + st.get(columnName[cIndex++]) + "\t"
					    + st.get(columnName[cIndex++]) + "\t"
					    + st.get(columnName[cIndex++]) + "\t"
					    +st.get("InStock"))  ;
			}
			pw.flush();
			pw.close();
    	   
	  } catch (IOException e)
		{
			e.printStackTrace();
		}
    }
	
  
    
    public void removeItem(String itemName){
    	if (checkExist(itemName) == true){
    		int i;
    		//System.out.println(checkExist(itemName));
        	for (i = 0; i < inventory.size(); i++)
    		{
        		Map<String, Object> removedItem = new HashMap<String, Object>();
        		removedItem = inventory.get(i);
        		
    			if (itemName.equals(removedItem.get("Name").toString())){
    				int a = Integer.parseInt((String) removedItem.get("Amount"));
    				removedItem.put("Amount",a-1);
    				
    				if (a-1 < 1){
    					removedItem.put("InStock","No");
    				}
    			}
    		}
    	}
    	
    	else{
    		System.out.println("Item not exist");
    		checkExist(itemName);
    	}
    	
    	outPutFile();
    		
    }
    
    public void addItem(String itemName){
        //add item to inventory
        boolean InList = false;
    	int i;
    	for (i = 0; i < inventory.size(); i++)
		{
    		Map<String, Object> newItem = new HashMap<String, Object>();
    		newItem = inventory.get(i);
			if (itemName.equals(newItem.get("Name").toString())){
				int a = Integer.parseInt((String) newItem
						.get("Amount"));
				newItem.put("Amount",a+1);
				//newItem.put("Size", null);
				newItem.put("InStock","Yes");
				InList = true;
			}
		}
    	
    	if (InList == false){
    		Map<String, Object> newItem = new HashMap<String, Object>();
    		newItem.put("Id", i+1);
    		newItem.put("Name",itemName);
    		newItem.put("Amount",1);
                newItem.put("Size", null);
    		newItem.put("InStock","Yes");
    		inventory.add(newItem);
    	}
    	
    	outPutFile();
    }
    
    public boolean checkExist(String itemName){
    	/* add code here to check existence, 
    	 * if exist isExist is true
    	 * else false*/
    	for (int i = 0; i < inventory.size(); i++)
		{
    		Map<String, Object> newItem = new HashMap<String, Object>();
    		newItem = inventory.get(i);
			if (itemName.equals(newItem.get("Name").toString())){
				int a = Integer.parseInt((String) newItem.get("Amount"));
				if (a != 0){
					isExist = true;
					break;
				}
				else{
					isExist = false;
				}
			}
			else{
				isExist = false;
				
			}
			
		}
        return isExist;
    }
    
    public static void main(String[] args) {
		List<Map<String, Object>> listA = new ArrayList<Map<String, Object>>();
		/*add code here to read file and insert the item in to listA*/
	    
		Inventory a = new Inventory(listA);
		a.Data();
	        a.addItem("A");
		a.addItem("H");
	        a.removeItem("H");
     }
    
}

/* 
Here is sample file "atest.txt"

ID     Name     Amount   Size   
001   Earpod      8     3*5*2    
002   Cup        58     5*8*3    
003   Box        541     8*6*5   
004   Glove      83     4*3*1    
005   Bottle     193    8*8*10   
006   Lamp       0      9*3*11    
007   Map        98      12*15*1  
008   AA          7     4*3*2     
009   DD         69     5*8*7    
010  RR          280     5*6*3     
011  TT          491     9*10*6    
012  UU         1008      4*5*6    
013  WW          0       6*9*2   
014  PP          73    16*8*22    
*/
