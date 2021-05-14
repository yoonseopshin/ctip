package Logic;

import java.util.*;


public class Title {

  private String Name;
  private int Price;
  private ArrayList<Item> Item_List;
  private Integer Hold;

  public Title(String Name, int Price) {
    this.Name = Name;
    this.Price = Price;
    this.Hold = 0;
    this.Item_List = new ArrayList<>();
  }

  public String Name() {
    return this.Name;
  }

  public int Price() {
    return this.Price;
  }

  public ArrayList<Item> Item_List() {
    return this.Item_List;
  }

  public void UpdateStock(int id, boolean IfHold) {
    if (id == 1) {
      this.Hold++;
      this.Item_List.remove(0);
    } else if (id == 0) {
      if (IfHold) {
        if (Hold <= 0) {
          //error
        }
        this.Hold--;
      } else {
        this.Item_List.remove(0);
      }
    }
  }

  public boolean CheckStock() {
    if (this.Item_List.size() > 0) {
      return true;
    } else {
      return false;
    }
  }

  public void AddItem(Item item) {
    this.Item_List.add(item);
  }

  public void DeleteItem(ArrayList<Integer> ilist) {
    ArrayList<Item> rm = new ArrayList<Item>();
    for (int i = 0; i < ilist.size(); i++) {
      rm.add(Item_List.get(ilist.get(i)));
    }
    Item_List.removeAll(rm);
  }
}