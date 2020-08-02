package behavioral.visitor;

public interface ShoppingCartVisitor {

  int visit(BookStationaryItem book);

  int visit(ClockStationaryItem clock);
}
