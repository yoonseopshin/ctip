package Logic;

import java.util.HashMap;

public class C_NumberManager {

    public C_NumberManager() {
    }

    //인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
    private HashMap<Integer, C_Number> C_List = new HashMap<Integer, C_Number>();
    private int M_Number = 111111;

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


