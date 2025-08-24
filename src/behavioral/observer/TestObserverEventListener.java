package behavioral.observer;

public class TestObserverEventListener {
    public static void main(String[] args) {
        System.out.println("=== Observer Pattern Demo ===\n");

        // Create publisher
        ConcreteSubscriber eventPublisher = new ConcreteSubscriber("NewsService");

        // Create listeners
        EmailMsgListener emailListener1 = new EmailMsgListener("user1@example.com");
        EmailMsgListener emailListener2 = new EmailMsgListener("user2@example.com");
        MobileAppListener mobileListener1 = new MobileAppListener("device-123");
        MobileAppListener mobileListener2 = new MobileAppListener("device-456");

        // Subscribe listeners
        System.out.println("Subscribing listeners...");
        eventPublisher.subscribe(emailListener1);
        eventPublisher.subscribe(emailListener2);
        eventPublisher.subscribe(mobileListener1);
        eventPublisher.subscribe(mobileListener2);

        System.out.println("\nTriggering event #1:");
        eventPublisher.performAction();

        // Unsubscribe one listener
        System.out.println("\nUnsubscribing user1@example.com...");
        eventPublisher.unsubscribe(emailListener1);

        System.out.println("\nTriggering event #2:");
        eventPublisher.performAction();

        // Test dynamic subscription
        System.out.println("\nAdding new mobile listener...");
        MobileAppListener newMobileListener = new MobileAppListener("device-789");
        eventPublisher.subscribe(newMobileListener);

        System.out.println("\nTriggering event #3:");
        eventPublisher.performAction();

        System.out.println("\n=== Demo Complete ===");
    }
}
