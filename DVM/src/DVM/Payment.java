ppackage DVM;

import java.util.*;

public class Payment {
//test
    Title t;
    DVM dvm;
    String Error_log;
    boolean pay_confirm;
    boolean Prepay;
    C_NumberManager CM = new C_NumberManager();


    public Message_Queue referto;
    //얜 뭔지 잘 모르게써여,,,


    public void CardPay() {

        System.out.println("결제하실 금액은 "+t.Price+"원 입니다.");
        if(Prepay) { // 선결제시
            //pay_confirm = Credit(t.price);   //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공
            CM.CreateCnumber(t, dvm.ID); //인증번호 생성
            CM.toString();  //인증번호 출력
        }
        else {
            //pay_confirm = Credit(t.price);  //외부의 카드결제 시스템 진행
            pay_confirm = true; //카드결제 성공

        }

    }

    public void SmartPay() {

        System.out.println("결제하실 금액은 "+t.Price+"원 입니다.");
        if(Prepay) { // 선결제시
            //pay_confirm = Smart(t.price);   //외부의 간편결제 시스템 진행
            pay_confirm = true; //간편결제 성공
            CM.CreateCnumber(t, dvm.ID); //인증번호 생성
            CM.toString();  //인증번호 출력
        }
        else {
            //pay_confirm = Smart(t.price);   //외부의 간편결제 시스템 진행
            pay_confirm = true; //간편결제 성공

        }

    }
}