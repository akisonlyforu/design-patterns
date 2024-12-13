package behavioral.state;

public class TestPhoneState {
    public static void main(String[] args) {
        System.out.println("=== State Pattern Demo - Phone States ===\n");

        Phone phone = new Phone();

        // Test initial state (OffState)
        System.out.println("Initial State: " + phone.getCurrentState());
        System.out.println("Action - onHome(): " + phone.onHome());
        System.out.println("Action - lock(): " + phone.lock());
        System.out.println();

        // Turn on phone (OffState -> ReadyState)
        System.out.println("Action - turnOn(): " + phone.turnOn());
        System.out.println("Current State: " + phone.getCurrentState());
        System.out.println("Action - onHome(): " + phone.onHome());
        System.out.println();

        // Lock phone (ReadyState -> LockedState)
        System.out.println("Action - lock(): " + phone.lock());
        System.out.println("Current State: " + phone.getCurrentState());
        System.out.println("Action - onHome(): " + phone.onHome());
        System.out.println("Action - home(): " + phone.home());
        System.out.println();

        // Unlock phone (LockedState -> ReadyState)
        System.out.println("Action - unlock(): " + phone.unlock());
        System.out.println("Current State: " + phone.getCurrentState());
        System.out.println("Action - onHome(): " + phone.onHome());
        System.out.println();

        // Turn off phone (ReadyState -> OffState)
        System.out.println("Action - onOffOn(): " + phone.onOffOn());
        System.out.println("Current State: " + phone.getCurrentState());
        System.out.println("Action - unlock(): " + phone.unlock());
        System.out.println();

        // Turn back on
        System.out.println("Action - onOffOn(): " + phone.onOffOn());
        System.out.println("Final State: " + phone.getCurrentState());

        System.out.println("\n=== Testing Interface-based State ===");

        // Test interface-based state (as mentioned in your notes)
        IState concreteState = new ConcreteState(phone);
        concreteState.doSomething();
        concreteState.doMoreStuff();

        System.out.println("\n=== Demo Complete ===");
    }
}