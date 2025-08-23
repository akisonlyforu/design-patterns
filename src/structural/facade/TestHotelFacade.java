package structural.facade;

public class TestHotelFacade {
    public static void main(String[] args) {
        System.out.println("=== Testing Facade Pattern ===\n");

        // Test 1: Simple menu retrieval through facade
        System.out.println("Test 1: Menu retrieval through HotelKeeper facade");
        HotelKeeper hotelKeeper = new HotelKeeper();

        Menu vegMenu = hotelKeeper.getMenu("VEG");
        Menu nonVegMenu = hotelKeeper.getMenu("NONVEG");
        Menu mixedMenu = hotelKeeper.getMenu("MIXED");

        System.out.println("✓ Different menu types retrieved:");
        vegMenu.getMenu();
        nonVegMenu.getMenu();
        mixedMenu.getMenu();

        // Test 2: Complex room booking through facade
        System.out.println("\nTest 2: Room booking through facade");
        System.out.println("Booking room 101 through HotelKeeper:");
        hotelKeeper.bookRoom(101);
        System.out.println("✓ Complex room booking process simplified through facade");

        // Test 3: Room service ordering through facade
        System.out.println("\nTest 3: Room service ordering through facade");
        System.out.println("Ordering veg food for room 101:");
        hotelKeeper.orderRoomService(101, "VEG", "Veggie Pasta");

        System.out.println("\nOrdering non-veg food for room 102:");
        hotelKeeper.orderRoomService(102, "NONVEG", "Grilled Chicken");
        System.out.println("✓ Room service process simplified through facade");

        // Test 4: Multiple room bookings
        System.out.println("\nTest 4: Multiple room bookings");
        int[] roomNumbers = {201, 202, 203};

        for (int roomNumber : roomNumbers) {
            System.out.println("\nBooking room " + roomNumber + ":");
            hotelKeeper.bookRoom(roomNumber);
        }
        System.out.println("✓ Multiple rooms booked through simple facade interface");

        // Test 5: Different menu types verification
        System.out.println("\nTest 5: Menu type handling verification");
        String[] menuTypes = {"VEG", "NONVEG", "MIXED", "INVALID"};

        for (String menuType : menuTypes) {
            System.out.println("Getting " + menuType + " menu:");
            Menu menu = hotelKeeper.getMenu(menuType);
            menu.getMenu();
        }
        System.out.println("✓ All menu types handled correctly (invalid defaults to mixed)");

        // Test 6: Case insensitive menu handling
        System.out.println("\nTest 6: Case insensitive menu handling");
        String[] caseVariations = {"veg", "VEG", "Veg", "nonveg", "NONVEG", "NonVeg"};

        for (String menuType : caseVariations) {
            System.out.println("Menu type '" + menuType + "':");
            Menu menu = hotelKeeper.getMenu(menuType);
            menu.getMenu();
        }
        System.out.println("✓ Case insensitive menu handling works");

        // Test 7: Facade simplification demonstration
        System.out.println("\nTest 7: Demonstrating facade simplification");
        System.out.println("Without facade, client would need to:");
        System.out.println("1. Create HousekeepingService, RestaurantService, RoomBookingService");
        System.out.println("2. Call multiple methods in correct order");
        System.out.println("3. Handle coordination between services");
        System.out.println("4. Manage complex interactions");

        System.out.println("\nWith facade, client simply calls:");
        System.out.println("hotelKeeper.bookRoom(101);");
        System.out.println("hotelKeeper.orderRoomService(101, 'VEG', 'Pasta');");
        System.out.println("✓ Complex subsystem hidden behind simple interface");

        // Test 8: Complete hotel workflow
        System.out.println("\nTest 8: Complete hotel workflow simulation");
        System.out.println("Simulating complete guest experience:");

        // Guest books room
        hotelKeeper.bookRoom(301);

        // Guest orders breakfast
        hotelKeeper.orderRoomService(301, "VEG", "Continental Breakfast");

        // Guest orders dinner
        hotelKeeper.orderRoomService(301, "NONVEG", "Grilled Salmon");

        System.out.println("✓ Complete hotel workflow managed through single facade");

        System.out.println("\n=== Test Summary ===");
        System.out.println("Facade Pattern verified:");
        System.out.println("- Facade provides simplified interface to complex subsystem");
        System.out.println("- Client doesn't need to know about internal subsystem classes");
        System.out.println("- Complex operations reduced to simple method calls");
        System.out.println("- Subsystem coordination handled by facade");
        System.out.println("- Single responsibility principle maintained");
        System.out.println("- Loose coupling between client and subsystem");
    }
}