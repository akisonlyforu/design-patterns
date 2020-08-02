package behavioral.stratergy;

public class CannotFlyBehaviour implements FlyBehaviour{
  @Override
  public void fly() {
    System.out.println("Cannot Fly");
  }
}
