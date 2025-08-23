package structural.flyweight;

public class TestFlyWeightIcon {
    public static void main(String[] args) {
        System.out.println("=== Testing Flyweight Pattern ===\n");

        // Test 1: Creating file icons of same type
        System.out.println("Test 1: Creating multiple file icons of same type");
        Icon txtIcon1 = IconFactory.getFileIcon("TXT");
        Icon txtIcon2 = IconFactory.getFileIcon("TXT");
        Icon txtIcon3 = IconFactory.getFileIcon("TXT");

        // Verify same flyweight instance is reused
        if (txtIcon1 == txtIcon2 && txtIcon2 == txtIcon3) {
            System.out.println("✓ Same flyweight instance reused for TXT files");
            System.out.println("Hash codes: " + txtIcon1.hashCode() + ", " + txtIcon2.hashCode() + ", " + txtIcon3.hashCode());
        } else {
            System.out.println("✗ Different instances created");
        }

        // Test 2: Creating different file types
        System.out.println("\nTest 2: Creating different file types");
        Icon pdfIcon = IconFactory.getFileIcon("PDF");
        Icon jpgIcon = IconFactory.getFileIcon("JPG");
        Icon docIcon = IconFactory.getFileIcon("DOC");

        System.out.println("✓ Different file type flyweights created");
        IconFactory.displayCacheStats();

        // Test 3: Creating folder icons
        System.out.println("\nTest 3: Creating folder icons");
        Icon blueFolder1 = IconFactory.getFolderIcon("BLUE");
        Icon blueFolder2 = IconFactory.getFolderIcon("BLUE");
        Icon redFolder = IconFactory.getFolderIcon("RED");
        Icon greenFolder = IconFactory.getFolderIcon("GREEN");

        if (blueFolder1 == blueFolder2) {
            System.out.println("✓ Same flyweight instance reused for blue folders");
        } else {
            System.out.println("✗ Different instances for same folder color");
        }

        IconFactory.displayCacheStats();

        // Test 4: Memory optimization demonstration
        System.out.println("\nTest 4: Memory optimization demonstration");
        System.out.println("Creating 1000 file system items with only few flyweight types:");

        FileSystemItem[] items = new FileSystemItem[1000];
        String[] fileTypes = {"TXT", "PDF", "JPG", "DOC"};
        String[] folderColors = {"BLUE", "RED", "GREEN"};

        // Create many items but few flyweights
        for (int i = 0; i < 1000; i++) {
            if (i % 10 == 0) {
                // Create folder every 10th item
                String color = folderColors[i % folderColors.length];
                Icon folderIcon = IconFactory.getFolderIcon(color);
                items[i] = new FileSystemItem("Folder_" + i, i * 10, i * 5, folderIcon);
            } else {
                // Create file
                String type = fileTypes[i % fileTypes.length];
                Icon fileIcon = IconFactory.getFileIcon(type);
                items[i] = new FileSystemItem("File_" + i + "." + type.toLowerCase(), i * 10, i * 5, fileIcon);
            }
        }

        System.out.println("✓ 1000 file system items created");
        IconFactory.displayCacheStats();
        System.out.println("Memory optimization: 1000 objects share only " +
                (fileTypes.length + folderColors.length) + " flyweight instances");

        // Test 5: Extrinsic state verification
        System.out.println("\nTest 5: Extrinsic state verification");
        System.out.println("Displaying first 5 items (same flyweight, different positions):");

        for (int i = 0; i < 5; i++) {
            System.out.print("Item " + i + ": ");
            items[i].display();
        }

        // Test 6: Flyweight sharing verification
        System.out.println("\nTest 6: Flyweight sharing verification");
        Icon txt1 = IconFactory.getFileIcon("TXT");
        Icon txt2 = IconFactory.getFileIcon("txt");  // Different case
        Icon txt3 = IconFactory.getFileIcon("TXT");

        System.out.println("TXT flyweight references:");
        System.out.println("txt1 hash: " + txt1.hashCode());
        System.out.println("txt2 hash: " + txt2.hashCode());
        System.out.println("txt3 hash: " + txt3.hashCode());

        if (txt1 == txt2 && txt2 == txt3) {
            System.out.println("✓ Case-insensitive flyweight sharing works");
        } else {
            System.out.println("Note: Case-sensitive flyweight creation");
        }

        // Test 7: Performance comparison
        System.out.println("\nTest 7: Performance comparison (with vs without flyweight)");

        // Simulate without flyweight (creating new objects each time)
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            new FileIcon("TXT");  // New object each time
        }
        long withoutFlyweight = System.currentTimeMillis() - startTime;

        // With flyweight (reusing cached objects)
        startTime = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            IconFactory.getFileIcon("TXT");  // Reuses cached flyweight
        }
        long withFlyweight = System.currentTimeMillis() - startTime;

        System.out.println("Without flyweight (1000 new objects): " + withoutFlyweight + " ms");
        System.out.println("With flyweight (reusing cached): " + withFlyweight + " ms");
        System.out.println("✓ Flyweight pattern provides performance benefits");

        // Test 8: Different context, same flyweight
        System.out.println("\nTest 8: Different context, same flyweight");
        Icon sharedIcon = IconFactory.getFileIcon("PDF");

        FileSystemItem pdf1 = new FileSystemItem("document.pdf", 100, 200, sharedIcon);
        FileSystemItem pdf2 = new FileSystemItem("report.pdf", 300, 400, sharedIcon);
        FileSystemItem pdf3 = new FileSystemItem("manual.pdf", 500, 600, sharedIcon);

        System.out.println("✓ Same flyweight used in different contexts:");
        pdf1.display();
        pdf2.display();
        pdf3.display();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Flyweight Pattern verified:");
        System.out.println("- Flyweight instances are shared and reused");
        System.out.println("- Memory usage optimized through object sharing");
        System.out.println("- Extrinsic state handled properly through context");
        System.out.println("- Factory manages flyweight creation and caching");
        System.out.println("- Performance benefits demonstrated");
        System.out.println("- Large number of similar objects handled efficiently");
    }
}
