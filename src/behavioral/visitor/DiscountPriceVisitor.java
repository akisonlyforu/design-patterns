package behavioral.visitor;

public class DiscountPriceVisitor implements ShoppingCartVisitor{
  @Override
  public int visit(BookStationaryItem book) {
    System.out.println("Book Price reduced from " + book.getPrice() + " to " + (0.8*book.getPrice()));
    return (int) (0.8 * book.getPrice());
  }

  @Override
  public int visit(ClockStationaryItem clock) {
    System.out.println("Clock Price reduced from " + clock.getPrice() + " to " + (0.7*clock.getPrice()));
    return (int) (0.7 * clock.getPrice());
  }
}
