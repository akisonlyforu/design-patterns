package behavioral.observer;

public class ObserverImpl1 implements ObserverContract{

  Item item;

  @Override
  public void update(Item item) {
    this.item = item;
    getItem();
  }

  public void getItem() {
    System.out.println("For Observer1, Item name is: " + item.getName() + " and Item price is: " + item.getPrice());
  }
}
