package behavioral.mediator;

import java.util.*;

public class TestMediatorATC {
    public static void main(String[] args) {
        System.out.println("=== Testing Mediator Pattern ===\n");

        // Test 1: Basic mediator and component setup
        System.out.println("Test 1: Basic mediator and component setup");
        AirTrafficControlTower atcTower = new AirTrafficControlTower();

        Airplane flight101 = new Airplane(atcTower, "AA101");
        Airplane flight202 = new Airplane(atcTower, "BA202");
        Airplane flight303 = new Airplane(atcTower, "UA303");

        atcTower.addComponent(flight101);
        atcTower.addComponent(flight202);
        atcTower.addComponent(flight303);

        System.out.println("✓ ATC Tower and aircraft created successfully");
        atcTower.showSystemStatus();

        // Test 2: Basic communication through mediator
        System.out.println("Test 2: Basic communication through mediator");

        System.out.println("✓ Testing takeoff request when runway available:");
        flight101.requestTakeOff();
        atcTower.showSystemStatus();

        // Simulate takeoff completion
        System.out.println("Simulating takeoff completion:");
        atcTower.notify(flight101, "TAKEOFF_COMPLETE");
        atcTower.showSystemStatus();

        // Test 3: Conflict resolution through mediator
        System.out.println("Test 3: Conflict resolution through mediator");

        System.out.println("✓ Testing simultaneous requests (conflict scenario):");
        flight202.requestTakeOff();
        System.out.println("Runway status after first request: " +
                (atcTower.isRunwayAvailable() ? "Available" : "Occupied"));

        flight303.requestLanding(); // Should be denied
        System.out.println("Second aircraft request while runway occupied:");
        atcTower.showSystemStatus();

        // Complete first operation
        System.out.println("Completing first operation:");
        atcTower.notify(flight202, "TAKEOFF_COMPLETE");
        atcTower.showSystemStatus();

        // Test 4: Multiple aircraft coordination
        System.out.println("Test 4: Multiple aircraft coordination");

        // Add more aircraft
        Airplane flight404 = new Airplane(atcTower, "DL404");
        Airplane flight505 = new Airplane(atcTower, "SW505");

        atcTower.addComponent(flight404);
        atcTower.addComponent(flight505);

        System.out.println("✓ Testing coordination with 5 aircraft:");
        atcTower.showSystemStatus();

        // Multiple requests
        flight303.requestLanding();
        flight404.requestTakeOff();
        flight505.requestLanding();

        System.out.println("Multiple requests processed through mediator:");
        atcTower.showSystemStatus();

        // Test 5: Mediator execute methods
        System.out.println("Test 5: Mediator execute methods");

        System.out.println("✓ Testing mediator's coordination methods:");
        atcTower.executeA(); // Coordinate takeoffs
        System.out.println();
        atcTower.executeB(); // Coordinate landings
        System.out.println();
        atcTower.execute();  // General coordination

        // Test 6: Component removal and dynamic system changes
        System.out.println("Test 6: Component removal and dynamic system changes");

        System.out.println("✓ Testing aircraft removal from system:");
        atcTower.showSystemStatus();

        atcTower.removeComponent(flight505);
        System.out.println("After removing flight SW505:");
        atcTower.showSystemStatus();

        // Add aircraft back
        atcTower.addComponent(flight505);
        System.out.println("After re-adding flight SW505:");
        atcTower.showSystemStatus();

        // Test 7: Complex interaction scenarios
        System.out.println("Test 7: Complex interaction scenarios");

        // Create scenario with multiple pending requests
        flight101.requestLanding();
        flight202.requestTakeOff();
        flight404.requestLanding();

        System.out.println("✓ Multiple pending requests created:");
        atcTower.showSystemStatus();

        // Process requests one by one
        System.out.println("Processing requests sequentially:");

        // Grant first request
        if (!atcTower.isRunwayAvailable()) {
            System.out.println("Runway occupied, processing first in queue...");
        } else {
            System.out.println("Processing first request...");
            atcTower.executeA(); // Coordinate takeoffs
        }

        // Test 8: Mediator state management
        System.out.println("Test 8: Mediator state management");

        System.out.println("✓ Testing mediator state tracking:");
        System.out.println("Current runway user: " + atcTower.getCurrentRunwayUser());
        System.out.println("Runway available: " + atcTower.isRunwayAvailable());
        System.out.println("Total aircraft in system: " + atcTower.getAirplanes().size());

        // Test state changes
        if (atcTower.isRunwayAvailable()) {
            flight303.requestTakeOff();
            System.out.println("After new takeoff request:");
            System.out.println("Runway available: " + atcTower.isRunwayAvailable());
            System.out.println("Current runway user: " + atcTower.getCurrentRunwayUser());
        }

        // Test 9: Communication patterns
        System.out.println("\nTest 9: Communication patterns");

        System.out.println("✓ Testing different communication scenarios:");

        // Emergency landing scenario
        flight404.notifyAirTrafficControl("EMERGENCY_LANDING");

        // Weather update scenario
        flight505.notifyAirTrafficControl("WEATHER_UPDATE_NEEDED");

        // Fuel status scenario
        flight101.notifyAirTrafficControl("LOW_FUEL_WARNING");

        System.out.println("All communications routed through mediator successfully");

        // Test 10: Advanced mediator features
        System.out.println("\nTest 10: Advanced mediator features");

        // Custom mediator with additional features
        class AdvancedATC extends AirTrafficControlTower {
            private Queue<Airplane> takeoffQueue = new LinkedList<>();
            private Queue<Airplane> landingQueue = new LinkedList<>();

            @Override
            public void notify(Component component, String message) {
                super.notify(component, message);

                if (component instanceof Airplane) {
                    Airplane airplane = (Airplane) component;

                    switch (message) {
                        case "TAKEOFF_REQUEST":
                            if (!isRunwayAvailable()) {
                                takeoffQueue.offer(airplane);
                                System.out.println("[Advanced ATC] " + airplane.getFlightNumber() +
                                        " added to takeoff queue");
                            }
                            break;

                        case "LANDING_REQUEST":
                            if (!isRunwayAvailable()) {
                                landingQueue.offer(airplane);
                                System.out.println("[Advanced ATC] " + airplane.getFlightNumber() +
                                        " added to landing queue (priority)");
                            }
                            break;
                    }
                }
            }

            public void processQueues() {
                if (isRunwayAvailable()) {
                    // Landing has priority over takeoff
                    if (!landingQueue.isEmpty()) {
                        Airplane airplane = landingQueue.poll();
                        System.out.println("[Advanced ATC] Processing queued landing for " +
                                airplane.getFlightNumber());
                        airplane.receivePermission("LANDING_APPROVED");
                    } else if (!takeoffQueue.isEmpty()) {
                        Airplane airplane = takeoffQueue.poll();
                        System.out.println("[Advanced ATC] Processing queued takeoff for " +
                                airplane.getFlightNumber());
                        airplane.receivePermission("TAKEOFF_APPROVED");
                    }
                }
            }

            public void showQueues() {
                System.out.println("Landing Queue: " + landingQueue.size() + " aircraft");
                System.out.println("Takeoff Queue: " + takeoffQueue.size() + " aircraft");
            }
        }

        AdvancedATC advancedATC = new AdvancedATC();

        // Create new aircraft for advanced testing
        Airplane flight606 = new Airplane(advancedATC, "EK606");
        Airplane flight707 = new Airplane(advancedATC, "QF707");

        advancedATC.addComponent(flight606);
        advancedATC.addComponent(flight707);

        System.out.println("✓ Advanced ATC with queuing system:");

        // Occupy runway first
        flight606.requestTakeOff();

        // Queue other requests
        flight707.requestLanding();

        advancedATC.showQueues();

        // Complete first operation and process queue
        advancedATC.notify(flight606, "TAKEOFF_COMPLETE");
        advancedATC.processQueues();

        System.out.println("\n=== Test Summary ===");
        System.out.println("Mediator Pattern verified:");
        System.out.println("- Components communicate only through mediator, not directly");
        System.out.println("- Mediator encapsulates interaction logic between components");
        System.out.println("- Components are decoupled from each other");
        System.out.println("- Mediator manages complex coordination and conflict resolution");
        System.out.println("- System supports dynamic addition/removal of components");
        System.out.println("- Mediator maintains system state and coordinates operations");
        System.out.println("- Communication flows through single point (mediator)");
        System.out.println("- Different types of requests handled through unified interface");
        System.out.println("- Advanced features like queuing can be added to mediator");

        demonstrateMediatorBenefits();
    }

