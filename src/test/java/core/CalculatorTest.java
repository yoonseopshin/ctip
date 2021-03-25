package core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

//  private transient Calculator<Integer> calculator;
  private Calculator<Integer> calculator;

  @Before
  public void init() {
    calculator = new IntCalculator();
  }

  @Test
  public void testSum() {
    int result = calculator.add(3, 5);
    Assert.assertEquals(8, result);
  }

  @Test
  public void testDifference() {
    int result = calculator.subtract(100, 4);
    Assert.assertEquals(96, result);
  }

  @Test
  public void testProduct() {
    int result = calculator.multiply(27, 527);
    Assert.assertEquals(14229, result);
  }

  @Test
  public void testDivision() {
    int result = calculator.divide(9, 3);
    Assert.assertEquals(3, result);
  }

  @Test(expected = ArithmeticException.class)
  public void testWrongDivision() {
    calculator.divide(201511271, 0);
    Assert.fail("It doesn't occur.");
  }

}
