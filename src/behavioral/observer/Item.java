package behavioral.observer;

public class Item {

  int price;
  String name;

  public Item(int price, String name) {
    this.price = price;
    this.name = name;
  }

  public int getPrice() {
    return price;
  }

  public String getName() {
    return name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public void setName(String name) {
    this.name = name;
  }
}
