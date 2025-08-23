package structural.private_class_data;

// Data class - extracts data from the target class
class DataClass {
    private String attribute1;
    private String attribute2;
    private String attribute3;

    public DataClass(String attr1, String attr2, String attr3) {
        this.attribute1 = attr1;
        this.attribute2 = attr2;
        this.attribute3 = attr3;
    }

    // Getters for accessing data (no setters - immutable)
    public String getAttribute1() {
        return attribute1;
    }

    public String getAttribute2() {
        return attribute2;
    }

    public String getAttribute3() {
        return attribute3;
    }
}

// Main class - uses data class instance
class MainClass {
    private DataClass data;  // Reference to data class

    public MainClass(String attr1, String attr2, String attr3) {
        this.data = new DataClass(attr1, attr2, attr3);
    }

    // Methods that use the data without exposing it
    public void displayInfo() {
        System.out.println("MainClass Info:");
        System.out.println("Attribute 1: " + data.getAttribute1());
        System.out.println("Attribute 2: " + data.getAttribute2());
        System.out.println("Attribute 3: " + data.getAttribute3());
    }

    public String getFormattedData() {
        return String.format("Data[%s, %s, %s]",
                data.getAttribute1(),
                data.getAttribute2(),
                data.getAttribute3());
    }
}

// Circle example from your notes
class CircleData {
    private double radius;
    private String color;
    private String origin;

    public CircleData(double radius, String color, String origin) {
        this.radius = radius;
        this.color = color;
        this.origin = origin;
    }

    // Getters only - no setters for immutability
    public double getRadius() {
        return radius;
    }

    public String getColor() {
        return color;
    }

    public String getOrigin() {
        return origin;
    }
}

// Circle class - uses CircleData
class Circle {
    private CircleData circleData;  // Private data reference

    public Circle(double radius, String color, String origin) {
        this.circleData = new CircleData(radius, color, origin);
    }

    // Calculate diameter using private data
    public double getDiameter() {
        return circleData.getRadius() * 2;
    }

    // Calculate circumference using private data
    public double getCircumference() {
        return 2 * Math.PI * circleData.getRadius();
    }

    // Display circle information
    public void displayCircle() {
        System.out.println("Circle - Radius: " + circleData.getRadius() +
                ", Color: " + circleData.getColor() +
                ", Origin: " + circleData.getOrigin());
        System.out.println("Diameter: " + getDiameter());
        System.out.println("Circumference: " + String.format("%.2f", getCircumference()));
    }

    // Method to get circle details without exposing internal data structure
    public String getCircleInfo() {
        return "Circle[radius=" + circleData.getRadius() +
                ", color=" + circleData.getColor() +
                ", origin=" + circleData.getOrigin() + "]";
    }
}