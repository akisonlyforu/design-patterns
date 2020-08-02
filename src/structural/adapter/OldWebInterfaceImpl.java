package structural.adapter;

public class OldWebInterfaceImpl implements OldWebInterface{

  @Override
  public void find() {
    System.out.println("FIND OldWebInterface Impl");
  }

  @Override
  public void click() {
    System.out.println("CLICK OldWebInterface Impl");
  }
}
