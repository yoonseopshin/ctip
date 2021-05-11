package Logic;


import java.util.*;

public class Payment {

	Title t;
	int DVMid;
	String Error_log;
	boolean pay_confirm;
	C_NumberManager CM = new C_NumberManager();
	
   
    public Message_Queue referto;
    //�� ���� �� �𸣰ԽῩ,,


    public void CardPay() {
    	if(DVMid>0) { // ��������
    	  //pay_confirm = Credit();   //�ܺ��� ī����� �ý��� ����
          pay_confirm = true; //ī����� ����
          CM.CreateCnumber(t, DVMid); //������ȣ ����
          CM.toString();  //������ȣ ���
    	}
    	else {
    		//pay_confirm = Credit();   //�ܺ��� ī����� �ý��� ����
            pay_confirm = true; //ī����� ����

    	}

    }

    public void SmartPay() {
    	if(DVMid>0) { // ��������
      	  //pay_confirm = Smart();   //�ܺ��� ������� �ý��� ����
            pay_confirm = true; //������� ����
            CM.CreateCnumber(t, DVMid); //������ȣ ����
            CM.toString();  //������ȣ ���
      	}
      	else {
      		//pay_confirm = Credit();   //�ܺ��� ������� �ý��� ����
              pay_confirm = true; //������� ����
              
      	}
      	
    }

 
    public void init() {
    	//�̰� �׳� ���� ���
        // TODO implement here
    }

}