package behavioral.strategy;

public class TestPaymentStrategy {
    public static void main(String[] args) {
        System.out.println("=== Strategy Pattern Demo - Payment Processing ===\n");

        Client client = new Client();

        // Test PayPal payment
        System.out.println("1. Testing PayPal Payment:");
        client.makePayment("paypal", 100);

        // Test Credit Card payment
        System.out.println("2. Testing Credit Card Payment:");
        client.makePayment("creditcard", 250);

        // Test unsupported payment method
        System.out.println("3. Testing Unsupported Payment Method:");
        client.makePayment("bitcoin", 50);

        // Direct strategy usage
        System.out.println("4. Direct Strategy Usage:");
        PaymentService service = new PaymentService();

        PaymentStrategy paypalStrategy = new PaymentByPayPal();
        service.setStrategy(paypalStrategy);
        service.execute(75);

        PaymentStrategy cardStrategy = new PaymentByCreditCard();
        service.setStrategy(cardStrategy);
        service.execute(150);

        // Test generic strategy interface
        System.out.println("5. Generic Strategy Interface:");
        IStrategy genericStrategy = new ConcreteStrategy();
        genericStrategy.execute(300.50);

        System.out.println("=== Demo Complete ===");
    }
}