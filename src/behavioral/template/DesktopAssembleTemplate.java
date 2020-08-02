package behavioral.template;

public abstract class DesktopAssembleTemplate {

  abstract void addMonitor();

  abstract void addKeyboard();

  abstract void addMouse();

  abstract void addCPU();

  void assemble() {
    addMonitor();
    addKeyboard();
    addMouse();
    addCPU();
    test();
  }

  final void test() {
    System.out.println("Tested");
  }
}
