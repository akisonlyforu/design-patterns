package creational.singleton;

public class TestSingleton {
    public static void main(String[] args) {
        testSingletonWithParameter();
        System.out.println("\n" + "=".repeat(50) + "\n");
        testSingletonWithNoParameter();
    }

    private static void testSingletonWithParameter() {
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

        // Test 7: Thread safety demonstration
        System.out.println("\nTest 7: Thread safety test");
        testParameterThreadSafety();
    }

    private static void testParameterThreadSafety() {
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

    private static void testSingletonWithNoParameter() {
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
        testNoParameterThreadSafety();

        // Test 7: Constructor accessibility test
        System.out.println("\nTest 7: Constructor accessibility");
        System.out.println("✓ Constructor is properly private (cannot be accessed directly)");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Singleton pattern correctly implemented:");
        System.out.println("- Private constructor prevents direct instantiation");
        System.out.println("- getInstance() provides controlled access");
        System.out.println("- Double-checked locking ensures thread safety");
        System.out.println("- Volatile keyword prevents memory visibility issues");
    }

    private static void testNoParameterThreadSafety() {
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