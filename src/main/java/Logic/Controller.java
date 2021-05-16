package Logic;

import GUI.*;

import java.util.ArrayList;
import java.util.Stack;
import javax.swing.*;

import static Logic.DVM.*;
import static Logic.Message_Queue.*;

/**
 *
 */

public class Controller {

  private JFrame k;
  public static ArrayList<Title> Title_List;
  private Payment Payment;
  private int basket;
  public static Stack<DVM> DVMStack;
  private int del;
  public static C_NumberManager CM;

  public Controller() {
    this.basket = -666;
    this.Payment = null;
    DVMStack = new Stack<>();
    CM = new C_NumberManager();
    Title_List = new ArrayList<>();
    Title_List.add(new Title("코카콜라", 700));
    Title_List.add(new Title("나랑드사이다", 700));
    Title_List.add(new Title("솔의눈", 700));
    Title_List.add(new Title("게토레이", 700));
    Title_List.add(new Title("스프라이트", 700));
    Title_List.add(new Title("포카리 스웨트", 700));
    Title_List.add(new Title("닥터페퍼", 700));
    Title_List.add(new Title("맥콜", 700));
    Title_List.add(new Title("제티", 700));
    Title_List.add(new Title("제주삼다수", 700));
    Title_List.add(new Title("데자와", 700));
    Title_List.add(new Title("아침햇살", 700));
    Title_List.add(new Title("밀키스", 700));
    Title_List.add(new Title("레쓰비", 700));
    Title_List.add(new Title("조지아", 700));
    Title_List.add(new Title("칠성사이다", 700));
    Title_List.add(new Title("티오피", 700));
    Title_List.add(new Title("몬스터", 700));
    Title_List.add(new Title("핫식스", 700));
    Title_List.add(new Title("레드불", 700));
  }

  public static void main(String[] args) {
    //선언

    Controller c = new Controller();
    c.setK(new JFrame());
    Message_Queue mq = new Message_Queue();
    //start
    mq.start();
    c.ReqMainMenu();

  }

  public void ReqMainMenu() {
    while (true) {
      del = -1;
      k.setVisible(false);
      k = new Sleep();
      while (del == -1) {
        System.out.print("");
        del = ((Sleep) k).return_value;
      }
      ShowMainMenu();
    }
  }

  public void ShowMainMenu() {
    while (true) {
      k.setVisible(false);
      k = new MainMenu(Title_List);
      del = InputSelect();
      if (del == -2) {
        break;
      }
    }
  }

