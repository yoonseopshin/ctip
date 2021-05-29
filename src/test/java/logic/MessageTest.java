package logic;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

  Message msg = new Message(DVM.getCurrentID());

  @Test
  public void testSetmsg1() {
    msg.setMsg(1, 1, 1);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(1, msg.getType());
    Assert.assertEquals(1, msg.getTitle());
  }

  @Test
  public void testSetmsg2() {
    msg.setMsg(1, 2, true);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(2, msg.getType());
    Assert.assertTrue(msg.isBoolData());
  }

  @Test
  public void testSetmsg3() {
    msg.setMsg(1, 3, 1, 971125);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(3, msg.getType());
    Assert.assertEquals(1, msg.getTitle());
    Assert.assertEquals(971125, msg.getCNumber());
  }

  @Test
  public void testSetmsg4() {
    msg.setMsg(1, 4);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(4, msg.getType());
  }

  @Test
  public void testSetmsg5() {
    msg.setMsg(1, 5, 1.0, 1.0);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(5, msg.getType());
    Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getXAddress()));
    Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getYAddress()));
  }

  @Test
  public void testSetmsg6() {
    msg.setMsg(1, 6, 971125);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(6, msg.getType());
    Assert.assertEquals(971125, msg.getCNumber());
  }

  @Test
  public void testSetmsg7() {
    msg.setMsg(1, 7, 971125, true);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(7, msg.getType());
    Assert.assertEquals(971125, msg.getCNumber());
    Assert.assertTrue(msg.isBoolData());
  }
}