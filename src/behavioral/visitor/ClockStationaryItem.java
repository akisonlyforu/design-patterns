package behavioral.visitor;

import java.util.Calendar;
import java.util.Date;

public class ClockStationaryItem implements StationaryItem{

  private final int price;
  private final String typeOfItem;
  private final Date dateOfArrival;
  private final int warrantyInMonths;


  ClockStationaryItem(ClockBuilder builder) {
    this.price = builder.price;
    this.typeOfItem = builder.typeOfItem;
    this.dateOfArrival = builder.dateOfArrival;
    this.warrantyInMonths = builder.warrantyInMonths;
  }

  @Override
  public int accept(ShoppingCartVisitor shoppingCartVisitor) {
    return shoppingCartVisitor.visit(this);
  }

  @Override
  public int getPrice() {
    return price;
  }

  @Override
  public String getTypeOfItem() {
    return typeOfItem;
  }

  @Override
  public Date getDateOfArrival() {
    return dateOfArrival;
  }

  @Override
  public int getWarrantyInMonths() {
    return warrantyInMonths;
  }

  static class ClockBuilder {
    private int price;
    private String typeOfItem;
    private Date dateOfArrival;
    private int warrantyInMonths;


    public ClockBuilder setPrice(int price) {
      this.price = price;
      return this;
    }

    public ClockBuilder setTypeOfItem(String typeOfItem) {
      this.typeOfItem = typeOfItem;
      return this;
    }

    public ClockBuilder setDateOfArrival(Date dateOfArrival) {
      this.dateOfArrival = dateOfArrival;
      return this;
    }

    public ClockBuilder setWarrantyInMonths(int warrantyInMonths) {
      this.warrantyInMonths = warrantyInMonths;
      return this;
    }

    public ClockStationaryItem build() {
      return new ClockStationaryItem(this);
    }
  }
}
