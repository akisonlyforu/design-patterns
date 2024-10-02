package creational.builder;

// Product class - immutable object created by builder
class Vehicle {
    // Required parameters
    private String engine;
    private int wheel;

    // Optional parameters
    private int airbags;

    public String getEngine() {
        return this.engine;
    }

    public int getWheel() {
        return this.wheel;
    }

    public int getAirbags() {
        return this.airbags;
    }

    // Private constructor - forces use of builder
    private Vehicle(VehicleBuilder builder) {
        this.engine = builder.engine;
        this.wheel = builder.wheel;
        this.airbags = builder.airbags;
    }

    // Static nested builder class
    public static class VehicleBuilder {
        // Required parameters in builder
        private String engine;
        private int wheel;

        // Optional parameters in builder
        private int airbags;

        // Constructor for required parameters only
        public VehicleBuilder(String engine, int wheel) {
            this.engine = engine;
            this.wheel = wheel;
        }

        // Fluent setter for optional parameter - returns this for chaining
        public VehicleBuilder setAirbags(int airbags) {
            this.airbags = airbags;
            return this;
        }

        // Build method - creates final immutable object
        public Vehicle build() {
            return new Vehicle(this);
        }
    }
}

// Director class - orchestrates building process for specific configurations
class VehicleDirector {

    // Creates a sports car configuration
    public Vehicle constructSportsCar(Vehicle.VehicleBuilder builder) {
        return builder
                .setAirbags(2)  // Sports cars have minimal airbags for weight
                .build();
    }

    // Creates a family car configuration
    public Vehicle constructFamilyCar(Vehicle.VehicleBuilder builder) {
        return builder
                .setAirbags(8)  // Family cars prioritize safety
                .build();
    }
}