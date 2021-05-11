package Logic;


import GUI.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Stack;

/**
 * 
 */
public class Controller2 {
	private JFrame k;
    private ArrayList<Title> Title_List;
    private Payment Payment;
    private Title basket;
    private Stack DVMStack;

    public Controller2() {
    	this.k=new JFrame();
    	this.basket=null;
    	this.Payment = new Payment();
    	Title_List=new ArrayList<Title>();
    	Title_List.add(new Title("코카콜라",700));
    	Title_List.add(new Title("나랑드사이다",700));
    	Title_List.add(new Title("솔의눈",700));
    	Title_List.add(new Title("게토레이",700));
    	Title_List.add(new Title("스프라이트",700));
    	Title_List.add(new Title("포카리 스웨트",700));
    	Title_List.add(new Title("닥터페퍼",700));
    	Title_List.add(new Title("맥콜",700));
    	Title_List.add(new Title("제티",700));
    	Title_List.add(new Title("제주삼다수",700));
    	Title_List.add(new Title("데자와",700));
    	Title_List.add(new Title("아침햇살",700));
    	Title_List.add(new Title("밀키스",700));
    	Title_List.add(new Title("레쓰비",700));
    	Title_List.add(new Title("조지아",700));
    	Title_List.add(new Title("칠성사이다",700));
    	Title_List.add(new Title("티오피",700));
    	Title_List.add(new Title("몬스터",700));
    	Title_List.add(new Title("핫식스",700));
    	Title_List.add(new Title("레드불",700));
    }

    public static void main(String[] args) {
    	//선언
    	Controller2 c= new Controller2();
    	c.Title_List.get(0).AddItem(new Item(20200101));

    	c.ReqMainMenu();
    	//시스템
		/*
    	int delimiter;
    	while(true){
    	c.ReqMainMenu();
    		while(true) {
    			c.ShowMainMenu();
    			delimiter=c.InputSelect();
    			//인증번호 입력
    			if(delimiter==0) {
    				while(true) {
    					c.ShowInputLine();
    					delimiter=c.InputCnumber();
    					if(delimiter==1)//저장되지 않은 값을 입력한 경우
    						continue;
    					//매니저 메뉴 출력
    					if(delimiter==2) {
    						while(true) {
    							c.ManShowTitle();
    							delimiter = c.ManSelectTitle();
    	    					if(delimiter>0) {
    	    						while(true) {
    	    							int del2;
    	    							c.ManShowItem(delimiter);
    	    							del2=c.ManEditItem(delimiter);
    	    							if(del2==0||del2==-2) {
    	    								if(del2==-2) delimiter=-2;
    	    								break;
    	    							}
    	    						}
    	    					}
    	    					if(delimiter==0||delimiter==-2) break;
    						}
    					}
    					if(delimiter==0||delimiter==-2) break;
    				}
    				if(delimiter==0) continue;
    			}
    			//재고가 있는 음료 선택
    			else if(delimiter==1) {
    				c.PrintSelectPay();
    				delimiter=c.SelectPayment();
					if(delimiter==1) {
						c.ShowCardPay();
						c.CancelPay(1);
					}
					if(delimiter==2) {
						c.ShowSmartPay();
						c.CancelPay(2);
					}
    				if(delimiter==0) {
    					c.init();
    					continue;
    				}
    			}
    			//재고가 없는 음료 선택
    			else if(delimiter==2) {
    				c.InfoNoItem();
    				c.ReqFindDVM();
    			}
    			//입력시간초과
    			if(delimiter==-2) {
    				c.init();
					break;
				}

    		}
    	}

		 */
    }

	public void ReqMainMenu() {
    	int del=-1;
    	k.setVisible(false);
    	k=new Sleep();
    	while(del==-1) {
    		System.out.print("");
    		del=((Sleep)k).return_value;
    	}
    	ShowMainMenu();
    	return;
    }
    
    public void ShowMainMenu() {
    	k.setVisible(false);
    	k=new MainMenu(Title_List);
		InputSelect();
    }
    
