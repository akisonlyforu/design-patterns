package structural.facade;

public class NonVegSpecial implements Special{
  @Override
  public void getCourse() {
    System.out.println("Your Non Veg Course is here.");
  }
}
