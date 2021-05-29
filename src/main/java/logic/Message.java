package logic;

public class Message {

  // 타입 1=재고확인요청, 타입2 = 재고응답, 타입3 = 주소 요청, 타입4= 주소 응답, 타입 5=인증번호 전송
  private int myId = -1;
  private int targetId = -1;
  private int type = -1;
  private double xAddress = -1;
  private double yAddress = -1;
  private int title = -1;
  private int cNumber = -1;
  private boolean boolData = false;

  Message(int myId) {
    this.myId = myId;
  }

  public void translate(int myId, int targetId, int type, double xAddress,
      double yAddress, int title, int cNumber, boolean boolData) {
    this.myId = myId;
    this.targetId = targetId;
    this.type = type;
    this.xAddress = xAddress;
    this.yAddress = yAddress;
    this.title = title;
    this.cNumber = cNumber;
    this.boolData = boolData;
  }

  //type1, 6
  void setmsg(int id, int type, int data) {
    targetId = id;
    this.type = type;
    if (this.type == 1) {
      title = data;
      MessageQueue.MsgSend(this);
    }
    // 2021.05.21 수정 부분
    else if (this.type == 6) {
      cNumber = data;
      MessageQueue.MsgSend(this);
    }
  }

  //type2
  void setmsg(int id, int type, boolean data) {
    targetId = id;
    this.type = type;
    if (this.type == 2) {
      boolData = data;
      MessageQueue.MsgSend(this);
    }
  }

  //type3
  void setmsg(int id, int type, int data1, int data2) {
    targetId = id;
    this.type = type;
    if (this.type == 3) {
      title = data1;
      cNumber = data2;
      MessageQueue.MsgSend(this);
    }
  }

  //type4
  void setmsg(int id, int type) {
    targetId = id;
    this.type = type;
    if (type == 4) {
      MessageQueue.MsgSend(this);
    }
  }

  //type5
  void setmsg(int id, int type, double data1, double data2) {
    targetId = id;
    this.type = type;
    if (this.type == 5) {
      xAddress = data1;
      yAddress = data2;
      MessageQueue.MsgSend(this);
    }
  }

  //  type7 2021.05.21 수정 부분
  void setmsg(int id, int type, int data1, boolean data2) {
    targetId = id;
    this.type = type;
    if (this.type == 7) {
      cNumber = data1;
      boolData = data2;
      MessageQueue.MsgSend(this);
    }
  }

  public int getMyId() {
    return myId;
  }

  public void setMyId(int myId) {
    this.myId = myId;
  }

  public int getTargetId() {
    return targetId;
  }

  public void setTargetId(int targetId) {
    this.targetId = targetId;
  }

  public int getType() {
    return type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public double getxAddress() {
    return xAddress;
  }

  public void setxAddress(double xAddress) {
    this.xAddress = xAddress;
  }

  public double getyAddress() {
    return yAddress;
  }

  public void setyAddress(double yAddress) {
    this.yAddress = yAddress;
  }

  public int getTitle() {
    return title;
  }

  public void setTitle(int title) {
    this.title = title;
  }

  public int getcNumber() {
    return cNumber;
  }

  public void setcNumber(int cNumber) {
    this.cNumber = cNumber;
  }

  public boolean isBoolData() {
    return boolData;
  }

  public void setBoolData(boolean boolData) {
    this.boolData = boolData;
  }

}