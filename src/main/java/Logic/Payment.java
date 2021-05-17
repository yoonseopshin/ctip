package Logic;

import static Logic.DVM.*;

public class Payment {

  private int title_id;
  private int DVMid;
  private String Error_log;

  Payment(int title_id, int DVMid) {
    this.title_id = title_id;
    this.DVMid = DVMid;
  }

  public int CardPay(boolean Pay_confirm) {
    if (DVMid > 0) {//카드결제 성공
      if (Pay_confirm) { //test용으로 성공이라 가정
        System.out.println("카드결제 선결제 성공");
        C_Number Cnumber = new C_Number(title_id, DVMid);
        return Cnumber.CreateCnumber(title_id, CurrentID);
      } else {
        this.Error_log = "카드결제 선결제 실패";
        return -3;
      }
    } else {
      if (Pay_confirm) {
        System.out.println("카드결제 성공"); //test용으로 성공이라 가정
        return 0;
      } else {
        this.Error_log = "카드결제 실패";
        return -3;
      }
    }
  }

  public int SmartPay(boolean Pay_confirm) {
    if (DVMid > 0) {//카드결제 성공
      if (Pay_confirm) { //test용으로 성공이라 가정
        System.out.println("간편결제 선결제 성공");
        C_Number Cnumber = new C_Number(title_id, DVMid);
        return Cnumber.CreateCnumber(title_id, DVMid);
      } else {
        this.Error_log = "간편결제 선결제 실패";
        return -3;
      }
    } else {
      if (Pay_confirm) {
        System.out.println("간편결제 성공"); //test용으로 성공이라 가정
        return 0;
      } else {
        this.Error_log = "간편결제 실패";
        return -3;
      }
    }
  }

  public void init() {
    this.title_id = -1;
    this.DVMid = -1;
    this.Error_log = null;
  }

  public int getTitle_id() {
    return title_id;
  }

  public void setTitle_id(int title_id) {
    this.title_id = title_id;
  }

  public int getDVMid() {
    return DVMid;
  }

  public void setDVMid(int DVMid) {
    this.DVMid = DVMid;
  }

  public String getError_log() {
    return Error_log;
  }

  public void setError_log(String error_log) {
    Error_log = error_log;
  }

}