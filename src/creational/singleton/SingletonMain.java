package creational.singleton;

public class SingletonMain {
  public static void main(String[] args) {
    Singleton4 singletonInstance1 = Singleton4.getInstance();
    System.out.println(singletonInstance1);
    Singleton4 singletonInstance2 = Singleton4.getInstance();
    System.out.println(singletonInstance2);
  }
}
