package logic;

public class Payment {

  private int titleId;
  private int DVMId;
  private String errorLog;

  Payment(int titleId, int DVMId) {
    this.titleId = titleId;
    this.DVMId = DVMId;
  }

  public int cardPay(boolean payConfirm) {
    if (DVMId > 0) { //카드결제 성공
      if (payConfirm) { //test용으로 성공이라 가정
        System.out.println("카드결제 선결제 성공");
        CNumber Cnumber = new CNumber(titleId, DVMId);
        return Cnumber.createCNumber();
      } else {
        this.errorLog = "카드결제 선결제 실패";
        return -3;
      }
    } else {
      if (payConfirm) {
        System.out.println("카드결제 성공"); //test용으로 성공이라 가정
        return 0;
      } else {
        this.errorLog = "카드결제 실패";
        return -3;
      }
    }
  }

  public int smartPay(boolean payConfirm) {
    if (DVMId > 0) { //카드결제 성공
      if (payConfirm) { //test용으로 성공이라 가정
        System.out.println("간편결제 선결제 성공");
        CNumber cNumber = new CNumber(titleId, DVMId);
        return cNumber.createCNumber();
      } else {
        this.errorLog = "간편결제 선결제 실패";
        return -3;
      }
    } else {
      if (payConfirm) {
        System.out.println("간편결제 성공"); //test용으로 성공이라 가정
        return 0;
      } else {
        this.errorLog = "간편결제 실패";
        return -3;
      }
    }
  }

  public void init() {
    this.titleId = -1;
    this.DVMId = -1;
    this.errorLog = "";
  }

  public int getTitleId() {
    return titleId;
  }

  public void setTitleId(int titleId) {
    this.titleId = titleId;
  }

  public int getDVMId() {
    return DVMId;
  }

  public void setDVMId(int DVMId) {
    this.DVMId = DVMId;
  }

  public String getErrorLog() {
    return errorLog;
  }

  public void setErrorLog(String errorLog) {
    this.errorLog = errorLog;
  }

}