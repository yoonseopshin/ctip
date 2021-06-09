package logic;

import java.security.SecureRandom;
import java.util.Random;

public class CNumber {

  private int titleId;
  private int DvmID;
  private int cNumberT;
  private static Random rand = new SecureRandom();

  /*
  {
    try {
      new SecureRandom();
      rand = SecureRandom.getInstanceStrong();
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException("Failed to instantiate random number generator", e);
    }
  }
  */

  public CNumber(int titleId, int id) {
    this.titleId = titleId;
    this.DvmID = id;
  }

  public int createCNumber() {
    //수정
    if (!(Controller.getCm().getChCList().isEmpty())) {
      MessageQueue.setCNum(Controller.getCm().getChCList().size());
      for (Integer key : Controller.getCm().getChCList().keySet()) {
        Message msg = new Message(DVM.getCurrentID());
        msg.setMsg(Controller.getCm().getChCList().get(key).getDvmID(), 6, key);
      }
      while (true) {
        if (Controller.getCm().checkCNumber2(-1)) {
          break;
        }
      }
      Controller.getCm().getChCList().remove(-1);
    }
    String numStr;
    do {
      numStr = randNumber();
    } while (numStr.equals("000000") || numStr
            .equals(Integer.toString(Controller.getCm().getMNumber())) || Controller.getCm()
            .checkCNumber2(Integer.parseInt(numStr)));
    cNumberT = Integer.parseInt(numStr);
    Message message = new Message(DVM.getCurrentID());
    message.setMsg(this.DvmID, 3, titleId, cNumberT);
    CNumber cn = new CNumber(titleId, DvmID);
    cn.setCNumberT(cNumberT);
    Controller.getCm().addChCNumber(cn);

    return cNumberT;
  }

  public String randNumber() {
    int len = 6;
    String numStr = ""; //난수가 저장될 변수
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

  public int getTitleId() {
    return titleId;
  }

  public void setTitleId(int titleId) {
    this.titleId = titleId;
  }

  public int getDvmID() {
    return DvmID;
  }

  public void setDvmID(int dvmID) {
    DvmID = dvmID;
  }

  public int getCNumberT() {
    return cNumberT;
  }

  public void setCNumberT(int cNumberT) {
    this.cNumberT = cNumberT;
  }

  public static Random getRand() {
    return rand;
  }

  public static void setRand(Random rand) {
    CNumber.rand = rand;
  }

}