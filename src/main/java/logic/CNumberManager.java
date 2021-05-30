package logic;

import java.util.HashMap;

public class CNumberManager {

  //인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
  private HashMap<Integer, CNumber> cList;
  private HashMap<Integer, CNumber> chCList; //여태까지 보낸 C_넘버를 저장
  private int mNumber;

  public CNumberManager() {
    this.cList = new HashMap<Integer, CNumber>();
    this.chCList = new HashMap<Integer, CNumber>();
    this.mNumber = 111111;
  }

  public int popCNumber(int cNumberT) {
    int r = cList.get(cNumberT).getTitleId();
    cList.remove(cNumberT); // key값 C_Number_t인 값 제거
    return r;
  }

  public int checkCNumber(int cNumberT) {
    if (mNumber == cNumberT) {
      return 0;
    } else if (cList.containsKey(cNumberT)) { //존재하는 인증번호 true반환
      return 1;
    } else {
      return -1;
    }
  }

  public boolean checkCNumber2(int cNumberT) {
    return chCList.containsKey(cNumberT);
  }

  public void addChCNumber(CNumber c_number) {
    chCList.put(c_number.getCNumberT(), c_number);
  }

  public void addCNumber(CNumber c_number) {
    cList.put(c_number.getCNumberT(), c_number);
  }

  public int getMNumber() {
    return mNumber;
  }

  public void setMNumber(int mNumber) {
    this.mNumber = mNumber;
  }

  public HashMap<Integer, CNumber> getCList() {
    return cList;
  }

  public void setCList(HashMap<Integer, CNumber> cList) {
    this.cList = cList;
  }

  public HashMap<Integer, CNumber> getChCList() {
    return chCList;
  }

  public void setChCList(HashMap<Integer, CNumber> chCList) {
    this.chCList = chCList;
  }
}


