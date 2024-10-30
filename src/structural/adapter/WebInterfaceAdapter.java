package structural.adapter;

// Target interface - what client expects to use
interface WebInterface {
    void get();
    void select();
}

// Adaptee interface - existing incompatible interface
interface OldWebInterface {
    void find();
    void click();
}

// Adaptee implementation - existing legacy code
class OldWebInterfaceImpl implements OldWebInterface {
    @Override
    public void find() {
        System.out.println("FIND OldWebInterface Impl");
    }

    @Override
    public void click() {
        System.out.println("CLICK OldWebInterface Impl");
    }
}

// Target implementation - new interface implementation
class WebInterfaceImpl implements WebInterface {
    @Override
    public void get() {
        System.out.println("GET WebInterface Impl");
    }

    @Override
    public void select() {
        System.out.println("SELECT WebInterface Impl");
    }
}

// Adapter class - bridges target and adaptee interfaces
public class WebInterfaceAdapter implements WebInterface {
    // Composition - holds reference to adaptee
    OldWebInterface oldWebInterface;

    WebInterfaceAdapter(OldWebInterface oldWebInterface) {
        this.oldWebInterface = oldWebInterface;
    }

    // Delegates target methods to adaptee methods
    @Override
    public void get() {
        oldWebInterface.find();  // Translates get() to find()
    }

    @Override
    public void select() {
        oldWebInterface.click(); // Translates select() to click()
    }
}