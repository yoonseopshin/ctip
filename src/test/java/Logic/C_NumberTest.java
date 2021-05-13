package Logic;

import org.junit.Test;

import static org.junit.Assert.*;

public class C_NumberTest {
    private C_Number CN;
    @Test
    public void createCnumber() {
        CN= new C_Number(1,1);
        assertEquals(CN.CreateCnumber(1,1),CN.getC_Number_t());
    }
}