package creational.prototype;

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