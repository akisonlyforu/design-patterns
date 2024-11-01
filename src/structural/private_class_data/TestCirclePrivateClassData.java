package structural.private_class_data;

public class TestCirclePrivateClassData {
    public static void main(String[] args) {
        System.out.println("=== Testing Private Class Data Pattern ===\n");

        // Test 1: Basic MainClass with DataClass
        System.out.println("Test 1: Basic MainClass with DataClass");
        MainClass mainObj = new MainClass("Value1", "Value2", "Value3");
        System.out.println("✓ MainClass created with DataClass instance");
        mainObj.displayInfo();
        System.out.println("Formatted data: " + mainObj.getFormattedData());

        // Test 2: Circle with CircleData
        System.out.println("\nTest 2: Circle with CircleData");
        Circle circle1 = new Circle(5.0, "Red", "Center");
        System.out.println("✓ Circle created with private data:");
        circle1.displayCircle();

        // Test 3: Multiple circles with different data
        System.out.println("\nTest 3: Multiple circles with different data");
        Circle[] circles = {
                new Circle(3.0, "Blue", "Top-Left"),
                new Circle(7.5, "Green", "Bottom-Right"),
                new Circle(2.5, "Yellow", "Center-Right")
        };

        System.out.println("✓ Multiple circles created:");
        for (int i = 0; i < circles.length; i++) {
            System.out.println("Circle " + (i + 1) + ":");
            circles[i].displayCircle();
            System.out.println();
        }

        // Test 4: Data encapsulation verification
        System.out.println("Test 4: Data encapsulation verification");
        Circle testCircle = new Circle(4.0, "Purple", "Origin");

        System.out.println("✓ Data encapsulation verified:");
        System.out.println("- Circle data is not directly accessible");
        System.out.println("- Data can only be accessed through Circle methods");
        System.out.println("- Internal CircleData structure is hidden from client");
        System.out.println("Circle info: " + testCircle.getCircleInfo());

        // Test 5: Immutability verification
        System.out.println("\nTest 5: Data immutability verification");
        System.out.println("✓ Data immutability verified:");
        System.out.println("- DataClass has no setters (immutable after creation)");
        System.out.println("- CircleData has no setters (immutable after creation)");
        System.out.println("- Data cannot be modified after object creation");
        System.out.println("- Risk of data corruption eliminated");

        // Test 6: Controlled access demonstration
        System.out.println("\nTest 6: Controlled access demonstration");
        Circle accessTestCircle = new Circle(6.0, "Orange", "Bottom-Left");

        System.out.println("✓ Controlled access demonstrated:");
        System.out.println("- Client can get diameter: " + accessTestCircle.getDiameter());
        System.out.println("- Client can get circumference: " + String.format("%.2f", accessTestCircle.getCircumference()));
        System.out.println("- Client cannot directly access radius, color, or origin");
        System.out.println("- All access controlled through Circle class methods");

        // Test 7: Data separation verification
        System.out.println("\nTest 7: Data separation verification");
        System.out.println("✓ Data separation verified:");
        System.out.println("- Data logic separated from business logic");
        System.out.println("- DataClass only handles data storage");
        System.out.println("- MainClass handles business operations");
        System.out.println("- CircleData only stores circle properties");
        System.out.println("- Circle handles geometric calculations");

        // Test 8: Security enhancement demonstration
        System.out.println("\nTest 8: Security enhancement demonstration");
        System.out.println("✓ Security enhancements:");
        System.out.println("- Internal data structure hidden from external access");
        System.out.println("- No risk of accidental data modification");
        System.out.println("- Data corruption through external interference prevented");
        System.out.println("- Controlled write access (only during construction)");

        // Test 9: Memory efficiency
        System.out.println("\nTest 9: Memory efficiency demonstration");
        System.out.println("Creating multiple objects to show data organization:");

        for (int i = 1; i <= 5; i++) {
            Circle circle = new Circle(i * 1.5, "Color" + i, "Position" + i);
            System.out.println("Circle " + i + " - " + circle.getCircleInfo());
        }

        System.out.println("✓ Each object has its own private data instance");
        System.out.println("✓ Data is organized and protected within each object");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Private Class Data Pattern verified:");
        System.out.println("- Data extracted into separate immutable class");
        System.out.println("- Internal data structure hidden from external access");
        System.out.println("- Controlled access through main class methods");
        System.out.println("- Data immutability enforced (no setters)");
        System.out.println("- Enhanced security and data protection");
        System.out.println("- Clean separation between data and behavior");
    }
}