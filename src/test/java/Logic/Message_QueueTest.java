package Logic;


import org.junit.Test;





public class Message_QueueTest {
    private Message message;
    private Message_Queue message_queue;

    @Test
    public void sendMsg() {
        message = new Message(1);
        message_queue = new Message_Queue();
        message.setmsg(1, 1, 1);


    }

    @Test
    public void dequeue() {
    }
}