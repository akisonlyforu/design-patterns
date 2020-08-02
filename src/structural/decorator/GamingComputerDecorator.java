package structural.decorator;

public class GamingComputerDecorator implements ComputerInterface{

  ComputerInterface computerInterface;

  GamingComputerDecorator(ComputerInterface computerInterface) {
    this.computerInterface = computerInterface;
  }

  @Override
  public void process() {
    computerInterface.process();
  }

  @Override
  public int getRamSize() {
    return computerInterface.getRamSize();
  }

  @Override
  public int getGraphicMemorySize() {
    return 8 + computerInterface.getGraphicMemorySize();
  }
}
