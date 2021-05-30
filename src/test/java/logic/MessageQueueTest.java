package logic;

import org.junit.Assert;
import org.junit.Test;

public class MessageQueueTest {

  private Message msg = new Message(DVM.getCurrentID());
  private MessageQueue queue = new MessageQueue();

    /*
    @Test
    public void testMsgSend() {
        Controller c = new Controller();
        c.getTitle_List().get(0).AddItem(new Item(20201125));
        queue.start();
        msg.setTargetID(1);
        msg.setType(5);
        msg.setTitle(1);
        msg.setC_Number(971125);
        Message_Queue.MsgSend(msg);
        queue.interrupt();
        while(queue.isAlive()){};
        Assert.assertEquals(1, c.getCM().CheckCnumber(971125));
    }
    */

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
}