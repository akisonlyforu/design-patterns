package structural.decorator;

public class ComputerImpl implements ComputerInterface{

  int ramSize;
  int graphicMemorySize;

  public ComputerImpl(int ramSize, int graphicMemorySize) {
    this.ramSize = ramSize;
    this.graphicMemorySize = graphicMemorySize;
  }

  @Override
  public void process() {
    System.out.println("Processing.");
  }

  @Override
  public int getRamSize() {
    return ramSize;
  }

  @Override
  public int getGraphicMemorySize() {
    return graphicMemorySize;
  }
}
