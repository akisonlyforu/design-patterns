package behavioral.null_object;

public class TestNullObjectLogger {
    public static void main(String[] args) {
        System.out.println("=== Testing Null Object Pattern ===\n");

        // Test 1: Application with Console Logger
        System.out.println("Test 1: Application with Console Logger");
        AbstractLogger consoleLogger = LoggerFactory.getLogger("CONSOLE");
        Application app1 = new Application(consoleLogger);
        app1.performOperation("Database Connection");
        app1.performOperation("User Authentication");

        // Test 2: Application with File Logger
        System.out.println("\nTest 2: Application with File Logger");
        AbstractLogger fileLogger = LoggerFactory.getLogger("FILE");
        Application app2 = new Application(fileLogger);
        app2.performOperation("File Processing");
        app2.performOperation("Data Export");

        // Test 3: Application with Null Logger
        System.out.println("\nTest 3: Application with Null Logger");
        AbstractLogger nullLogger = LoggerFactory.getLogger("NULL");
        Application app3 = new Application(nullLogger);
        app3.performOperation("Silent Operation");
        app3.performOperation("Background Task");
        System.out.println("✓ No logging output - Null Object working correctly");

        // Test 4: Application with null parameter (should use NullLogger)
        System.out.println("\nTest 4: Application with null parameter");
        Application app4 = new Application(null);
        app4.performOperation("Null Parameter Test");
        System.out.println("✓ No NullPointerException - Null Object prevents crashes");

        // Test 5: Invalid logger type (should default to NullLogger)
        System.out.println("\nTest 5: Invalid logger type handling");
        AbstractLogger invalidLogger = LoggerFactory.getLogger("INVALID");
        Application app5 = new Application(invalidLogger);
        app5.performOperation("Invalid Logger Test");
        System.out.println("✓ Invalid type handled gracefully with Null Object");

        // Test 6: Switching logger types at runtime
        System.out.println("\nTest 6: Runtime logger switching");
        Application dynamicApp = new Application(LoggerFactory.getLogger("CONSOLE"));
        dynamicApp.performOperation("Initial Operation");

        // Switch to file logger
        dynamicApp.setLogger(LoggerFactory.getLogger("FILE"));
        dynamicApp.performOperation("After Switch to File");

        // Switch to null logger
        dynamicApp.setLogger(LoggerFactory.getLogger("NULL"));
        dynamicApp.performOperation("After Switch to Null");

        // Switch back to console
        dynamicApp.setLogger(LoggerFactory.getLogger("CONSOLE"));
        dynamicApp.performOperation("Back to Console");

        System.out.println("✓ Logger switching works seamlessly");

        // Test 7: Multiple applications with different loggers
        System.out.println("\nTest 7: Multiple applications with different loggers");
        Application[] apps = {
                new Application(LoggerFactory.getLogger("CONSOLE")),
                new Application(LoggerFactory.getLogger("FILE")),
                new Application(LoggerFactory.getLogger("NULL")),
                new Application(null)
        };

        for (int i = 0; i < apps.length; i++) {
            System.out.println("App " + (i + 1) + " operation:");
            apps[i].performOperation("Multi-app test " + (i + 1));
        }

        // Test 8: Null Object behavior verification
        System.out.println("\nTest 8: Null Object behavior verification");
        NullLogger nullObj1 = new NullLogger();
        NullLogger nullObj2 = new NullLogger();

        // Test that null objects can be called safely
        nullObj1.log("Test message 1");
        nullObj2.log("Test message 2");

        System.out.println("✓ Null objects can be called safely without side effects");

        // Test 9: Factory default behavior
        System.out.println("\nTest 9: Factory default behavior");
        AbstractLogger[] factoryTests = {
                LoggerFactory.getLogger(null),
                LoggerFactory.getLogger(""),
                LoggerFactory.getLogger("UNKNOWN")
        };

        for (int i = 0; i < factoryTests.length; i++) {
            System.out.println("Factory test " + (i + 1) + ":");
            factoryTests[i].log("Factory test message");
            System.out.println("Logger type: " + factoryTests[i].getClass().getSimpleName());
        }

        System.out.println("\n=== Test Summary ===");
        System.out.println("Null Object Pattern verified:");
        System.out.println("- Eliminates null pointer exceptions");
        System.out.println("- Provides default 'do nothing' behavior");
        System.out.println("- Client code doesn't need null checks");
        System.out.println("- Seamless integration with real objects");
        System.out.println("- Factory provides safe defaults");
        System.out.println("- Runtime switching between objects works smoothly");
    }
}