package behavioral.null_object;

// Abstract class - defines common interface
abstract class AbstractLogger {
    public abstract void log(String message);
}

// Concrete logger - actual logging behavior
class ConsoleLogger extends AbstractLogger {
    public void log(String message) {
        System.out.println("Console Log: " + message);
    }
}

// File logger - logs to file
class FileLogger extends AbstractLogger {
    private String filename;

    public FileLogger(String filename) {
        this.filename = filename;
    }

    public void log(String message) {
        System.out.println("File Log to " + filename + ": " + message);
    }
}

// Null object - does nothing behavior
class NullLogger extends AbstractLogger {
    public void log(String message) {
        // Do nothing - this is the null object behavior
    }
}

// Factory to create appropriate logger
class LoggerFactory {
    public static AbstractLogger getLogger(String type) {
        if (type == null) {
            return new NullLogger();
        }

        switch (type.toUpperCase()) {
            case "CONSOLE":
                return new ConsoleLogger();
            case "FILE":
                return new FileLogger("application.log");
            case "NULL":
                return new NullLogger();
            default:
                return new NullLogger(); // Default to null object
        }
    }
}

// Client class that uses logger
class Application {
    private AbstractLogger logger;

    public Application(AbstractLogger logger) {
        // Use null object if logger is null
        this.logger = (logger != null) ? logger : new NullLogger();
    }

    public void performOperation(String operation) {
        System.out.println("Performing operation: " + operation);

        // No null check needed - null object handles it
        logger.log("Operation completed: " + operation);
    }

    public void setLogger(AbstractLogger logger) {
        this.logger = (logger != null) ? logger : new NullLogger();
    }
}