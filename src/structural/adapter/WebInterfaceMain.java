package structural.adapter;

public class WebInterfaceMain {
  public static void main(String[] args) {
    WebInterface webInterface = new WebInterfaceImpl();
    webInterface.get();
    webInterface.select();

    WebInterface webInterfaceAdapter = new WebInterfaceAdapter(new OldWebInterfaceImpl());
    webInterfaceAdapter.get();
    webInterfaceAdapter.select();
  }
}
