package Logic;

import java.util.LinkedList;
import java.util.Queue;

public class Message_Queue
{
    Queue<Message> msgQueue = new LinkedList<>();
    Queue<Message> Connection = new LinkedList<>();

    public void sendMsg(Message message)
    {
        Connection.offer(message);
    }

    public void recivMsg()
    {
        Message message = new Message();
        message = Connection.poll();
        msgQueue.offer(message);
    }

    public Message dequeue()
    {
        Message message = new Message();
        msgQueue.poll();
        return message;
    }

}