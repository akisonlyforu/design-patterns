package behavioral.state;

abstract class State {
    protected Phone phone;

    public State(Phone phone) {
        this.phone = phone;
    }

    public abstract String onHome();
    public abstract String onOffOn();
    public abstract String lock();
    public abstract String home();
    public abstract String unlock();
    public abstract String turnOn();
}

// Concrete State - LockedState
class LockedState extends State {
    public LockedState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return "Phone is locked. Please unlock first.";
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return "Phone turning off from locked state.";
    }

    @Override
    public String lock() {
        return "Phone is already locked.";
    }

    @Override
    public String home() {
        return "Phone is locked. Home button disabled.";
    }

    @Override
    public String unlock() {
        phone.setState(new ReadyState(phone));
        return "Phone unlocked successfully.";
    }

    @Override
    public String turnOn() {
        return "Phone is already on but locked.";
    }
}

// Concrete State - ReadyState
class ReadyState extends State {
    public ReadyState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return "Going to home screen.";
    }

    @Override
    public String onOffOn() {
        phone.setState(new OffState(phone));
        return "Phone turning off from ready state.";
    }

    @Override
    public String lock() {
        phone.setState(new LockedState(phone));
        return "Phone locked.";
    }

    @Override
    public String home() {
        return "Already on home screen.";
    }

    @Override
    public String unlock() {
        return "Phone is already unlocked.";
    }

    @Override
    public String turnOn() {
        return "Phone is already on and ready.";
    }
}

// Concrete State - OffState
class OffState extends State {
    public OffState(Phone phone) {
        super(phone);
    }

    @Override
    public String onHome() {
        return "Phone is off. Cannot access home.";
    }

    @Override
    public String onOffOn() {
        phone.setState(new ReadyState(phone));
        return "Phone turning on.";
    }

    @Override
    public String lock() {
        return "Phone is off. Cannot lock.";
    }

    @Override
    public String home() {
        return "Phone is off. Cannot access home.";
    }

    @Override
    public String unlock() {
        return "Phone is off. Cannot unlock.";
    }

    @Override
    public String turnOn() {
        phone.setState(new ReadyState(phone));
        return "Phone turning on to ready state.";
    }
}

// Context class - Phone
class Phone {
    private State state;

    public Phone() {
        this.state = new OffState(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getCurrentState() {
        return this.state.getClass().getSimpleName();
    }

    // Delegate methods to current state
    public String onHome() {
        return state.onHome();
    }

    public String onOffOn() {
        return state.onOffOn();
    }

    public String lock() {
        return state.lock();
    }

    public String home() {
        return state.home();
    }

    public String unlock() {
        return state.unlock();
    }

    public String turnOn() {
        return state.turnOn();
    }
}

// State Interface (alternative approach mentioned in your notes)
interface IState {
    void doSomething();
    void doMoreStuff();
}

// Concrete State implementing interface
class ConcreteState implements IState {
    private Phone context;

    public ConcreteState(Phone context) {
        this.context = context;
    }

    public void setContext(Phone context) {
        this.context = context;
    }

    @Override
    public void doSomething() {
        System.out.println("ConcreteState doing something...");
        // Can change context state here if needed
    }

    @Override
    public void doMoreStuff() {
        System.out.println("ConcreteState doing more stuff...");
    }
}