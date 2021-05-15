package Logic;

import java.io.Serializable;

public class Message implements Serializable {

  // 타입 1=재고확인요청, 타입2 = 재고응답, 타입3 = 주소 요청, 타입4= 인증번호 전송, 타입 5=주소응답
  int myID = -1;
  int targetID = -1;
  int Type = -1;
  double xAdress = -1;
  double yAdress = -1;
  int Title = -1;
  int C_Number = -1;

  boolean boolData = false;

  Message(int myid) {
    myID = myid;
  }

  void setmsg(int id, int type, int data) {
    targetID = id;
    Type = type;
    if (Type == 1 ) {
      Title = data;
      Message_Queue.MsgSend(this);
    }
  }

  void setmsg(int id, int type, boolean data) {
    targetID = id;
    Type = type;
    if (Type == 2) {
      boolData = data;
      Message_Queue.MsgSend(this);
    }
  }

  void setmsg(int id, int type) {
    targetID = id;
    Type = type;
    if(type==3)
    Message_Queue.MsgSend(this);
  }

  void setmsg(int id, int type, int data1, int data2) {
    targetID = id;
    Type = type;
    Title = data1;
    C_Number = data2;
    Message_Queue.MsgSend(this);
  }

  void setmsg(int id, int type, double data1, double data2) {
    targetID = id;
    Type = type;
    xAdress = data1;
    yAdress = data2;
    Message_Queue.MsgSend(this);
  }
}
