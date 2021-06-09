package logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class ControllerTest {

  private Controller c = new Controller();

  /*
  @Before
  public void initController() {
    c.setK(new JFrame());
  }

  @Test
  public void inputSelect() {
    int del;
    c.setK(new MainMenu(Controller.getTitle_List()));
    del = c.InputSelect();
    Assert.assertEquals(-2, del);
    c.getK().setVisible(false);
  }

  @Test
  public void showInputLine() {
    int del;
    c.setK(new MainMenu(Controller.getTitle_List()));
    del = c.InputSelect();
    Assert.assertEquals(0, del);
    c.getK().setVisible(false);
  }

  @Test
  public void inputCnumber() {
    int del;
    c.setK(new InputLine());
    del = c.InputCnumber();
    Assert.assertEquals(0, del);
    c.getK().setVisible(false);
  }

  @Test
  public void infoCnumberError() {
    int del;
    c.setK(new InputLine());
    del = c.InputCnumber();
    Assert.assertEquals(1, del);
    c.getK().setVisible(false);
  }

  @Test
  public void manShowTitle() {
    c.ManShowTitle();
    Assert.assertEquals(0, c.getDel());
    c.getK().setVisible(false);
  }

  @Test
  public void manSelectTitle() {
    c.ManShowTitle();
    Assert.assertEquals(0, c.getDel());
    c.getK().setVisible(false);
  }

  @Test
  public void manShowItem() {
    Controller.getTitle_List().get(0).AddItem(new Item(20250101));
    c.ManShowItem(1);
    Assert.assertEquals(0, c.getDel());
    c.getK().setVisible(false);
  }

  @Test
  public void manEditItem() {
    Controller.getTitle_List().get(0).AddItem(new Item(20250101));
    c.ManShowItem(1);
    Assert.assertEquals(2, Controller.getTitle_List().get(0).getItem_List().size());
    c.ManShowItem(1);
    Assert.assertEquals(0, Controller.getTitle_List().get(0).getItem_List().size());
    c.getK().setVisible(false);
  }

  @Test
  public void printSelectPay() {
    c.setK(new MainMenu(Controller.getTitle_List()));
    Controller.getTitle_List().get(0).AddItem(new Item(20201125));
    c.InputSelect();
    Assert.assertEquals(1, Controller.getTitle_List().get(0).getItem_List().size());
    c.getK().setVisible(false);
  }

  @Test
  public void selectPayment() {
    c.setK(new PaymentMenu(new Title("test", 700)));
    c.setBasket(1);
    c.SelectPayment(0);
    Assert.assertEquals(0, c.getDel());
    c.getK().setVisible(false);
  }

  @Test
  public void cancelItem() {
    c.setBasket(1);
    c.setPayment(new Payment(1, 0));
    c.CancelItem();
    Assert.assertEquals(-666, c.getBasket());
    Assert.assertTrue(Controller.getDVMStack().isEmpty());
    Assert.assertNull(c.getPayment());
    c.getK().setVisible(false);
  }

  @Test
  public void showCardPay() {
    c.setPayment(new Payment(1, 0));
    c.ShowCardPay();
    c.getK().setVisible(false);
  }

  @Test
  public void cardPay() {
    c.setK(new CardPayUI());
    Controller.getTitle_List().get(0).AddItem(new Item(20201125));
    c.setBasket(1);
    c.setPayment(new Payment(1, 0));
    c.CardPay();
    Assert.assertEquals(0, Controller.getTitle_List().get(0).getItem_List().size());
    c.getK().setVisible(false);
  }

  @Test
  public void showSmartPay() {
    c.setPayment(new Payment(1, 0));
    c.ShowSmartPay();
    c.getK().setVisible(false);
  }

  @Test
  public void smartPay() {
    c.setK(new SmartPayUI());
    Controller.getTitle_List().get(0).AddItem(new Item(20201125));
    c.setBasket(1);
    c.setPayment(new Payment(1, 0));
    c.SmartPay();
    Assert.assertEquals(0, Controller.getTitle_List().get(0).getItem_List().size());
    c.getK().setVisible(false);
  }

  @Test
  public void returnItem() {
    Controller.getTitle_List().get(0).AddItem(new Item(20201125));
    c.setBasket(1);
    c.setPayment(new Payment(1, 0));
    c.ReturnItem(Controller.getTitle_List().get(0), false);
    Assert.assertEquals(0, Controller.getTitle_List().get(0).getItem_List().size());
    c.getK().setVisible(false);
  }

  @Test
  public void printCnumber() {
    c.setBasket(1);
    c.setPayment(new Payment(1, 1));
    c.PrintCnumber(Controller.getTitle_List().get(0), 1, 66666);
    c.getK().setVisible(false);
  }

  @Test
  public void infoNoItem() {
    c.setBasket(1);
    c.InfoNoItem();
    c.getK().setVisible(false);
  }

  @Test
  public void reqFindDVM() {
    c.setBasket(1);
    c.InfoNoItem();
    c.getK().setVisible(false);
  }
  */

  @Test
  public void testCancelItem() {
    c.setBasket(10);
    Controller.getDvmStack().push(new DVM(1, 1.0, 1.0));
    Assert.assertEquals(1, Controller.getDvmStack().size());
    Assert.assertEquals(10, c.getBasket());
    c.cancelItem();
    int ExpectedResult = -666;
    int ActualResult = c.getBasket();
    Assert.assertEquals(ExpectedResult, ActualResult);
    Assert.assertEquals(0, Controller.getDvmStack().size());
  }

  @Test
  public void testCancelPay() {
    c.setBasket(10);
    c.setPayment(new Payment(c.getBasket(), 1));
    Controller.getDvmStack().push(new DVM(1, 1.0, 1.0));
    Assert.assertEquals(1, Controller.getDvmStack().size());
    Assert.assertEquals(10, c.getBasket());
    Assert.assertEquals(1, c.getPayment().getDVMId());
    Assert.assertEquals(10, c.getPayment().getTitleId());
    c.cancelPay();
    int ExpectedResult = -666;
    int ActualResult = c.getBasket();
    Assert.assertEquals(ExpectedResult, ActualResult);
    Assert.assertEquals(0, Controller.getDvmStack().size());
    Assert.assertEquals(-1, c.getPayment().getDVMId());
    Assert.assertEquals(-1, c.getPayment().getTitleId());
    Assert.assertEquals("", c.getPayment().getErrorLog());
  }

  @Test
  public void testInit() {
    c.setBasket(10);
    Controller.getDvmStack().push(new DVM(1, 1.0, 1.0));
    Assert.assertEquals(1, Controller.getDvmStack().size());
    Assert.assertEquals(10, c.getBasket());
    c.init();
    int ExpectedResult = -666;
    int ActualResult = c.getBasket();
    Assert.assertEquals(ExpectedResult, ActualResult);
    Assert.assertEquals(0, Controller.getDvmStack().size());
  }

  @Test
  public void testReturnMain() {
    c.setBasket(10);
    c.setPayment(new Payment(c.getBasket(), 1));
    c.returnMain();
    int ExpectedResult = -666;
    int ActualResult = c.getBasket();
    Assert.assertEquals(ExpectedResult, ActualResult);
    Assert.assertEquals(0, Controller.getDvmStack().size());
  }

  @Test
  public void testGetDel() {
    c.setDel(0);
    Assert.assertEquals(0, c.getDel());
  }

  @Test
  public void testGetTitleList() {
    ArrayList<Title> temp = Controller.getTitleList();
    ArrayList<Title> t = new ArrayList<>();
    t.add(new Title("A", 700));
    Controller.setTitleList(t);
    Assert.assertEquals(1, Controller.getTitleList().size());
    Assert.assertEquals("A", Controller.getTitleList().get(0).getName());
    Assert.assertEquals(700, Controller.getTitleList().get(0).getPrice());
    Controller.setTitleList(temp);
  }

  @Test
  public void testGetCm() {
    CNumberManager temp = Controller.getCm();
    CNumberManager cm = new CNumberManager();
    cm.setMNumber(971125);
    Controller.setCm(cm);
    Assert.assertNotNull(Controller.getCm());
    Assert.assertEquals(971125, Controller.getCm().getMNumber());
    Assert.assertEquals(cm, Controller.getCm());
    Controller.setCm(temp);
  }

  @Test
  public void testGetMq() {
    MessageQueue temp = Controller.getMq();
    MessageQueue mq = new MessageQueue();
    Controller.setMq(mq);
    Assert.assertEquals(mq, Controller.getMq());
    Controller.setMq(temp);
  }
}