package behavioral.visitor;

import java.util.Date;

public class BookStationaryItem implements StationaryItem{

  private final int price;
  private final String typeOfItem;
  private final Date dateOfArrival;
  private final int warrantyInMonths;

  BookStationaryItem(BookBuilder builder) {
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

  static class BookBuilder {
    private int price;
    private String typeOfItem;
    private Date dateOfArrival;
    private int warrantyInMonths;

    public BookBuilder setPrice(int price) {
      this.price = price;
      return this;
    }

    public BookBuilder setTypeOfItem(String typeOfItem) {
      this.typeOfItem = typeOfItem;
      return this;
    }

    public BookBuilder setDateOfArrival(Date dateOfArrival) {
      this.dateOfArrival = dateOfArrival;
      return this;
    }

    public BookBuilder setWarrantyInMonths(int warrantyInMonths) {
      this.warrantyInMonths = warrantyInMonths;
      return this;
    }

    public BookStationaryItem build() {
      return new BookStationaryItem(this);
    }
  }
}
