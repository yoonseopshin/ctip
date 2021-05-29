package logic;

import gui.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;

public class Controller {

  private JFrame k;
  private Payment payment;
  private int basket;
  private int del;
  private static ArrayList<Title> titleList = new ArrayList<>();
  private static Stack<DVM> DVMStack = new Stack<>();
  private static CNumberManager cm = new CNumberManager();
  private static MessageQueue mq = new MessageQueue();

  public Controller() {
    this.basket = -666;
    this.payment = null;
    titleList.add(new Title("코카콜라", 700));
    titleList.add(new Title("나랑드사이다", 700));
    titleList.add(new Title("솔의눈", 700));
    titleList.add(new Title("게토레이", 700));
    titleList.add(new Title("스프라이트", 700));
    titleList.add(new Title("포카리 스웨트", 700));
    titleList.add(new Title("닥터페퍼", 700));
    titleList.add(new Title("맥콜", 700));
    titleList.add(new Title("제티", 700));
    titleList.add(new Title("제주삼다수", 700));
    titleList.add(new Title("데자와", 700));
    titleList.add(new Title("아침햇살", 700));
    titleList.add(new Title("밀키스", 700));
    titleList.add(new Title("레쓰비", 700));
    titleList.add(new Title("조지아", 700));
    titleList.add(new Title("칠성사이다", 700));
    titleList.add(new Title("티오피", 700));
    titleList.add(new Title("몬스터", 700));
    titleList.add(new Title("핫식스", 700));
    titleList.add(new Title("레드불", 700));
  }

  public static void main(String[] args) {
    //선언
    Controller c = new Controller();
    c.setK(new JFrame());
    //start
    Controller.mq.start();
    c.ReqMainMenu();
  }

  public void ReqMainMenu() {
    while (true) {
      del = -1;
      k.setVisible(false);
      k = new Sleep();
      while (del == -1) {
        System.out.print("");
        del = ((Sleep) k).getReturnValue();
      }
      ShowMainMenu();
      if (!mq.isAlive()) {
        break;
      }
    }
  }

  public void ShowMainMenu() {
    while (true) {
      k.setVisible(false);
      k = new MainMenu(titleList);
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
      del = ((MainMenu) k).getReturnValue();
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
      if (del <= titleList.size()) {
        basket = del;
      } else {
        //error
        return -1;
      }
      if (titleList.get(basket - 1).CheckStock()) {
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
      del = ((InputLine) k).getReturnValue();
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del > 0) {
      int check = cm.CheckCnumber(del);
      if (check == -1) {
        InfoCnumberError();
        return 1;
      } else if (check == 1) {
        int t = cm.PopCnumber(del);
        ReturnItem(titleList.get(t - 1), true);
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
    k = new InfoUI("<html><center>해당 인증번호에 대한 선결제 정보를 확인할 수 없습니다."
        + "<br>다시 입력하세요</center></html>", "이전");
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoUI) k).getReturnValue();
    }
  }

  public void ManShowTitle() {
    while (true) {
      k.setVisible(false);
      k = new ManTitleMenu(titleList);
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
      del = ((ManTitleMenu) k).getReturnValue();
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

  public void ManShowItem(int titleId) {
    while (true) {
      k.setVisible(false);
      k = new ManItemMenu(titleList.get(titleId - 1));
      del = ManEditItem(titleId);
      if (del == 0 || del == -2) {
        break;
      }
    }
  }

  public int ManEditItem(int titleId) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((ManItemMenu) k).getReturnValue();
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del == 1) { //AddItem
      if (titleList.get(titleId - 1).getItemList().size() >= 30) {
        k.setVisible(false);
        k = new InfoUI("더 이상 재고를 추가할 수 없습니다.", "이전");

        int del4 = -1;
        while (del4 == -1) {
          System.out.print("");
          del4 = ((InfoUI) k).getReturnValue();
        }
        return 1;
      }
      int count = 0;
      for (int i = 0; i < 20; i++) {
        if (titleList.get(i).CheckStock()) {
          count++;
        }
      }
      if (count >= 7 && titleList.get(titleId - 1).CheckStock() == false) {
        k.setVisible(false);
        k = new InfoUI("음료를 7종류 이상 추가할 수 없습니다.", "이전");

        int del4 = -1;
        while (del4 == -1) {
          System.out.print("");
          del4 = ((InfoUI) k).getReturnValue();
        }
        return 1;
      }
      k.setVisible(false);
      k = new AddItemMenu();
      int del2 = -1;
      while (del2 == -1) {
        System.out.print("");
        if (((AddItemMenu) k).getReturn_value() == 1) {
          Calendar cal = Calendar.getInstance();
          int today = (cal.get(Calendar.YEAR) * 10000) + ((cal.get(Calendar.MONTH) + 1) * 100) + cal
              .get(Calendar.DATE);
          if (today > ((AddItemMenu) k).getReturn_date()) {
            k.setVisible(false);
            k = new InfoUI("유통기한이 지난 재고를 추가할 수 없습니다.", "이전");
            int del3 = -1;
            while (del3 == -1) {
              System.out.print("");
              del3 = ((InfoUI) k).getReturnValue();
            }
            k.setVisible(false);
            k = new AddItemMenu();
            continue;
          }
        }
        del2 = ((AddItemMenu) k).getReturn_value();
      }
      if (del2 == -2) {
        return -2;
      } else if (del2 == 0) {
        return 1;
      } else if (del2 == 1) {
        titleList.get(titleId - 1).AddItem(new Item(((AddItemMenu) k).getReturn_date()));
        return 1;
      } else {
        //error
        return -1;
      }
    } else if (del == 2) {//delete item
      titleList.get(titleId - 1).DeleteItem(((ManItemMenu) k).getReturnItemList());
      return 1;
    } else {//error
      return -1;
    }
  }

