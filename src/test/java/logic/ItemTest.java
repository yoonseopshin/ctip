package logic;

import org.junit.Assert;
import org.junit.Test;

public class ItemTest {

  Item item = new Item(20201125);

  @Test
  public void testGetExpirationDate() {
    item.setExpirationDate(20211026);
    int ExpectedResult = 20211026;
    int ActualResult = item.getExpirationDate();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }

    public void testSetExpirationDate() {
    }
}