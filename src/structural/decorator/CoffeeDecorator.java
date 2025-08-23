package structural.decorator;

// Component interface - defines basic coffee operations
interface Coffee {
    String getDesc();
    double getCost();
}

// Concrete component - basic coffee implementation
class PlainCoffee implements Coffee {
    public String getDesc() {
        return "Plain Coffee";
    }

    public double getCost() {
        return 2.0;
    }
}

// Abstract decorator - base class for all decorators
abstract class CoffeeDecorator implements Coffee {
    protected Coffee coffee;  // Reference to decorated coffee

    public CoffeeDecorator(Coffee coffee) {
        this.coffee = coffee;
    }

    // Default implementation delegates to wrapped coffee
    public String getDesc() {
        return coffee.getDesc();
    }

    public double getCost() {
        return coffee.getCost();
    }
}

// Concrete decorator - adds milk functionality
class MilkDecorator extends CoffeeDecorator {
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDesc() {
        return coffee.getDesc() + ", Milk";
    }

    public double getCost() {
        return coffee.getCost() + 0.5;
    }
}

// Concrete decorator - adds sugar functionality
class SugarDecorator extends CoffeeDecorator {
    public SugarDecorator(Coffee coffee) {
        super(coffee);
    }

    public String getDesc() {
        return coffee.getDesc() + ", Sugar";
    }

    public double getCost() {
        return coffee.getCost() + 0.3;
    }
}