package Logic;

import GUI.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ControllerTest {

  Controller c;

  @Ignore
  @Before
  public void initController() {
    c = new Controller();
    c.setK();
  }

  @Ignore
  @Test
  public void inputSelect() {
    int del;
    c.k = new MainMenu(c.Title_List);
    del = c.InputSelect();
    Assert.assertEquals(-2, del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void showInputLine() {
    int del;
    c.k = new MainMenu(c.Title_List);
    del = c.InputSelect();
    Assert.assertEquals(0, del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void inputCnumber() {
    int del;
    c.k = new InputLine();
    del = c.InputCnumber();
    Assert.assertEquals(0, del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void infoCnumberError() {
    int del;
    c.k = new InputLine();
    del = c.InputCnumber();
    Assert.assertEquals(1, del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void manShowTitle() {
    c.ManShowTitle();
    Assert.assertEquals(0, c.del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void manSelectTitle() {
    c.ManShowTitle();
    Assert.assertEquals(0, c.del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void manShowItem() {
    c.Title_List.get(0).AddItem(new Item(20250101));
    c.ManShowItem(1);
    Assert.assertEquals(0, c.del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void manEditItem() {
    c.Title_List.get(0).AddItem(new Item(20250101));
    c.ManShowItem(1);
    Assert.assertEquals(2, c.Title_List.get(0).Item_List().size());
    c.ManShowItem(1);
    Assert.assertEquals(0, c.Title_List.get(0).Item_List().size());
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void printSelectPay() {
    c.k = new MainMenu(c.Title_List);
    c.Title_List.get(0).AddItem(new Item(20201125));
    c.InputSelect();
    Assert.assertEquals(1, c.Title_List.get(0).Item_List().size());
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void selectPayment() {
    c.k = new PaymentMenu(new Title("test", 700));
    c.basket = 1;
    c.SelectPayment(0);
    Assert.assertEquals(0, c.del);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void cancelItem() {
    c.basket = 1;
    c.Payment = new Payment(1, 0);
    c.CancelItem();
    Assert.assertEquals(-666, c.basket);
    Assert.assertTrue(c.DVMStack.isEmpty());
    Assert.assertNull(c.Payment);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void showCardPay() {
    c.Payment = new Payment(1, 0);
    c.ShowCardPay();
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void cardPay() {
    c.k = new CardPayUI();
    c.Title_List.get(0).AddItem(new Item(20201125));
    c.basket = 1;
    c.Payment = new Payment(1, 0);
    c.CardPay();
    Assert.assertEquals(0, c.Title_List.get(0).Item_List().size());
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void showSmartPay() {
    c.Payment = new Payment(1, 0);
    c.ShowSmartPay();
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void smartPay() {
    c.k = new SmartPayUI();
    c.Title_List.get(0).AddItem(new Item(20201125));
    c.basket = 1;
    c.Payment = new Payment(1, 0);
    c.SmartPay();
    Assert.assertEquals(0, c.Title_List.get(0).Item_List().size());
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void returnItem() {
    c.Title_List.get(0).AddItem(new Item(20201125));
    c.basket = 1;
    c.Payment = new Payment(1, 0);
    c.ReturnItem(c.Title_List.get(0), false);
    Assert.assertEquals(0, c.Title_List.get(0).Item_List().size());
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void printCnumber() {
    c.basket = 1;
    c.Payment = new Payment(1, 1);
    c.PrintCnumber(c.Title_List.get(0), 1, 66666);
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void infoNoItem() {
    c.basket = 1;
    c.InfoNoItem();
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void reqFindDVM() {
    c.basket = 1;
    c.InfoNoItem();
    c.k.setVisible(false);
  }

  @Ignore
  @Test
  public void init() {
    c.basket = 1;
    c.Payment = new Payment(1, 0);
    c.CancelItem();
    Assert.assertEquals(-666, c.basket);
    Assert.assertTrue(c.DVMStack.isEmpty());
    Assert.assertNull(c.Payment);
  }
}