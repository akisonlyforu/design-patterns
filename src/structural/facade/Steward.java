package structural.facade;

public class Steward {

  Special special;

   void getSpecials(String course) {
    if (course.equalsIgnoreCase("Veg")) {
      special = new VegSpecial();
      special.getCourse();
    } else if (course.equalsIgnoreCase("Non-Veg")) {
      special = new NonVegSpecial();
      special.getCourse();
    } else
      System.out.println("Wrong Choice.");
  }

}
