package behavioral.template;

public class GamingDesktopAssemble extends DesktopAssembleTemplate {

  public GamingDesktopAssemble() {
    System.out.println("Assembling Gaming Desktop");
  }

  @Override
  void addMonitor() {
    System.out.println("Choosing High Refresh Rate Monitor");
  }

  @Override
  void addKeyboard() {
    System.out.println("Choosing Gaming Keyboard");
  }

  @Override
  void addMouse() {
    System.out.println("Choosing Gaming Mouse");
  }

  @Override
  void addCPU() {
    System.out.println("Choosing High Configuration CPU");
  }
}
