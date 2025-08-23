package structural.composite;

public class TestCompositeFileSystem {
    public static void main(String[] args) {
        System.out.println("=== Testing Composite Pattern ===\n");

        // Test 1: Basic file creation and display
        System.out.println("Test 1: Basic file creation and display");
        File file1 = new File("document.txt", 1024);
        File file2 = new File("image.jpg", 2048);

        System.out.println("✓ Files created successfully:");
        file1.display();
        file2.display();

        // Test 2: Basic directory creation and adding files
        System.out.println("\nTest 2: Directory creation and file addition");
        Directory rootDirectory = new Directory("Root");
        rootDirectory.add(file1);
        rootDirectory.add(file2);

        System.out.println("✓ Directory created and files added:");
        rootDirectory.display();

        // Test 3: Nested directory structure
        System.out.println("\nTest 3: Creating nested directory structure");
        Directory documentsDir = new Directory("Documents");
        Directory imagesDir = new Directory("Images");
        Directory projectsDir = new Directory("Projects");

        // Add files to specific directories
        documentsDir.add(new File("readme.txt", 512));
        documentsDir.add(new File("notes.txt", 256));

        imagesDir.add(new File("photo1.png", 1500));
        imagesDir.add(new File("photo2.jpg", 1800));

        projectsDir.add(new File("project.java", 800));
        projectsDir.add(new File("config.xml", 300));

        // Create main directory and add subdirectories
        Directory mainDir = new Directory("Main");
        mainDir.add(documentsDir);
        mainDir.add(imagesDir);
        mainDir.add(projectsDir);

        System.out.println("✓ Nested directory structure created:");
        mainDir.display();

        // Test 4: Remove operation
        System.out.println("\nTest 4: Remove operation testing");
        System.out.println("Before removal:");
        documentsDir.display();

        File readmeFile = new File("readme.txt", 512);
        documentsDir.remove(readmeFile); // This won't work as it's different instance

        System.out.println("After removal attempt (different instance):");
        documentsDir.display();

        // Test 5: Exception handling for leaf operations
        System.out.println("\nTest 5: Exception handling for invalid operations");
        File testFile = new File("test.txt", 100);

        try {
            testFile.add(new File("another.txt", 200));
            System.out.println("✗ Should have thrown exception");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ Correctly prevented adding to file: " + e.getMessage());
        }

        try {
            testFile.remove(new File("another.txt", 200));
            System.out.println("✗ Should have thrown exception");
        } catch (UnsupportedOperationException e) {
            System.out.println("✓ Correctly prevented removing from file: " + e.getMessage());
        }

        // Test 6: Polymorphic treatment
        System.out.println("\nTest 6: Polymorphic treatment verification");
        FileSystemItem[] items = {
                new File("polymorphic.txt", 512),
                new Directory("PolymorphicDir")
        };

        System.out.println("Treating files and directories uniformly:");
        for (FileSystemItem item : items) {
            System.out.println("Item name: " + item.getName());
            item.display();
        }

        // Test 7: Complex nested structure
        System.out.println("\nTest 7: Complex nested structure");
        Directory complexDir = createComplexStructure();
        System.out.println("✓ Complex directory structure:");
        complexDir.display();

        // Test 8: Directory operations
        System.out.println("\nTest 8: Directory operations verification");
        Directory testDir = new Directory("TestDir");
        File tempFile = new File("temp.txt", 100);

        System.out.println("Directory children count before: " + testDir.getChildren().size());
        testDir.add(tempFile);
        System.out.println("Directory children count after add: " + testDir.getChildren().size());
        testDir.remove(tempFile);
        System.out.println("Directory children count after remove: " + testDir.getChildren().size());

        System.out.println("\n=== Test Summary ===");
        System.out.println("Composite Pattern verified:");
        System.out.println("- Files and directories implement common interface");
        System.out.println("- Directories can contain both files and other directories");
        System.out.println("- Uniform treatment of individual and composite objects");
        System.out.println("- Exception handling for invalid operations on leaves");
        System.out.println("- Recursive structure display works correctly");
    }

    private static Directory createComplexStructure() {
        Directory root = new Directory("ProjectRoot");

        // Source directory with subdirectories
        Directory srcDir = new Directory("src");
        Directory mainDir = new Directory("main");
        Directory javaDir = new Directory("java");

        javaDir.add(new File("App.java", 500));
        javaDir.add(new File("Service.java", 750));
        mainDir.add(javaDir);
        srcDir.add(mainDir);

        // Resources directory
        Directory resourcesDir = new Directory("resources");
        resourcesDir.add(new File("config.properties", 200));
        resourcesDir.add(new File("data.xml", 300));

        // Add everything to root
        root.add(srcDir);
        root.add(resourcesDir);
        root.add(new File("pom.xml", 400));
        root.add(new File("README.md", 150));

        return root;
    }
}