package DVM;

import java.util.HashMap;

public class C_NumberManager extends C_Number {

	public C_NumberManager(Title ti, int id) {
		super(ti, id);
	}
	public C_NumberManager() {
	}
	
	
	//인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결
	HashMap<Integer, C_Number> C_List = new HashMap<Integer, C_Number>();
    
    public void CreateCnumber(Title ti, int DvmID) {
        // TODO implement here
    	C_Number_t =(int)(Math.random()*100000 + 1);  //인증번호 생성
    	C_Number cn = new C_Number(ti, DvmID);
    	C_List.put(C_Number_t, cn);
    	//sendMessage()
    	
    }

    public void PopCnumber(int C_Number_t) {
        C_List.remove(C_Number_t); // key값 C_Number_t인 값 제거
    }

   
    public boolean CheckCnumber(int C_Number_t) {
       return C_List.containsKey(C_Number_t); //값 존재시 true반환
    }
    
    

}
