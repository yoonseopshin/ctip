package Logic;

import java.util.Random;

public class C_Number {

    private int title_id;
    private int DvmID;
    private int C_Number_t;

    public C_Number(int title_id, int id) {
        this.title_id = title_id;
        this.DvmID = id;
    }


    public int CreateCnumber() {
        //수정
        if (!(Controller.getCM().getCh_C_List().isEmpty())) {
            Message_Queue.setCnum(Controller.getCM().getCh_C_List().size());
            for (Integer key : Controller.getCM().getCh_C_List().keySet()) {
                Message msg = new Message(DVM.getCurrentID());
                msg.setmsg(Controller.getCM().getCh_C_List().get(key).getDvmID(), 6, key);
            }
            while (true) {
                if (Controller.getCM().CheckCnumber2(-1))
                    break;
            }
            Controller.getCM().getCh_C_List().remove(-1);
        }
        String numStr = new String();
        do {
            numStr = randnumber();
        } while (numStr.equals("000000") || numStr.equals(Integer.toString(Controller.getCM().getM_Number())) || Controller.getCM().CheckCnumber2(Integer.parseInt(numStr)));
        C_Number_t = Integer.parseInt(numStr);
        Message message = new Message(DVM.getCurrentID());
        message.setmsg(this.DvmID, 3, title_id, C_Number_t);
        C_Number cn = new C_Number(title_id, DvmID);
        cn.setC_Number_t(C_Number_t);
        Controller.getCM().AddchCnumber(cn);

        return C_Number_t;
    }

    public String randnumber() {

        Random rand = new Random();
        int len = 6;
        String numStr = new String(); //난수가 저장될 변수
        String ran = Integer.toString(rand.nextInt(9) + 1);  //첫번째 숫자 0이 아님
        numStr += ran;
        numStr += Integer.toString(DVM.getCurrentID() - 1); // 두번째 자릿수 => DVMID
        numStr += Integer.toString(this.DvmID - 1);
        for (int i = 3; i < len; i++) {
            //0~9 까지 난수 생성
            ran = Integer.toString(rand.nextInt(10));
            numStr += ran;
        }

        return numStr;
    }

    public int getTitle_id() { return title_id; }

    public void setTitle_id(int title_id) { this.title_id = title_id; }

    public int getDvmID() { return DvmID; }

    public void setDvmID(int dvmID) { DvmID = dvmID; }

    public int getC_Number_t() { return C_Number_t; }

    public void setC_Number_t(int c_Number_t) { C_Number_t = c_Number_t; }

}