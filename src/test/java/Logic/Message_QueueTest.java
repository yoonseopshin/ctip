package Logic;

import org.junit.Assert;
import org.junit.Test;

public class Message_QueueTest {
    private Message msg = new Message(DVM.getCurrentID());
    private Message_Queue queue = new Message_Queue();

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
        c.getTitle_List().get(1).AddItem(new Item(20201125));
        msg.setTargetID(1);
        msg.setType(3);
        msg.setTitle(2);
        msg.setC_Number(971026);
        Message_Queue.getMsgQueue().offer(msg);
        Message_Queue.Dequeue();
        Assert.assertEquals(1, c.getCM().CheckCnumber(971026));
    }
}