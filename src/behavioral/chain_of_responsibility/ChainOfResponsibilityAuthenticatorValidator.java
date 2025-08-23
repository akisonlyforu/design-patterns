package behavioral.chain_of_responsibility;

/**
 * Request object that contains authentication information
 * Passed through the chain of handlers
 */
class AuthenticationRequest {
    private String username;
    private String password;
    private String email;
    private boolean isAuthorized;

    public AuthenticationRequest(String username, String password) {
        this.username = username;
        this.password = password;
        this.isAuthorized = false;
    }

    public AuthenticationRequest(String email, String password, boolean isEmail) {
        this.email = email;
        this.password = password;
        this.isAuthorized = false;
    }

    // Getters and setters
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isAuthorized() { return isAuthorized; }
    public void setAuthorized(boolean authorized) { this.isAuthorized = authorized; }

    @Override
    public String toString() {
        return "AuthenticationRequest{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='***'" +
                ", isAuthorized=" + isAuthorized +
                '}';
    }
}

/**
 * Abstract base handler class
 * Defines interface for handling requests and chaining handlers
 */
abstract class BaseHandler {
    protected BaseHandler nextHandler;

    public void setNextHandler(BaseHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract boolean handle(AuthenticationRequest request);

    protected boolean handleRequest(AuthenticationRequest request, String handlerName) {
        System.out.println("[" + handlerName + "] Processing request: " + request);

        if (canHandle(request)) {
            System.out.println("[" + handlerName + "] ✓ Request handled successfully");
            return true;
        } else {
            System.out.println("[" + handlerName + "] ✗ Cannot handle request, passing to next handler");
            if (nextHandler != null) {
                return nextHandler.handle(request);
            } else {
                System.out.println("[" + handlerName + "] ✗ No more handlers in chain - request failed");
                return false;
            }
        }
    }

    protected abstract boolean canHandle(AuthenticationRequest request);
}

/**
 * Handler for username/password authentication
 */
class UserExistsHandler extends BaseHandler {

    @Override
    public boolean handle(AuthenticationRequest request) {
        return handleRequest(request, "UserExistsHandler");
    }

    @Override
    protected boolean canHandle(AuthenticationRequest request) {
        // Simulate checking if user exists in database
        if (request.getUsername() != null) {
            // Simulate valid usernames
            String[] validUsers = {"admin", "user", "manager", "developer"};
            for (String validUser : validUsers) {
                if (validUser.equals(request.getUsername())) {
                    System.out.println("[UserExistsHandler] User '" + request.getUsername() + "' exists in database");
                    return true;
                }
            }
            System.out.println("[UserExistsHandler] User '" + request.getUsername() + "' does not exist");
        }
        return false;
    }
}

/**
 * Handler for password validation
 */
class ValidPasswordHandler extends BaseHandler {

    @Override
    public boolean handle(AuthenticationRequest request) {
        return handleRequest(request, "ValidPasswordHandler");
    }

    @Override
    protected boolean canHandle(AuthenticationRequest request) {
        // Simulate password validation
        if (request.getPassword() != null && request.getPassword().length() >= 6) {
            // Simulate password checking against database
            String expectedPassword = getExpectedPassword(request.getUsername());
            if (expectedPassword != null && expectedPassword.equals(request.getPassword())) {
                System.out.println("[ValidPasswordHandler] Password is valid");
                return true;
            }
            System.out.println("[ValidPasswordHandler] Invalid password");
        } else {
            System.out.println("[ValidPasswordHandler] Password too short or null");
        }
        return false;
    }

    private String getExpectedPassword(String username) {
        // Simulate database lookup
        if ("admin".equals(username)) return "admin123";
        if ("user".equals(username)) return "user123";
        if ("manager".equals(username)) return "manager123";
        if ("developer".equals(username)) return "dev123";
        return null;
    }
}

/**
 * Handler for role-based authorization
 */
class RoleCheckHandler extends BaseHandler {

    @Override
    public boolean handle(AuthenticationRequest request) {
        boolean result = handleRequest(request, "RoleCheckHandler");
        if (result) {
            request.setAuthorized(true);
            System.out.println("[RoleCheckHandler] User authorized successfully");
        }
        return result;
    }

    @Override
    protected boolean canHandle(AuthenticationRequest request) {
        // Simulate role checking
        if (request.getUsername() != null) {
            String userRole = getUserRole(request.getUsername());
            if (userRole != null) {
                System.out.println("[RoleCheckHandler] User '" + request.getUsername() +
                        "' has role: " + userRole);
                return true;
            }
            System.out.println("[RoleCheckHandler] No role found for user");
        }
        return false;
    }

    private String getUserRole(String username) {
        // Simulate role lookup
        if ("admin".equals(username)) return "ADMINISTRATOR";
        if ("user".equals(username)) return "USER";
        if ("manager".equals(username)) return "MANAGER";
        if ("developer".equals(username)) return "DEVELOPER";
        return null;
    }
}

/**
 * Main authentication service that sets up the chain
 */
class AuthService {
    private BaseHandler handlerChain;

    public AuthService() {
        setupHandlerChain();
    }

    private void setupHandlerChain() {
        // Create handlers
        BaseHandler userExistsHandler = new UserExistsHandler();
        BaseHandler validPasswordHandler = new ValidPasswordHandler();
        BaseHandler roleCheckHandler = new RoleCheckHandler();

        // Set up the chain
        userExistsHandler.setNextHandler(validPasswordHandler);
        validPasswordHandler.setNextHandler(roleCheckHandler);

        // Set the first handler as chain entry point
        this.handlerChain = userExistsHandler;

        System.out.println("[AuthService] Handler chain initialized:");
        System.out.println("  UserExistsHandler -> ValidPasswordHandler -> RoleCheckHandler");
    }

    public boolean login(String username, String password) {
        System.out.println("\n=== Authentication Process Started ===");
        System.out.println("Attempting login for user: " + username);

        AuthenticationRequest request = new AuthenticationRequest(username, password);
        boolean result = handlerChain.handle(request);

        System.out.println("=== Authentication Process Completed ===");
        System.out.println("Result: " + (result ? "SUCCESS" : "FAILED"));
        System.out.println("Request final state: " + request);

        return result;
    }

    public void addHandler(BaseHandler newHandler) {
        // Add handler to end of chain
        BaseHandler current = handlerChain;
        while (current.nextHandler != null) {
            current = current.nextHandler;
        }
        current.setNextHandler(newHandler);
        System.out.println("[AuthService] New handler added to end of chain");
    }
}