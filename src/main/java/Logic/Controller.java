package Logic;


import java.util.*;

import javax.swing.JFrame;
import java.util.ArrayList;

import GUI.*;

/**
 * 
 */
public class Controller {
	private JFrame k;
    public ArrayList<Title> Title_List;
    private Payment Payment;
    private Title basket;
    private Stack DVMStack;

    public Controller() {
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
    	Controller c= new Controller();
    	c.Title_List.get(0).AddItem(new Item(20200101));
    	
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
/*
	public void ReqMainMenu() {
    	int del=-1;
    	k.setVisible(false);
    	k=new Sleep();
    	while(del==-1) {
    		System.out.print("");
    		del=((Sleep)k).return_value;
    	}
    }
    
    public void ShowMainMenu() {
    	k.setVisible(false);
    	k=new MainMenu(Title_List);
    }
    
    public int InputSelect() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((MainMenu)k).return_value;
    	}
    	if(del==-2)
    		return -2;
    	if(del==0)
    		return 0;
    	if(del>0) {
    		if(del<=Title_List.size())
    			basket=Title_List.get(del-1);
    		else
    			return -1;
			if(basket.CheckStock())
				return 1;
			else 
				return 2;
    	}
    	return -1;
    }
    
    public void ShowInputLine() {
    	k.setVisible(false);
    	k=new InputLine();
    }
    
    public int InputCnumber() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((InputLine)k).return_value;
    	}
    	if(del==-2)
    		return -2;
    	if(del==0)
    		return 0;

    	else if(del>0) {
    		int check=Payment.CM.CheckCnumber(del);
			if(check==-1) {
				InfoCnumberError();
				return 1;
			}
			else if(check==1){
				int t = Payment.CM.C_List.get(del).getTitle_id();
				ReturnItem(Title_List.get(t-1),true);
				return 0;
			}
			else if(check==0)
				return 2;
    	}
    	return -1;
    }
	public void ManShowTitle() {
        k.setVisible(false);
        k=new ManTitleMenu(Title_List);
	}
	public int ManSelectTitle() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((ManTitleMenu)k).return_value;
    	}
		return del;
	}
	public void ManShowItem(int TitleID) {
        k.setVisible(false);
        k=new ManItemMenu(Title_List.get(TitleID-1));
	}
	public int ManEditItem(int TitleID) {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((ManItemMenu)k).return_value;
    	}
    	if(del==1){ //AddItem
			k.setVisible(false);
			k=new AddItemMenu();
			{
				int del2 = -1;
				while (del2 == -1) {
					System.out.print("");
					del2 = ((AddItemMenu) k).return_value;
				}
				if (del2 == 0)
					return del;
				if (del2 == 1) {
					Title_List.get(TitleID - 1).AddItem(new Item(((AddItemMenu) k).return_date));
					return del;
				}
			}
		}
    	if(del==2){//delete item
			Title_List.get(TitleID - 1).DeleteItem(((ManItemMenu)k).return_itemlist);
			return del;
		}
    	if(del==0)
    		return del;
    	return -1;
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
    
    public void PrintSelectPay() {
        k.setVisible(false);
        k=new PaymentMenu(basket);
    }
    
    public int SelectPayment() {
    	int del=-1;
    	while(del==-1) {
    		System.out.print("");
    		del=((PaymentMenu)k).return_value;
    	}
    	if(del==-2)
    		return -2;
    	if(del==0)
    		return 0;
    	if(del>0) {
    		//payment=new payment(어쩌구);->reqcardpay/reqsmartpay 말고 생성자 넣는게 좋을듯..
    		if(del==1)
    			return 1;
    		if(del==2)
    			return 2;
    	}
    	return -1;
    }

	public void ShowCardPay() {
		k.setVisible(false);
		k=new CardPayUI();
	}

	public void ShowSmartPay() {
    	k.setVisible(false);
    	k=new SmartPayUI();
	}

	private int CancelPay(int paytype) {
		int del=-1;
    	//if()
		return -1;
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
	}

	public void InfoNoItem() {
        k.setVisible(false);
        k=new InfoNoItemUI(basket);
    }

    public void ReqFindDVM() {
		int del=-1;
		while(del==-1) {
			System.out.print("");
			del=((InfoNoItemUI)k).return_value;
		}
	}
 */
    /**
     * 
     */


    /**
     * @param Title.ID
     */


    /**
     * 
     */


    /**
     * 
     */
    public void CancelItem() {
        // TODO implement here
    }


    /**
     * 
     */


    /**
     * 
     */


    /**
     */
    public void ReturnItem(Title t) {
        // TODO implement here
    }

    /**
     * 
     */
    public void ShowMangerMenu() {
        // TODO implement here
    }

    /**
     * 
     */


    /**
     * 
     */


    /**
     * 
     */


    /**
     * 
     */
    public void SelectDVM() {
        // TODO implement here
    }

    /**
     * 
     */
    public void init() {
        basket=null;
    }
    

}