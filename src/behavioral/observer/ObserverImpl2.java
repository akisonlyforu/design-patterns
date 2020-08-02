package behavioral.observer;

public class ObserverImpl2 implements ObserverContract{

  Item item;

  @Override
  public void update(Item item) {
    this.item = item;
    getItem();
  }

  public void getItem() {
    System.out.println("For Observer2, Item name is: " + item.getName() + " and Item price is: " + item.getPrice());
  }
}
