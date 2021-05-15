package Logic;

public class Message {

  // 타입 1=재고확인요청(타이틀) 2 = 재고응답(타이틀, 불리안) 3 = 인증번호(타이틀, 인증번호)
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

  void setmsg(int DVMid, int id, int type, String data) {
    myID = id;
    Type = type;
    if (Type == 1 || Type == 6) {
      Title = Integer.parseInt(data);
    }
  }

  void setmsg(int id, int type, String data) {
    targetID = id;
    Type = type;
    if (Type == 2 || Type == 7) {
      boolData = data;
    }
  }

  void setmsg(int id, int type, int intdata, boolean booldata) {
    targetID = id;
    Type = type;
    Title = intdata;
    boolData = booldata;
  }

  void setmsg(int id, int type, int data1, int data2) {
    targetID = id;
    Type = type;
    Title = data1;
    C_Number = data2;


  }

  void setmsg(int id, int type, double data1, double data2) {
    targetID = id;
    Type = type;
    xAdress = data1;
    yAdress = data2;
  }
}

