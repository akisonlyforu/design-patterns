package structural.decorator;

public class DecoratorMain {
  public static void main(String[] args) {
    ComputerInterface computerInterface = new ComputerImpl(4, 2);
    System.out.println("DesktopAssembleTemplate Ram is: " + computerInterface.getRamSize() + " and graphic is: " + computerInterface.getGraphicMemorySize());
    GamingComputerDecorator gamingComputerDecorator = new GamingComputerDecorator(computerInterface);
    System.out.println("Gaming DesktopAssembleTemplate Ram is: " + gamingComputerDecorator.getRamSize() + " and graphic is: " + gamingComputerDecorator.getGraphicMemorySize());
    ServerComputerDecorator serverComputerDecorator = new ServerComputerDecorator(computerInterface);
    System.out.println("Server DesktopAssembleTemplate Ram is: " + serverComputerDecorator.getRamSize() + " and graphic is: " + serverComputerDecorator.getGraphicMemorySize());

  }
}
