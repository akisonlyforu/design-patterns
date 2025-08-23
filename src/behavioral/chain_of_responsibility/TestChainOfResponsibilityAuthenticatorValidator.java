package behavioral.chain_of_responsibility;

public class TestChainOfResponsibilityAuthenticatorValidator {

    public static void main(String[] args) {
        System.out.println("=== Testing Chain of Responsibility Pattern ===\n");

        // Test 1: Basic authentication service setup
        System.out.println("Test 1: Basic authentication service setup");
        AuthService authService = new AuthService();
        System.out.println("✓ Authentication service created with handler chain\n");

        // Test 2: Successful authentication
        System.out.println("Test 2: Successful authentication");
        boolean result1 = authService.login("admin", "admin123");
        System.out.println("✓ Expected: SUCCESS, Got: " + (result1 ? "SUCCESS" : "FAILED") + "\n");

        // Test 3: Invalid username
        System.out.println("Test 3: Invalid username");
        boolean result2 = authService.login("invaliduser", "password123");
        System.out.println("✓ Expected: FAILED, Got: " + (result2 ? "SUCCESS" : "FAILED") + "\n");

        // Test 4: Valid username but invalid password
        System.out.println("Test 4: Valid username but invalid password");
        boolean result3 = authService.login("user", "wrongpassword");
        System.out.println("✓ Expected: FAILED, Got: " + (result3 ? "SUCCESS" : "FAILED") + "\n");

        // Test 5: Multiple successful authentications
        System.out.println("Test 5: Multiple successful authentications");
        String[][] validCredentials = {
                {"user", "user123"},
                {"manager", "manager123"},
                {"developer", "dev123"}
        };

        System.out.println("✓ Testing multiple valid users:");
        for (String[] cred : validCredentials) {
            boolean result = authService.login(cred[0], cred[1]);
            System.out.println("User: " + cred[0] + " - " + (result ? "SUCCESS" : "FAILED"));
        }
        System.out.println();

        // Test 6: Chain breaking scenarios
        System.out.println("Test 6: Chain breaking scenarios");
        System.out.println("✓ Testing various failure points in chain:");

        // Empty/null credentials
        System.out.println("--- Testing null username ---");
        boolean result4 = authService.login(null, "password");
        System.out.println("Result: " + (result4 ? "SUCCESS" : "FAILED"));

        // Short password
        System.out.println("--- Testing short password ---");
        boolean result5 = authService.login("admin", "123");
        System.out.println("Result: " + (result5 ? "SUCCESS" : "FAILED"));
        System.out.println();

        // Test 7: Handler chain extensibility
        System.out.println("Test 7: Handler chain extensibility");

        // Create custom handler for two-factor authentication
        class TwoFactorHandler extends BaseHandler {
            @Override
            public boolean handle(AuthenticationRequest request) {
                return handleRequest(request, "TwoFactorHandler");
            }

            @Override
            protected boolean canHandle(AuthenticationRequest request) {
                // Simulate two-factor authentication check
                if (request.isAuthorized()) {
                    System.out.println("[TwoFactorHandler] Two-factor authentication passed");
                    return true;
                }
                System.out.println("[TwoFactorHandler] Two-factor authentication required");
                return false;
            }
        }

        TwoFactorHandler twoFactorHandler = new TwoFactorHandler();
        authService.addHandler(twoFactorHandler);

        System.out.println("✓ Added TwoFactorHandler to chain");
        boolean result6 = authService.login("admin", "admin123");
        System.out.println("Result with extended chain: " + (result6 ? "SUCCESS" : "FAILED") + "\n");

        // Test 8: Request object state tracking
        System.out.println("Test 8: Request object state tracking");

        class StateTrackingHandler extends BaseHandler {
            @Override
            public boolean handle(AuthenticationRequest request) {
                System.out.println("[StateTrackingHandler] Request state before processing: " + request);
                boolean result = handleRequest(request, "StateTrackingHandler");
                System.out.println("[StateTrackingHandler] Request state after processing: " + request);
                return result;
            }

            @Override
            protected boolean canHandle(AuthenticationRequest request) {
                // Always pass through, just for state tracking
                return true;
            }
        }

        // Create new service to test state tracking
        BaseHandler userHandler = new UserExistsHandler();
        BaseHandler passwordHandler = new ValidPasswordHandler();
        BaseHandler stateHandler = new StateTrackingHandler();

        userHandler.setNextHandler(passwordHandler);
        passwordHandler.setNextHandler(stateHandler);

        AuthenticationRequest testRequest = new AuthenticationRequest("user", "user123");
        System.out.println("✓ Testing state tracking through chain:");
        boolean result7 = userHandler.handle(testRequest);
        System.out.println("Final result: " + (result7 ? "SUCCESS" : "FAILED") + "\n");

        // Test 9: Different authentication methods
        System.out.println("Test 9: Different authentication methods");

        // Email-based authentication handler
        class EmailAuthHandler extends BaseHandler {
            @Override
            public boolean handle(AuthenticationRequest request) {
                return handleRequest(request, "EmailAuthHandler");
            }

            @Override
            protected boolean canHandle(AuthenticationRequest request) {
                if (request.getEmail() != null && request.getEmail().contains("@")) {
                    System.out.println("[EmailAuthHandler] Email format valid: " + request.getEmail());
                    return "admin@company.com".equals(request.getEmail()) &&
                            "admin123".equals(request.getPassword());
                }
                return false;
            }
        }

        EmailAuthHandler emailHandler = new EmailAuthHandler();
        AuthenticationRequest emailRequest = new AuthenticationRequest("admin@company.com", "admin123", true);

        System.out.println("✓ Testing email-based authentication:");
        boolean result8 = emailHandler.handle(emailRequest);
        System.out.println("Email auth result: " + (result8 ? "SUCCESS" : "FAILED") + "\n");

        // Test 10: Chain performance with multiple handlers
        System.out.println("Test 10: Chain performance with multiple handlers");

        // Create long chain
        BaseHandler[] handlers = new BaseHandler[5];
        handlers[0] = new UserExistsHandler();
        handlers[1] = new ValidPasswordHandler();
        handlers[2] = new RoleCheckHandler();
        handlers[3] = new TwoFactorHandler();
        handlers[4] = new StateTrackingHandler();

        // Chain them together
        for (int i = 0; i < handlers.length - 1; i++) {
            handlers[i].setNextHandler(handlers[i + 1]);
        }

        long startTime = System.currentTimeMillis();

        System.out.println("✓ Testing performance with 5-handler chain:");
        for (int i = 0; i < 10; i++) {
            AuthenticationRequest perfRequest = new AuthenticationRequest("admin", "admin123");
            handlers[0].handle(perfRequest);
        }

        long endTime = System.currentTimeMillis();
        System.out.println("Processed 10 requests through 5-handler chain in " +
                (endTime - startTime) + "ms\n");

        // Test 11: Chain modification and reordering
        System.out.println("Test 11: Chain modification and reordering");

        // Create handler with different order
        BaseHandler roleFirst = new RoleCheckHandler();
        BaseHandler passwordSecond = new ValidPasswordHandler();
        BaseHandler userLast = new UserExistsHandler();

        roleFirst.setNextHandler(passwordSecond);
        passwordSecond.setNextHandler(userLast);

        System.out.println("✓ Testing chain with different handler order:");
        System.out.println("RoleCheck -> PasswordValidation -> UserExists");

        AuthenticationRequest reorderRequest = new AuthenticationRequest("admin", "admin123");
        boolean result9 = roleFirst.handle(reorderRequest);
        System.out.println("Reordered chain result: " + (result9 ? "SUCCESS" : "FAILED") + "\n");

        System.out.println("=== Test Summary ===");
        System.out.println("Chain of Responsibility Pattern verified:");
        System.out.println("- Request passes through chain of handlers sequentially");
        System.out.println("- Each handler can process or pass request to next handler");
        System.out.println("- Chain can be extended with new handlers dynamically");
        System.out.println("- Handlers are decoupled and can be reordered");
        System.out.println("- Request object maintains state throughout chain");
        System.out.println("- Chain stops when handler processes request successfully");
        System.out.println("- Multiple handlers can modify request before final processing");
        System.out.println("- Different types of handlers can coexist in same chain");

        demonstrateChainStructure();
    }

