package logic;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TitleTest {

  private final Title t = new Title("코카콜라", 700);

  @Test
  public void testAddItem() {
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    int ExpectedResult = 5;
    int ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testDeleteItem() {
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    ArrayList<Integer> delitemlist = new ArrayList<>();
    delitemlist.add(0);
    delitemlist.add(1);
    delitemlist.add(2);
    t.deleteItem(delitemlist);
    int ExpectedResult = 2;
    int ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testGetItemList() {
    t.addItem(new Item(20201025));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    int ExpectedResult = 20201025;
    int ActualResult = t.getItemList().get(0).getExpirationDate();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testUpdateStock() {
    t.addItem(new Item(20201025));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.addItem(new Item(20201125));
    t.updateStock(1, true);
    int ExpectedResult = 1;
    int ActualResult = t.getHold();
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = 4;
    ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
    t.updateStock(1, false);
    ExpectedResult = 1;
    ActualResult = t.getHold();
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = 4;
    ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
    t.updateStock(0, true);
    ExpectedResult = 0;
    ActualResult = t.getHold();
    Assert.assertEquals(ExpectedResult, ActualResult);
    t.updateStock(0, true);
    ExpectedResult = 0;
    ActualResult = t.getHold();
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = 4;
    ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
    t.updateStock(0, false);
    ExpectedResult = 0;
    ActualResult = t.getHold();
    Assert.assertEquals(ExpectedResult, ActualResult);
    ExpectedResult = 3;
    ActualResult = t.getItemList().size();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

  @Test
  public void testCheckStock() {
    t.addItem(new Item(20201025));
    Assert.assertTrue(t.checkStock());
    t.updateStock(0, false);
    Assert.assertFalse(t.checkStock());
  }
  @Test
  public void testGetName() {
    t.setName("코카콜라");
    Assert.assertEquals("코카콜라",t.getName());
  }

  @Test
  public void testGetPrice() {
    t.setPrice(1000);
    Assert.assertEquals(1000,t.getPrice());
  }

  @Test
  public void testSetItemList() {
    ArrayList<Item> i = new ArrayList();
    t.setItemList(i);
    Assert.assertEquals(i, t.getItemList());
  }

  @Test
  public void testGetHold() {
    t.setHold(1);
    Assert.assertEquals(1, t.getHold());
  }
}