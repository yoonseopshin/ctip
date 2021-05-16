package Logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TitleTest {

    private final Title t = new Title("코카콜라", 700);

    @Test
    public void testAddItem() {
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        int ExpectedResult = 5;
        int ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testDeleteItem() {
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        ArrayList<Integer> delitemlist = new ArrayList<>();
        delitemlist.add(0);
        delitemlist.add(1);
        delitemlist.add(2);
        t.DeleteItem(delitemlist);
        int ExpectedResult = 2;
        int ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testItem_List() {
        t.AddItem(new Item(20201025));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        int ExpectedResult = 20201025;
        int ActualResult = t.getItem_List().get(0).getExpiration_Date();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testUpdateStock() {
        t.AddItem(new Item(20201025));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.AddItem(new Item(20201125));
        t.UpdateStock(1, true);
        int ExpectedResult = 1;
        int ActualResult = t.getHold();
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = 4;
        ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
        t.UpdateStock(1, false);
        ExpectedResult = 1;
        ActualResult = t.getHold();
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = 4;
        ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
        t.UpdateStock(0, true);
        ExpectedResult = 0;
        ActualResult = t.getHold();
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = 4;
        ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
        t.UpdateStock(0, false);
        ExpectedResult = 0;
        ActualResult = t.getHold();
        Assert.assertEquals(ExpectedResult, ActualResult);
        ExpectedResult = 3;
        ActualResult = t.getItem_List().size();
        Assert.assertEquals(ExpectedResult, ActualResult);
    }

    @Test
    public void testCheckStock() {
        t.AddItem(new Item(20201025));
        assertTrue(t.CheckStock());
        t.UpdateStock(0, false);
        assertFalse(t.CheckStock());
    }
}