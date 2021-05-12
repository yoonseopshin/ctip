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
                case 2:
                    //상대방에게 재고응답해주는 부분
                case 3:
                    //상대방한테 인증번호 줬음.
                case 4:
                    //주소 정보 가져오는것
                    Message msg4 = new Message(2);
                    msg4.setmsg(message.myID, 5, 3754169, 12707881);
                    msgQueue.offer(msg4);
                case 5:
                    //상대방한테 주소 응답해준거 다시 올 필요 없음
                case 6:
                    //나한테 인증번호 음료 판매했는지 대답해주는것
                    Message msg6 = new Message(2);
                    msg6.setmsg(message.myID, 7, message.Title, true);
                    msgQueue.offer(msg6);
                case 7:
                    //저쪽에 음료 판매했는지 말해주는것
            }
        }
        msgQueue.offer(message);
    }

    public static Message dequeue()
    {
        Message message = msgQueue.poll();
        return message;
    }

}