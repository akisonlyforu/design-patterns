package behavioral.visitor;

import java.util.Date;

public interface StationaryItem {

  int accept(ShoppingCartVisitor shoppingCartVisitor);

  int getPrice();

  String getTypeOfItem();

  Date getDateOfArrival();

  int getWarrantyInMonths();
}
