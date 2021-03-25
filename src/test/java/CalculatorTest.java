import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {

    Calculator<Integer> calculator;

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
        int result = calculator.subtract(10, 4);
        Assert.assertEquals(6, result);
    }

    @Test
    public void testProduct() {
        int result = calculator.multiply(27, 527);
        Assert.assertEquals(14229, result);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivision() {
        calculator.divide(201511271, 0);
    }
}
