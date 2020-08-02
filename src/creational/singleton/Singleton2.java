package creational.singleton;

class Singleton2
{
  private static Singleton2 obj;

  private Singleton2() {}

  public static synchronized Singleton2 getInstance()
  {
    if (obj==null)
      obj = new Singleton2();
    return obj;
  }
}