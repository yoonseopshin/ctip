package logic;

import java.util.ArrayList;
import java.util.Comparator;

public class Title {

  private String name;
  private int price;
  private ArrayList<Item> itemList;
  private int hold;

  public Title(String name, int price) {
    this.name = name;
    this.price = price;
    this.hold = 0;
    this.itemList = new ArrayList<>();
  }

  public void UpdateStock(int id, boolean ifHold) {
    if (id == 1) {
      if (ifHold) {
        this.hold++;
        this.itemList.remove(0);
      } else {
        //error
        System.out.print("재고오류");
      }
    } else if (id == 0) {
      if (ifHold) {
        if (hold <= 0) {
          //error
          System.out.print("재고오류");
        }
        this.hold--;
      } else {
        this.itemList.remove(0);
      }
    }
  }

  public boolean CheckStock() {
    return !(this.itemList.isEmpty());
  }

  public void AddItem(Item item) {
    this.itemList.add(item);
    this.itemList.sort(new Comparator<Item>() {
      @Override
      public int compare(Item o1, Item o2) {
        return o1.getExpirationDate() - o2.getExpirationDate();
      }
    });
  }

  public void DeleteItem(ArrayList<Integer> items) {
    ArrayList<Item> rm = new ArrayList<Item>();
    for (int i = 0; i < items.size(); i++) {
      rm.add(itemList.get(items.get(i)));
    }
    itemList.removeAll(rm);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public ArrayList<Item> getItemList() {
    return itemList;
  }

  public void setItemList(ArrayList<Item> itemList) {
    this.itemList = itemList;
  }

  public Integer getHold() {
    return hold;
  }

  public void setHold(Integer hold) {
    this.hold = hold;
  }
}