package Logic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.Queue;
import java.util.LinkedList;

import java.net.Socket;

public class Message_Queue extends Thread {

    private static Queue<Message> msgQueue = new LinkedList<>();
    private static Queue<Message> StkmsgQueue = new LinkedList<>();
    private static Queue<Message> LocmsgQueue = new LinkedList<>();
    private static int loc = -1;
    private static int stk = 9;

    @Override
    public void run() {
        System.out.print("메시지 수신 시작\n");
        MsgReciv(DVM.getCurrentID());
        System.out.print("메시지 수신 종료\n");
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

    public void MsgReciv(int myid) {
        Socket socket = null;                //Client와 통신하기 위한 Socket
        ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
        BufferedReader in = null;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
        PrintWriter out = null;                //Client로 데이터를 내보내기 위한 출력 스트림
        int port = myid + 50000;
        ObjectInputStream objectInputStream; // 직렬화된 객체를 읽어올때 사용
        PrintWriter printWriter; // 값을 전달할때 사용

        try {
            server_socket = new ServerSocket(port); //서버 소캣 생성
        } catch (IOException e) {
            System.out.println(port + "번 포트 사용 불가");
        }
        try {
            while (true) {
                if (this.isInterrupted()) break;
                socket = server_socket.accept();  //서버 오픈 ,클라이언트 접속 대기.
                printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String msg = in.readLine();
                String[] temp = msg.split(",");
                //메시지객체로 변환
                Message message = new Message(-1);
                message.translate(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]), Integer.parseInt(temp[2]),
                        Double.parseDouble(temp[3]), Double.parseDouble(temp[4]), Integer.parseInt(temp[5]),
                        Integer.parseInt(temp[6]), Boolean.parseBoolean(temp[7]));
                msgQueue.offer(message); // 전송받은 메시지를 큐에 집어넣기
                if (message.getType() == 1) System.out.println("재고요청메시지 수신됨");
                if (message.getType() == 2) System.out.println("재고응답메시지 수신됨");
                if (message.getType() == 3) System.out.println("위치요청메시지 수신됨");
                if (message.getType() == 4) System.out.println("위치응답메시지 수신됨");
                if (message.getType() == 5) System.out.println("인증번호메시지 수신됨");
                printWriter.write("1");
                printWriter.flush();//메시지 정상 전송을 클라이언트에게 알려줌
                socket.close();// 소캣을 종료시켜 접속된 클라이언트 종료시킴.
                Dequeue();
            }
        } catch (IOException e) {
            System.out.println("서버 메세지수신 오류");
        } finally {
            if (socket != null) {
                try {
                    socket.close();
                } catch (IOException e) {
                }

            }
            if (server_socket != null) {
                try {
                    server_socket.close();
                } catch (IOException e) {
                }
                ;
            }
            System.out.println("서버 종료");
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
                in = new BufferedReader(new InputStreamReader(socket.getInputStream())); //서버로부터 메시지를 받기위한 버퍼
                out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())));
                //메시지객체 스트링으로 전환
                String msg = message.getMyID() + "," + message.getTargetID() + "," + message.getType() + ","
                        + message.getxAdress() + "," + message.getyAdress() + "," + message.getTitle() + ","
                        + message.getC_Number() + "," + message.isBoolData();
                out.println(msg);                        //서버로 데이터 전송
                out.flush();                      //서버로 데이터 전송
                String returnMsg = in.readLine();
                //객체 정리하는 부분
                socket.close();
                //서버에서 확인메시지 리시브 및 완료시 브레이크
                if (returnMsg.equals("1"))
                    break;
            }
        } catch (IOException e) {
            System.err.println("서버 접속 오류, 오류 DVM :" + message.getTargetID());
            if (message.getType() == 1) {
                stk--;
                if (stk == StkmsgQueue.size()) {
                    Dequeue();
                }
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
                Message sm = new Message(DVM.getCurrentID());
                sm.setmsg(rm.getMyID(), 2, Controller.getTitle_List().get(rm.getTitle() - 1).CheckStock());
                System.out.println("재고 요청 응답 완료");
            } else if (rm.getType() == 2) {
                StkmsgQueue.offer(rm);
            } else if (rm.getType() == 3) {
                Message sm = new Message(DVM.getCurrentID());
                sm.setmsg(rm.getMyID(), 4, DVM.getCurrentX(), DVM.getCurrentY());
                System.out.println("위치 요청 메시지 응답 완료");
            } else if (rm.getType() == 4) {
                LocmsgQueue.offer(rm);
            } else if (rm.getType() == 5) {
                C_Number rc = new C_Number(rm.getTitle(), rm.getMyID());
                rc.setC_Number_t(rm.getC_Number());
                Controller.getCM().AddCnumber(rc);
                Controller.getTitle_List().get(rm.getTitle() - 1).UpdateStock(1, true);
            } else {
                System.out.println("메시지 오류");
            }
        }
        if (StkmsgQueue.size() == stk) {
            int i = 0;
            while (StkmsgQueue.size() > 0) {
                Message stk = StkmsgQueue.poll();
                if (stk.isBoolData()) {
                    Message sm = new Message(DVM.getCurrentID());
                    sm.setmsg(stk.getMyID(), 3);
                    System.out.println("위치 요청 메시지 전송 완료");
                    i++;
                }
            }
            loc = i;
            stk = 9;
        }
        if (LocmsgQueue.size() == loc) {
            if (loc != 0) {
                while (LocmsgQueue.size() > 0) {
                    Message loc = LocmsgQueue.poll();
                    Controller.getDVMStack().push(new DVM(loc.getMyID(), loc.getxAdress(), loc.getyAdress()));
                    System.out.println("위치 응답 메시지 수신 완료");
                }
            }
            Controller.getDVMStack().push(new DVM(-1, 0.0, 0.0));
            loc = -1;
        }
    }

    public static int getLoc() { return loc; }

    public static void setLoc(int loc) { Message_Queue.loc = loc; }

    public static int getStk() { return stk; }

    public static void setStk(int stk) { Message_Queue.stk = stk; }

    public static Queue<Message> getMsgQueue() { return msgQueue; }

    public static void setMsgQueue(Queue<Message> msgQueue) { Message_Queue.msgQueue = msgQueue; }

    public static Queue<Message> getStkmsgQueue() { return StkmsgQueue; }

    public static void setStkmsgQueue(Queue<Message> stkmsgQueue) { StkmsgQueue = stkmsgQueue; }

    public static Queue<Message> getLocmsgQueue() { return LocmsgQueue; }

    public static void setLocmsgQueue(Queue<Message> locmsgQueue) { LocmsgQueue = locmsgQueue; }
}
