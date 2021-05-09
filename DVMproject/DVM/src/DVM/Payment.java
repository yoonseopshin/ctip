package DVM;

import java.util.*;

public class Payment {

	Title t;
	DVM dvm;
	String Error_log;
	boolean pay_confirm;
	boolean Prepay;
	C_NumberManager CM = new C_NumberManager();
	
   
    public Message_Queue referto;
    //얜 뭔지 잘 모르게써여,,


    public void CardPay() {
    	if(Prepay) { // 선결제시
    	  //pay_confirm = Credit();   //외부의 카드결제 시스템 진행
          pay_confirm = true; //카드결제 성공
          CM.CreateCnumber(t, dvm.ID); //인증번호 생성
          CM.toString();  //인증번호 출력
    	}
    	else {
    		//pay_confirm = Credit();   //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            
    	}
    	
    }

    public void SmartPay() {
    	if(Prepay) { // 선결제시
      	  //pay_confirm = Smart();   //외부의 간편결제 시스템 진행
            pay_confirm = true; //간편결제 성공
            CM.CreateCnumber(t, dvm.ID); //인증번호 생성
            CM.toString();  //인증번호 출력
      	}
      	else {
      		//pay_confirm = Credit();   //외부의 간편결제 시스템 진행
              pay_confirm = true; //간편결제 성공
              
      	}
      	
    }

 
    public void init() {
    	//이거 그냥 결제 취소
        // TODO implement here
    }

}