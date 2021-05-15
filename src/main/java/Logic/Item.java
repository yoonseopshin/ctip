package Logic;

public class Item {

  private Integer Expiration_Date;

  public Integer getExpiration_Date() {
    return Expiration_Date;
  }

  public void setExpiration_Date(Integer expiration_Date) {
    Expiration_Date = expiration_Date;
  }

  public Item(Integer Expiration_Date) {
    this.Expiration_Date = Expiration_Date;
  }

  public int Expiration_Date() {
    return this.Expiration_Date;
  }

}