package creational.singleton;

public class SingletonWithParameter {
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

class TestSingletonWithParameter {
    public static void main(String[] args) {
        System.out.println("=== Testing SingletonWithParameter ===\n");

        // Test 1: Basic singleton creation and functionality
        System.out.println("Test 1: Basic singleton creation");
        SingletonWithParameter singleton1 = SingletonWithParameter.getInstance("Initial Data");
        System.out.println("First instance created with data: " + singleton1.getData());

        // Test 2: Verify singleton behavior - second call with different data
        System.out.println("\nTest 2: Attempting to create with different data");
        SingletonWithParameter singleton2 = SingletonWithParameter.getInstance("Different Data");
        System.out.println("Second instance data: " + singleton2.getData());

        // Test 3: Reference equality check
        System.out.println("\nTest 3: Reference equality check");
        if (singleton1 == singleton2) {
            System.out.println("✓ Success: Both references point to the same instance");
        } else {
            System.out.println("✗ Error: Different instances created");
        }

        // Test 4: Hash code verification
        System.out.println("\nTest 4: Hash code verification");
        System.out.println("First reference hash code: " + singleton1.hashCode());
        System.out.println("Second reference hash code: " + singleton2.hashCode());

        // Test 5: Data immutability verification
        System.out.println("\nTest 5: Data immutability");
        System.out.println("Data remains: " + singleton1.getData());
        System.out.println("Note: Data is final and cannot be changed after first initialization");

        // Test 6: Multiple getInstance calls with null parameter
        System.out.println("\nTest 6: Testing with null parameter");
        try {
            SingletonWithParameter singleton3 = SingletonWithParameter.getInstance(null);
            System.out.println("Third instance data: " + singleton3.getData());
            System.out.println("Same instance? " + (singleton1 == singleton3));
        } catch (Exception e) {
            System.out.println("Exception caught: " + e.getMessage());
        }

        // Test 7: Thread safety demonstration (basic)
        System.out.println("\nTest 7: Basic thread safety test");
        testThreadSafety();
    }

    private static void testThreadSafety() {
        final String[] results = new String[2];
        final Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            SingletonWithParameter instance = SingletonWithParameter.getInstance("Thread1 Data");
            synchronized (lock) {
                results[0] = instance.getData() + " (Hash: " + instance.hashCode() + ")";
            }
        });

        Thread thread2 = new Thread(() -> {
            SingletonWithParameter instance = SingletonWithParameter.getInstance("Thread2 Data");
            synchronized (lock) {
                results[1] = instance.getData() + " (Hash: " + instance.hashCode() + ")";
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();

            System.out.println("Thread 1 result: " + results[0]);
            System.out.println("Thread 2 result: " + results[1]);

            // Extract hash codes for comparison
            String hash1 = results[0].substring(results[0].indexOf("Hash: ") + 6, results[0].length() - 1);
            String hash2 = results[1].substring(results[1].indexOf("Hash: ") + 6, results[1].length() - 1);

            if (hash1.equals(hash2)) {
                System.out.println("✓ Thread safety verified: Same instance across threads");
            } else {
                System.out.println("✗ Thread safety issue: Different instances created");
            }

        } catch (InterruptedException e) {
            System.out.println("Thread interrupted: " + e.getMessage());
        }
    }
}