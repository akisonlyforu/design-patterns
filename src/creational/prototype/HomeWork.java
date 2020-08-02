package creational.prototype;

public interface HomeWork {

  int getId();

  String getHomeWork();

  void doHomeWork(int id, String homeWork);

  HomeWork copyHomeWork(HomeWork homeWork);

  void setId(int id);
}
