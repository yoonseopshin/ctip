package Logic;

import java.util.*;
import static Logic.DVM.CurrentID;
/**
 *
 */
public class C_Number {

  private int title_id;
  private int DvmID;
  private int C_Number_t;

  public C_Number() {

  }

  public C_Number(int title_id, int id) {
    this.title_id = title_id;
    this.DvmID = id;
  }


  public int CreateCnumber(int title_id, int DvmID) {
    String numStr = "";
    do {
      numStr = randnumber();
    } while (numStr.equals("000000") || numStr.equals("111111"));
    C_Number_t = Integer.parseInt(numStr);
    Message message = new Message(1);
    message.setmsg(DvmID, 4, title_id, C_Number_t);
    return C_Number_t;

  }

  public String randnumber() {

    Random rand = new Random();
    int len = 6;
    String numStr = ""; //난수가 저장될 변수
    String ran = Integer.toString(rand.nextInt(9)+1);  //첫번째 숫자 0이 아님
    numStr += ran;
    numStr += Integer.toString(DvmID-1); // 두번째 자릿수 => DVMID
    for (int i = 2; i < len; i++) {
      //0~9 까지 난수 생성
      ran = Integer.toString(rand.nextInt(10));
      numStr += ran;
    }

    return numStr;
  }


  @Override
  public String toString() {
    return "C_Number [����� :" + title_id + 1 + ", ���Ǳ� id=" + DvmID + ", ������ȣ=" + C_Number_t
        + "]";
  }

  public int getTitle_id() {
    return title_id;
  }

  public void setTitle_id(int title_id) {
    this.title_id = title_id;
  }

  public int getDvmID() {
    return DvmID;
  }

  public void setDvmID(int dvmID) {
    DvmID = dvmID;
  }

  public int getC_Number_t() {
    return C_Number_t;
  }

  public void setC_Number_t(int c_Number_t) {
    C_Number_t = c_Number_t;
  }


}