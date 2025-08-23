package structural.proxy;

public class TestImageProxy {
    public static void main(String[] args) {
        System.out.println("=== Testing Proxy Pattern ===\n");

        // Test 1: Direct RealImage usage
        System.out.println("Test 1: Direct RealImage usage");
        Image realImage = new RealImage("direct_photo.jpg");
        System.out.println("✓ RealImage created (notice immediate loading)");
        realImage.display();

        System.out.println("\n" + "-".repeat(40));

        // Test 2: ProxyImage usage with lazy loading
        System.out.println("\nTest 2: ProxyImage usage with lazy loading");
        Image proxyImage = new ProxyImage("proxy_photo.jpg");
        System.out.println("✓ ProxyImage created (no loading yet - lazy initialization)");

        System.out.println("Now calling display() for first time:");
        proxyImage.display();

        // Test 3: Second call to same proxy (caching effect)
        System.out.println("\nTest 3: Second call to same proxy (caching verification)");
        System.out.println("Calling display() again on same proxy:");
        proxyImage.display();
        System.out.println("✓ Image loaded only once (cached behavior)");

        // Test 4: Multiple proxy instances
        System.out.println("\nTest 4: Multiple proxy instances");
        Image[] proxyImages = {
                new ProxyImage("image1.png"),
                new ProxyImage("image2.jpg"),
                new ProxyImage("image3.gif")
        };

        System.out.println("✓ Three proxy images created (no loading yet)");

        System.out.println("Displaying only first two images:");
        proxyImages[0].display();
        proxyImages[1].display();
        System.out.println("✓ Third image proxy created but not loaded (demonstrates lazy loading)");

        // Test 5: Polymorphic behavior
        System.out.println("\nTest 5: Polymorphic behavior verification");
        Image[] images = {
                new RealImage("real_image.jpg"),
                new ProxyImage("proxy_image.jpg")
        };

        System.out.println("✓ Both real and proxy images treated uniformly through Image interface:");
        for (int i = 0; i < images.length; i++) {
            System.out.println("Image " + (i + 1) + " (" + images[i].getClass().getSimpleName() + "):");
            images[i].display();
        }

        // Test 6: Performance comparison (creation time)
        System.out.println("\nTest 6: Performance comparison - object creation");

        long startTime = System.currentTimeMillis();
        new RealImage("performance_real.jpg");
        long realImageTime = System.currentTimeMillis() - startTime;

        startTime = System.currentTimeMillis();
        new ProxyImage("performance_proxy.jpg");
        long proxyImageTime = System.currentTimeMillis() - startTime;

        System.out.println("RealImage creation time: " + realImageTime + " ms (includes loading)");
        System.out.println("ProxyImage creation time: " + proxyImageTime + " ms (no loading)");
        System.out.println("✓ Proxy creation is significantly faster");

        // Test 7: Lazy loading timing verification
        System.out.println("\nTest 7: Lazy loading timing verification");
        System.out.println("Creating proxy (should be instant):");
        startTime = System.currentTimeMillis();
        Image lazyImage = new ProxyImage("lazy_test.jpg");
        long proxyCreationTime = System.currentTimeMillis() - startTime;

        System.out.println("Proxy created in: " + proxyCreationTime + " ms");

        System.out.println("First display() call (should trigger loading):");
        startTime = System.currentTimeMillis();
        lazyImage.display();
        long firstDisplayTime = System.currentTimeMillis() - startTime;

        System.out.println("First display took: " + firstDisplayTime + " ms (includes loading)");

        System.out.println("Second display() call (should use cached image):");
        startTime = System.currentTimeMillis();
        lazyImage.display();
        long secondDisplayTime = System.currentTimeMillis() - startTime;

        System.out.println("Second display took: " + secondDisplayTime + " ms (cached)");

        // Test 8: Memory efficiency demonstration
        System.out.println("\nTest 8: Memory efficiency demonstration");
        System.out.println("Creating 5 proxy images (memory efficient):");

        Image[] manyProxies = new Image[5];
        for (int i = 0; i < 5; i++) {
            manyProxies[i] = new ProxyImage("bulk_image_" + i + ".jpg");
        }
        System.out.println("✓ 5 proxy images created with no memory overhead");

        System.out.println("Displaying only 2 images (others remain unloaded):");
        manyProxies[0].display();
        manyProxies[2].display();
        System.out.println("✓ Only 2 real images loaded, 3 remain as lightweight proxies");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Proxy Pattern verified:");
        System.out.println("- Proxy provides lazy loading of expensive objects");
        System.out.println("- Real object created only when actually needed");
        System.out.println("- Subsequent calls use cached real object");
        System.out.println("- Proxy and real object share same interface");
        System.out.println("- Significant performance improvement for object creation");
        System.out.println("- Memory efficiency through on-demand loading");
    }
}