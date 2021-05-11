package Logic;

import java.util.HashMap;

public class C_NumberManager extends C_Number {

	public C_NumberManager(Title ti, int id) {
		super(ti, id);
	}
	public C_NumberManager() {
	}
	
	
	//������ȣ�� C_number(title�� dvmID�� ������ ����)�� �ؽ������� ����
	HashMap<Integer, C_Number> C_List = new HashMap<Integer, C_Number>();
    
    public void CreateCnumber(Title ti, int DvmID) {
        // TODO implement here
    	C_Number_t =(int)(Math.random()*100000 + 1);  //������ȣ ����
    	C_Number cn = new C_Number(ti, DvmID);
    	C_List.put(C_Number_t, cn);
    	//sendMessage()
    	
    }

    public void PopCnumber(int C_Number_t) {
        C_List.remove(C_Number_t); // key�� C_Number_t�� �� ����
    }

   
    public boolean CheckCnumber(int C_Number_t) {
       return C_List.containsKey(C_Number_t); //�� ����� true��ȯ
    }
    
    

}
