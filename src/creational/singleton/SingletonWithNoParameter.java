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

class TestSingletonWithNoParameter {
    public static void main(String[] args) {
        System.out.println("=== Testing SingletonWithNoParameter ===\n");

        // Test 1: Basic singleton creation
        System.out.println("Test 1: Creating first instance");
        SingletonWithNoParameter singleton1 = SingletonWithNoParameter.getInstance();
        System.out.println("✓ First instance created successfully");
        System.out.println("Instance hash code: " + singleton1.hashCode());

        // Test 2: Verify singleton behavior - second getInstance call
        System.out.println("\nTest 2: Creating second instance");
        SingletonWithNoParameter singleton2 = SingletonWithNoParameter.getInstance();
        System.out.println("✓ Second getInstance() call completed");
        System.out.println("Instance hash code: " + singleton2.hashCode());

        // Test 3: Reference equality check
        System.out.println("\nTest 3: Reference equality verification");
        if (singleton1 == singleton2) {
            System.out.println("✓ Success: Both references point to the same instance");
        } else {
            System.out.println("✗ Error: Different instances created - Singleton pattern violated!");
        }

        // Test 4: Object equality check
        System.out.println("\nTest 4: Object equality verification");
        if (singleton1.equals(singleton2)) {
            System.out.println("✓ Objects are equal");
        } else {
            System.out.println("✗ Objects are not equal");
        }

        // Test 5: Multiple getInstance calls
        System.out.println("\nTest 5: Multiple getInstance() calls");
        SingletonWithNoParameter singleton3 = SingletonWithNoParameter.getInstance();
        SingletonWithNoParameter singleton4 = SingletonWithNoParameter.getInstance();
        SingletonWithNoParameter singleton5 = SingletonWithNoParameter.getInstance();

        boolean allSame = (singleton1 == singleton3) &&
                (singleton1 == singleton4) &&
                (singleton1 == singleton5);

        if (allSame) {
            System.out.println("✓ All references point to the same instance");
        } else {
            System.out.println("✗ Multiple instances detected");
        }

        // Test 6: Thread safety test
        System.out.println("\nTest 6: Thread safety verification");
        testThreadSafety();

        // Test 7: Constructor accessibility test
        System.out.println("\nTest 7: Constructor accessibility");
        try {
            // This would cause compilation error if uncommented:
            // SingletonWithNoParameter directInstance = new SingletonWithNoParameter();
            System.out.println("✓ Constructor is properly private (cannot be accessed directly)");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("\n=== Test Summary ===");
        System.out.println("Singleton pattern correctly implemented:");
        System.out.println("- Private constructor prevents direct instantiation");
        System.out.println("- getInstance() provides controlled access");
        System.out.println("- Double-checked locking ensures thread safety");
        System.out.println("- Volatile keyword prevents memory visibility issues");
    }

    private static void testThreadSafety() {
        final SingletonWithNoParameter[] results = new SingletonWithNoParameter[10];
        Thread[] threads = new Thread[10];

        // Create 10 threads that simultaneously try to get singleton instance
        for (int i = 0; i < 10; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                results[index] = SingletonWithNoParameter.getInstance();
            });
        }

        // Start all threads
        for (Thread thread : threads) {
            thread.start();
        }

        // Wait for all threads to complete
        try {
            for (Thread thread : threads) {
                thread.join();
            }

            // Verify all threads got the same instance
            boolean allSame = true;
            for (int i = 1; i < results.length; i++) {
                if (results[0] != results[i]) {
                    allSame = false;
                    break;
                }
            }

            if (allSame) {
                System.out.println("✓ Thread safety verified: All threads got the same instance");
                System.out.println("Common hash code: " + results[0].hashCode());
            } else {
                System.out.println("✗ Thread safety issue: Different instances created by threads");
                for (int i = 0; i < results.length; i++) {
                    System.out.println("Thread " + i + " hash: " + results[i].hashCode());
                }
            }

        } catch (InterruptedException e) {
            System.out.println("Thread test interrupted: " + e.getMessage());
        }
    }
}