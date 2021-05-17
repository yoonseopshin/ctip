package Logic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.LinkedList;
import java.lang.*;

import static Logic.DVM.*;
import static Logic.Controller.*;

import java.net.Socket;

public class Message_Queue extends Thread {

    static Queue<Message> msgQueue = new LinkedList<>();
    static Queue<Message> StkmsgQueue = new LinkedList<>();
    static Queue<Message> LocmsgQueue = new LinkedList<>();
    public static int loc = -1;
    public static int stk = 9;

    @Override
    public void run() {
        MsgReciv(CurrentID);
    }

    public static String getIP() {
        InetAddress local = null;
        String ip = "-1";
        try {
            local = InetAddress.getLocalHost();
            ip = local.getHostAddress();
            return ip;
        } catch (Exception e) {
            System.out.println(e);
            return ip;
        }
    }

    public static void MsgReciv(int myid) {
        Socket socket = null;                //Client와 통신하기 위한 Socket
        ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
        BufferedReader in = null;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;                //Client로 데이터를 내보내기 위한 출력 스트림
        int port = myid + 50000;
        ObjectInputStream objectInputStream; // 직렬화된 객체를 읽어올때 사용
        PrintWriter printWriter; // 값을 전달할때 사용
        Message message;

        try {
            server_socket = new ServerSocket(port); //서버 소캣 생성
        } catch (IOException e) {
            System.out.println(port + "번 포트 사용 불가");
        } finally {
            if (server_socket != null) {
                try {
                    server_socket.close();
                } catch (IOException e) {
                }
                ;
            }
        }
        try {
            while (true) {
                socket = server_socket.accept();  //서버 오픈 ,클라이언트 접속 대기.
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                objectInputStream = new ObjectInputStream(socket.getInputStream()); // Client 로부터 객체를 읽어오는 역활을 하는 객체를 생성
                message = (Message) objectInputStream.readObject(); // readObject는 object 객체로 불러오기 때문에 형변환
                msgQueue.offer(message); // 전송받은 메시지를 큐에 집어넣기
                if (message.getType() == 1) System.out.print("재고요청메시지 수신됨\n");
                if (message.getType() == 2) System.out.print("재고응답메시지 수신됨\n");
                if (message.getType() == 3) System.out.print("위치요청메시지 수신됨\n");
                if (message.getType() == 4) System.out.print("위치응답메시지 수신됨\n");
                if (message.getType() == 5) System.out.print("인증번호메시지 수신됨\n");
                printWriter.write("1");
                printWriter.flush();//메시지 정상 전송을 클라이언트에게 알려줌
                socket.close();// 소캣을 종료시켜 접속된 클라이언트 종료시킴.
                Dequeue();
                if (Thread.interrupted()) {
                    break;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            //System.out.println("서버 메세지수신 오류");
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                ;
            }
        }
    }

    public static void MsgSend(Message message) {
        Socket socket = null;            //Server와 통신하기 위한 Socket
        BufferedReader in = null;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;            //서버로 내보내기 위한 출력 스트림
        InetAddress ia = null;
        int port = message.getTargetID() + 50000;
        try
        //서버로 접속하고 인풋스티림을 지정하는 부분
        {
            while (true) {
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
                break;
            }
        } catch (IOException e) {
            System.err.println("서버 접속 오류, 오류 DVM :" + message.getMyID());
            if (message.getType() == 1) {
                stk--;
                Dequeue();
            }
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }
                ;
            }
        }
    }

    public static void Dequeue() {
        while (msgQueue.size() > 0) {
            Message rm = msgQueue.poll();
            if (rm.getType() == 1) {
                Message sm = new Message(CurrentID);
                sm.setmsg(rm.getMyID(), 2, Title_List.get(rm.getTitle() - 1).CheckStock());
                System.out.print("재고요청응답완료\n");
            }
            if (rm.getType() == 2) {
                StkmsgQueue.offer(rm);
            }
            if (rm.getType() == 3) {
                Message sm = new Message(CurrentID);
                sm.setmsg(rm.getMyID(), 4, CurrentX, CurrentY);
                System.out.print("위치 요청 메시지 응답 완료\n");
            }
            if (rm.getType() == 4) {
                LocmsgQueue.offer(rm);
            }
            if (rm.getType() == 5) {
                C_Number rc = new C_Number(rm.getTitle(), rm.getMyID());
                rc.setC_Number_t(rm.getC_Number());
                CM.AddCnumber(rc);
                Title_List.get(rm.getTitle() - 1).UpdateStock(1, true);
            }
        }
        if (StkmsgQueue.size() == stk) {
            loc = 0;
            while (StkmsgQueue.size() > 0) {
                Message stk = StkmsgQueue.poll();
                if (stk.isBoolData()) {
                    Message sm = new Message(CurrentID);
                    sm.setmsg(stk.getMyID(), 3);
                    loc++;
                }
            }
            System.out.print("위치 요청 메시지 전송완료\n");
            stk = 9;
        }
        if (LocmsgQueue.size() == loc) {
            if (loc == 0) {
                DVMStack.push(new DVM(-1, 0.0, 0.0));
            } else {
                while (LocmsgQueue.size() > 0) {
                    Message loc = LocmsgQueue.poll();
                    DVMStack.push(new DVM(loc.getMyID(), loc.getxAdress(), loc.getyAdress()));
                }
                DVMStack.push(new DVM(-1, 0.0, 0.0));
            }
            loc = -1;
        }
    }
}
