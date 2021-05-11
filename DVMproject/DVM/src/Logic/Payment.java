package Logic;


import java.util.*;

public class Payment {

	int title_id;
	int DVMid;
	String Error_log;
	boolean pay_confirm;
	C_NumberManager CM = new C_NumberManager();
	
   
    public Message_Queue referto;


    public int CardPay() {
        if(DVMid>0) { // 선결제시
            //pay_confirm = Credit(t.price);   //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            if(pay_confirm){ //test용으로 성공이라 가정
                System.out.println("카드결제 성공");
                CM.CreateCnumber(title_id, DVMid); //인증번호 생성
                CM.toString();  //인증번호 출력
                return 1;
            }else {
                System.out.println("카드결제 실패");
                return 0;
            }
    	}
    	else {
            //pay_confirm = Credit(t.price);  //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            if(pay_confirm){
                System.out.println("카드결제 성공"); //test용으로 성공이라 가정
                return 1;
            }else {
                System.out.println("카드결제 실패");
                return 0;
            }
    	}

    }

    public int SmartPay() {
        if(DVMid>0) { // 선결제시
            //pay_confirm = Credit(t.price);   //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            if(pay_confirm){ //test용으로 성공이라 가정
                System.out.println("카드결제 성공");
                CM.CreateCnumber(title_id, DVMid); //인증번호 생성
                CM.toString();  //인증번호 출력
                return 1;
            }else {
                System.out.println("카드결제 실패");
                return 0;
            }
        }
        else {
            //pay_confirm = Credit(t.price);  //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            if(pay_confirm){
                System.out.println("카드결제 성공"); //test용으로 성공이라 가정
                return 1;
            }else {
                System.out.println("카드결제 실패");
                return 0;
            }
        }

    }

 
    public void init() {
    	//�̰� �׳� ���� ���
        // TODO implement here
    }

}