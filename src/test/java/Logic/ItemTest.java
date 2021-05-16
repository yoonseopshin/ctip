package Logic;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {

  Item item= new Item(20201125);;

  @Test
  public void testGetExpiration_Date() {
    item.setExpiration_Date(20211026);
    int ExpectedResult=20211026;
    int ActualResult=item.getExpiration_Date();
    Assert.assertEquals(ExpectedResult, ActualResult);
  }
}