    public void InputSelect() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((MainMenu)k).return_value;
    	}
    	if(del==-2) {
			ReqMainMenu();return;
		}
    	else if(del==0) {
			ShowInputLine();return;
		}
    	else if(del>0) {
    		if(del<=Title_List.size())
    			basket=Title_List.get(del-1);
    		else {
    			//error
			}
			if(basket.CheckStock()) {
				PrintSelectPay(false);return;
			}
			else {
				InfoNoItem();return;
			}
    	}
    	else {
    	//	error
    	}
    }
    
    public void ShowInputLine() {
    	k.setVisible(false);
    	k=new InputLine();
    	InputCnumber();
    	return;
    }
    
    public void InputCnumber() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((InputLine)k).return_value;
    	}
    	if(del==-2) {
			ReqMainMenu();return;
		}
    	else if(del==0) {
			ShowMainMenu();return;
		}
    	else if(del>0) {
    		int check=Payment.CM.CheckCnumber(del);
			if(check==-1) {
				InfoCnumberError();
				ShowInputLine();
				return;
			}
			else if(check==1){
				int t = Payment.CM.C_List.get(del).getTitle_id();
				ReturnItem(Title_List.get(t-1),true);
				return;
			}
			else if(check==0) {
				ManShowTitle();return;
			}
    	}
    	else{
    		//error
		}
    }

    public void InfoCnumberError() {
		int del=-1;
		k.setVisible(false);
		k=new InfoCnumberErrUI();
		while(del==-1) {
			System.out.print("");
			del=((InfoCnumberErrUI)k).return_value;
		}
	}

	public void ManShowTitle() {
        k.setVisible(false);
        k=new ManTitleMenu(Title_List);
		ManSelectTitle();
		return;
	}
	public void ManSelectTitle() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((ManTitleMenu)k).return_value;
    	}
		if(del==-2) {
			ReqMainMenu();return;
		}
		else if(del==0) {
			ShowMainMenu();return;
		}
		else if(del>0) {
			ManShowItem(del);return;
		}
	}
	public void ManShowItem(int TitleID) {
        k.setVisible(false);
        k=new ManItemMenu(Title_List.get(TitleID-1));
		ManEditItem(TitleID);
		return;
	}
	public void ManEditItem(int TitleID) {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((ManItemMenu)k).return_value;
    	}
    	if(del==-2){
    		ReqMainMenu();return;
    	}
    	else if(del==0) {
			ManShowTitle();return;
		}
    	else if(del==1){ //AddItem
			k.setVisible(false);
			k=new AddItemMenu();
			{
				int del2 = -1;
				while (del2 == -1) {
					System.out.print("");
					del2 = ((AddItemMenu) k).return_value;
				}
				if (del2 == -2) {
					ReqMainMenu();return;
				}
				if (del2==0) {
					ManShowItem(TitleID);return;
				}
				if (del2 == 1) {
					Title_List.get(TitleID - 1).AddItem(new Item(((AddItemMenu) k).return_date));
					ManShowItem(TitleID);
					return;
				}
			}
		}
    	if(del==2){//delete item
			Title_List.get(TitleID - 1).DeleteItem(((ManItemMenu)k).return_itemlist);
			ManShowItem(TitleID);
			return;
		}
    	else{
    		//error
		}
	}
    
    public void PrintSelectPay(boolean prepay) {
        k.setVisible(false);
        k=new PaymentMenu(basket);
        SelectPayment(prepay);
        return;
    }

    public void SelectPayment(boolean prepay) {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((PaymentMenu)k).return_value;
    	}
    	if(del==-2) {
			ReqMainMenu();return;
		}
    	else if(del==0) {
			CancelItem();return;
		}
    	else if(del>0) {
    		//payment=new payment(어쩌구);->reqcardpay/reqsmartpay 말고 생성자 넣는게 좋을듯..
    		if(del==1) {
				ShowCardPay();return;
			}
    		if(del==2) {
				ShowSmartPay();return;
			}
    	}
    	else{
    		//error
		}
    }

	public void CancelItem() {
		init();
		ShowMainMenu();
		return;
	}

	public void ShowCardPay() {
		k.setVisible(false);
		k=new CardPayUI();
		CardPay();
		return;
	}

	public void CardPay(){
    	int del=-1;
    	while(del==-1){
    		System.out.print("");
    		del=((CardPayUI)k).return_value;
		}
    	if(del==-3){//결제실패
    		//k.setVisible(false);
    		//k=new PayErrUI(payment에러로그참조)
			//init();
			//payment.init();
			//ShowMainMenu();
		}
    	if(del==0){//결제취소
    		CancelPay();return;
		}
    	if(del==1){//결제성공
    		//payment참조..리턴 아이템 혹은 인증번호 출력
		}
	}

	public void ShowSmartPay() {
    	k.setVisible(false);
    	k=new SmartPayUI();
    	SmartPay();
    	return;
	}

	public void SmartPay() {
    	int del=-1;
		while(del==-1){
			System.out.print("");
			del=((SmartPayUI)k).return_value;
		}
		if(del==-3){//결제실패
			//k.setVisible(false);
			//k=new PayErrUI(payment에러로그참조)
			//init();
			//payment.init();
			//ShowMainMenu();
		}
		if(del==0){//결제취소
			CancelPay();return;
		}
		if(del==1){//결제성공
			//payment참조..return하거나 다른거
		}
	}

	private void CancelPay() {
		init();
		//Payment.init();
		ShowMainMenu();
	}

	public void ReturnItem(Title t,boolean IfHold) {
		int del=-1;
    	k.setVisible(false);
		k=new InfoReturnItemUI(t.Name());
		while(del==-1){
			System.out.print("");
			del=((InfoReturnItemUI)k).return_value;
		}
		t.UpdateStock(2,IfHold);
		ShowMainMenu();
		return;
	}

	public void InfoNoItem() {
        k.setVisible(false);
        k=new InfoNoItemUI(basket);
        ReqFindDVM();
        return;
    }

    public void ReqFindDVM() {
		int del=-1;
		while(del==-1) {
			System.out.print("");
			del=((InfoNoItemUI)k).return_value;
		}
		if(del==-2){
			ReqMainMenu();return;
		}
		else if(del==0){
			init();
			ShowMainMenu();
			return;
		}
		else if(del==1){
			//메시지의 송수신
			ShowUsableDVM();
		}
	}
	public void ShowUsableDVM() {
		k.setVisible(false);
		k=new DVMMenu(DVMStack);
		SelectDVM();
	}

    public void SelectDVM() {
        int del=-1;
        while(del==-1){
			System.out.print("");
			del=((DVMMenu)k).return_value;
		}
        if(del==-2){
        	ReqMainMenu();return;
		}
        else if(del==0){
        	init();
        	ShowMainMenu();
		}
        else if(del>0){
        	//PrintSelectPay()
		}
    }

    /**
     * 
     */
    public void init() {
        basket=null;
        DVMStack=null;
    }
    

}