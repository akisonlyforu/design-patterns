package behavioral.stratergy;

public class LoudQuackBehaviour implements QuackBehavior{
  @Override
  public void quack() {
    System.out.println("Quacking Loudly.");
  }
}
