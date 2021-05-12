package Logic;

import java.util.Queue;
import java.util.LinkedList;

public class Message_Queue
{
    static Queue<Message> msgQueue = new LinkedList<>();
    static Queue<Message> Connection = new LinkedList<>();



    public static void sendMsg(Message message)
    {
        Connection.offer(message);
    }

    public static void recivMsg()
    {
        Message message = new Message(1);
        message = Connection.poll();
        while(msgQueue.isEmpty()) {
            switch (message.Type) {
                case 1:
                    //재고 확인한거 응답온것
                    Message msg1 = new Message(2);
                    msg1.setmsg(message.myID, 2, message.Title, true);
                    msgQueue.offer(msg1);
                    Message msg11 = new Message(3);
                    msg11.setmsg(message.myID, 2, message.Title, true);
                    msgQueue.offer(msg11);
                    Message msg12 = new Message(4);
                    msg12.setmsg(message.myID, 2, message.Title, true);
                    msgQueue.offer(msg12);
                case 2:
                    //상대방에게 재고응답해주는 부분
                case 3:
                    //상대방한테 인증번호 줬음.
                case 4:
                    //주소 정보 가져오는것
                    Message msg4 = new Message(2);
                    msg4.setmsg(message.myID, 5, 37.54169, 127.07881);
                    msgQueue.offer(msg4);
                    Message msg41 = new Message(3);
                    msg41.setmsg(message.myID, 5, 37.54180, 127.0788);
                    msgQueue.offer(msg41);
                    Message msg42 = new Message(4);
                    msg42.setmsg(message.myID, 5, 37.54112, 127.0788);
                    msgQueue.offer(msg42);
                case 5:
                    //상대방한테 주소 응답해준거 다시 올 필요 없음
                case 6:
                    //나한테 인증번호 음료 판매했는지 대답해주는것
                    Message msg6 = new Message(2);
                    msg6.setmsg(message.myID, 7, message.Title, false);
                    msgQueue.offer(msg6);
                case 7:
                    //저쪽에 음료 판매했는지 말해주는것
            }
        }
        msgQueue.offer(message);
    }

    public static Queue dequeue(int type)
    {
        Queue<Message> temp1 = new LinkedList<>();
        Queue<Message> temp2 = new LinkedList<>();
        Message message = new Message(-1);

        while(!msgQueue.isEmpty())
        {
            message = msgQueue.poll();
            if(message.Type == type)
            {
                temp1.offer(message);
            }
            else
                temp2.offer(message);
        }
        while(!temp2.isEmpty())
        {
            msgQueue.offer(temp2.poll());
        }
        return temp1;
    }
}