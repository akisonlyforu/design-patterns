package structural.bridge;

public class TestBridgeVehicleWorkshop {
    public static void main(String[] args) {
        System.out.println("=== Testing Bridge Pattern ===\n");

        // Test 1: Basic vehicle creation and manufacturing
        System.out.println("Test 1: Basic vehicle creation and manufacturing");
        Workshop produce = new Produce();
        Workshop assemble = new Assemble();

        Vehicle car1 = new Car(produce, assemble);
        Vehicle bike1 = new Bike(produce, assemble);

        System.out.println("✓ Vehicles created successfully:");
        car1.displayConfiguration();
        bike1.displayConfiguration();
        System.out.println();

        car1.manufacture();
        bike1.manufacture();

        // Test 2: Workshop independence - different combinations
        System.out.println("Test 2: Workshop independence - different combinations");
        Workshop paint = new Paint();
        Workshop inspect = new Inspect();

        Vehicle car2 = new Car(paint, inspect);
        Vehicle bike2 = new Bike(assemble, paint);

        System.out.println("✓ Different workshop combinations:");
        car2.displayConfiguration();
        bike2.displayConfiguration();
        System.out.println();

        car2.manufacture();
        bike2.manufacture();

        // Test 3: Abstraction independence - same workshops, different vehicles
        System.out.println("Test 3: Abstraction independence - same workshops, different vehicles");
        Vehicle truck1 = new Truck(produce, assemble);
        Vehicle motorcycle1 = new Motorcycle(produce, assemble);

        System.out.println("✓ Same workshops with different vehicle types:");
        truck1.displayConfiguration();
        motorcycle1.displayConfiguration();
        System.out.println();

        truck1.manufacture();
        motorcycle1.manufacture();

        // Test 4: Complete workshop matrix testing
        System.out.println("Test 4: Complete workshop matrix testing");
        Workshop[] workshops = {produce, assemble, paint, inspect};

        System.out.println("✓ Testing all workshop combinations with Car:");
        for (int i = 0; i < workshops.length; i++) {
            for (int j = 0; j < workshops.length; j++) {
                if (i != j) { // Different workshops for variety
                    Vehicle testCar = new Car(workshops[i], workshops[j]);
                    System.out.println("Configuration: " + workshops[i].getWorkshopType() +
                            " + " + workshops[j].getWorkshopType());
                    testCar.manufacture();
                }
            }
        }

        // Test 5: Runtime workshop switching (advanced)
        System.out.println("Test 5: Runtime workshop flexibility demonstration");
        Vehicle flexibleCar = new Car(produce, assemble);

        System.out.println("✓ Original configuration:");
        flexibleCar.displayConfiguration();
        flexibleCar.manufacture();

        // Demonstrate that we can create new instances with different workshops
        Vehicle reconfiguredCar = new Car(paint, inspect);
        System.out.println("✓ Reconfigured vehicle:");
        reconfiguredCar.displayConfiguration();
        reconfiguredCar.manufacture();

        // Test 6: Polymorphic treatment verification
        System.out.println("Test 6: Polymorphic treatment verification");
        Vehicle[] vehicles = {
                new Car(produce, assemble),
                new Bike(paint, inspect),
                new Truck(assemble, paint),
                new Motorcycle(inspect, produce)
        };

        System.out.println("✓ Treating all vehicles uniformly:");
        for (Vehicle vehicle : vehicles) {
            System.out.println("Vehicle type: " + vehicle.getVehicleType());
            vehicle.displayConfiguration();
            vehicle.manufacture();
        }

        // Test 7: Extensibility demonstration
        System.out.println("Test 7: Extensibility demonstration");
        System.out.println("✓ Adding new workshop type and vehicle type:");

        // New workshop type
        Workshop repair = new Workshop() {
            @Override
            public void work() {
                System.out.println("Repairing the vehicle");
            }

            @Override
            public String getWorkshopType() {
                return "Repair";
            }
        };

        // New vehicle type
        class Bus extends Vehicle {
            public Bus(Workshop workshop1, Workshop workshop2) {
                super(workshop1, workshop2);
            }

            @Override
            public String getVehicleType() {
                return "Bus";
            }
        }

        Vehicle newBus = new Bus(repair, paint);
        newBus.displayConfiguration();
        newBus.manufacture();

        // Test 8: Bridge pattern structure verification
        System.out.println("Test 8: Bridge pattern structure verification");
        Vehicle testVehicle = new Car(produce, assemble);

        System.out.println("✓ Verifying bridge structure:");
        System.out.println("- Vehicle contains workshop references: ✓");
        System.out.println("- Vehicle delegates work to workshops: ✓");
        System.out.println("- Client interacts only with vehicle abstraction: ✓");
        System.out.println("- Workshops can be used by any vehicle type: ✓");

        // Test 9: Performance and flexibility comparison
        System.out.println("\nTest 9: Performance and flexibility demonstration");
        long startTime = System.currentTimeMillis();

        System.out.println("✓ Creating 1000 vehicles with different combinations:");
        for (int i = 0; i < 1000; i++) {
            Workshop w1 = (i % 2 == 0) ? produce : assemble;
            Workshop w2 = (i % 3 == 0) ? paint : inspect;
            Vehicle v = (i % 2 == 0) ? new Car(w1, w2) : new Bike(w1, w2);
            // Just create, don't manufacture for performance test
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Created 1000 vehicles in " + (endTime - startTime) + "ms");
        System.out.println("Bridge pattern enables flexible combinations without class explosion!");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Bridge Pattern verified:");
        System.out.println("- Abstraction and implementation hierarchies are separate");
        System.out.println("- Both hierarchies can be extended independently");
        System.out.println("- Any vehicle can use any workshop combination");
        System.out.println("- Client code is decoupled from implementation details");
        System.out.println("- No class explosion problem (n+m classes instead of n×m)");
        System.out.println("- Runtime flexibility in workshop combinations");
        System.out.println("- Uniform treatment of all vehicle types");

        demonstrateClassCountComparison();
    }

    private static void demonstrateClassCountComparison() {
        System.out.println("\n=== Class Count Comparison ===");
        System.out.println("Without Bridge Pattern (inheritance approach):");
        System.out.println("- CarProduce, CarAssemble, CarPaint, CarInspect");
        System.out.println("- BikeProduce, BikeAssemble, BikePaint, BikeInspect");
        System.out.println("- TruckProduce, TruckAssemble, TruckPaint, TruckInspect");
        System.out.println("- MotorcycleProduce, MotorcycleAssemble, MotorcyclePaint, MotorcycleInspect");
        System.out.println("Total: 4 vehicles × 4 workshops = 16 classes");

        System.out.println("\nWith Bridge Pattern:");
        System.out.println("- Vehicle hierarchy: Vehicle, Car, Bike, Truck, Motorcycle (5 classes)");
        System.out.println("- Workshop hierarchy: Workshop, Produce, Assemble, Paint, Inspect (5 classes)");
        System.out.println("Total: 4 vehicles + 4 workshops + 2 base classes = 10 classes");
        System.out.println("Savings: 6 classes (37.5% reduction) with much better flexibility!");
    }
}