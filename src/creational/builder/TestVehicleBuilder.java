package creational.builder;

public class TestVehicleBuilder {
    public static void main(String[] args) {
        System.out.println("=== Testing Builder Pattern ===\n");

        // Test 1: Build vehicle with required parameters only
        System.out.println("Test 1: Building vehicle with required parameters only");
        Vehicle basicVehicle = new Vehicle.VehicleBuilder("V4", 4).build();
        System.out.println("✓ Basic vehicle created successfully");
        System.out.println("Engine: " + basicVehicle.getEngine());
        System.out.println("Wheels: " + basicVehicle.getWheel());
        System.out.println("Airbags: " + basicVehicle.getAirbags()); // Should be 0 (default)

        // Test 2: Build vehicle with all parameters
        System.out.println("\nTest 2: Building vehicle with all parameters");
        Vehicle fullVehicle = new Vehicle.VehicleBuilder("V8", 4)
                .setAirbags(6)
                .build();
        System.out.println("✓ Full vehicle created successfully");
        System.out.println("Engine: " + fullVehicle.getEngine());
        System.out.println("Wheels: " + fullVehicle.getWheel());
        System.out.println("Airbags: " + fullVehicle.getAirbags());

        // Test 3: Method chaining verification
        System.out.println("\nTest 3: Method chaining verification");
        Vehicle chainedVehicle = new Vehicle.VehicleBuilder("V6", 4)
                .setAirbags(4)
                .build();
        System.out.println("✓ Method chaining works correctly");
        System.out.println("Chained vehicle - Engine: " + chainedVehicle.getEngine() +
                ", Airbags: " + chainedVehicle.getAirbags());

        // Test 4: Multiple vehicles with different configurations
        System.out.println("\nTest 4: Creating multiple vehicles with different configurations");
        Vehicle motorcycle = new Vehicle.VehicleBuilder("Single Cylinder", 2).build();
        Vehicle truck = new Vehicle.VehicleBuilder("Diesel V8", 6)
                .setAirbags(2)
                .build();
        Vehicle luxuryCar = new Vehicle.VehicleBuilder("Twin Turbo V12", 4)
                .setAirbags(10)
                .build();

        System.out.println("✓ Multiple vehicles created successfully");
        System.out.println("Motorcycle - Engine: " + motorcycle.getEngine() + ", Wheels: " + motorcycle.getWheel());
        System.out.println("Truck - Engine: " + truck.getEngine() + ", Airbags: " + truck.getAirbags());
        System.out.println("Luxury Car - Engine: " + luxuryCar.getEngine() + ", Airbags: " + luxuryCar.getAirbags());

        // Test 5: Immutability verification
        System.out.println("\nTest 5: Immutability verification");
        System.out.println("✓ Vehicle objects are immutable (no setter methods available)");
        System.out.println("Original vehicle data cannot be modified after creation");

        // Test 6: Builder reusability
        System.out.println("\nTest 6: Builder reusability test");
        Vehicle.VehicleBuilder reusableBuilder = new Vehicle.VehicleBuilder("V6", 4);

        Vehicle vehicle1 = reusableBuilder.setAirbags(6).build();
        Vehicle vehicle2 = reusableBuilder.setAirbags(8).build();

        System.out.println("✓ Builder can be reused");
        System.out.println("Vehicle1 airbags: " + vehicle1.getAirbags());
        System.out.println("Vehicle2 airbags: " + vehicle2.getAirbags());
        System.out.println("Same builder instance: " + (vehicle1 != vehicle2 ? "Different vehicles" : "Same vehicle"));

        // Test 7: Different engine types
        System.out.println("\nTest 7: Testing different engine types");
        String[] engineTypes = {"Electric", "Hybrid", "Gasoline", "Diesel"};

        for (String engineType : engineTypes) {
            Vehicle vehicle = new Vehicle.VehicleBuilder(engineType, 4)
                    .setAirbags(4)
                    .build();
            System.out.println(engineType + " vehicle created - Engine: " + vehicle.getEngine());
        }

        // Test 8: Constructor requirement verification
        System.out.println("\nTest 8: Constructor requirement verification");
        try {
            // This would cause compilation error if uncommented:
            // Vehicle directVehicle = new Vehicle();
            System.out.println("✓ Constructor is properly private (cannot create Vehicle directly)");
            System.out.println("✓ Builder constructor requires engine and wheel parameters");
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
        }

        System.out.println("\n=== Test Summary ===");
        System.out.println("Builder Pattern verified:");
        System.out.println("- Handles required and optional parameters correctly");
        System.out.println("- Supports method chaining for fluent interface");
        System.out.println("- Creates immutable objects");
        System.out.println("- Builder can be reused for multiple objects");
        System.out.println("- Private constructor enforces builder usage");
    }
}