    private static void demonstrateMediatorBenefits() {
        System.out.println("\n=== Mediator Pattern Benefits ===");

        System.out.println("Without Mediator Pattern:");
        System.out.println("- Aircraft would need references to all other aircraft");
        System.out.println("- Each aircraft would need to coordinate directly with others");
        System.out.println("- Complex many-to-many communication relationships");
        System.out.println("- Tight coupling between all aircraft objects");
        System.out.println("- Difficult to add new aircraft types or coordination logic");

        System.out.println("\nWith Mediator Pattern:");
        System.out.println("- Aircraft only know about the ATC mediator");
        System.out.println("- ATC handles all coordination and conflict resolution");
        System.out.println("- One-to-many communication through central mediator");
        System.out.println("- Loose coupling - aircraft are independent of each other");
        System.out.println("- Easy to extend with new aircraft types and coordination rules");

        System.out.println("\nReal-World Mediator Examples:");
        System.out.println("- Chat Room Applications (users communicate through chat room)");
        System.out.println("- Airport Control Systems (aircraft coordinate through control tower)");
        System.out.println("- GUI Dialog Boxes (controls interact through dialog controller)");
        System.out.println("- Workflow Management (tasks coordinate through workflow engine)");
        System.out.println("- Microservice Orchestration (services communicate through orchestrator)");

        System.out.println("\nMediator vs Other Patterns:");
        System.out.println("- Mediator vs Observer: Mediator handles complex interactions, Observer handles notifications");
        System.out.println("- Mediator vs Facade: Mediator manages peer communication, Facade simplifies subsystem access");
        System.out.println("- Mediator vs Command: Mediator coordinates objects, Command encapsulates requests");
    }
}