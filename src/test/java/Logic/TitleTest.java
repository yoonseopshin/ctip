package Logic;

import org.junit.Assert;
import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class TitleTest {
    private Title t=new Title("코카콜라",700);
    @Test
    public void addItem() {
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        Assert.assertEquals(5,t.Item_List().size());
    }
    @Test
    public void deleteItem() {
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        ArrayList<Integer> delitemlist = new ArrayList<Integer>();
        delitemlist.add(0);delitemlist.add(1);delitemlist.add(2);
        t.DeleteItem(delitemlist);
        Assert.assertEquals(2,t.Item_List().size());
    }
    @Test
    public void item_List() {
        t.AddItem(new Item(20201025));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        Assert.assertEquals(20201025,t.Item_List().get(0).Expiration_Date());
    }
    @Test
    public void updateStock() {
        t.AddItem(new Item(20201025));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.UpdateStock(1,true);
        Assert.assertEquals(4,t.Item_List().size());
        t.UpdateStock(1,false);
        Assert.assertEquals(3,t.Item_List().size());
        t.UpdateStock(0,true);
        Assert.assertEquals(3,t.Item_List().size());
        t.UpdateStock(0,false);
        Assert.assertEquals(2,t.Item_List().size());
    }
    @Test
    public void checkStock() {
        t.AddItem(new Item(20201025));
        Assert.assertEquals(true,t.CheckStock());
        t.UpdateStock(0,false);
        Assert.assertEquals(false,t.CheckStock());
    }
}