package logic;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.Queue;

public class MessageQueue extends Thread {

  private static Queue<Message> msgQueue = new LinkedList<>();
  private static Queue<Message> stkMsgQueue = new LinkedList<>();
  private static Queue<Message> locMsgQueue = new LinkedList<>();
  private static Queue<Message> cNMsgQueue = new LinkedList<>();
  private static int loc = -1;
  private static int stk = 9;
  private static int cNum = -1;

  @Override
  public void run() {
    System.out.print("메시지 수신 시작\n");
    msgReceive(DVM.getCurrentID());
    System.out.print("메시지 수신 종료\n");
  }

  public static String getIP() {
    InetAddress local;
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

  public void msgReceive(int myId) {
    Socket socket = null;                //Client와 통신하기 위한 Socket
    ServerSocket server_socket = null;  //서버 생성을 위한 ServerSocket
    BufferedReader in;            //Client로부터 데이터를 읽어들이기 위한 입력스트림
    int port = myId + 50000;
    PrintWriter printWriter; // 값을 전달할때 사용

    try {
      server_socket = new ServerSocket(port); //서버 소캣 생성
    } catch (IOException e) {
      System.out.println(port + "번 포트 사용 불가");
    }
    try {
      while (!this.isInterrupted()) {
        assert server_socket != null;
        socket = server_socket.accept();  //서버 오픈 ,클라이언트 접속 대기.
        printWriter = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)));
        in = new BufferedReader(new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8));
        String msg = in.readLine();
        if (msg == null || msg.length() == 0)
          throw new NullPointerException();
        String[] temp = msg.split(",");
        //메시지객체로 변환
        Message message = new Message(-1);
        message.translate(Integer.parseInt(temp[0]), Integer.parseInt(temp[1]),
                Integer.parseInt(temp[2]),
                Double.parseDouble(temp[3]), Double.parseDouble(temp[4]), Integer.parseInt(temp[5]),
                Integer.parseInt(temp[6]), Boolean.parseBoolean(temp[7]));
        if (!msgQueue.offer(message))
          throw new NullPointerException();