  public int InputSelect() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((MainMenu) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      ShowInputLine();
      if (del == -2) {
        return -2;
      }
      return 0;
    } else if (del > 0) {
      if (del <= Title_List.size()) {
        basket = del;
      } else {
        //error
        return -1;
      }
      if (Title_List.get(basket - 1).CheckStock()) {
        PrintSelectPay(0);
      } else {
        InfoNoItem();
      }
      if (del == -2) {
        return -2;
      }
      return 0;
    } else {
      //	error
      return -1;
    }
  }

  public void ShowInputLine() {
    while (true) {
      k.setVisible(false);
      k = new InputLine();
      del = InputCnumber();
      if (del == 0 || del == -2) {
        break;
      }
    }
  }

  public int InputCnumber() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((InputLine) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del > 0) {
      int check = CM.CheckCnumber(del);
      if (check == -1) {
        InfoCnumberError();
        return 1;
      } else if (check == 1) {
        int t = CM.PopCnumber(del);
        ReturnItem(Title_List.get(t - 1), true);
        return 0;
      } else if (check == 0) {
        ManShowTitle();
        if (del == -2) {
          return -2;
        }
        return 0;
      } else {
        //error
        return -1;
      }
    } else {
      //error
      return -1;
    }
  }

  public void InfoCnumberError() {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoCnumberErrUI();
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoCnumberErrUI) k).return_value;
    }
  }

  public void ManShowTitle() {
    while (true) {
      k.setVisible(false);
      k = new ManTitleMenu(Title_List);
      del = ManSelectTitle();
      if (del == 0 || del == -2) {
        break;
      }
    }
  }

  public int ManSelectTitle() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((ManTitleMenu) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del > 0) {
      ManShowItem(del);
      if (del == -2) {
        return -2;
      }
      return 1;
    } else {
      //error
      return -1;
    }
  }

  public void ManShowItem(int TitleID) {
    while (true) {
      k.setVisible(false);
      k = new ManItemMenu(Title_List.get(TitleID - 1));
      del = ManEditItem(TitleID);
      if (del == 0 || del == -2) {
        break;
      }
    }
  }

  public int ManEditItem(int TitleID) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((ManItemMenu) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del == 1) { //AddItem
      k.setVisible(false);
      k = new AddItemMenu();
      {
        int del2 = -1;
        while (del2 == -1) {
          System.out.print("");
          del2 = ((AddItemMenu) k).return_value;
        }
        if (del2 == -2) {
          return -2;
        } else if (del2 == 0) {
          return 1;
        } else if (del2 == 1) {
          Title_List.get(TitleID - 1).AddItem(new Item(((AddItemMenu) k).return_date));
          return 1;
        } else {
          //error
          return -1;
        }
      }
    } else if (del == 2) {//delete item
      Title_List.get(TitleID - 1).DeleteItem(((ManItemMenu) k).return_itemlist);
      return 1;
    } else {
      //error
      return -1;
    }
  }

  public void PrintSelectPay(int DVMid) {
    k.setVisible(false);
    k = new PaymentMenu(Title_List.get(basket - 1));
    del = SelectPayment(DVMid);
  }

  public int SelectPayment(int DVMid) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((PaymentMenu) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      CancelItem();
      return 0;
    } else if (del > 0) {
      Payment = new Payment(basket, DVMid);
      if (del == 1) {
        ShowCardPay();
        return 0;
      }
      if (del == 2) {
        ShowSmartPay();
        return 0;
      } else {//error
        return -1;
      }
    } else {
      //error
      return -1;
    }
  }

  public void CancelItem() {
    ReturnMain();
  }

  public void ShowCardPay() {
    k.setVisible(false);
    k = new CardPayUI();
    del = CardPay();
  }

  public int CardPay() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((CardPayUI) k).return_value;
    }

    if (del == -3) {//결제실패
      int del2 = -1;
      Payment.CardPay(false);
      k.setVisible(false);
      k = new PayErrUI(Payment.getError_log());
      while (del2 == -1) {
        System.out.print("");
        del2 = ((PayErrUI) k).return_value;
      }
      Payment.init();
      init();
      return 0;
    } else if (del == 0) {//결제취소
      CancelPay();
      return 0;
    } else if (del == 1) {//결제성공
      int p = Payment.CardPay(true);
      if (p == 0) {
        ReturnItem(Title_List.get(basket - 1), false);
      }
      if (p > 0) {
        PrintCnumber(Title_List.get(basket - 1), Payment.getDVMid(), p);
      }
      return 0;
    } else {
      //error
      return -1;
    }
  }

  public void ShowSmartPay() {
    k.setVisible(false);
    k = new SmartPayUI();
    del = SmartPay();
  }

  public int SmartPay() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((SmartPayUI) k).return_value;
    }

    if (del == -3) {//결제실패
      int del2 = -1;
      Payment.SmartPay(false);
      k.setVisible(false);
      k = new PayErrUI(Payment.getError_log());
      while (del2 == -1) {
        System.out.print("");
        del2 = ((PayErrUI) k).return_value;
      }
      Payment.init();
      init();

      return 0;
    } else if (del == 0) {//결제취소
      CancelPay();
      return 0;
    } else if (del == 1) {//결제성공
      int p = Payment.SmartPay(true);
      if (p == 0) {
        ReturnItem(Title_List.get(basket - 1), false);
      }
      if (p > 0) {
        PrintCnumber(Title_List.get(basket - 1), Payment.getDVMid(), p);
      }
      return 0;
    } else {
      //error
      return -1;
    }
  }

  private void CancelPay() {
    Payment.init();
    init();
    ReturnMain();
  }

  public void ReturnItem(Title t, boolean IfHold) {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoReturnItemUI(t.getName());
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoReturnItemUI) k).return_value;
    }
    t.UpdateStock(0, IfHold);
    if (!IfHold) {
      Payment.init();
    }
    init();
    ReturnMain();
  }

  public void PrintCnumber(Title t, int DVMid, int Cnumber) {
    int del2 = -1;
    k.setVisible(false);
    k = new PrintCnumberUI(t, DVMid, Cnumber);
    while (del2 == -1) {
      System.out.print("");
      del2 = ((PrintCnumberUI) k).return_value;
    }
    Payment.init();
    init();
  }

  public void InfoNoItem() {
    k.setVisible(false);
    k = new InfoNoItemUI(Title_List.get(basket - 1));
    del = ReqFindDVM();
  }

  public int ReqFindDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((InfoNoItemUI) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      init();
      return 0;
    } else if (del == 1) {//finding DVM....
      k.setVisible(false);
      k = new FindingDVM(Title_List.get(basket - 1).getName());
      int i = 1;
      while (true) {
        int del2;
        System.out.print("");
        int size = DVMStack.size();
        if (size > 0) {
            if (DVMStack.get(size - 1).getID() == -1) {
                break;
            }
        }
        del2 = ((FindingDVM) k).return_value;
        if (del2 == 0) {//시간 초과or취소
          stk = 9;
          loc = -1;
          init();
          return 0;
        }
        if (i <= 10 && i != CurrentID) {
          Message sm = new Message(CurrentID);
          sm.setmsg(i, 1, basket);
        }
        i++;
      }
      if (DVMStack.size() == 1) {
        InfoNoUsableDVM();
        init();
        return 0;
      }
      DVMStack.pop();
      ShowUsableDVM();
      if (del == -2) {
        return -2;
      }
      return 0;
    } else {
      //error
      return -1;
    }
  }

  public void ShowUsableDVM() {
    k.setVisible(false);
    k = new DVMMenu(DVMStack);
    del = SelectDVM();
  }

  public void InfoNoUsableDVM() {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoNoDVMUI(Title_List.get(basket - 1).getName());
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoNoDVMUI) k).return_value;
    }
  }

  public int SelectDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((DVMMenu) k).return_value;
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      ReturnMain();
      return 0;
    } else if (del > 0) {
      PrintSelectPay(del);
      if (del == -2) {
        return -2;
      }
      return 0;
    } else {
      //error
      return -1;
    }
  }

  public void init() {
    basket = -666;
    DVMStack.clear();
    Payment = null;
  }

  public void ReturnMain() {
    init();
  }

  public JFrame getK() {
    return k;
  }

  public void setK(JFrame k) {
    this.k = k;
  }

  public ArrayList<Title> getTitle_List() {
    return Title_List;
  }

  public void setTitle_List(ArrayList<Title> title_List) {
    Title_List = title_List;
  }

  public Logic.Payment getPayment() {
    return Payment;
  }

  public void setPayment(Logic.Payment payment) {
    Payment = payment;
  }

  public int getBasket() {
    return basket;
  }

  public void setBasket(int basket) {
    this.basket = basket;
  }

  public Stack<DVM> getDVMStack() {
    return DVMStack;
  }

  public void setDVMStack(Stack<DVM> dvmStack) {
    DVMStack = dvmStack;
  }

  public int getDel() {
    return del;
  }

  public void setDel(int del) {
    this.del = del;
  }

  public C_NumberManager getCM() {
    return CM;
  }

  public void setCM(C_NumberManager cm) {
    CM = cm;
  }

}