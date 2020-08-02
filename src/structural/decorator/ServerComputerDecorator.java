package structural.decorator;

public class ServerComputerDecorator implements ComputerInterface{

  ComputerInterface computerInterface;

  ServerComputerDecorator(ComputerInterface computerInterface) {
    this.computerInterface = computerInterface;
  }

  @Override
  public void process() {
    computerInterface.process();
  }

  @Override
  public int getRamSize() {
    return 12 + computerInterface.getRamSize();
  }

  @Override
  public int getGraphicMemorySize() {
    return  computerInterface.getGraphicMemorySize();
  }
}
