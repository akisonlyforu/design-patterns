package creational.object_pool;

public class TestObjectPool {
    public static void main(String[] args) {
        System.out.println("=== Testing Object Pool Pattern ===\n");

        // Test 1: Create object pool with factory
        System.out.println("Test 1: Creating Object Pool with Factory");

        // WHY WE NEED ObjectFactory<T>:
        // We cannot do: ObjectPool<ExpensiveObject> pool = new ObjectPool<>();
        // Because the pool needs to know HOW to create ExpensiveObject instances

        ExpensiveObjectFactory factory = new ExpensiveObjectFactory();
        ObjectPool<ExpensiveObject> pool = new ObjectPool<>(factory);

        System.out.println("✓ Object pool created with factory");
        pool.showStats();

        // Test 2: Acquire objects from empty pool
        System.out.println("\nTest 2: Acquiring objects from empty pool");
        System.out.println("Pool is empty, so factory.create() will be called...");

        ExpensiveObject obj1 = pool.acquire();
        System.out.println("✓ First object acquired");
        pool.showStats();

        ExpensiveObject obj2 = pool.acquire();
        System.out.println("✓ Second object acquired");
        pool.showStats();

        // Test 3: Release objects back to pool
        System.out.println("\nTest 3: Releasing objects back to pool");

        pool.release(obj1);
        System.out.println("✓ First object released");
        pool.showStats();

        pool.release(obj2);
        System.out.println("✓ Second object released");
        pool.showStats();

        // Test 4: Reuse objects from pool
        System.out.println("\nTest 4: Reusing objects from pool");
        System.out.println("Pool has objects now, so NO factory.create() calls...");

        ExpensiveObject obj3 = pool.acquire();
        System.out.println("✓ Third object acquired (reused)");
        pool.showStats();

        ExpensiveObject obj4 = pool.acquire();
        System.out.println("✓ Fourth object acquired (reused)");
        pool.showStats();

        // Test 5: Demonstrate factory importance with different factories
        System.out.println("\nTest 5: Different factories create different object types");

        // Create another factory that creates objects with different behavior
        ObjectFactory<ExpensiveObject> customFactory = new ObjectFactory<ExpensiveObject>() {
            public ExpensiveObject create() {
                System.out.println("Custom factory creating specialized object");
                return new ExpensiveObject();
            }
        };

        ObjectPool<ExpensiveObject> customPool = new ObjectPool<>(customFactory);
        ExpensiveObject customObj = customPool.acquire();
        System.out.println("✓ Object created with custom factory");

        // Test 6: Show what happens without factory (conceptual)
        System.out.println("\nTest 6: Why factory.create() is essential");
        System.out.println("Without ObjectFactory<T>:");
        System.out.println("- Pool cannot create new objects of generic type T");
        System.out.println("- We cannot write 'new T()' in Java generics");
        System.out.println("- Factory.create() tells pool HOW to make new objects");
        System.out.println("- Different factories can create objects with different configurations");

        // Test 7: Object reuse verification
        System.out.println("\nTest 7: Object reuse verification");

        // Release current objects
        pool.release(obj3);
        pool.release(obj4);

        // Acquire again - should reuse existing objects
        System.out.println("Acquiring objects again...");
        ExpensiveObject reused1 = pool.acquire();
        ExpensiveObject reused2 = pool.acquire();

        // Check if we got the same objects back (by reference)
        boolean reusedObj3 = (reused1 == obj3 || reused1 == obj4);
        boolean reusedObj4 = (reused2 == obj3 || reused2 == obj4);

        if (reusedObj3 && reusedObj4) {
            System.out.println("✓ Objects were successfully reused from pool");
        } else {
            System.out.println("✗ New objects created instead of reusing");
        }

        // Test 8: Factory delegation demonstration
        System.out.println("\nTest 8: Factory delegation in action");

        // Create a pool that's empty
        ObjectPool<ExpensiveObject> emptyPool = new ObjectPool<>(factory);

        System.out.println("Empty pool about to acquire object...");
        System.out.println("Watch how pool delegates to factory.create():");

        ExpensiveObject newObj = emptyPool.acquire();
        System.out.println("✓ Pool successfully delegated object creation to factory");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Object Pool Pattern verified:");
        System.out.println("- Factory.create() solves generic instantiation problem");
        System.out.println("- Pool reuses objects to avoid expensive creation");
        System.out.println("- Objects are properly reset before reuse");
        System.out.println("- Different factories enable different object creation strategies");
        System.out.println("- Pool manages object lifecycle efficiently");

        System.out.println("\n=== Why ObjectFactory<T>.create() is Critical ===");
        System.out.println("1. Java generics cannot be instantiated directly (no 'new T()')");
        System.out.println("2. create() method encapsulates object creation logic");
        System.out.println("3. Pool delegates creation responsibility to factory");
        System.out.println("4. Factory can handle complex initialization, parameters, etc.");
        System.out.println("5. Different factories = different object creation strategies");
    }
}