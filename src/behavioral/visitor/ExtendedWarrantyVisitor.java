package behavioral.visitor;

public class ExtendedWarrantyVisitor implements ShoppingCartVisitor {
  @Override
  public int visit(BookStationaryItem book) {
    System.out.println("Warranty extended by " + (book.getWarrantyInMonths() + 1) + " Month(s).");
    return book.getWarrantyInMonths() + 1;
  }

  @Override
  public int visit(ClockStationaryItem clock) {
    System.out.println("Warranty extended by " + (clock.getWarrantyInMonths() + 3) + " Month(s).");
    return clock.getWarrantyInMonths() + 3;
  }
}
