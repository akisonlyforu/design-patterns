package creational.singleton;

class SingletonWithNoParameter {
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

class SingletonWithParameter {
    // volatile need - Due to memory reordering, partially constructed objects might be visible.
    private static volatile SingletonWithParameter instance;

    // make it 'final' as we don't want to modify the state of our singleton
    private final String data;

    // Why no private no-args constructor ?
    // We have made 'data' final. So, it needs to be initialized in a constructor. No-args won't initialize it here.

    private SingletonWithParameter(String data) {
        this.data = data;
    }

    public static SingletonWithParameter getInstance(String data) {
        if (instance == null) { // Need - without this, all threads will have to wait at synchronize despite instance being initialized
            synchronized (SingletonWithParameter.class) {
                if (instance == null)
                    instance = new SingletonWithParameter(data);
            }
        }
        return instance;
    }

    public String getData() {
        return data;
    }
}
