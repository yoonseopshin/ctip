package Logic;

import java.util.*;


public class Title {

    public String Name;
    public Integer ID;
    public Float Price;
    public ArrayList<Item> Item_List;
    public Integer Hold;
	
    public Title(String Name, Integer ID, Float Price) {
    	this.Name = Name;
    	this.ID = ID;
    	this.Price = Price;
    	this.Hold = 0;
    	this.Item_List = new ArrayList<>();
    }
    
    public void UpdateStock(boolean IfHold) {
    	if(IfHold)
    		this.Hold ++;
    }
    
    public boolean CheckStock() {
    	if (this.Item_List.size() > 0)
    		return true;
    	else
    		return false;
    }
    
    public void AddItem(Item item) {
    	this.Item_List.add(item);
    }
    
    public void DeleteItem(Integer index) {
    	Item_List.remove(index);
    }
}