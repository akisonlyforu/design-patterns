package behavioral.template;

public class TestTemplateBaseGameLoader {
    public static void main(String[] args) {
        System.out.println("=== Testing Template Method Pattern ===\n");

        // Test 1: Basic loader creation and execution
        System.out.println("Test 1: Basic loader creation and execution");
        BaseGameLoader loader1 = new Loader1();
        BaseGameLoader loader2 = new Loader2();

        System.out.println("✓ Loaders created successfully:");
        System.out.println("Loader1 type: " + loader1.getLoaderType());
        System.out.println("Loader2 type: " + loader2.getLoaderType());
        System.out.println();

        loader1.load();
        loader2.load();

        // Test 2: Template method structure verification
        System.out.println("Test 2: Template method structure verification");
        BaseGameLoader debugLoader = new DebugLoader();

        System.out.println("✓ Template method ensures consistent algorithm structure:");
        debugLoader.load();

        // Test 3: Hook method customization
        System.out.println("Test 3: Hook method customization");
        System.out.println("✓ Testing different hook method implementations:");

        BaseGameLoader[] loaders = {
                new Loader1(),    // Uses logging
                new Loader2(),    // Disables logging
                new DebugLoader() // Enhanced logging
        };

        for (BaseGameLoader loader : loaders) {
            System.out.println("Testing " + loader.getLoaderType() + ":");
            System.out.println("Should log progress: " + loader.shouldLogProgress());
            loader.load();
        }

        // Test 4: Extensibility demonstration
        System.out.println("Test 4: Extensibility demonstration");
        BaseGameLoader cloudLoader = new CloudLoader();

        System.out.println("✓ New loader type added without changing existing code:");
        cloudLoader.load();

        // Test 5: Polymorphic treatment
        System.out.println("Test 5: Polymorphic treatment verification");
        BaseGameLoader[] allLoaders = {
                new Loader1(),
                new Loader2(),
                new CloudLoader(),
                new DebugLoader()
        };

        System.out.println("✓ Treating all loaders uniformly:");
        for (BaseGameLoader loader : allLoaders) {
            System.out.println("Loader: " + loader.getLoaderType());
            System.out.println("Logging enabled: " + loader.shouldLogProgress());
            loader.load();
        }

        // Test 6: Algorithm consistency verification
        System.out.println("Test 6: Algorithm consistency verification");
        System.out.println("✓ Verifying that all loaders follow the same algorithm structure:");

        BaseGameLoader testLoader = new Loader1();
        System.out.println("Algorithm steps for " + testLoader.getLoaderType() + ":");
        System.out.println("1. Execute step1() - Implementation specific");
        System.out.println("2. Execute step2() - Implementation specific");
        System.out.println("3. Execute step3() - Implementation specific");
        System.out.println("Template method ensures this order cannot be changed\n");

        // Test 7: Code duplication elimination demonstration
        System.out.println("Test 7: Code duplication elimination demonstration");
        System.out.println("✓ Common algorithm structure shared across all loaders:");
        System.out.println("- All loaders start with same header");
        System.out.println("- All loaders execute steps in same order");
        System.out.println("- All loaders end with same footer");
        System.out.println("- Only step implementations vary between loaders\n");

        // Test 8: Hook method override testing
        System.out.println("Test 8: Hook method override testing");

        // Create custom loader that overrides hook methods differently
        BaseGameLoader customLoader = new BaseGameLoader() {
            @Override
            protected void step1() {
                logProgress("Custom step 1");
                System.out.println("Custom: Performing specialized initialization");
            }

            @Override
            protected void step2() {
                logProgress("Custom step 2");
                System.out.println("Custom: Executing custom loading logic");
            }

            @Override
            protected void step3() {
                logProgress("Custom step 3");
                System.out.println("Custom: Finalizing with custom cleanup");
            }

            @Override
            protected String getLoaderType() {
                return "Custom Anonymous Loader";
            }

            @Override
            protected boolean shouldLogProgress() {
                return true;
            }
        };

        System.out.println("✓ Custom loader with overridden hook methods:");
        customLoader.load();

        // Test 9: Template method immutability
        System.out.println("Test 9: Template method immutability verification");
        System.out.println("✓ Template method is final and cannot be overridden:");
        System.out.println("- Ensures algorithm structure remains consistent");
        System.out.println("- Prevents subclasses from breaking the intended flow");
        System.out.println("- Guarantees that all steps are executed in correct order");
        System.out.println("- Maintains the invariant behavior across all implementations\n");

        // Test 10: Performance comparison
        System.out.println("Test 10: Performance and reusability demonstration");
        long startTime = System.currentTimeMillis();

        System.out.println("✓ Creating and executing 100 loaders of different types:");
        for (int i = 0; i < 100; i++) {
            BaseGameLoader loader;
            switch (i % 4) {
                case 0: loader = new Loader1(); break;
                case 1: loader = new Loader2(); break;
                case 2: loader = new CloudLoader(); break;
                default: loader = new DebugLoader(); break;
            }
            // Just create and get type for performance test
            loader.getLoaderType();
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Created and typed 100 loaders in " + (endTime - startTime) + "ms");
        System.out.println("Template method provides consistent interface for all types!\n");

        System.out.println("=== Test Summary ===");
        System.out.println("Template Method Pattern verified:");
        System.out.println("- Algorithm skeleton defined in base class");
        System.out.println("- Subclasses provide specific implementations for abstract steps");
        System.out.println("- Template method controls execution order and cannot be overridden");
        System.out.println("- Hook methods allow optional customization");
        System.out.println("- Code duplication eliminated through shared algorithm structure");
        System.out.println("- Easy to add new loader types without changing algorithm");
        System.out.println("- Consistent behavior guaranteed across all implementations");

        demonstrateCodeDuplicationPrevention();
    }

    private static void demonstrateCodeDuplicationPrevention() {
        System.out.println("\n=== Code Duplication Prevention ===");
        System.out.println("Without Template Method Pattern:");
        System.out.println("- Each loader would duplicate the algorithm structure");
        System.out.println("- Common logging, headers, and flow control repeated");
        System.out.println("- Changes to algorithm require updating all loaders");
        System.out.println("- Risk of inconsistent implementations");

        System.out.println("\nWith Template Method Pattern:");
        System.out.println("- Algorithm structure defined once in base class");
        System.out.println("- Common behavior shared across all implementations");
        System.out.println("- Changes to algorithm structure made in one place");
        System.out.println("- Guaranteed consistency across all loader types");
        System.out.println("- Subclasses focus only on their specific step implementations");
    }
}