  public void PrintSelectPay(int DVMid) {
    k.setVisible(false);
    k = new PaymentMenu(titleList.get(basket - 1));
    del = SelectPayment(DVMid);
  }

  public int SelectPayment(int DVMid) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((PaymentMenu) k).getReturnValue();
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      CancelItem();
      return 0;
    } else if (del > 0) {
      payment = new Payment(basket, DVMid);
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
    } else {//error
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
      del = ((CardPayUI) k).getReturnValue();
    }

    if (del == -3) {//결제실패
      int del2 = -1;
      payment.CardPay(false);
      k.setVisible(false);
      k = new InfoUI(payment.getErrorLog(), "메인");
      while (del2 == -1) {
        System.out.print("");
        del2 = ((InfoUI) k).getReturnValue();
      }
      payment.init();
      init();
      return 0;
    } else if (del == 0) {//결제취소
      CancelPay();
      return 0;
    } else if (del == 1) {//결제성공
      int p = payment.CardPay(true);
      if (p == 0) {
        ReturnItem(titleList.get(basket - 1), false);
      }
      if (p > 0) {
        PrintCnumber(titleList.get(basket - 1).getName(), payment.getDVMId(), p);
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
      del = ((SmartPayUI) k).getReturnValue();
    }

    if (del == -3) {//결제실패
      int del2 = -1;
      payment.SmartPay(false);
      k.setVisible(false);
      k = new InfoUI(payment.getErrorLog(), "메인");
      while (del2 == -1) {
        System.out.print("");
        del2 = ((InfoUI) k).getReturnValue();
      }
      payment.init();
      init();

      return 0;
    } else if (del == 0) {//결제취소
      CancelPay();
      return 0;
    } else if (del == 1) {//결제성공
      int p = payment.SmartPay(true);
      if (p == 0) {
        ReturnItem(titleList.get(basket - 1), false);
      }
      if (p > 0) {
        PrintCnumber(titleList.get(basket - 1).getName(), payment.getDVMId(), p);
      }
      return 0;
    } else {
      //error
      return -1;
    }
  }

  private void CancelPay() {
    payment.init();
    init();
    ReturnMain();
  }

  public void ReturnItem(Title t, boolean ifHold) {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center><strong>" + t.getName()
        + "</strong><br>음료가 나왔습니다.</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoUI) k).getReturnValue();
    }
    t.UpdateStock(0, ifHold);
    if (!ifHold) {
      payment.init();
    }
    init();
    ReturnMain();
  }

  public void PrintCnumber(String name, int DVMid, int Cnumber) {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center>제품: " + name + "   DVM ID: " + DVMid +
        "<br>인증번호: " + Cnumber + "</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoUI) k).getReturnValue();
    }
    payment.init();
    init();
  }

  public void InfoNoItem() {
    k.setVisible(false);
    k = new InfoNoItemUI(titleList.get(basket - 1).getName());
    del = ReqFindDVM();
  }

  public int ReqFindDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((InfoNoItemUI) k).getReturnValue();
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      init();
      return 0;
    } else if (del == 1) {//finding DVM....
      k.setVisible(false);
      k = new FindingDVM(titleList.get(basket - 1).getName());
      int i = 1;
      while (true) {
        int del2;
        System.out.print("");
        int size = DVMStack.size();
        if ((size > 0) && (DVMStack.get(size - 1).getId() == -1)) {
          break;
        }
        del2 = ((FindingDVM) k).getReturnValue();
        if (del2 == 0) {//시간 초과or취소
          MessageQueue.setStk(9);
          MessageQueue.setLoc(-1);
          init();
          return 0;
        }
        if (i <= 10 && i != DVM.getCurrentID()) {
          Message sm = new Message(DVM.getCurrentID());
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
    k = new InfoUI("<html><center><strong>" + titleList.get(basket - 1).getName()
        + "</strong><br>해당 음료의 재고가 있는 자판기가 없습니다.</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      del2 = ((InfoUI) k).getReturnValue();
    }
  }

  public int SelectDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      del = ((DVMMenu) k).getReturnValue();
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
    payment = null;
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

  public logic.Payment getPayment() {
    return payment;
  }

  public void setPayment(logic.Payment payment) {
    this.payment = payment;
  }

  public int getBasket() {
    return basket;
  }

  public void setBasket(int basket) {
    this.basket = basket;
  }

  public int getDel() {
    return del;
  }

  public void setDel(int del) {
    this.del = del;
  }

  public static ArrayList<Title> getTitleList() {
    return titleList;
  }

  public static void setTitleList(ArrayList<Title> titleList) {
    Controller.titleList = titleList;
  }

  public static Stack<DVM> getDVMStack() {
    return DVMStack;
  }

  public static void setDVMStack(Stack<DVM> DVMStack) {
    Controller.DVMStack = DVMStack;
  }

  public static CNumberManager getCm() {
    return cm;
  }

  public static void setCm(CNumberManager cm) {
    Controller.cm = cm;
  }

  public static MessageQueue getMq() {
    return mq;
  }

  public static void setMq(MessageQueue mq) {
    Controller.mq = mq;
  }
}