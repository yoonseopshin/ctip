package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    Item item;
    @Test
    public void Testexpiration_Date() {
        item=new Item(20201125);
        Assert.assertEquals(20201125,item.Expiration_Date());
    }
}