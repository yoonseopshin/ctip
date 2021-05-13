package Logic;


import org.junit.Assert;
import org.junit.Test;





public class Message_QueueTest {
    private Message message;
    private Message_Queue message_queue;

    @Test
    public void sendMsg() {
        message = new Message(1);
        message_queue = new Message_Queue();
        message.setmsg(1, 1, 1);
        Message_Queue.sendMsg(message);
        Assert.assertEquals(message, message_queue.Connection.poll());
    }

    @Test
    public void dequeue() {
        message = new Message(1);
        message_queue = new Message_Queue();
        message.setmsg(1, 1, 1);
        Message_Queue.sendMsg(message);
        Message_Queue.recivMsg();
        message = (Message) Message_Queue.dequeue(1).poll();
        Assert.assertEquals(1, message.Type);
    }
}