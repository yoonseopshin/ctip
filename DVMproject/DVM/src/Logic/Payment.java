package Logic;


import java.util.*;

public class Payment {

	Title t;
	DVM dvm;
	String Error_log;
	boolean pay_confirm;
	boolean Prepay;
	C_NumberManager CM = new C_NumberManager();
	
   
    public Message_Queue referto;
    //�� ���� �� �𸣰ԽῩ,,


    public void CardPay() {
    	if(Prepay) { // ��������
    	  //pay_confirm = Credit();   //�ܺ��� ī����� �ý��� ����
          pay_confirm = true; //ī����� ����
          CM.CreateCnumber(t, dvm.ID); //������ȣ ����
          CM.toString();  //������ȣ ���
    	}
    	else {
    		//pay_confirm = Credit();   //�ܺ��� ī����� �ý��� ����
            pay_confirm = true; //ī����� ����
            
    	}
    	
    }

    public void SmartPay() {
    	if(Prepay) { // ��������
      	  //pay_confirm = Smart();   //�ܺ��� ������� �ý��� ����
            pay_confirm = true; //������� ����
            CM.CreateCnumber(t, dvm.ID); //������ȣ ����
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