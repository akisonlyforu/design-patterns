package structural.proxy;

public class DatabaseExecutorImpl implements DatabaseExecutorContract{
  @Override
  public void add() {
    System.out.println("ADD Successful");
  }

  @Override
  public void delete() {
    System.out.println("DELETE Successful");
  }
}
