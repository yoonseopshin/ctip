package logic;

import org.junit.Assert;
import org.junit.Test;

public class MessageTest {

  Message msg = new Message(DVM.getCurrentID());

  @Test
  public void testSetMsg1() {
    msg.setMsg(2, 1, 1);
    Message testMsg = new Message(DVM.getCurrentID());
    testMsg.setMyId(msg.getMyId());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(1);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(-1);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(false);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg2() {
    msg.setMsg(2, 2, true);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(2);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(-1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(-1);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(true);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg3() {
    msg.setMsg(2, 3, 1, 971125);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(3);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(971125);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(false);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg4() {
    msg.setMsg(2, 4);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(4);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(-1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(-1);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(false);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg5() {
    msg.setMsg(2, 5, 1.0, 1.0);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(5);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(1.0);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(1.0);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(-1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(-1);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(false);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg6() {
    msg.setMsg(2, 6, 971125);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(6);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(-1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(971125);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(false);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testSetMsg7() {
    msg.setMsg(2, 7, 971125, true);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
    Assert.assertEquals(testMsg.getTargetId(),msg.getTargetId());
    testMsg.setType(7);
    Assert.assertEquals(testMsg.getType(),msg.getType());
    testMsg.setXAddress(-1);
    Assert.assertEquals(testMsg.getXAddress(),msg.getXAddress(),1);
    testMsg.setYAddress(-1);
    Assert.assertEquals(testMsg.getYAddress(),msg.getYAddress(),1);
    testMsg.setTitle(-1);
    Assert.assertEquals(testMsg.getTitle(),msg.getTitle());
    testMsg.setCNumber(971125);
    Assert.assertEquals(testMsg.getCNumber(),msg.getCNumber());
    testMsg.setBoolData(true);
    Assert.assertEquals(testMsg.isBoolData(),msg.isBoolData());
  }

  @Test
  public void testTranslate() {
    msg.translate(1,2,1,1,1,1,1,true);
    Message testMsg = new Message(DVM.getCurrentID());
    Assert.assertEquals(testMsg.getMyId(),msg.getMyId());
    testMsg.setTargetId(2);
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