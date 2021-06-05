package logic;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

  Message msg = new Message(DVM.getCurrentID());

  @Test
  public void testSetMsg1() {
    msg.setMsg(1, 1, 1);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(1, msg.getType());
    Assert.assertEquals(1, msg.getTitle());
  }

  @Test
  public void testSetMsg2() {
    msg.setMsg(1, 2, true);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(2, msg.getType());
    Assert.assertTrue(msg.isBoolData());
  }

  @Test
  public void testSetMsg3() {
    msg.setMsg(1, 3, 1, 971125);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(3, msg.getType());
    Assert.assertEquals(1, msg.getTitle());
    Assert.assertEquals(971125, msg.getCNumber());
  }

  @Test
  public void testSetMsg4() {
    msg.setMsg(1, 4);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(4, msg.getType());
  }

  @Test
  public void testSetMsg5() {
    msg.setMsg(1, 5, 1.0, 1.0);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(5, msg.getType());
    Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getXAddress()));
    Assert.assertEquals(Double.toString(1.0), Double.toString(msg.getYAddress()));
  }

  @Test
  public void testSetMsg6() {
    msg.setMsg(1, 6, 971125);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(6, msg.getType());
    Assert.assertEquals(971125, msg.getCNumber());
  }

  @Test
  public void testSetMsg7() {
    msg.setMsg(1, 7, 971125, true);
    Assert.assertEquals(1, msg.getTargetId());
    Assert.assertEquals(7, msg.getType());
    Assert.assertEquals(971125, msg.getCNumber());
    Assert.assertTrue(msg.isBoolData());
  }
  @Test
  public void testTranslate() {
    Message testMsg = new Message(DVM.getCurrentID());
    msg.translate(1,1,1,1,1,1,1,true);
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(1);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(1);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(1);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(true);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

}