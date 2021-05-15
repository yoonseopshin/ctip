package Logic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.LinkedList;

import java.net.Socket;

public class Message_Queue {

  static Queue<Message> msgQueue = new LinkedList<>();
  /*static Queue<Message> Connection = new LinkedList<>();

  public static void sendMsg(Message message) {
    Connection.offer(message);
  }

  public static void recivMsg() {
    Message message = new Message(1);
    message = Connection.poll();
    while (msgQueue.isEmpty()) {
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

  public static Queue dequeue(int type) {
    Queue<Message> temp1 = new LinkedList<>();
    Queue<Message> temp2 = new LinkedList<>();
    Message message = new Message(-1);

    while (!msgQueue.isEmpty()) {
      message = msgQueue.poll();
      if (message.Type == type) {
        temp1.offer(message);
      } else {
        temp2.offer(message);
      }
    }
    while (!temp2.isEmpty()) {
      msgQueue.offer(temp2.poll());
    }
    return temp1;
  }*/

    public static String getIP()
    {
        InetAddress local = null;
        String ip = "-1";
        try
        {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
            return ip;
        }
        catch (Exception e) { System.out.println(e); return ip;}
    }

    public static void MsgReciv(int myid)
    {
        Socket socket = null;                //Client와 통신하기 위한 Socket
        ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
        BufferedReader in = null;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;                //Client로 데이터를 내보내기 위한 출력 스트림
        int port = myid + 50000;
        ObjectInputStream objectInputStream; // 직렬화된 객체를 읽어올때 사용
        PrintWriter printWriter; // 값을 전달할때 사용
        Message message;

        try
        {
            server_socket = new ServerSocket(port); //서버 소캣 생성
        }catch(IOException e) { System.out.println(port+"번 포트 사용 불가"); }
        try
        {
            while(true)
            {
                socket = server_socket.accept();  //서버 오픈 ,클라이언트 접속 대기.
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                objectInputStream = new ObjectInputStream(socket.getInputStream()); // Client 로부터 객체를 읽어오는 역활을 하는 객체를 생성
                 message = (Message)objectInputStream.readObject(); // readObject는 object 객체로 불러오기 때문에 형변환
                msgQueue.offer(message); // 전송받은 메시지를 큐에 집어넣기
                printWriter.write("1");
                printWriter.flush();//메시지 정상 전송을 클라이언트에게 알려줌
                socket.close();// 소캣을 종료시켜 접속된 클라이언트 종료시킴.
            }
        }catch(IOException | ClassNotFoundException e){System.out.println("서버 메세지수신 오류");}
    }

    public static void MsgSend(Message message)
    {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress ia = null;
        int port = message.targetID + 20000;
        try
        //서버로 접속하고 인풋스티림을 지정하는 부분
        {
            while(true)
            {
                ia = InetAddress.getByName(getIP());    //서버로 접속
                socket = new Socket(ia, port);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());//직렬화를 위한 객체 생성
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 메시지를 받기위한 버퍼
                objectOutputStream.writeObject(message); // 전송을 위한 객체 직렬화
                objectOutputStream.flush(); //직렬화를 끝낸 메시지를 타겟 서버로 전송
                String returnMsg = in.readLine();
                //객체 정리하는 부분
                objectOutputStream.close();
                socket.close();
                if (returnMsg == "1")
            }
        } catch(IOException e) {System.err.println("서버 접속 오류, 오류 DVM :"+message.myID);}
    }
}
