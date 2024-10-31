package structural.facade;

// Complex subsystem classes - inner workings of hotel system
class HousekeepingService {
    public void cleanRoom(int roomNumber) {
        System.out.println("Housekeeping: Cleaning room " + roomNumber);
    }

    public void prepareRoom(int roomNumber) {
        System.out.println("Housekeeping: Preparing room " + roomNumber + " for guest");
    }
}

class RestaurantService {
    public void orderFood(String menuItem) {
        System.out.println("Restaurant: Preparing " + menuItem);
    }

    public void deliverFood(int roomNumber, String menuItem) {
        System.out.println("Restaurant: Delivering " + menuItem + " to room " + roomNumber);
    }
}

class RoomBookingService {
    public boolean checkAvailability(int roomNumber) {
        System.out.println("Booking: Checking availability for room " + roomNumber);
        return true; // Simplified - assume always available
    }

    public void reserveRoom(int roomNumber) {
        System.out.println("Booking: Room " + roomNumber + " reserved successfully");
    }
}

// Menu classes - demonstrate complex menu hierarchy
interface Menu {
    void getMenu();
}

class VegMenu implements Menu {
    public void getMenu() {
        System.out.println("Veg Menu: Salad, Pasta, Veggie Burger");
    }
}

class NonVegMenu implements Menu {
    public void getMenu() {
        System.out.println("Non-Veg Menu: Chicken, Fish, Beef");
    }
}

class MixedMenu implements Menu {
    public void getMenu() {
        System.out.println("Mixed Menu: Veg and Non-Veg options available");
    }
}

// Hotel Keeper - Facade class
class HotelKeeper {
    private HousekeepingService housekeeping;
    private RestaurantService restaurant;
    private RoomBookingService roomBooking;

    public HotelKeeper() {
        this.housekeeping = new HousekeepingService();
        this.restaurant = new RestaurantService();
        this.roomBooking = new RoomBookingService();
    }

    // Facade method - simplifies complex operations
    public Menu getMenu(String menuType) {
        if ("VEG".equalsIgnoreCase(menuType)) {
            return new VegMenu();
        } else if ("NONVEG".equalsIgnoreCase(menuType)) {
            return new NonVegMenu();
        } else if ("MIXED".equalsIgnoreCase(menuType)) {
            return new MixedMenu();
        }
        return new MixedMenu(); // Default
    }

    // Facade method - handles complete room booking process
    public void bookRoom(int roomNumber) {
        System.out.println("HotelKeeper: Starting room booking process for room " + roomNumber);

        // Coordinate multiple subsystems
        if (roomBooking.checkAvailability(roomNumber)) {
            roomBooking.reserveRoom(roomNumber);
            housekeeping.cleanRoom(roomNumber);
            housekeeping.prepareRoom(roomNumber);
            System.out.println("HotelKeeper: Room " + roomNumber + " is ready for guest");
        } else {
            System.out.println("HotelKeeper: Room " + roomNumber + " is not available");
        }
    }

    // Facade method - handles food ordering process
    public void orderRoomService(int roomNumber, String menuType, String foodItem) {
        System.out.println("HotelKeeper: Processing room service order for room " + roomNumber);

        // Get appropriate menu
        Menu menu = getMenu(menuType);
        menu.getMenu();

        // Process order through restaurant
        restaurant.orderFood(foodItem);
        restaurant.deliverFood(roomNumber, foodItem);

        System.out.println("HotelKeeper: Room service completed for room " + roomNumber);
    }
}