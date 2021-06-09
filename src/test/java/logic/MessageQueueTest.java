package logic;

import org.junit.Assert;
import org.junit.Test;

import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Queue;

public class MessageQueueTest {

  private Message msg = new Message(DVM.getCurrentID());
  private MessageQueue queue = new MessageQueue();
  private Controller controller = new Controller();

  @Test
  public void testGetIP() {
    try {
      InetAddress local = InetAddress.getLocalHost();
      String ip = local.getHostAddress();
      Assert.assertEquals(ip, queue.getIP());
    } catch (Exception e) {
    }
  }

  /*
    @Test
    public void testMsgReceive() {
      Thread thread = new Thread(() -> queue.msgReceive(1));
      MessageQueue.getStkMsgQueue().clear();
      thread.start();
      msg.setMsg(1, 2, true);
      thread.interrupt();
      queue.start();
      queue.interrupt();
      while (queue.isAlive()) {
      }
      Message rm = MessageQueue.getStkMsgQueue().poll();
      try {
        Assert.assertEquals(msg.getTargetId(), rm.getTargetId());
        Assert.assertEquals(msg.getType(), rm.getType());
        Assert.assertEquals(msg.isBoolData(), rm.isBoolData());
        Assert.assertEquals(msg.getCNumber(), rm.getCNumber());
        Assert.assertEquals(msg.getMyId(), rm.getMyId());
        Assert.assertEquals(msg.getTitle(), rm.getTitle());
        Assert.assertEquals(Double.toString(msg.getXAddress()), Double.toString(rm.getXAddress()));
        Assert.assertEquals(Double.toString(msg.getYAddress()), Double.toString(rm.getYAddress()));
      } catch (NullPointerException e) {
        Assert.assertEquals(0, MessageQueue.getStkMsgQueue().size());
      }
    }
  */
  @Test
  public void testMsgSend() {
    Controller.getTitleList().get(0).addItem(new Item(20201125));
    queue.start();
    msg.setTargetId(1);
    msg.setType(3);
    msg.setTitle(1);
    msg.setCNumber(971125);
    MessageQueue.msgSend(msg);
    while (queue.isAlive()) {
      if (Controller.getCm().checkCNumber(msg.getCNumber()) != -1)
        queue.interrupt();
    }
    Assert.assertEquals(1, Controller.getCm().checkCNumber(msg.getCNumber()));
    Assert.assertEquals(false, Controller.getTitleList().get(msg.getTitle() - 1).checkStock());
    Assert.assertEquals(1, (int) (Controller.getTitleList().get(msg.getTitle() - 1).getHold()));
    Assert.assertEquals(msg.getTitle(), Controller.getCm().popCNumber(msg.getCNumber()));
  }

  @Test
  public void testDequeue() {
    MessageQueue.getStkMsgQueue().clear();
    MessageQueue.getMsgQueue().clear();
    MessageQueue.getLocMsgQueue().clear();
    MessageQueue.getcNMsgQueue().clear();
    Controller c = new Controller();
    msg.setTargetId(1);
    msg.setType(1);
    msg.setTitle(1);
    MessageQueue.getMsgQueue().offer(msg);
    MessageQueue.dequeue();
    Assert.assertEquals(1, MessageQueue.getStkMsgQueue().size());
    msg.setTargetId(1);
    msg.setType(2);
    msg.setTitle(1);
    msg.setBoolData(true);
    MessageQueue.getMsgQueue().offer(msg);
    MessageQueue.dequeue();
    Assert.assertEquals(1, MessageQueue.getStkMsgQueue().size());
    MessageQueue.getMsgQueue().offer(msg);
    MessageQueue.dequeue();
    c.getTitleList().get(1).addItem(new Item(20201125));
    msg.setTargetId(1);
    msg.setType(3);
    msg.setTitle(2);
    msg.setCNumber(971026);
    MessageQueue.getMsgQueue().offer(msg);
    MessageQueue.dequeue();
    Assert.assertEquals(1, c.getCm().checkCNumber(msg.getCNumber()));
    Assert.assertEquals(false, Controller.getTitleList().get(msg.getTitle() - 1).checkStock());
    Assert.assertEquals(1, (int) (Controller.getTitleList().get(msg.getTitle() - 1).getHold()));
    Assert.assertEquals(msg.getTitle(), Controller.getCm().popCNumber(msg.getCNumber()));
  }

  @Test
  public void testGetLoc() {
    MessageQueue.setLoc(5);
    Assert.assertEquals(5, MessageQueue.getLoc());
  }

  @Test
  public void testGetStk() {
    MessageQueue.setStk(5);
    Assert.assertEquals(5, MessageQueue.getStk());
  }

  @Test
  public void testGetCNum() {
    MessageQueue.setCNum(5);
    Assert.assertEquals(5, MessageQueue.getCNum());
  }

  @Test
  public void testGetMsgQueue() {
    Queue<Message> msgQueueT = new LinkedList<>();
    msgQueueT.offer(msg);
    MessageQueue.setMsgQueue(msgQueueT);
    Assert.assertEquals(1, MessageQueue.getMsgQueue().size());
  }

  @Test
  public void testGetStkMsgQueue() {
    Queue<Message> stkMsgQueueT = new LinkedList<>();
    stkMsgQueueT.offer(msg);
    MessageQueue.setStkMsgQueue(stkMsgQueueT);
    Assert.assertEquals(1, MessageQueue.getStkMsgQueue().size());
  }

  @Test
  public void testGetLocMsgQueue() {
    Queue<Message> locMsgQueueT = new LinkedList<>();
    locMsgQueueT.offer(msg);
    MessageQueue.setLocMsgQueue(locMsgQueueT);
    Assert.assertEquals(1, MessageQueue.getLocMsgQueue().size());
  }

  @Test
  public void testGetCnMsgQueue() {
    Queue<Message> cnMsgQueueT = new LinkedList<>();
    cnMsgQueueT.offer(msg);
    MessageQueue.setcNMsgQueue(cnMsgQueueT);
    Assert.assertEquals(1, MessageQueue.getcNMsgQueue().size());
  }
}