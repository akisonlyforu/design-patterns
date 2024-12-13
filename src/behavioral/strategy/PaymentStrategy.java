package behavioral.strategy;

import java.util.*;

// Strategy Interface - defines the common interface for all strategies
interface IStrategy {
    void execute(double amount);
}

// Payment Strategy Interface - more specific for payment processing
interface PaymentStrategy {
    void collectPaymentDetails();
    boolean validate();
    void pay(int amount);
}

// Concrete Strategy - PayPal Payment
class PaymentByPayPal implements PaymentStrategy {
    private String email;
    private String password;

    public PaymentByPayPal() {
        // Constructor for PayPal payment method
    }

    @Override
    public void collectPaymentDetails() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter PayPal email: ");
        this.email = "user@paypal.com"; // Simulated input
        System.out.print("Enter PayPal password: ");
        this.password = "password123"; // Simulated input
        System.out.println("PayPal payment details collected.");
    }

    @Override
    public boolean validate() {
        System.out.println("Validating PayPal credentials...");
        // Simulate validation logic
        boolean isValid = email != null && !email.isEmpty() &&
                password != null && !password.isEmpty();
        System.out.println("PayPal validation: " + (isValid ? "SUCCESS" : "FAILED"));
        return isValid;
    }

    @Override
    public void pay(int amount) {
        if (validate()) {
            System.out.println("Paying $" + amount + " via PayPal");
            System.out.println("PayPal payment processed successfully!");
        } else {
            System.out.println("PayPal payment failed - invalid credentials");
        }
    }
}

// Concrete Strategy - Credit Card Payment
class PaymentByCreditCard implements PaymentStrategy {
    private String cardNumber;
    private String expiryDate;
    private String cvv;
    private String cardHolderName;

    public PaymentByCreditCard() {
        // Constructor for Credit Card payment method
    }

    @Override
    public void collectPaymentDetails() {
        System.out.println("Collecting Credit Card details...");
        this.cardNumber = "1234-5678-9012-3456"; // Simulated input
        this.expiryDate = "12/25"; // Simulated input
        this.cvv = "123"; // Simulated input
        this.cardHolderName = "John Doe"; // Simulated input
        System.out.println("Credit Card payment details collected.");
    }

    @Override
    public boolean validate() {
        System.out.println("Validating Credit Card details...");
        // Simulate validation logic
        boolean isValid = cardNumber != null && cardNumber.length() >= 16 &&
                expiryDate != null && cvv != null && cvv.length() == 3;
        System.out.println("Credit Card validation: " + (isValid ? "SUCCESS" : "FAILED"));
        return isValid;
    }

    @Override
    public void pay(int amount) {
        if (validate()) {
            System.out.println("Paying $" + amount + " via Credit Card");
            System.out.println("Card: ****-****-****-" + cardNumber.substring(cardNumber.length() - 4));
            System.out.println("Credit Card payment processed successfully!");
        } else {
            System.out.println("Credit Card payment failed - invalid details");
        }
    }
}

// Context Class - PaymentService
class PaymentService {
    private PaymentStrategy strategy;

    public PaymentService() {
        // Default constructor
    }

    public void setStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    public void processOrder(int amount) {
        if (strategy == null) {
            System.out.println("No payment strategy selected!");
            return;
        }

        System.out.println("\n--- Processing Order ---");
        strategy.collectPaymentDetails();
        strategy.pay(amount);
        System.out.println("--- Order Complete ---\n");
    }

    // Alternative method for direct execution
    public void execute(int amount) {
        processOrder(amount);
    }
}

// Generic Strategy implementations (as shown in your interface diagram)
class ConcreteStrategy implements IStrategy {
    @Override
    public void execute(double amount) {
        System.out.println("ConcreteStrategy executing with amount: $" + amount);
    }
}

// Client class that uses the strategies
class Client {
    private PaymentService paymentService;

    public Client() {
        this.paymentService = new PaymentService();
    }

    public void makePayment(String paymentMethod, int amount) {
        PaymentStrategy strategy;

        switch (paymentMethod.toLowerCase()) {
            case "paypal":
                strategy = new PaymentByPayPal();
                break;
            case "creditcard":
            case "credit":
                strategy = new PaymentByCreditCard();
                break;
            default:
                System.out.println("Unsupported payment method: " + paymentMethod);
                return;
        }

        paymentService.setStrategy(strategy);
        paymentService.processOrder(amount);
    }
}