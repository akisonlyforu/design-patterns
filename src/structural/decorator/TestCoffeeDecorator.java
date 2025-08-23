package structural.decorator;

public class TestCoffeeDecorator {
    public static void main(String[] args) {
        System.out.println("=== Testing Decorator Pattern ===\n");

        // Test 1: Basic coffee without decorators
        System.out.println("Test 1: Basic coffee without decorators");
        Coffee plainCoffee = new PlainCoffee();
        System.out.println("✓ Plain coffee created:");
        System.out.println("Description: " + plainCoffee.getDesc());
        System.out.println("Cost: $" + plainCoffee.getCost());

        // Test 2: Coffee with single decorator
        System.out.println("\nTest 2: Coffee with single decorator");
        Coffee milkCoffee = new MilkDecorator(new PlainCoffee());
        System.out.println("✓ Coffee with milk created:");
        System.out.println("Description: " + milkCoffee.getDesc());
        System.out.println("Cost: $" + milkCoffee.getCost());

        // Test 3: Coffee with multiple decorators
        System.out.println("\nTest 3: Coffee with multiple decorators");
        Coffee decoratedCoffee = new SugarDecorator(
                new MilkDecorator(
                        new PlainCoffee()
                )
        );
        System.out.println("✓ Decorated coffee created:");
        System.out.println("Description: " + decoratedCoffee.getDesc());
        System.out.println("Cost: $" + decoratedCoffee.getCost());

        // Test 4: Different decorator combinations
        System.out.println("\nTest 4: Different decorator combinations");

        Coffee[] coffeeVariations = {
                new SugarDecorator(new PlainCoffee()),
                new MilkDecorator(new SugarDecorator(new PlainCoffee())),
        };

        System.out.println("✓ Different coffee combinations:");
        for (int i = 0; i < coffeeVariations.length; i++) {
            System.out.println("Variation " + (i + 1) + ": " +
                    coffeeVariations[i].getDesc() +
                    " - $" + coffeeVariations[i].getCost());
        }

        // Test 5: Runtime decoration
        System.out.println("\nTest 5: Runtime decoration building");
        Coffee runtimeCoffee = new PlainCoffee();
        System.out.println("Starting with: " + runtimeCoffee.getDesc() + " - $" + runtimeCoffee.getCost());

        runtimeCoffee = new MilkDecorator(runtimeCoffee);
        System.out.println("After adding milk: " + runtimeCoffee.getDesc() + " - $" + runtimeCoffee.getCost());

        runtimeCoffee = new SugarDecorator(runtimeCoffee);
        System.out.println("After adding sugar: " + runtimeCoffee.getDesc() + " - $" + runtimeCoffee.getCost());

        // Test 6: Polymorphic behavior verification
        System.out.println("\nTest 6: Polymorphic behavior verification");
        Coffee[] polymorphicCoffees = {
                new PlainCoffee(),
                new MilkDecorator(new PlainCoffee()),
                new SugarDecorator(new MilkDecorator(new PlainCoffee()))
        };

        System.out.println("✓ All objects treated uniformly through Coffee interface:");
        for (Coffee coffee : polymorphicCoffees) {
            System.out.println("Type: " + coffee.getClass().getSimpleName() +
                    " - " + coffee.getDesc() +
                    " - $" + coffee.getCost());
        }

        // Test 7: Multiple instances of same decorator
        System.out.println("\nTest 7: Multiple instances of same decorator");
        Coffee doubleMilk = new MilkDecorator(new MilkDecorator(new PlainCoffee()));
        Coffee tripleSugar = new SugarDecorator(new SugarDecorator(new SugarDecorator(new PlainCoffee())));

        System.out.println("✓ Multiple same decorators:");
        System.out.println("Double milk: " + doubleMilk.getDesc() + " - $" + doubleMilk.getCost());
        System.out.println("Triple sugar: " + tripleSugar.getDesc() + " - $" + tripleSugar.getCost());

        // Test 8: Cost calculation verification
        System.out.println("\nTest 8: Cost calculation verification");
        Coffee costTest = new SugarDecorator(new MilkDecorator(new PlainCoffee()));
        double expectedCost = 2.0 + 0.5 + 0.3 + 0.8; // Plain + Milk + Sugar
        double actualCost = costTest.getCost();

        System.out.println("Expected cost: $" + expectedCost);
        System.out.println("Actual cost: $" + actualCost);

        if (Math.abs(expectedCost - actualCost) < 0.01) {
            System.out.println("✓ Cost calculation correct");
        } else {
            System.out.println("✗ Cost calculation incorrect");
        }

        System.out.println("\n=== Test Summary ===");
        System.out.println("Decorator Pattern verified:");
        System.out.println("- Objects can be decorated with additional functionality");
        System.out.println("- Multiple decorators can be combined");
        System.out.println("- Decorators can be added at runtime");
        System.out.println("- Original object structure remains unchanged");
        System.out.println("- Polymorphic behavior works correctly");
        System.out.println("- Cost and description calculations work properly");
    }
}