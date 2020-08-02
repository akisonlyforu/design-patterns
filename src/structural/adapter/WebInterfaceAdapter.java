package structural.adapter;

public class WebInterfaceAdapter implements WebInterface{

  OldWebInterface oldWebInterface;

  WebInterfaceAdapter(OldWebInterface oldWebInterface) {
    this.oldWebInterface = oldWebInterface;
  }

  @Override
  public void get() {
    oldWebInterface.find();
  }

  @Override
  public void select() {
    oldWebInterface.click();
  }
}
