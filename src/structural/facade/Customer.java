package structural.facade;

public class Customer {
  public static void main(String[] args) {
    Steward steward = new Steward();
    steward.getSpecials("Veg");
  }
}
