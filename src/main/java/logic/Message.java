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
  void setMsg(int id, int type, int data) {
    targetId = id;
    this.type = type;
    if (this.type == 1) {
      title = data;
      MessageQueue.msgSend(this);
    } else if (this.type == 6) { // 2021.05.21 수정 부분
      cNumber = data;
      MessageQueue.msgSend(this);
    }
  }

  //type2
  void setMsg(int id, int type, boolean data) {
    targetId = id;
    this.type = type;
    if (this.type == 2) {
      boolData = data;
      MessageQueue.msgSend(this);
    }
  }

  //type3
  void setMsg(int id, int type, int data1, int data2) {
    targetId = id;
    this.type = type;
    if (this.type == 3) {
      title = data1;
      cNumber = data2;
      MessageQueue.msgSend(this);
    }
  }

  //type4
  void setMsg(int id, int type) {
    targetId = id;
    this.type = type;
    if (type == 4) {
      MessageQueue.msgSend(this);
    }
  }

  //type5
  void setMsg(int id, int type, double data1, double data2) {
    targetId = id;
    this.type = type;
    if (this.type == 5) {
      xAddress = data1;
      yAddress = data2;
      MessageQueue.msgSend(this);
    }
  }

  //  type7 2021.05.21 수정 부분
  void setMsg(int id, int type, int data1, boolean data2) {
    targetId = id;
    this.type = type;
    if (this.type == 7) {
      cNumber = data1;
      boolData = data2;
      MessageQueue.msgSend(this);
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

  public double getXAddress() {
    return xAddress;
  }

  public void setXAddress(double xAddress) {
    this.xAddress = xAddress;
  }

  public double getYAddress() {
    return yAddress;
  }

  public void setYAddress(double yAddress) {
    this.yAddress = yAddress;
  }

  public int getTitle() {
    return title;
  }

  public void setTitle(int title) {
    this.title = title;
  }

  public int getCNumber() {
    return cNumber;
  }

  public void setCNumber(int cNumber) {
    this.cNumber = cNumber;
  }

  public boolean isBoolData() {
    return boolData;
  }

  public void setBoolData(boolean boolData) {
    this.boolData = boolData;
  }

}