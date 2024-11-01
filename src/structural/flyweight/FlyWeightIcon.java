package structural.flyweight;

import java.util.*;

// Flyweight interface - defines operations that can act on extrinsic state
interface Icon {
    void draw(int x, int y);  // x, y are extrinsic state (context)
}

// Concrete flyweight - file icon implementation
class FileIcon implements Icon {
    private String type;    // Intrinsic state - shared among all file icons of same type
    private String image;   // Intrinsic state - image data shared

    public FileIcon(String type) {
        this.type = type;
        this.image = "Image for " + type + " files";
        System.out.println("Creating flyweight for file type: " + type);
    }

    public void draw(int x, int y) {
        System.out.println("Drawing " + type + " icon at position (" + x + ", " + y + ") using " + image);
    }

    public String getType() {
        return type;
    }
}

// Concrete flyweight - folder icon implementation
class FolderIcon implements Icon {
    private String color;   // Intrinsic state - shared among folders of same color
    private String image;   // Intrinsic state - image data shared

    public FolderIcon(String color) {
        this.color = color;
        this.image = "Folder image in " + color + " color";
        System.out.println("Creating flyweight for folder color: " + color);
    }

    public void draw(int x, int y) {
        System.out.println("Drawing " + color + " folder at position (" + x + ", " + y + ") using " + image);
    }

    public String getColor() {
        return color;
    }
}

// Flyweight factory - manages and reuses flyweight instances
class IconFactory {
    private static Map<String, Icon> iconCache = new HashMap<>();

    // Get file icon flyweight - reuses existing ones
    public static Icon getFileIcon(String fileType) {
        String key = "FILE_" + fileType.toUpperCase();
        Icon icon = iconCache.get(key);

        if (icon == null) {
            icon = new FileIcon(fileType);
            iconCache.put(key, icon);
            System.out.println("Created new flyweight for file type: " + fileType);
        } else {
            System.out.println("Reusing existing flyweight for file type: " + fileType);
        }

        return icon;
    }

    // Get folder icon flyweight - reuses existing ones
    public static Icon getFolderIcon(String color) {
        String key = "FOLDER_" + color.toUpperCase();
        Icon icon = iconCache.get(key);

        if (icon == null) {
            icon = new FolderIcon(color);
            iconCache.put(key, icon);
            System.out.println("Created new flyweight for folder color: " + color);
        } else {
            System.out.println("Reusing existing flyweight for folder color: " + color);
        }

        return icon;
    }

    // Get cache statistics
    public static void displayCacheStats() {
        System.out.println("IconFactory cache contains " + iconCache.size() + " flyweight objects");
        for (String key : iconCache.keySet()) {
            System.out.println("  Cached: " + key);
        }
    }
}

// Context class - contains extrinsic state and uses flyweights
class FileSystemItem {
    private String name;        // Extrinsic state - unique to each item
    private int x, y;          // Extrinsic state - position unique to each item
    private Icon icon;         // Reference to shared flyweight

    public FileSystemItem(String name, int x, int y, Icon icon) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.icon = icon;
    }

    public void display() {
        System.out.print(name + " ");
        icon.draw(x, y);  // Pass extrinsic state to flyweight
    }

    public String getName() {
        return name;
    }
}
