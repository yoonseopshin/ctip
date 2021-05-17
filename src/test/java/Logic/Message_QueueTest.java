package Logic;

import org.junit.Assert;
import org.junit.Test;

public class Message_QueueTest {
    private Message msg = new Message(1);
    private Message_Queue queue = new Message_Queue();

    @Test
    public void testMsgSend() {
        msg.setTargetID(1);
        msg.setType(2);
        msg.setBoolData(true);
        Message_Queue.MsgSend(msg);
        msg.setType(3);
        Message_Queue.MsgSend(msg);
    }

    @Test
    public void testDequeue() {
        Controller c = new Controller();
        c.getTitle_List().get(0).AddItem(new Item(20201125));
        msg.setTargetID(1);
        msg.setType(5);
        msg.setTitle(1);
        msg.setC_Number(971125);
        Message_Queue.msgQueue.offer(msg);
        Message_Queue.Dequeue();
        Assert.assertEquals(1, c.getCM().CheckCnumber(971125));
    }
}