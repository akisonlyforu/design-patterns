package behavioral.template;

public class DesktopAssembleMain {

  public static void main(String[] args) {
    DesktopAssembleTemplate gamingDesktopAssemble = new GamingDesktopAssemble();
    gamingDesktopAssemble.assemble();
    System.out.println();
    DesktopAssembleTemplate nonGamingDesktopAssemble = new NonGamingDesktopAssemble();
    nonGamingDesktopAssemble.assemble();
  }

}
