package structural.adapter;

public class WebInterfaceImpl implements WebInterface{


  @Override
  public void get() {
    System.out.println("GET WebInterface Impl");
  }

  @Override
  public void select() {
    System.out.println("SELECT WebInterface Impl");
  }
}
