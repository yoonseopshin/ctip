package Logic;


import org.junit.Assert;
import org.junit.Test;

public class Message_QueueTest {

  private Message message;
  private Message_Queue queue1, queue2;

  @Test
  public void sendMsg() {
    message = new Message(1);
    queue1 = new Message_Queue();
    queue2 = new Message_Queue();
    message.setmsg(2, 1, 1);
    queue1.MsgReciv(1);
    queue2.MsgSend(message);
    Message_Queue.MsgSend(message);
    Assert.assertEquals(message, queue1.msgQueue.poll());

  }

  @Test
  public void dequeue() {
    /*message = new Message(1);
    message_queue = new Message_Queue();
    message.setmsg(1, 1, 1);
    Message_Queue.MsgReciv(message);
    Message_Queue.recivMsg();
    message = (Message) Message_Queue.dequeue(1).poll();
      assert message != null;*/
      Assert.assertEquals(1, 0);
  }
}