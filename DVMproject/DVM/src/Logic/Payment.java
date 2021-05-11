package Logic;


import java.util.*;

public class Payment {

	int title_id;
	int DVMid;
	String Error_log;
	boolean pay_confirm;
	C_NumberManager CM = new C_NumberManager();
	
   
    public Message_Queue referto;


    public void CardPay() {
        if(DVMid>0) { // 선결제시
            //pay_confirm = Credit(t.price);   //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            CM.CreateCnumber(title_id, DVMid); //인증번호 생성
            CM.toString();  //인증번호 출력
    	}
    	else {
            //pay_confirm = Credit(t.price);  //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공

    	}

    }

    public void SmartPay() {
    	if(DVMid>0) { // 선결제시
            //pay_confirm = Smart(t.price);   //외부의 간편결제 시스템 진행
            pay_confirm = true; //간편결제 성공
            CM.CreateCnumber(title_id, DVMid); //인증번호 생성
            CM.toString();  //인증번호 출력
      	}
      	else {
            //pay_confirm = Smart(t.price);   //외부의 간편결제 시스템 진행
            pay_confirm = true; //간편결제 성공


        }
      	
    }

 
    public void init() {
    	//�̰� �׳� ���� ���
        // TODO implement here
    }

}