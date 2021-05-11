package Logic;

import java.util.HashMap;

public class C_NumberManager extends C_Number {

	public C_NumberManager(Title ti, int id) {
		super(ti, id);
	}
	public C_NumberManager() {
	}


	//인증번호와 C_number(title과 dvmID를 가지고 있음)를 해쉬맵으로 연결.
	HashMap<Integer, C_Number> C_List = new HashMap<Integer, C_Number>();

	public void CreateCnumber(Title ti, int DvmID) {
		// TODO implement here
		String numStr = "";
		do {
			numStr = randnumber();
		}while(numStr.equals("000000") || numStr.equals("111111"));
		super.C_Number_t = Integer.parseInt(numStr);
		C_Number cn = new C_Number(ti, DvmID);
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


	public void PopCnumber(int num) {
		C_List.remove(num); // key값 C_Number_t인 값 제거
	}


	public int CheckCnumber(int num) {
		if(super.M_Number == num) {
			return 0;
		}else if(C_List.containsKey(num)) { //존재하는 인증번호 true반환
			return 1;
		}else {
			return 2;
		}

	}

	@Override
	public String toString() {
		return "C_Number [음료명 :"+ti.Name + ", 자판기 id=" + DvmID + ", 인증번호=" + C_Number_t
				+ "]";
	}

}
