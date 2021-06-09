package logic;

import gui.AddItemMenu;
import gui.CardPayUI;
import gui.DVMMenu;
import gui.FindingDVM;
import gui.InfoNoItemUI;
import gui.InfoUI;
import gui.InputLine;
import gui.MainMenu;
import gui.ManItemMenu;
import gui.ManTitleMenu;
import gui.PaymentMenu;
import gui.Sleep;
import gui.SmartPayUI;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Stack;
import javax.swing.JFrame;

public class Controller {

  private JFrame k;
  private Payment payment;
  private int basket;
  private int del;
  private static ArrayList<Title> titleList = new ArrayList<>();
  private static Stack<DVM> dvmStack = new Stack<>();
  private static CNumberManager cm = new CNumberManager();
  private static MessageQueue mq = new MessageQueue();

  public Controller() {
    this.basket = -666;
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
    c.reqMainMenu();
  }

  public void reqMainMenu() {
    do {
      del = -1;
      k.setVisible(false);
      k = new Sleep();
      while (del == -1) {
        System.out.print("");
        if (k instanceof Sleep) {
          del = ((Sleep) k).getReturnValue();
        }
      }
      showMainMenu();
    } while (mq.isAlive());
  }

  public void showMainMenu() {
    do {
      k.setVisible(false);
      k = new MainMenu(titleList);
      del = inputSelect();
    } while (del != -2);
  }

