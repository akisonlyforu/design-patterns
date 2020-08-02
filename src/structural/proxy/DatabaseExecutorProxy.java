package structural.proxy;

public class DatabaseExecutorProxy implements DatabaseExecutorContract {

  DatabaseExecutorContract databaseExecutorContract;
  boolean isAdmin;

  public DatabaseExecutorProxy(String level) {
      this.databaseExecutorContract = new DatabaseExecutorImpl();
      if(level.equalsIgnoreCase("Admin"))
        isAdmin = true;
  }

  @Override
  public void add() {
      databaseExecutorContract.add();
  }

  @Override
  public void delete() {
    if(isAdmin)
      databaseExecutorContract.delete();
    else
      System.out.println("User not Qualified to delete");
  }
}
