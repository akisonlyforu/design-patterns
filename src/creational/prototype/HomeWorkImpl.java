package creational.prototype;

public class HomeWorkImpl implements HomeWork {

  int id;
  String homeWork;

  @Override
  public int getId() {
    return id;
  }

  @Override
  public String getHomeWork() {
    return homeWork;
  }

  public HomeWorkImpl(int id, String homeWork) {
    this.id = id;
    this.homeWork = homeWork;
  }

  @Override
  public void doHomeWork(int id, String homeWork) {
    this.id = id;
    this.homeWork = homeWork;
    System.out.println("Doing Homework");
  }

  @Override
  public HomeWork copyHomeWork(HomeWork homeWork) {
    return new HomeWorkImpl(homeWork.getId(), homeWork.getHomeWork());
  }

  @Override
  public void setId(int id) {
    this.id = id;
  }
}
