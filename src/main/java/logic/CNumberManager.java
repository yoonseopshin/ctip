package logic;

import java.util.HashMap;

public class CNumberManager {

  //인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
  private HashMap<Integer, CNumber> C_List;
  private HashMap<Integer, CNumber> ch_C_List; //여태까지 보낸 C_넘버를 저장
  private int M_Number;

  public CNumberManager() {
    this.C_List = new HashMap<Integer, CNumber>();
    this.ch_C_List = new HashMap<Integer, CNumber>();
    this.M_Number = 111111;
  }

  public int PopCnumber(int C_Number_t) {
    int r = C_List.get(C_Number_t).getTitle_id();
    C_List.remove(C_Number_t); // key값 C_Number_t인 값 제거
    return r;
  }

  public int CheckCnumber(int C_Number_t) {
    if (M_Number == C_Number_t) {
      return 0;
    } else if (C_List.containsKey(C_Number_t)) { //존재하는 인증번호 true반환
      return 1;
    } else {
      return -1;
    }
  }

  public boolean CheckCnumber2(int C_Number_t) {
    return ch_C_List.containsKey(C_Number_t);
  }

  public void AddchCnumber(CNumber c_number) {
    ch_C_List.put(c_number.getC_Number_t(), c_number);
  }

  public void AddCnumber(CNumber c_number) {
    C_List.put(c_number.getC_Number_t(), c_number);
  }

  public int getM_Number() {
    return M_Number;
  }

  public void setM_Number(int m_Number) {
    M_Number = m_Number;
  }

  public HashMap<Integer, CNumber> getC_List() {
    return C_List;
  }

  public void setC_List(HashMap<Integer, CNumber> c_List) {
    C_List = c_List;
  }

  public HashMap<Integer, CNumber> getCh_C_List() {
    return ch_C_List;
  }

  public void setCh_C_List(HashMap<Integer, CNumber> ch_C_List) {
    this.ch_C_List = ch_C_List;
  }
}

