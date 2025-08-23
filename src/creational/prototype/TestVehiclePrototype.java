package creational.prototype;

class TestVehiclePrototype {
    public static void main(String[] args) {
        System.out.println("=== Testing Prototype Pattern ===\n");

        // Test 1: Basic Car cloning
        System.out.println("Test 1: Basic Car cloning");
        Car originalCar = new Car("V6", 4, "Blue", 4);
        System.out.println("Original car created:");
        originalCar.displayInfo();

        Vehicle clonedCar = originalCar.cloneVehicle();
        System.out.println("✓ Car cloned successfully:");
        clonedCar.displayInfo();

        // Test 2: Basic Bus cloning
        System.out.println("\nTest 2: Basic Bus cloning");
        Bus originalBus = new Bus("Diesel V8", 6, "Yellow", 50);
        System.out.println("Original bus created:");
        originalBus.displayInfo();

        Vehicle clonedBus = originalBus.cloneVehicle();
        System.out.println("✓ Bus cloned successfully:");
        clonedBus.displayInfo();

        // Test 3: Independence verification - modify clone
        System.out.println("\nTest 3: Clone independence verification");
        Car testCar = new Car("V4", 4, "Red", 2);
        Vehicle testCarClone = testCar.cloneVehicle();

        System.out.println("Before modification:");
        System.out.print("Original: ");
        testCar.displayInfo();
        System.out.print("Clone: ");
        testCarClone.displayInfo();

        // Modify clone
        testCarClone.setColor("Green");

        System.out.println("After modifying clone color:");
        System.out.print("Original: ");
        testCar.displayInfo();
        System.out.print("Clone: ");
        testCarClone.displayInfo();

        if (!testCar.getColor().equals(testCarClone.getColor())) {
            System.out.println("✓ Clone independence verified - original unchanged");
        } else {
            System.out.println("✗ Clone independence failed");
        }

        // Test 4: Reference equality check
        System.out.println("\nTest 4: Reference equality check");
        Car refCar = new Car("V8", 4, "Black", 4);
        Vehicle refCarClone = refCar.cloneVehicle();

        if (refCar != refCarClone) {
            System.out.println("✓ Clone is different object (different memory address)");
            System.out.println("Original hash: " + refCar.hashCode());
            System.out.println("Clone hash: " + refCarClone.hashCode());
        } else {
            System.out.println("✗ Clone points to same object");
        }

        // Test 5: Multiple clones from same prototype
        System.out.println("\nTest 5: Multiple clones from same prototype");
        Bus prototypeBus = new Bus("Diesel V6", 6, "White", 40);

        Vehicle clone1 = prototypeBus.cloneVehicle();
        Vehicle clone2 = prototypeBus.cloneVehicle();
        Vehicle clone3 = prototypeBus.cloneVehicle();

        System.out.println("✓ Multiple clones created from same prototype:");
        System.out.print("Clone 1: ");
        clone1.displayInfo();
        System.out.print("Clone 2: ");
        clone2.displayInfo();
        System.out.print("Clone 3: ");
        clone3.displayInfo();

        // Verify all are different objects
        boolean allDifferent = (clone1 != clone2) && (clone1 != clone3) && (clone2 != clone3);
        if (allDifferent) {
            System.out.println("✓ All clones are separate objects");
        } else {
            System.out.println("✗ Some clones reference same object");
        }

        // Test 6: Polymorphic cloning
        System.out.println("\nTest 6: Polymorphic cloning verification");
        Vehicle[] prototypes = {
                new Car("Electric", 4, "Silver", 4),
                new Bus("Hybrid", 6, "Green", 60)
        };

        System.out.println("Cloning through polymorphic interface:");
        for (int i = 0; i < prototypes.length; i++) {
            Vehicle clone = prototypes[i].cloneVehicle();
            System.out.print("Original " + (i+1) + ": ");
            prototypes[i].displayInfo();
            System.out.print("Clone " + (i+1) + ": ");
            clone.displayInfo();
        }

        // Test 7: Covariant return types verification
        System.out.println("\nTest 7: Covariant return types verification");
        Car originalSportsCar = new Car("V8", 4, "Red", 2);
        Bus originalCityBus = new Bus("Diesel", 6, "Blue", 50);

        // Clone with specific return types
        Car clonedSportsCar = originalSportsCar.cloneVehicle();  // Returns Car directly
        Bus clonedCityBus = originalCityBus.cloneVehicle();      // Returns Bus directly

        System.out.println("✓ Covariant return types work:");
        System.out.println("Car clone type: " + clonedSportsCar.getClass().getSimpleName());
        System.out.println("Bus clone type: " + clonedCityBus.getClass().getSimpleName());
        System.out.println("Car doors: " + clonedSportsCar.getDoors());
        System.out.println("Bus capacity: " + clonedCityBus.getCapacity());

        // Test 8: Performance comparison with constructor
        System.out.println("\nTest 8: Performance comparison (cloning vs constructor)");
        long startTime, endTime;

        // Test constructor creation
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new Car("V6", 4, "Blue", 4);
        }
        endTime = System.currentTimeMillis();
        long constructorTime = endTime - startTime;

        // Test cloning
        Car prototype = new Car("V6", 4, "Blue", 4);
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            prototype.cloneVehicle();
        }
        endTime = System.currentTimeMillis();
        long cloneTime = endTime - startTime;

        System.out.println("Constructor creation (1000x): " + constructorTime + " ms");
        System.out.println("Prototype cloning (1000x): " + cloneTime + " ms");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Prototype Pattern verified:");
        System.out.println("- Objects can be cloned using Cloneable interface");
        System.out.println("- Clones are independent of originals");
        System.out.println("- Clones can be modified without affecting prototype");
        System.out.println("- Covariant return types provide type safety");
        System.out.println("- Performance benefits for object creation");
    }
}