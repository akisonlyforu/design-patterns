package behavioral.stratergy;

public abstract class Duck {

  QuackBehavior quackBehavior;
  FlyBehaviour flyBehaviour;

  public Duck(QuackBehavior quackBehavior, FlyBehaviour flyBehaviour) {
    this.quackBehavior = quackBehavior;
    this.flyBehaviour = flyBehaviour;
  }

  void quack() {
    quackBehavior.quack();
  }

  void fly() {
    flyBehaviour.fly();
  }

  void eat() {
    System.out.println("Default Eat");
  }

  abstract void type();

}
