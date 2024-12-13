package behavioral.mediator;

import java.util.*;
/**
 * Mediator interface that defines communication contract
 * Encapsulates how components interact with each other
 */
interface IMediator {
    void execute();
    void executeA();
    void executeB();
    void notify(Component component, String message);
    void addComponent(Component component);
    void removeComponent(Component component);
}

/**
 * Abstract component class that has reference to mediator
 * All components communicate through the mediator
 */
abstract class Component {
    protected IMediator mediator;
    protected String componentId;

    public Component(IMediator mediator, String componentId) {
        this.mediator = mediator;
        this.componentId = componentId;
    }

    public abstract void executeA();
    public abstract void executeB();

    protected void notifyMediator(String message) {
        if (mediator != null) {
            mediator.notify(this, message);
        }
    }

    public String getComponentId() {
        return componentId;
    }
}

/**
 * Airplane component - represents aircraft in the system
 */
class Airplane extends Component {
    private String flightNumber;
    private String status;
    private String currentLocation;

    public Airplane(IMediator mediator, String flightNumber) {
        super(mediator, flightNumber);
        this.flightNumber = flightNumber;
        this.status = "In Transit";
        this.currentLocation = "Airspace";
    }

    @Override
    public void executeA() {
        System.out.println("[" + flightNumber + "] Requesting takeoff clearance");
        status = "Requesting Takeoff";
        notifyMediator("TAKEOFF_REQUEST");
    }

    @Override
    public void executeB() {
        System.out.println("[" + flightNumber + "] Requesting landing clearance");
        status = "Requesting Landing";
        notifyMediator("LANDING_REQUEST");
    }

    public void requestTakeOff() {
        executeA();
    }

    public void requestLanding() {
        executeB();
    }

    public void receivePermission(String permissionType) {
        if ("TAKEOFF_APPROVED".equals(permissionType)) {
            status = "Taking Off";
            currentLocation = "Runway";
            System.out.println("[" + flightNumber + "] ✓ Takeoff approved - proceeding to runway");
        } else if ("LANDING_APPROVED".equals(permissionType)) {
            status = "Landing";
            currentLocation = "Runway";
            System.out.println("[" + flightNumber + "] ✓ Landing approved - proceeding to land");
        } else if ("PERMISSION_DENIED".equals(permissionType)) {
            System.out.println("[" + flightNumber + "] ✗ Permission denied - maintaining current position");
        }
    }

    public void notifyAirTrafficControl(String message) {
        System.out.println("[" + flightNumber + "] Notifying ATC: " + message);
        notifyMediator(message);
    }

    public String getFlightNumber() { return flightNumber; }
    public String getStatus() { return status; }
    public String getCurrentLocation() { return currentLocation; }

    @Override
    public String toString() {
        return "Flight " + flightNumber + " [Status: " + status + ", Location: " + currentLocation + "]";
    }
}

/**
 * Concrete Mediator - Air Traffic Control Tower
 * Manages communication between airplanes and coordinates their operations
 */
class AirTrafficControlTower implements IMediator {
    private List<Component> components;
    private List<Airplane> airplanes;
    private boolean runwayAvailable;
    private String currentRunwayUser;

    public AirTrafficControlTower() {
        this.components = new ArrayList<>();
        this.airplanes = new ArrayList<>();
        this.runwayAvailable = true;
        this.currentRunwayUser = null;
    }

    @Override
    public void execute() {
        System.out.println("[ATC Tower] General coordination operation executed");
        showSystemStatus();
    }

    @Override
    public void executeA() {
        System.out.println("[ATC Tower] Executing takeoff coordination protocol");
        coordinateTakeoffs();
    }

    @Override
    public void executeB() {
        System.out.println("[ATC Tower] Executing landing coordination protocol");
        coordinateLandings();
    }

    @Override
    public void notify(Component component, String message) {
        System.out.println("[ATC Tower] Received notification from " + component.getComponentId() + ": " + message);

        if (component instanceof Airplane) {
            Airplane airplane = (Airplane) component;
            handleAirplaneRequest(airplane, message);
        }
    }

