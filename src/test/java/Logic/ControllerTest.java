package Logic;

import GUI.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import javax.swing.*;

import static org.junit.Assert.*;

public class ControllerTest {
    private Controller c = new Controller();
/*
    @Ignore
    @Before
    public void initController() {
        c = new Controller();
        c.setK(new JFrame());
    }

    @Ignore
    @Test
    public void inputSelect() {
        int del;
        c.setK(new MainMenu(c.getTitle_List()));
        del = c.InputSelect();
        Assert.assertEquals(-2, del);
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void showInputLine() {
        int del;
        c.setK(new MainMenu(c.Title_List));
        del = c.InputSelect();
        Assert.assertEquals(0, del);
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void inputCnumber() {
        int del;
        c.setK(new InputLine());
        del = c.InputCnumber();
        Assert.assertEquals(0, del);
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void infoCnumberError() {
        int del;
        c.setK(new InputLine());
        del = c.InputCnumber();
        Assert.assertEquals(1, del);
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void manShowTitle() {
        c.ManShowTitle();
        Assert.assertEquals(0, c.getDel());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void manSelectTitle() {
        c.ManShowTitle();
        Assert.assertEquals(0, c.getDel());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void manShowItem() {
        c.Title_List.get(0).AddItem(new Item(20250101));
        c.ManShowItem(1);
        Assert.assertEquals(0, c.getDel());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void manEditItem() {
        c.Title_List.get(0).AddItem(new Item(20250101));
        c.ManShowItem(1);
        Assert.assertEquals(2, c.getTitle_List().get(0).getItem_List().size());
        c.ManShowItem(1);
        Assert.assertEquals(0, c.getTitle_List().get(0).getItem_List().size());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void printSelectPay() {
        c.setK(new MainMenu(c.Title_List));
        c.Title_List.get(0).AddItem(new Item(20201125));
        c.InputSelect();
        Assert.assertEquals(1, c.Title_List.get(0).getItem_List().size());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void selectPayment() {
        c.setK(new PaymentMenu(new Title("test", 700)));
        c.setBasket(1);
        c.SelectPayment(0);
        Assert.assertEquals(0, c.getDel());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void cancelItem() {
        c.setBasket(1);
        c.setPayment(new Payment(1, 0));
        c.CancelItem();
        Assert.assertEquals(-666, c.getBasket());
        Assert.assertTrue(c.DVMStack.isEmpty());
        Assert.assertNull(c.getPayment());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void showCardPay() {
        c.setPayment(new Payment(1, 0));
        c.ShowCardPay();
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void cardPay() {
        c.setK(new CardPayUI());
        c.Title_List.get(0).AddItem(new Item(20201125));
        c.setBasket(1);
        c.setPayment(new Payment(1, 0));
        c.CardPay();
        Assert.assertEquals(0, c.getTitle_List().get(0).getItem_List().size());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void showSmartPay() {
        c.setPayment(new Payment(1, 0));
        c.ShowSmartPay();
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void smartPay() {
        c.setK(new SmartPayUI());
        c.Title_List.get(0).AddItem(new Item(20201125));
        c.setBasket(1);
        c.setPayment(new Payment(1, 0));
        c.SmartPay();
        Assert.assertEquals(0, c.Title_List.get(0).getItem_List().size());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void returnItem() {
        c.Title_List.get(0).AddItem(new Item(20201125));
        c.setBasket(1);
        c.setPayment(new Payment(1, 0));
        c.ReturnItem(c.Title_List.get(0), false);
        Assert.assertEquals(0, c.Title_List.get(0).getItem_List().size());
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void printCnumber() {
        c.setBasket(1);
        c.setPayment(new Payment(1, 1));
        c.PrintCnumber(c.Title_List.get(0), 1, 66666);
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void infoNoItem() {
        c.setBasket(1);
        c.InfoNoItem();
        c.getK().setVisible(false);
    }

    @Ignore
    @Test
    public void reqFindDVM() {
        c.setBasket(1);
        c.InfoNoItem();
        c.getK().setVisible(false);
    }
*/
    @Test
    public void testInit() {
        c.setBasket(10);
        c.setPayment(new Payment(c.getBasket(), 1));
        c.init();
        int ExpectedResult = -666;
        int ActualResult = c.getBasket();
        Assert.assertEquals(ExpectedResult, ActualResult);
        Assert.assertNull(c.getPayment());
    }
}