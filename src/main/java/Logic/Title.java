package Logic;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

public class Title {

    private String Name;
    private int Price;
    private ArrayList<Item> Item_List;
    private int Hold;

    public Title(String Name, int Price) {
        this.Name = Name;
        this.Price = Price;
        this.Hold = 0;
        this.Item_List = new ArrayList<>();
    }

    public void UpdateStock(int id, boolean IfHold) {
        if (id == 1) {
            if (IfHold) {
                this.Hold++;
                this.Item_List.remove(0);
            } else {
                //error
                System.out.print("재고오류");
            }
        } else if (id == 0) {
            if (IfHold) {
                if (Hold <= 0) {
                    //error
                    System.out.print("재고오류");
                }
                this.Hold--;
            } else {
                this.Item_List.remove(0);
            }
        }
    }

    public boolean CheckStock() {
        return !(this.Item_List.isEmpty());
    }

    public void AddItem(Item item) {
        this.Item_List.add(item);
        this.Item_List.sort(new Comparator<Item>() {
            @Override
            public int compare(Item o1, Item o2) {
                return o1.getExpiration_Date()-o2.getExpiration_Date();
            }
        });
    }

    public void DeleteItem(ArrayList<Integer> ilist) {
        ArrayList<Item> rm = new ArrayList<Item>();
        for (int i = 0; i < ilist.size(); i++) {
            rm.add(Item_List.get(ilist.get(i)));
        }
        Item_List.removeAll(rm);
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public ArrayList<Item> getItem_List() {
        return Item_List;
    }

    public void setItem_List(ArrayList<Item> item_List) {
        Item_List = item_List;
    }

    public Integer getHold() {
        return Hold;
    }

    public void setHold(Integer hold) {
        Hold = hold;
    }
}