package logic;

import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;

public class MessageQueueTest {

  private Message msg = new Message(DVM.getCurrentID());
  private MessageQueue queue = new MessageQueue();
  private Controller controller = new Controller();

  public void testRun() {
  }
@Test
  public void testGetIP() {
    try {
      InetAddress local = InetAddress.getLocalHost();
      String ip = local.getHostAddress();
      Assert.assertEquals(ip, queue.getIP());
    } catch (Exception e) { }
  }
@Test
  public void testMsgReceive() {
    queue.run();
    Thread.interrupted();
    msg.setMsg(2,1,true);
    Assert.assertEquals(msg,queue.getStkMsgQueue().poll());
  }

  public void testMsgSend() {
  }

  @Test
  public void testDequeue() {
    Controller c = new Controller();
    c.getTitleList().get(1).addItem(new Item(20201125));
    msg.setTargetId(1);
    msg.setType(3);
    msg.setTitle(2);
    msg.setCNumber(971026);
    MessageQueue.getMsgQueue().offer(msg);
    MessageQueue.dequeue();
    Assert.assertEquals(1, c.getCm().checkCNumber(971026));
  }

  public void testGetLoc() {
  }

  public void testSetLoc() {
  }

  public void testGetStk() {
  }

  public void testSetStk() {
  }

  public void testGetCNum() {
  }

  public void testSetCNum() {
  }

  public void testGetMsgQueue() {
  }

  public void testSetMsgQueue() {
  }

  public void testGetStkMsgQueue() {
  }

  public void testSetStkMsgQueue() {
  }

  public void testGetLocMsgQueue() {
  }

  public void testSetLocMsgQueue() {
  }
}