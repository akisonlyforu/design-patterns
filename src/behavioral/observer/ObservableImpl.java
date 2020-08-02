package behavioral.observer;

import java.util.LinkedList;
import java.util.List;

public class ObservableImpl implements ObservableContract {

  private List<ObserverContract> observers = new LinkedList<>();
  Item item;

  @Override
  public void add(ObserverContract observer) {
    observers.add(observer);
  }

  @Override
  public void remove(ObserverContract observer) {
    if(observers.size() != 0)
      observers.remove(observer);
  }

  @Override
  public void notifyObservers() {
    for (ObserverContract observerContract: observers)
      observerContract.update(item);
  }

  public Item getItem() {
    return item;
  }

  public void updateItem(Item item) {
    this.item = item;
    notifyObservers();
  }

}
