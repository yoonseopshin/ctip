package Logic;

import java.util.*;


public class Title {

    private String Name;
    private int Price;
    private ArrayList<Item> Item_List;
    private Integer Hold;
	
    public Title(String Name, int price) {
    	this.Name = Name;
    	this.Price = Price;
    	this.Hold = 0;
    	this.Item_List = new ArrayList<>();
    }
    public String Name(){
        return this.Name();
    }
    public int Price(){
        return this.Price();
    }
    public ArrayList<Item> Item_List(){
        return this.Item_List;
    }
    
    public void UpdateStock(int i, boolean IfHold) {
    	if(IfHold) {
            this.Hold++;
            this.Item_List.remove(0);
        }
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
    
    public void DeleteItem(ArrayList<Item> ilist) {
    	for(int i=0;i<ilist.size();i++){
    	    Item_List.remove(ilist.get(i));
        }
    }
}