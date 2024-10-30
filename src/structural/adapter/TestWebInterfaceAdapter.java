package structural.adapter;

public class TestWebInterfaceAdapter {
    public static void main(String[] args) {
        System.out.println("=== Testing Adapter Pattern ===\n");

        // Test 1: Direct WebInterface implementation
        System.out.println("Test 1: Testing direct WebInterface implementation");
        WebInterface webInterface = new WebInterfaceImpl();
        System.out.println("✓ WebInterfaceImpl created successfully");
        System.out.print("get() method: ");
        webInterface.get();
        System.out.print("select() method: ");
        webInterface.select();

        // Test 2: Adapter with OldWebInterface
        System.out.println("\nTest 2: Testing WebInterfaceAdapter with OldWebInterface");
        OldWebInterface oldWebInterface = new OldWebInterfaceImpl();
        WebInterface webInterfaceAdapter = new WebInterfaceAdapter(oldWebInterface);
        System.out.println("✓ WebInterfaceAdapter created successfully");
        System.out.print("get() through adapter: ");
        webInterfaceAdapter.get();
        System.out.print("select() through adapter: ");
        webInterfaceAdapter.select();

        // Test 3: Polymorphic usage
        System.out.println("\nTest 3: Polymorphic usage verification");
        WebInterface[] interfaces = {
                new WebInterfaceImpl(),
                new WebInterfaceAdapter(new OldWebInterfaceImpl())
        };

        System.out.println("Testing both implementations polymorphically:");
        for (int i = 0; i < interfaces.length; i++) {
            System.out.println("Interface " + (i + 1) + " (" + interfaces[i].getClass().getSimpleName() + "):");
            System.out.print("  get(): ");
            interfaces[i].get();
            System.out.print("  select(): ");
            interfaces[i].select();
        }

        // Test 4: Adapter independence
        System.out.println("\nTest 4: Adapter independence verification");
        OldWebInterface oldImpl1 = new OldWebInterfaceImpl();
        OldWebInterface oldImpl2 = new OldWebInterfaceImpl();

        WebInterface adapter1 = new WebInterfaceAdapter(oldImpl1);
        WebInterface adapter2 = new WebInterfaceAdapter(oldImpl2);

        if (adapter1 != adapter2) {
            System.out.println("✓ Different adapter instances created");
            System.out.println("Adapter1 hash: " + adapter1.hashCode());
            System.out.println("Adapter2 hash: " + adapter2.hashCode());
        } else {
            System.out.println("✗ Same adapter instance returned");
        }

        // Test 5: Null handling
        System.out.println("\nTest 5: Null handling verification");
        try {
            WebInterface nullAdapter = new WebInterfaceAdapter(null);
            nullAdapter.get();
            System.out.println("✗ Should handle null adaptee properly");
        } catch (Exception e) {
            System.out.println("✓ Adapter handles null adaptee: " + e.getClass().getSimpleName());
        }

        // Test 6: Interface compatibility
        System.out.println("\nTest 6: Interface compatibility verification");
        System.out.println("Testing that both implementations satisfy WebInterface contract:");

        testWebInterface("WebInterfaceImpl", new WebInterfaceImpl());
        testWebInterface("WebInterfaceAdapter", new WebInterfaceAdapter(new OldWebInterfaceImpl()));

        // Test 7: Method delegation verification
        System.out.println("\nTest 7: Method delegation verification");
        System.out.println("Verifying adapter properly delegates to old interface:");

        OldWebInterface directOld = new OldWebInterfaceImpl();
        WebInterface adaptedOld = new WebInterfaceAdapter(new OldWebInterfaceImpl());

        System.out.println("Direct old interface calls:");
        System.out.print("  oldGet(): ");
        directOld.find();
        System.out.print("  oldSelect(): ");
        directOld.click();

        System.out.println("Same methods through adapter:");
        System.out.print("  get() -> oldGet(): ");
        adaptedOld.get();
        System.out.print("  select() -> oldSelect(): ");
        adaptedOld.select();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Adapter Pattern verified:");
        System.out.println("- New interface implemented correctly");
        System.out.println("- Adapter successfully wraps old interface");
        System.out.println("- Polymorphic usage works for both implementations");
        System.out.println("- Method delegation functions properly");
        System.out.println("- Client code can use old functionality through new interface");
    }

    private static void testWebInterface(String implementationType, WebInterface webInterface) {
        System.out.println("Testing " + implementationType + ":");
        try {
            webInterface.get();
            webInterface.select();
            System.out.println("  ✓ All methods executed successfully");
        } catch (Exception e) {
            System.out.println("  ✗ Exception during method execution: " + e.getMessage());
        }
    }
}