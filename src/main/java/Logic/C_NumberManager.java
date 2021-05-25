package Logic;

import java.util.HashMap;

public class C_NumberManager {
    //인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
    private HashMap<Integer, C_Number> C_List;
    private HashMap<Integer, C_Number> ch_C_List; //여태까지 보낸 C_넘버를 저장
    private int M_Number;

    public C_NumberManager() {
        this.C_List = new HashMap<Integer, C_Number>();
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
    public int CheckCnumber2(int C_Number_t){
        Message msg = new Message(DVM.getCurrentID());
        msg.setmsg(0,6,C_Number_t);
        //메세지에서 이미 사용된 번호면 1(사용 o)
        //아직 사용되지 않은 번호면 0(사용 x)
        if (C_Number_t == 1){
            return 1;
        }else
            return 0;
    }

    public void AddchCnumber(C_Number c_number) {
        ch_C_List.put(c_number.getC_Number_t(), c_number);
    }

    public void AddCnumber(C_Number c_number) {
        C_List.put(c_number.getC_Number_t(), c_number);
    }

    public int getM_Number() {
        return M_Number;
    }

    public void setM_Number(int m_Number) {
        M_Number = m_Number;
    }

    public HashMap<Integer, C_Number> getC_List() {
        return C_List;
    }

    public void setC_List(HashMap<Integer, C_Number> c_List) {
        C_List = c_List;
    }
}


