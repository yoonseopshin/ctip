package Logic;

import org.junit.*;

import static org.junit.Assert.*;

public class C_NumberTest {
    private C_Number CN=new C_Number(1,1);
    @Test
    public void CreateCnumber() {
        Assert.assertEquals(CN.CreateCnumber(1,1),CN.getC_Number_t());
    }

}