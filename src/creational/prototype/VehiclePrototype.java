package creational.prototype;

import java.util.*;

// Prototype interface - defines cloning contract
interface VehiclePrototype {
    public Vehicle cloneVehicle();
    void displayInfo();
}

// Abstract base class - common vehicle properties
abstract class Vehicle implements VehiclePrototype {
    protected String engine;
    protected int wheels;
    protected String color;

    public Vehicle(String engine, int wheels, String color) {
        this.engine = engine;
        this.wheels = wheels;
        this.color = color;
    }

    // Copy constructor for cloning
    protected Vehicle(Vehicle vehicle) {
        this.engine = vehicle.engine;
        this.wheels = vehicle.wheels;
        this.color = vehicle.color;
    }

    public String getEngine() {
        return engine;
    }

    public int getWheels() {
        return wheels;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setEngine(String color) {
        this.engine = color;
    }
}

// Concrete prototype - Car implementation
class Car extends Vehicle {
    private int doors;

    public Car(String engine, int wheels, String color, int doors) {
        super(engine, wheels, color);
        this.doors = doors;
    }

    // Copy constructor
    private Car(Car car) {
        super(car);
        this.doors = car.doors;
    }

    // Clone method - returns Vehicle type
    public Car cloneVehicle() {
        return new Car(this);
    }

    public void displayInfo() {
        System.out.println("Car - Engine: " + engine + ", Wheels: " + wheels +
                ", Color: " + color + ", Doors: " + doors);
    }

    public int getDoors() {
        return doors;
    }
}

// Concrete prototype - Bus implementation
class Bus extends Vehicle {
    private int capacity;

    public Bus(String engine, int wheels, String color, int capacity) {
        super(engine, wheels, color);
        this.capacity = capacity;
    }

    // Copy constructor
    private Bus(Bus bus) {
        super(bus);
        this.capacity = bus.capacity;
    }

    // Clone method - returns Vehicle type
    public Bus cloneVehicle() {
        return new Bus(this);
    }

    public void displayInfo() {
        System.out.println("Bus - Engine: " + engine + ", Wheels: " + wheels +
                ", Color: " + color + ", Capacity: " + capacity);
    }

    public int getCapacity() {
        return capacity;
    }
}

// Prototype registry - manages prototype instances
class VehicleRegistry {
    // Instance map to store prototype instances
    private Map<String, VehiclePrototype> prototypes = new HashMap<>();

    // Constructor - initialize with default prototypes
    public VehicleRegistry() {
        prototypes.put("STANDARD_CAR", new Car("V6", 4, "White", 4));
        prototypes.put("SPORTS_CAR", new Car("V8", 4, "Red", 2));
        prototypes.put("CITY_BUS", new Bus("Diesel V6", 6, "Blue", 50));
        prototypes.put("SCHOOL_BUS", new Bus("Diesel V8", 6, "Yellow", 72));
    }

    // Get clone of registered prototype by type
    public Vehicle getPrototype(String type) {
        VehiclePrototype prototype = prototypes.get(type);
        if (prototype != null) {
            return prototype.cloneVehicle();
        }
        throw new IllegalArgumentException("Unknown prototype type: " + type);
    }

    // Add new prototype to registry
    public void addPrototype(String type, VehiclePrototype prototype) {
        if (type == null || prototype == null) {
            throw new IllegalArgumentException("Type and prototype cannot be null");
        }
        prototypes.put(type, prototype);
    }

    // Remove prototype from registry
    public boolean removePrototype(String type) {
        return prototypes.remove(type) != null;
    }

    // Get all available prototype types
    public Set<String> getAvailableTypes() {
        return prototypes.keySet();
    }

    // Check if prototype type exists
    public boolean hasPrototype(String type) {
        return prototypes.containsKey(type);
    }

    // Get number of registered prototypes
    public int getPrototypeCount() {
        return prototypes.size();
    }

    // Clear all prototypes (useful for testing)
    public void clearRegistry() {
        prototypes.clear();
    }
}