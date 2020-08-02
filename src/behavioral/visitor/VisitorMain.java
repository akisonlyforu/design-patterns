package behavioral.visitor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VisitorMain {
  public static void main(String[] args) {
    BookStationaryItem book = new BookStationaryItem.BookBuilder().setPrice(100).setDateOfArrival(new Date(2020, 2,
        19)).setTypeOfItem("Book").setWarrantyInMonths(0).build();
    ClockStationaryItem clock = new ClockStationaryItem.ClockBuilder().setPrice(100).setDateOfArrival(new Date(2020, 2,
        19)).setTypeOfItem("Clock").setWarrantyInMonths(0).build();
    List<StationaryItem> stationaryItems = new ArrayList<>();
    stationaryItems.add(book);
    stationaryItems.add(clock);
    checkout(stationaryItems);
  }

  static void checkout(List<StationaryItem> stationaryItemList) {
    ShoppingCartVisitor discountPriceVisitor = new DiscountPriceVisitor();
    ShoppingCartVisitor extendedWarrantyVisitor = new ExtendedWarrantyVisitor();
    int totalPrice = 0;
    for (StationaryItem stationaryItem: stationaryItemList) {
      totalPrice += stationaryItem.accept(discountPriceVisitor);
      stationaryItem.accept(extendedWarrantyVisitor);
    }
  }
}