  public int inputSelect() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof MainMenu) {
        del = ((MainMenu) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      showInputLine();
      if (del == -2) {
        return -2;
      }
      return 0;
    } else if (del > 0) {
      if (del <= titleList.size()) {
        basket = del;
      } else { //error
        return -1;
      }
      if (titleList.get(basket - 1).checkStock()) {
        printSelectPay(0);
      } else {
        infoNoItem();
      }
      if (del == -2) {
        return -2;
      }
      return 0;
    } else { //	error
      return -1;
    }
  }

  public void showInputLine() {
    do {
      k.setVisible(false);
      k = new InputLine();
      del = inputCNumber();
    } while (del != 0 && del != -2);
  }

  public int inputCNumber() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof  InputLine) {
        del = ((InputLine) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del > 0) {
      int check = cm.checkCNumber(del);
      if (check == -1) {
        infoCNumberError();
        return 1;
      } else if (check == 1) {
        int t = cm.popCNumber(del);
        returnItem(titleList.get(t - 1), true);
        return 0;
      } else if (check == 0) {
        manShowTitle();
        if (del == -2) {
          return -2;
        }
        return 0;
      } else { //error
        return -1;
      }
    } else { //error
      return -1;
    }
  }

  public void infoCNumberError() {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center>해당 인증번호에 대한 선결제 정보를 확인할 수 없습니다."
        + "<br>다시 입력하세요</center></html>", "이전");
    while (del2 == -1) {
      System.out.print("");
      if (k instanceof InfoUI) {
        del2 = ((InfoUI) k).getReturnValue();
      }
    }
  }

  public void manShowTitle() {
    do {
      k.setVisible(false);
      k = new ManTitleMenu(titleList);
      del = manSelectTitle();
    } while (del != 0 && del != -2);
  }

  public int manSelectTitle() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof ManTitleMenu) {
        del = ((ManTitleMenu) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      return 0;
    } else if (del > 0) {
      manShowItem(del);
      if (del == -2) {
        return -2;
      }
      return 1;
    } else { //error
      return -1;
    }
  }

  public void manShowItem(int titleId) {
    do {
      k.setVisible(false);
      k = new ManItemMenu(titleList.get(titleId - 1));
      del = manEditItem(titleId);
    } while (del != 0 && del != -2);
  }

  public int manEditItem(int titleId) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof ManItemMenu) {
        del = ((ManItemMenu) k).getReturnValue();
      }
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
          if (k instanceof InfoUI) {
            del4 = ((InfoUI) k).getReturnValue();
          }
        }
        return 1;
      }
      int count = 0;
      for (int i = 0; i < 20; i++) {
        if (titleList.get(i).checkStock()) {
          count++;
        }
      }
      if (count >= 7 && titleList.get(titleId - 1).checkStock() == false) {
        k.setVisible(false);
        k = new InfoUI("음료를 7종류 이상 추가할 수 없습니다.", "이전");

        int del4 = -1;
        while (del4 == -1) {
          System.out.print("");
          if (k instanceof InfoUI) {
            del4 = ((InfoUI) k).getReturnValue();
          }
        }
        return 1;
      }
      k.setVisible(false);
      k = new AddItemMenu();
      int del2 = -1;
      while (del2 == -1) {
        System.out.print("");
        if (k instanceof AddItemMenu) {
          del2 = ((AddItemMenu) k).getReturnValue();
        }
        if (del2 == 1) {
          Calendar cal = Calendar.getInstance();
          int today = (cal.get(Calendar.YEAR) * 10000)
              + ((cal.get(Calendar.MONTH) + 1) * 100) + cal.get(Calendar.DATE);
          int returnDate = -1;
          if (k instanceof AddItemMenu) {
            returnDate = ((AddItemMenu) k).getReturnDate();
          }
          if (today > returnDate) {
            k.setVisible(false);
            k = new InfoUI("유통기한이 지난 재고를 추가할 수 없습니다.", "이전");
            int del3 = -1;
            while (del3 == -1) {
              System.out.print("");
              if (k instanceof InfoUI) {
                del3 = ((InfoUI) k).getReturnValue();
              }
            }
            k.setVisible(false);
            k = new AddItemMenu();
            del2= -1;
            continue;
          }
        }
      }
      if (del2 == -2) {
        return -2;
      } else if (del2 == 0) {
        return 1;
      } else if (del2 == 1) {
        if (k instanceof AddItemMenu) {
          titleList.get(titleId - 1).addItem(new Item(((AddItemMenu) k).getReturnDate()));
        }
        return 1;
      } else { //error
        return -1;
      }
    } else if (del == 2) { //delete item
      if (k instanceof ManItemMenu) {
        titleList.get(titleId - 1).deleteItem(((ManItemMenu) k).getReturnItemList());
      }
      return 1;
    } else { //error
      return -1;
    }
  }

  public void printSelectPay(int DVMid) {
    k.setVisible(false);
    k = new PaymentMenu(titleList.get(basket - 1));
    del = selectPayment(DVMid);
  }

  public int selectPayment(int DVMid) {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof PaymentMenu) {
        del = ((PaymentMenu) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      cancelItem();
      return 0;
    } else if (del > 0) {
      payment = new Payment(basket, DVMid);
      if (del == 1) {
        showCardPay();
        return 0;
      }
      if (del == 2) {
        showSmartPay();
        return 0;
      } else { //error
        return -1;
      }
    } else { //error
      return -1;
    }
  }

  public void cancelItem() {
    returnMain();
  }

  public void showCardPay() {
    k.setVisible(false);
    k = new CardPayUI();
    del = cardPay();
  }

  public int cardPay() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof CardPayUI) {
        del = ((CardPayUI) k).getReturnValue();
      }
    }

    if (del == -3) { //결제실패
      int del2 = -1;
      payment.cardPay(false);
      k.setVisible(false);
      k = new InfoUI(payment.getErrorLog(), "메인");
      while (del2 == -1) {
        System.out.print("");
        if (k instanceof InfoUI) {
          del2 = ((InfoUI) k).getReturnValue();
        }
      }
      payment.init();
      init();
      return 0;
    } else if (del == 0) { //결제취소
      cancelPay();
      return 0;
    } else if (del == 1) { //결제성공
      int p = payment.cardPay(true);
      if (p == 0) {
        returnItem(titleList.get(basket - 1), false);
      }
      if (p > 0) {
        printCNumber(titleList.get(basket - 1).getName(), payment.getDVMId(), p);
      }
      return 0;
    } else { //error
      return -1;
    }
  }

  public void showSmartPay() {
    k.setVisible(false);
    k = new SmartPayUI();
    del = smartPay();
  }

  public int smartPay() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof SmartPayUI) {
        del = ((SmartPayUI) k).getReturnValue();
      }
    }

    if (del == -3) { //결제실패
      int del2 = -1;
      payment.smartPay(false);
      k.setVisible(false);
      k = new InfoUI(payment.getErrorLog(), "메인");
      while (del2 == -1) {
        System.out.print("");
        if (k instanceof InfoUI) {
          del2 = ((InfoUI) k).getReturnValue();
        }
      }
      payment.init();
      init();

      return 0;
    } else if (del == 0) { //결제취소
      cancelPay();
      return 0;
    } else if (del == 1) { //결제성공
      int p = payment.smartPay(true);
      if (p == 0) {
        returnItem(titleList.get(basket - 1), false);
      }
      if (p > 0) {
        printCNumber(titleList.get(basket - 1).getName(), payment.getDVMId(), p);
      }
      return 0;
    } else { //error
      return -1;
    }
  }

  public void cancelPay() {
    payment.init();
    init();
    returnMain();
  }

  public void returnItem(Title t, boolean ifHold) {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center><strong>" + t.getName()
        + "</strong><br>음료가 나왔습니다.</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      if (k instanceof InfoUI) {
        del2 = ((InfoUI) k).getReturnValue();
      }
    }
    t.updateStock(0, ifHold);
    if (!ifHold) {
      payment.init();
    }
    init();
    returnMain();
  }

  public void printCNumber(String name, int DVMId, int cNumber) {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center>제품: " + name + "   DVM ID: " + DVMId +
        "<br>인증번호: " + cNumber + "</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      if (k instanceof InfoUI) {
        del2 = ((InfoUI) k).getReturnValue();
      }
    }
    payment.init();
    init();
  }

  public void infoNoItem() {
    k.setVisible(false);
    k = new InfoNoItemUI(titleList.get(basket - 1).getName());
    del = reqFindDVM();
  }

  public int reqFindDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof InfoNoItemUI) {
        del = ((InfoNoItemUI) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      init();
      return 0;
    } else if (del == 1) { //finding DVM....
      k.setVisible(false);
      k = new FindingDVM(titleList.get(basket - 1).getName());
      int i = 1;
      while (true) {
        int del2;
        System.out.print("");
        int size = dvmStack.size();
        if ((size > 0) && (dvmStack.get(size - 1).getId() == -1)) {
          break;
        }
        if (k instanceof FindingDVM) {
          del2 = ((FindingDVM) k).getReturnValue();
          if (del2 == 0) { //시간 초과or취소
            MessageQueue.setStk(9);
            MessageQueue.setLoc(-1);
            init();
            return 0;
          }
        }
        if (i <= 10 && i != DVM.getCurrentID()) {
          Message sm = new Message(DVM.getCurrentID());
          sm.setMsg(i, 1, basket);
        }
        i++;
      }
      if (dvmStack.size() == 1) {
        infoNoUsableDVM();
        init();
        return 0;
      }
      dvmStack.pop();
      showUsableDVM();
      if (del == -2) {
        return -2;
      }
      return 0;
    } else { //error
      return -1;
    }
  }

  public void showUsableDVM() {
    k.setVisible(false);
    k = new DVMMenu(dvmStack);
    del = selectDVM();
  }

  public void infoNoUsableDVM() {
    int del2 = -1;
    k.setVisible(false);
    k = new InfoUI("<html><center><strong>" + titleList.get(basket - 1).getName()
        + "</strong><br>해당 음료의 재고가 있는 자판기가 없습니다.</center></html>", "메인");
    while (del2 == -1) {
      System.out.print("");
      if (k instanceof InfoUI) {
        del2 = ((InfoUI) k).getReturnValue();
      }
    }
  }

  public int selectDVM() {
    del = -1;
    while (del == -1) {
      System.out.print("");
      if (k instanceof DVMMenu) {
        del = ((DVMMenu) k).getReturnValue();
      }
    }
    if (del == -2) {
      return -2;
    } else if (del == 0) {
      returnMain();
      return 0;
    } else if (del > 0) {
      printSelectPay(del);
      if (del == -2) {
        return -2;
      }
      return 0;
    } else { //error
      return -1;
    }
  }

  public void init() {
    basket = -666;
    dvmStack.clear();
  }

  public void returnMain() {
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

  public static Stack<DVM> getDvmStack() {
    return dvmStack;
  }

  public static void setDvmStack(Stack<DVM> dvmStack) {
    Controller.dvmStack = dvmStack;
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