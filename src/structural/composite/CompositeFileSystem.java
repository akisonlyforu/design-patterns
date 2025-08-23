package structural.composite;

import java.util.*;

// Component interface - common interface for both leaf and composite
interface FileSystemItem {
    String getName();
    void display();

    // Default methods for composite operations
    default void add(FileSystemItem item) {
        throw new UnsupportedOperationException("Cannot add to a file");
    }

    default void remove(FileSystemItem item) {
        throw new UnsupportedOperationException("Cannot remove from a file");
    }
}

// Leaf component - represents files
class File implements FileSystemItem {
    private String name;
    private int size;

    public File(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void display() {
        System.out.println("File: " + name + " (Size: " + size + " KB)");
    }

    public int getSize() {
        return size;
    }
}

// Composite component - represents directories
class Directory implements FileSystemItem {
    private String name;
    private List<FileSystemItem> children;

    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void add(FileSystemItem item) {
        children.add(item);
    }

    public void remove(FileSystemItem item) {
        children.remove(item);
    }

    public void display() {
        System.out.println("Directory: " + name);
        for (FileSystemItem child : children) {
            child.display();
        }
    }

    public List<FileSystemItem> getChildren() {
        return children;
    }
}