package behavioral.stratergy;

public class CityDuck extends Duck{

  public CityDuck(QuackBehavior quackBehavior, FlyBehaviour flyBehaviour) {
    super(quackBehavior, flyBehaviour);
  }

  @Override
  void type() {
    System.out.println("City Duck Type");
  }
}
