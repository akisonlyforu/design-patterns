package structural.adapter;

interface WebInterface {
    void get();
    void select();
}

interface OldWebInterface {
    void find();
    void click();
}

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

public class WebInterfaceAdapter implements WebInterface {

    OldWebInterface oldWebInterface;

    WebInterfaceAdapter(OldWebInterface oldWebInterface) {
        this.oldWebInterface = oldWebInterface;
    }

    @Override
    public void get() {
        oldWebInterface.find();
    }

    @Override
    public void select() {
        oldWebInterface.click();
    }
}
