package structural.proxy;

public class DatabaseExecutorMain {
  public static void main(String[] args) {
    DatabaseExecutorContract databaseExecutorContract = new DatabaseExecutorProxy("User");
    databaseExecutorContract.add();
    databaseExecutorContract.delete();
    databaseExecutorContract = new DatabaseExecutorProxy("Admin");
    databaseExecutorContract.add();
    databaseExecutorContract.delete();
  }
}
