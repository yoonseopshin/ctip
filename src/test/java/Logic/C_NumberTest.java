package Logic;

import org.junit.*;

import static org.junit.Assert.*;

public class C_NumberTest {
    private C_Number CN=new C_Number(1,1);
    int i=CN.CreateCnumber(1,1);


    @Before
    public void beforeTest() throws Exception {
        System.out.println("Before");
    }
    @Test
    public void getC_number_t() {
        Assert.assertEquals(i,CN.getC_Number_t());
    }
    @Test
    public void setC_number_t() {
        CN.setC_Number_t(i);
    }
    @After
    public void printAfter() throws Exception{
        System.out.println("After");
    }
}