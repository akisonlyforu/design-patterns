package structural.bridge;

/**
 * Workshop interface - defines what operations can be performed
 * This is the implementor in the Bridge pattern
 */
interface Workshop {
    void work();
    String getWorkshopType();
}

class Produce implements Workshop {
    @Override
    public void work() {
        System.out.println("Producing the vehicle");
    }

    @Override
    public String getWorkshopType() {
        return "Production";
    }
}

class Assemble implements Workshop {
    @Override
    public void work() {
        System.out.println("Assembling the vehicle");
    }

    @Override
    public String getWorkshopType() {
        return "Assembly";
    }
}

class Paint implements Workshop {
    @Override
    public void work() {
        System.out.println("Painting the vehicle");
    }

    @Override
    public String getWorkshopType() {
        return "Painting";
    }
}

class Inspect implements Workshop {
    @Override
    public void work() {
        System.out.println("Inspecting the vehicle");
    }

    @Override
    public String getWorkshopType() {
        return "Quality Control";
    }
}

/**
 * Vehicle abstraction - contains reference to implementor
 * This is the abstraction in the Bridge pattern
 */
abstract class Vehicle {
    protected Workshop workshop1;
    protected Workshop workshop2;

    protected Vehicle(Workshop workshop1, Workshop workshop2) {
        this.workshop1 = workshop1;
        this.workshop2 = workshop2;
    }

    // Template method that defines the manufacturing process
    public void manufacture() {
        System.out.println("Starting " + getVehicleType() + " manufacturing process:");
        performWorkshop1();
        performWorkshop2();
        System.out.println(getVehicleType() + " manufacturing completed!\n");
    }

    protected void performWorkshop1() {
        System.out.print(getVehicleType() + " - ");
        workshop1.work();
    }

    protected void performWorkshop2() {
        System.out.print(getVehicleType() + " - ");
        workshop2.work();
    }

    public abstract String getVehicleType();

    public void displayConfiguration() {
        System.out.println(getVehicleType() + " Configuration:");
        System.out.println("  Workshop 1: " + workshop1.getWorkshopType());
        System.out.println("  Workshop 2: " + workshop2.getWorkshopType());
    }
}

class Car extends Vehicle {
    public Car(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    public String getVehicleType() {
        return "Car";
    }
}

class Bike extends Vehicle {
    public Bike(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    public String getVehicleType() {
        return "Bike";
    }
}

class Truck extends Vehicle {
    public Truck(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    public String getVehicleType() {
        return "Truck";
    }
}

class Motorcycle extends Vehicle {
    public Motorcycle(Workshop workshop1, Workshop workshop2) {
        super(workshop1, workshop2);
    }

    @Override
    public String getVehicleType() {
        return "Motorcycle";
    }
}