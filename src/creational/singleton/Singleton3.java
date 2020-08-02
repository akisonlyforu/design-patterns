package creational.singleton;

class Singleton3
{
  private static Singleton3 obj = new Singleton3();

  private Singleton3() {}

  public static Singleton3 getInstance()
  {
    return obj;
  }
} 