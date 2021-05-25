package Logic;

public class DVM {

    private static int CurrentID = 2;
    private static double CurrentX = 1.0;
    private static double CurrentY = 1.0;
    private int ID; // 메시지 받은 DVM ID
    private double Address_X; // 실제 주소
    private double Address_Y;

    public DVM(int ID, double X, double Y) {
        this.ID = ID;
        this.Address_X = X;
        this.Address_Y = Y;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public double getAddress_X() {
        return Address_X;
    }

    public void setAddress_X(double address_X) {
        Address_X = address_X;
    }

    public double getAddress_Y() {
        return Address_Y;
    }

    public void setAddress_Y(double address_Y) {
        Address_Y = address_Y;
    }

    public static int getCurrentID() { return CurrentID; }

    public static void setCurrentID(int currentID) { CurrentID = currentID; }

    public static double getCurrentX() { return CurrentX; }

    public static void setCurrentX(double currentX) { CurrentX = currentX; }

    public static double getCurrentY() { return CurrentY; }

    public static void setCurrentY(double currentY) { CurrentY = currentY; }

}