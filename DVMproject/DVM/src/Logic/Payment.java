package Logic;


import java.util.*;

public class Payment {

	int title_id;
	int DVMid;
	String Error_log;

	Payment(int title_id, int DVMid){
	    this.title_id =title_id;
	    this.DVMid=DVMid;
    }

    public String Error_log(){
	    return this.Error_log;
    }

    public int CardPay(boolean Pay_confirm) {
        if(DVMid>0) {//카드결제 성공
            if(Pay_confirm){ //test용으로 성공이라 가정
                System.out.println("카드결제 선결제 성공");
                C_Number Cnumber= new C_Number(title_id, DVMid);
                int CN=Cnumber.CreateCnumber(title_id, DVMid);

                Message message = new Message(1);
                message.setmsg(DVMid, 3, title_id, CN);
                Message_Queue.sendMsg(message);
                Message_Queue.recivMsg();
                return CN;
            }else {
                this.Error_log="카드결제 선결제 실패";
                return -3;
            }
        }
        else {
            if(Pay_confirm){
                System.out.println("카드결제 성공"); //test용으로 성공이라 가정
                return 0;
            }else {
                this.Error_log="카드결제 실패";
                return -3;
            }
        }
    }

    public int SmartPay(boolean Pay_confirm) {
        if(DVMid>0) {//카드결제 성공
            if(Pay_confirm){ //test용으로 성공이라 가정
                System.out.println("카드결제 선결제 성공");
                C_Number Cnumber= new C_Number(title_id, DVMid);
                int CN=Cnumber.CreateCnumber(title_id, DVMid);

                Message message = new Message(1);
                message.setmsg(DVMid, 3, title_id, CN);
                Message_Queue.sendMsg(message);
                Message_Queue.recivMsg();
                return CN;
            }else {
                this.Error_log="카드결제 선결제 실패";
                return -3;
            }
        }
        else {
            if(Pay_confirm){
                System.out.println("카드결제 성공"); //test용으로 성공이라 가정
                return 0;
            }else {
                this.Error_log="카드결제 실패";
                return -3;
            }
        }
    }

 
    public void init() {
        this.title_id=-1;
        this.DVMid=-1;
        this.Error_log=null;
    }

}