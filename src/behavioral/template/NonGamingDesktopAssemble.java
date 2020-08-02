package behavioral.template;

public class NonGamingDesktopAssemble extends DesktopAssembleTemplate {

  public NonGamingDesktopAssemble() {
    System.out.println("Assembling Non Gaming Desktop");
  }

  @Override
  void addMonitor() {
    System.out.println("Choosing Normal Refresh Rate Monitor");
  }

  @Override
  void addKeyboard() {
    System.out.println("Choosing Normal Keyboard");
  }

  @Override
  void addMouse() {
    System.out.println("Choosing Normal Mouse");
  }

  @Override
  void addCPU() {
    System.out.println("Choosing Normal Configuration CPU");
  }
}
