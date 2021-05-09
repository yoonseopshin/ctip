package DVM;

import java.util.*;

/**
 * 
 */
public class C_Number {

	Title ti;
    int DvmID;
    int M_Number;
    int C_Number_t;
    
    public C_Number() {
		
	}

	public C_Number(Title ti, int id) {
    	this.ti = ti;
    	this.DvmID =id;
    }

	@Override
	public String toString() {
		return "C_Number [음료명 :"+ti.Name + ", 자판기 id=" + DvmID + ", 인증번호=" + C_Number_t
				+ "]";
	}

	public Title getTi() {
		return ti;
	}

	public void setTi(Title ti) {
		this.ti = ti;
	}

	public int getDvmID() {
		return DvmID;
	}

	public void setDvmID(int dvmID) {
		DvmID = dvmID;
	}

	public int getM_Number() {
		return M_Number;
	}

	public void setM_Number(int m_Number) {
		M_Number = m_Number;
	}

	public int getC_Number_t() {
		return C_Number_t;
	}

	public void setC_Number_t(int c_Number_t) {
		C_Number_t = c_Number_t;
	}

    

}