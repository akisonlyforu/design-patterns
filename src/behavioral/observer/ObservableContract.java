package behavioral.observer;

public interface ObservableContract {

  void add(ObserverContract observer);

  void remove(ObserverContract observer);

  void notifyObservers();

  void updateItem(Item item);
}
