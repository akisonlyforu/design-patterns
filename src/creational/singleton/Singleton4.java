package creational.singleton;

class Singleton4
{
  private volatile static Singleton4 obj;

  private Singleton4() {}

  public static Singleton4 getInstance()
  {
    if (obj == null)
    {
      synchronized (Singleton4.class)
      {
        if (obj==null)
          obj = new Singleton4();
      }
    }
    return obj;
  }
} 