package Logic;

public class DVM {

  int ID; // 메세지 보낼 타겟 아이디

  private double Address_X; // 실제 주소
  private double Address_Y;

  public DVM(int ID, double X, double Y) {
    this.ID = ID;
    this.Address_X = X;
    this.Address_Y = Y;
  }

  public int ID() {
    return this.ID;
  }

  public double getX() {
    return this.Address_X;
  }

  public double getY() {
    return this.Address_Y;
  }


}