    private void handleAirplaneRequest(Airplane airplane, String request) {
        switch (request) {
            case "TAKEOFF_REQUEST":
                if (runwayAvailable) {
                    grantTakeoffPermission(airplane);
                } else {
                    denyPermission(airplane, "Runway occupied by " + currentRunwayUser);
                }
                break;

            case "LANDING_REQUEST":
                if (runwayAvailable) {
                    grantLandingPermission(airplane);
                } else {
                    denyPermission(airplane, "Runway occupied by " + currentRunwayUser);
                }
                break;

            case "TAKEOFF_COMPLETE":
                runwayAvailable = true;
                currentRunwayUser = null;
                System.out.println("[ATC Tower] Runway is now available");
                notifyWaitingAircraft();
                break;

            case "LANDING_COMPLETE":
                runwayAvailable = true;
                currentRunwayUser = null;
                System.out.println("[ATC Tower] Runway is now available");
                notifyWaitingAircraft();
                break;

            default:
                System.out.println("[ATC Tower] Unknown request: " + request);
        }
    }

    private void grantTakeoffPermission(Airplane airplane) {
        runwayAvailable = false;
        currentRunwayUser = airplane.getFlightNumber();
        airplane.receivePermission("TAKEOFF_APPROVED");

        // Notify other aircraft about runway status
        for (Airplane other : airplanes) {
            if (!other.equals(airplane)) {
                System.out.println("[ATC Tower] Informing " + other.getFlightNumber() +
                        " that runway is occupied");
            }
        }
    }

    private void grantLandingPermission(Airplane airplane) {
        runwayAvailable = false;
        currentRunwayUser = airplane.getFlightNumber();
        airplane.receivePermission("LANDING_APPROVED");

        // Notify other aircraft about runway status
        for (Airplane other : airplanes) {
            if (!other.equals(airplane)) {
                System.out.println("[ATC Tower] Informing " + other.getFlightNumber() +
                        " that runway is occupied");
            }
        }
    }

    private void denyPermission(Airplane airplane, String reason) {
        airplane.receivePermission("PERMISSION_DENIED");
        System.out.println("[ATC Tower] Permission denied for " + airplane.getFlightNumber() +
                " - Reason: " + reason);
    }

    private void notifyWaitingAircraft() {
        System.out.println("[ATC Tower] Notifying waiting aircraft that runway is available");
        for (Airplane airplane : airplanes) {
            if ("Requesting Takeoff".equals(airplane.getStatus()) ||
                    "Requesting Landing".equals(airplane.getStatus())) {
                System.out.println("[ATC Tower] " + airplane.getFlightNumber() +
                        " can now proceed with request");
            }
        }
    }

    private void coordinateTakeoffs() {
        System.out.println("[ATC Tower] Coordinating takeoff operations");
        for (Airplane airplane : airplanes) {
            if ("Requesting Takeoff".equals(airplane.getStatus())) {
                handleAirplaneRequest(airplane, "TAKEOFF_REQUEST");
            }
        }
    }

    private void coordinateLandings() {
        System.out.println("[ATC Tower] Coordinating landing operations");
        for (Airplane airplane : airplanes) {
            if ("Requesting Landing".equals(airplane.getStatus())) {
                handleAirplaneRequest(airplane, "LANDING_REQUEST");
            }
        }
    }

    @Override
    public void addComponent(Component component) {
        components.add(component);
        if (component instanceof Airplane) {
            airplanes.add((Airplane) component);
            System.out.println("[ATC Tower] Aircraft " + component.getComponentId() +
                    " registered with control tower");
        }
    }

    @Override
    public void removeComponent(Component component) {
        components.remove(component);
        if (component instanceof Airplane) {
            airplanes.remove((Airplane) component);
            System.out.println("[ATC Tower] Aircraft " + component.getComponentId() +
                    " unregistered from control tower");
        }
    }

    public void showSystemStatus() {
        System.out.println("\n=== Air Traffic Control Status ===");
        System.out.println("Runway Status: " + (runwayAvailable ? "Available" : "Occupied by " + currentRunwayUser));
        System.out.println("Registered Aircraft: " + airplanes.size());

        for (Airplane airplane : airplanes) {
            System.out.println("  " + airplane);
        }
        System.out.println("================================\n");
    }

    public boolean isRunwayAvailable() {
        return runwayAvailable;
    }

    public String getCurrentRunwayUser() {
        return currentRunwayUser;
    }

    public List<Airplane> getAirplanes() {
        return new ArrayList<>(airplanes);
    }
}