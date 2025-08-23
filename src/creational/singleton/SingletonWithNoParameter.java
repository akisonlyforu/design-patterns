package creational.singleton;

public class SingletonWithNoParameter {
    // volatile need - Due to memory reordering, partially constructed objects might be visible.
    private static volatile SingletonWithNoParameter instance;

    private SingletonWithNoParameter() {} // Need - private constructor to stop initialization via constructor

    public static SingletonWithNoParameter getInstance() {
        if (instance == null) { // Need - without this, all threads will have to wait at synchronize despite instance being initialized
            synchronized (SingletonWithNoParameter.class) {
                if (instance == null)
                    instance = new SingletonWithNoParameter();
            }
        }
        return instance;
    }
}
