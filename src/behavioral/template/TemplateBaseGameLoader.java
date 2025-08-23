package behavioral.template;

/**
 * Abstract base class defining the skeleton of data loading algorithm
 * This is the Abstract Class in Template Method pattern
 */
abstract class BaseGameLoader {

    public final void load() {
        System.out.println("=== Starting " + getLoaderType() + " Loading Process ===");
        step1(); // always executed
        step2(); // always executed
        step3(); // always executed
        System.out.println("=== " + getLoaderType() + " Loading Process Completed ===\n");
    }

    // Abstract methods that must be implemented by subclasses
    protected abstract void step1();
    protected abstract void step2();
    protected abstract void step3();

    // Hook method - can be overridden but has default implementation
    protected String getLoaderType() {
        return "Generic";
    }

    // Another hook method with default behavior
    protected boolean shouldLogProgress() {
        return true;
    }

    protected void logProgress(String message) {
        if (shouldLogProgress()) {
            System.out.println("[LOG] " + message);
        }
    }
}

class Loader1 extends BaseGameLoader {

    @Override
    protected void step1() {
        logProgress("Loader1 executing step 1");
        System.out.println("Loader1: Initializing configuration files");
    }

    @Override
    protected void step2() {
        logProgress("Loader1 executing step 2");
        System.out.println("Loader1: Loading game assets from database");
    }

    @Override
    protected void step3() {
        logProgress("Loader1 executing step 3");
        System.out.println("Loader1: Setting up network connections");
    }

    @Override
    protected String getLoaderType() {
        return "Database Loader";
    }
}

class Loader2 extends BaseGameLoader {

    @Override
    protected void step1() {
        logProgress("Loader2 executing step 1");
        System.out.println("Loader2: Reading configuration from files");
    }

    @Override
    protected void step2() {
        logProgress("Loader2 executing step 2");
        System.out.println("Loader2: Loading game assets from local storage");
    }

    @Override
    protected void step3() {
        logProgress("Loader2 executing step 3");
        System.out.println("Loader2: Initializing offline mode");
    }

    @Override
    protected String getLoaderType() {
        return "File System Loader";
    }

    @Override
    protected boolean shouldLogProgress() {
        return false; // Loader2 doesn't want verbose logging
    }
}

class CloudLoader extends BaseGameLoader {

    @Override
    protected void step1() {
        logProgress("CloudLoader executing step 1");
        System.out.println("CloudLoader: Authenticating with cloud services");
    }

    @Override
    protected void step2() {
        logProgress("CloudLoader executing step 2");
        System.out.println("CloudLoader: Downloading assets from cloud storage");
    }

    @Override
    protected void step3() {
        logProgress("CloudLoader executing step 3");
        System.out.println("CloudLoader: Syncing user data across devices");
    }

    @Override
    protected String getLoaderType() {
        return "Cloud Loader";
    }
}

class DebugLoader extends BaseGameLoader {

    @Override
    protected void step1() {
        logProgress("DebugLoader executing step 1");
        System.out.println("DebugLoader: Loading debug configuration");
        System.out.println("DebugLoader: Enabling verbose logging");
    }

    @Override
    protected void step2() {
        logProgress("DebugLoader executing step 2");
        System.out.println("DebugLoader: Loading test assets");
        System.out.println("DebugLoader: Initializing debug tools");
    }

    @Override
    protected void step3() {
        logProgress("DebugLoader executing step 3");
        System.out.println("DebugLoader: Setting up performance monitors");
        System.out.println("DebugLoader: Enabling crash reporting");
    }

    @Override
    protected String getLoaderType() {
        return "Debug Development Loader";
    }

    @Override
    protected boolean shouldLogProgress() {
        return true; // Debug loader always wants detailed logging
    }
}