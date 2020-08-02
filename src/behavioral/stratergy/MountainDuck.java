package behavioral.stratergy;

public class MountainDuck extends Duck{

  public MountainDuck(QuackBehavior quackBehavior, FlyBehaviour flyBehaviour) {
    super(quackBehavior, flyBehaviour);
  }

  @Override
  void type() {
    System.out.println("Mountain Duck Type");
  }
}
