package behavioral.stratergy;

public class StrategyMain {
  public static void main(String[] args) {
    QuackBehavior loudQuackBehavior = new LoudQuackBehaviour();
    QuackBehavior quietQuackBehaviour= new QuietQuackBehaviour();
    FlyBehaviour canFlyBehaviour = new CanFlyBehaviour();
    FlyBehaviour cannotFlyBehaviour = new CannotFlyBehaviour();
    Duck mountainDuck = new MountainDuck(quietQuackBehaviour, cannotFlyBehaviour);
    System.out.println("For Mountain Duck: -> ");
    mountainDuck.fly();
    mountainDuck.quack();
    mountainDuck.eat();
    Duck cityDuck = new CityDuck(loudQuackBehavior, canFlyBehaviour);
    System.out.println("For City Duck: -> ");
    cityDuck.fly();
    cityDuck.quack();
    cityDuck.eat();

  }
}
