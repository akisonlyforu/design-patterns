package creational.prototype;

public class PrototypeMain {
  public static void main(String[] args) {
    HomeWork homeWork = new HomeWorkImpl(1, "abc");
    System.out.println("Homework Original Id is: " + homeWork.getId());
    System.out.println("Homework Original homework is: " + homeWork.getHomeWork());
    HomeWork homeWorkCopied = homeWork.copyHomeWork(homeWork);
    homeWorkCopied.setId(2);
    System.out.println("Homework Copied Id is: " + homeWorkCopied.getId());
    System.out.println("Homework Copied homework is: " + homeWorkCopied.getHomeWork());
  }
}
