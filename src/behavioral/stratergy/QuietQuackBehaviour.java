package behavioral.stratergy;

public class QuietQuackBehaviour implements QuackBehavior{
  @Override
  public void quack() {
    System.out.println("Quacking Quietly.");
  }
}
