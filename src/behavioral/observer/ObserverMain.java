package behavioral.observer;

public class ObserverMain {

  public static void main(String[] args) {
    ObservableContract observableContract = new ObservableImpl();
    ObserverContract observerContract1 = new ObserverImpl1();
    ObserverContract observerContract2 = new ObserverImpl2();
    Item item = new Item(0, "ABC");
    observableContract.add(observerContract1);
    item.setPrice(1);
    observableContract.updateItem(item);
    observableContract.add(observerContract2);
    item.setPrice(2);
    item.setName("ABCD");
    observableContract.updateItem(item);
    observableContract.remove(observerContract1);
    item.setName("ABC");
    observableContract.updateItem(item);
  }
}
