package logic;

public class DVM {

  private static int currentID = 1;
  private static double currentX = 1.0;
  private static double currentY = 1.0;
  private int id; // 메시지 받은 DVM ID
  private double addressX; // 실제 주소
  private double addressY;

  public DVM(int id, double x, double y) {
    this.id = id;
    this.addressX = x;
    this.addressY = y;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getAddressX() {
    return addressX;
  }

  public void setAddressX(double addressX) {
    this.addressX = addressX;
  }

  public double getAddressY() {
    return addressY;
  }

  public void setAddressY(double addressY) {
    this.addressY = addressY;
  }

  public static int getCurrentID() {
    return currentID;
  }

  public static void setCurrentID(int currentID) {
    DVM.currentID = currentID;
  }

  public static double getCurrentX() {
    return currentX;
  }

  public static void setCurrentX(double currentX) {
    DVM.currentX = currentX;
  }

  public static double getCurrentY() {
    return currentY;
  }

  public static void setCurrentY(double currentY) {
    DVM.currentY = currentY;
  }

}