        printWriter.write("1");
        printWriter.flush(); //메시지 정상 전송을 클라이언트에게 알려줌
        in.close();
        printWriter.close();
        socket.close(); // 소캣을 종료시켜 접속된 클라이언트 종료시킴.
        dequeue();
      }
    } catch (IOException e) {
      System.out.println("서버 메세지수신 오류");
    } finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }

      }
      if (server_socket != null) {
        try {
          server_socket.close();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
      }
      System.out.println("서버 종료");
    }
  }

  public static void msgSend(Message message) {
    Socket socket = null;            //Server와 통신하기 위한 Socket
    BufferedReader in;        //Server로부터 데이터를 읽어들이기 위한 입력스트림
    PrintWriter out;            //서버로 내보내기 위한 출력 스트림
    InetAddress ia;
    int port = message.getTargetId() + 50000;
    try { //서버로 접속하고 인풋스티림을 지정하는 부분
      while (true) {
        ia = InetAddress.getByName(getIP());    //서버로 접속
        socket = new Socket(ia, port);
        in = new BufferedReader(
                new InputStreamReader(socket.getInputStream(), StandardCharsets.UTF_8)); //서버로부터 메시지를 받기위한 버퍼
        out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.UTF_8)));
        //메시지객체 스트링으로 전환
        String msg = message.getMyId() + "," + message.getTargetId() + "," + message.getType() + ","
                + message.getXAddress() + "," + message.getYAddress() + "," + message.getTitle() + ","
                + message.getCNumber() + "," + message.isBoolData();
        out.println(msg);                        //서버로 데이터 전송
        out.flush();                      //서버로 데이터 전송
        String returnMsg = in.readLine();
        if (returnMsg == null || returnMsg.length() == 0)
          throw new NullPointerException();
        //객체 정리하는 부분
        socket.close();
        in.close();
        out.close();
        //서버에서 확인메시지 리시브 및 완료시 브레이크
        if (returnMsg.equals("1")) {
          break;
        }
      }
    } catch (IOException e) {
      System.err.println("서버 접속 오류, 오류 DVM :" + message.getTargetId());
      if (message.getType() == 1) {
        stk--;
        if (stk == stkMsgQueue.size()) {
          dequeue();
        }
      }
      if (message.getType() == 6) {
        cNum--;
        if (cNum == cNMsgQueue.size()) {
          dequeue();
        }
      }
    } finally {
      if (socket != null) {
        try {
          socket.close();
        } catch (IOException e) {
          System.err.println(e.getMessage());
        }
      }
    }
  }

    /*
    1. 재고 확인 요청 - title
    2. 재고 확인 응답 - boolean
    3. 선결제 확인 - boolean
    4. 주소 요청
    5. 주소 응답 - title, c_Number
    6. 음료 판매 확인 - title, Cnumber
    7. 음료 판매 응답 - boolean
    */

  public static void dequeue() {
    while (msgQueue.size() > 0) {
      Message rm = msgQueue.poll();
      if (rm.getType() == 1) {
        Message sm = new Message(DVM.getCurrentID());
        sm.setMsg(rm.getMyId(), 2, Controller.getTitleList().get(rm.getTitle() - 1).checkStock());
        //System.out.println("재고 요청 응답 완료");
      } else if (rm.getType() == 2) {
        if (!stkMsgQueue.offer(rm))
          throw new NullPointerException();
      } else if (rm.getType() == 3) {
        CNumber rc = new CNumber(rm.getTitle(), rm.getMyId());
        rc.setCNumberT(rm.getCNumber());
        Controller.getCm().addCNumber(rc);
        Controller.getTitleList().get(rm.getTitle() - 1).updateStock(1, true);
      } else if (rm.getType() == 4) {
        Message sm = new Message(DVM.getCurrentID());
        sm.setMsg(rm.getMyId(), 5, DVM.getCurrentX(), DVM.getCurrentY());
        //System.out.println("위치 요청 메시지 응답 완료");
      } else if (rm.getType() == 5) {
        if (!locMsgQueue.offer(rm))
          throw new NullPointerException();
      } else if (rm.getType() == 6) {
        Message sm = new Message(DVM.getCurrentID());
        int data = Controller.getCm().checkCNumber(rm.getCNumber());
        sm.setMsg(rm.getMyId(), 7, rm.getCNumber(), data != -1);
      } else if (rm.getType() == 7) {
        if (!cNMsgQueue.offer(rm))
          throw new NullPointerException();
      } else {
        System.out.println("메시지 오류");
      }
    }
    if (stkMsgQueue.size() == stk) {
      int i = 0;
      while (stkMsgQueue.size() > 0) {
        Message stk = stkMsgQueue.poll();
        if (stk.isBoolData()) {
          Message sm = new Message(DVM.getCurrentID());
          sm.setMsg(stk.getMyId(), 4);
          //System.out.println("위치 요청 메시지 전송 완료");
          i++;
        }
      }
      loc = i;
      stk = 9;
    }
    if (locMsgQueue.size() == loc) {
      if (loc != 0) {
        while (locMsgQueue.size() > 0) {
          Message loc = locMsgQueue.poll();
          Controller.getDvmStack()
                  .push(new DVM(loc.getMyId(), loc.getXAddress(), loc.getYAddress()));
          //System.out.println("위치 응답 메시지 수신 완료");
        }
      }
      Controller.getDvmStack().push(new DVM(-1, 0.0, 0.0));
      loc = -1;
    }
    if (cNMsgQueue.size() == cNum) {
      while (cNMsgQueue.size() > 0) {
        Message cn = cNMsgQueue.poll();
        if (!cn.isBoolData()) {
          Controller.getCm().getChCList().remove(cn.getCNumber());
        }
      }
      Controller.getCm().getChCList().put(-1, null);
      cNum = -1;
    }
  }

  public static int getLoc() {
    return loc;
  }

  public static void setLoc(int loc) {
    MessageQueue.loc = loc;
  }

  public static int getStk() {
    return stk;
  }

  public static void setStk(int stk) {
    MessageQueue.stk = stk;
  }

  public static int getCNum() {
    return cNum;
  }

  public static void setCNum(int cNum) {
    MessageQueue.cNum = cNum;
  }

  public static Queue<Message> getMsgQueue() {
    return msgQueue;
  }

  public static void setMsgQueue(Queue<Message> msgQueue) {
    MessageQueue.msgQueue = msgQueue;
  }

  public static Queue<Message> getStkMsgQueue() {
    return stkMsgQueue;
  }

  public static void setStkMsgQueue(Queue<Message> stkMsgQueue) {
    MessageQueue.stkMsgQueue = stkMsgQueue;
  }

  public static Queue<Message> getLocMsgQueue() {
    return locMsgQueue;
  }

  public static void setLocMsgQueue(Queue<Message> locMsgQueue) {
    MessageQueue.locMsgQueue = locMsgQueue;
  }

  public static Queue<Message> getcNMsgQueue() {
    return cNMsgQueue;
  }

  public static void setcNMsgQueue(Queue<Message> cNMsgQueue) {
    MessageQueue.cNMsgQueue = cNMsgQueue;
  }

}