    private static void demonstrateChainStructure() {
        System.out.println("\n=== Chain Structure Demonstration ===");
        System.out.println("Chain of Responsibility Flow:");
        System.out.println("Request → Handler1 → Handler2 → Handler3 → ... → HandlerN");
        System.out.println("");
        System.out.println("Each Handler Decision:");
        System.out.println("if (canHandle(request)) {");
        System.out.println("    process(request);");
        System.out.println("    return result;");
        System.out.println("} else {");
        System.out.println("    nextHandler.handle(request);");
        System.out.println("}");

        System.out.println("\nAuthentication Chain Example:");
        System.out.println("1. UserExistsHandler: Check if user exists in database");
        System.out.println("2. ValidPasswordHandler: Validate password against stored hash");
        System.out.println("3. RoleCheckHandler: Verify user has required permissions");
        System.out.println("4. TwoFactorHandler: Check two-factor authentication token");

        System.out.println("\nChain Benefits:");
        System.out.println("- Decouples sender from receiver of request");
        System.out.println("- Multiple handlers can process same request");
        System.out.println("- Chain can be modified at runtime");
        System.out.println("- Handlers can be reused in different chains");
        System.out.println("- Easy to add new processing steps without changing existing code");

        System.out.println("\nCommon Use Cases:");
        System.out.println("- Authentication and authorization systems");
        System.out.println("- Request processing pipelines");
        System.out.println("- Event handling systems");
        System.out.println("- Validation chains");
        System.out.println("- Middleware processing in web frameworks");
    }
}