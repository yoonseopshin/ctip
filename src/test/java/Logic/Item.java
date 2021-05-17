package Logic;

public class Item {

  private int Expiration_Date;

  public Item(int Expiration_Date) {
    this.Expiration_Date = Expiration_Date;
  }

  public int getExpiration_Date() {
    return Expiration_Date;
  }

  public void setExpiration_Date(int expiration_Date) {
    Expiration_Date = expiration_Date;
  }

}