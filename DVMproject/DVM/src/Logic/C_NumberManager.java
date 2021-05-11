package Logic;

import java.util.HashMap;
import java.util.Random;


public class C_NumberManager extends C_Number {

	public C_NumberManager(int title_id, int id) {
		super(title_id, id);
	}
	public C_NumberManager() {
	}


	//인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
	HashMap<Integer, C_Number> C_List = new HashMap<Integer, C_Number>();

	public void CreateCnumber(int title_id, int DvmID) {
		// TODO implement here
		String numStr = "";
		do {
			numStr = randnumber();
		}while(numStr.equals("000000") || numStr.equals("111111"));
		super.C_Number_t = Integer.parseInt(numStr);
		C_Number cn = new C_Number(title_id, DvmID);
		C_List.put(super.C_Number_t, cn);
		//sendMessage()

	}

	public String randnumber() {

		Random rand = new Random();
		int len = 6;
		String numStr = ""; //난수가 저장될 변수
		for(int i=0;i<len;i++) {
			//0~9 까지 난수 생성
			String ran = Integer.toString(rand.nextInt(10));
			numStr += ran;
		}

		return numStr;
	}


	public void PopCnumber(int C_Number_t) {
		C_List.remove(C_Number_t); // key값 C_Number_t인 값 제거
	}


	public int CheckCnumber(int C_Number_t) {
		if(super.M_Number == C_Number_t) {
			return 0;
		}else if(C_List.containsKey(C_Number_t)) { //존재하는 인증번호 true반환
			return 1;
		}else {
			return -1;
		}

	}

	@Override
	public String toString() {
		return "C_Number [음료명 :"+title_id+1 + ", 자판기 id=" + super.DvmID + ", 인증번호=" + super.C_Number_t
				+ "]";
	}

}
