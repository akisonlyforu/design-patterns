package behavioral.observer;

import java.util.*;

// Observer interface
interface EventListener {
    void update();
}

// Publisher class
class Publisher {
    private List<EventListener> subscribers = new ArrayList<>();

    public void subscribe(EventListener listener) {
        subscribers.add(listener);
    }

    public void unsubscribe(EventListener listener) {
        subscribers.remove(listener);
    }

    public void notifySubscribers() {
        for (EventListener subscriber : subscribers) {
            subscriber.update();
        }
    }
}

// Concrete EmailMsgListener implementation
class EmailMsgListener implements EventListener {
    private final String email;

    public EmailMsgListener(String email) {
        this.email = email;
    }

    @Override
    public void update() {
        sendEmail();
    }

    private void sendEmail() {
        System.out.println("Sending email notification to: " + email);
    }
}

// Concrete MobileAppListener implementation
class MobileAppListener implements EventListener {
    private final String deviceId;

    public MobileAppListener(String deviceId) {
        this.deviceId = deviceId;
    }

    @Override
    public void update() {
        sendPushNotification();
    }

    private void sendPushNotification() {
        System.out.println("Sending push notification to device: " + deviceId);
    }
}

// ConcreteSubscriber that combines both notification types
class ConcreteSubscriber extends Publisher {
    private final String name;

    public ConcreteSubscriber(String name) {
        this.name = name;
    }

    public void performAction() {
        System.out.println("Event happened for: " + name);
        notifySubscribers();
    }

    public String getName() {
        return name;
    }
}