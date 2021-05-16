package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {
    Message msg = new Message(1);

    @Test
    public void testsetmsg1() {
        msg.setmsg(1, 1, 1);
        Assert.assertEquals(1, msg.getTargetID());
        Assert.assertEquals(1, msg.getType());
        Assert.assertEquals(1, msg.getTitle());
    }

    @Test
    public void testSetmsg2() {
        msg.setmsg(1, 2, true);
        Assert.assertEquals(1, msg.getTargetID());
        Assert.assertEquals(2, msg.getType());
        Assert.assertTrue(msg.isBoolData());
    }

    @Test
    public void testSetmsg3() {
        msg.setmsg(1, 3);
        Assert.assertEquals(1, msg.getTargetID());
        Assert.assertEquals(3, msg.getType());
    }

    @Test
    public void testSetmsg4() {
        msg.setmsg(1, 4, 1.0, 1.0);
        Assert.assertEquals(1, msg.getTargetID());
        Assert.assertEquals(4, msg.getType());
        Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getxAdress()));
        Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getyAdress()));
    }

    @Test
    public void testSetmsg5() {
        msg.setmsg(1, 5, 1, 971125);
        Assert.assertEquals(1, msg.getTargetID());
        Assert.assertEquals(5, msg.getType());
        Assert.assertEquals(1, msg.getTitle());
        Assert.assertEquals(971125, msg.getC_Number());

    }
}