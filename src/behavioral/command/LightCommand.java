package behavioral.command;

import java.util.*;

/**
 * Light class - the receiver that knows how to perform operations
 * Contains the business logic for light operations
 */
class Light {
    private boolean switchedOn;
    private String location;

    public Light(String location) {
        this.location = location;
        this.switchedOn = false;
    }

    public void switchOn() {
        switchedOn = true;
        System.out.println("[" + location + " Light] Switched ON");
    }

    public void switchOff() {
        switchedOn = false;
        System.out.println("[" + location + " Light] Switched OFF");
    }

    public boolean isSwitchedOn() {
        return switchedOn;
    }

    public String getLocation() {
        return location;
    }

    public void getStatus() {
        System.out.println("[" + location + " Light] Status: " + (switchedOn ? "ON" : "OFF"));
    }
}

/**
 * Command interface - defines execute method
 * All commands must implement this interface
 */
interface ICommand {
    void execute();
    void undo(); // For undoable commands
    String getDescription();
}

// ============================================
// CONCRETE COMMANDS
// ============================================

/**
 * Concrete command for switching light on
 */
class SwitchLightOnCommand implements ICommand {
    private Light light;

    public SwitchLightOnCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("[SwitchOnCommand] Executing switch on command");
        light.switchOn();
    }

    @Override
    public void undo() {
        System.out.println("[SwitchOnCommand] Undoing switch on command");
        light.switchOff();
    }

    @Override
    public String getDescription() {
        return "Switch ON " + light.getLocation() + " light";
    }
}

/**
 * Concrete command for switching light off
 */
class SwitchLightOffCommand implements ICommand {
    private Light light;

    public SwitchLightOffCommand(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        System.out.println("[SwitchOffCommand] Executing switch off command");
        light.switchOff();
    }

    @Override
    public void undo() {
        System.out.println("[SwitchOffCommand] Undoing switch off command");
        light.switchOn();
    }

    @Override
    public String getDescription() {
        return "Switch OFF " + light.getLocation() + " light";
    }
}

/**
 * Macro command that executes multiple commands
 */
class MacroCommand implements ICommand {
    private List<ICommand> commands;
    private String macroName;

    public MacroCommand(String macroName) {
        this.macroName = macroName;
        this.commands = new ArrayList<>();
    }

    public void addCommand(ICommand command) {
        commands.add(command);
    }

    @Override
    public void execute() {
        System.out.println("[MacroCommand] Executing macro: " + macroName);
        for (ICommand command : commands) {
            command.execute();
        }
        System.out.println("[MacroCommand] Macro '" + macroName + "' completed");
    }

    @Override
    public void undo() {
        System.out.println("[MacroCommand] Undoing macro: " + macroName);
        // Undo in reverse order
        for (int i = commands.size() - 1; i >= 0; i--) {
            commands.get(i).undo();
        }
        System.out.println("[MacroCommand] Macro '" + macroName + "' undone");
    }

    @Override
    public String getDescription() {
        return "Macro: " + macroName + " (" + commands.size() + " commands)";
    }
}

/**
 * Null Object Command - does nothing
 */
class NoCommand implements ICommand {
    @Override
    public void execute() {
        System.out.println("[NoCommand] No operation performed");
    }

    @Override
    public void undo() {
        System.out.println("[NoCommand] No operation to undo");
    }

    @Override
    public String getDescription() {
        return "No Command";
    }
}

/**
 * Room class - the invoker that triggers commands
 * Contains slots for commands and can execute them
 */
class Room {
    private String roomName;
    private ICommand[] commandSlots;
    private Stack<ICommand> commandHistory;
    private static final int SLOT_COUNT = 5;

    public Room(String roomName) {
        this.roomName = roomName;
        this.commandSlots = new ICommand[SLOT_COUNT];
        this.commandHistory = new Stack<>();

        // Initialize all slots with NoCommand (Null Object pattern)
        for (int i = 0; i < SLOT_COUNT; i++) {
            commandSlots[i] = new NoCommand();
        }
    }

    public void setCommand(int slot, ICommand command) {
        if (slot >= 0 && slot < SLOT_COUNT) {
            commandSlots[slot] = command;
            System.out.println("[" + roomName + "] Command set in slot " + slot + ": " + command.getDescription());
        } else {
            System.out.println("[" + roomName + "] Invalid slot number: " + slot);
        }
    }

    public void executeCommand(int slot) {
        if (slot >= 0 && slot < SLOT_COUNT) {
            System.out.println("[" + roomName + "] Executing command in slot " + slot);
            ICommand command = commandSlots[slot];
            command.execute();
            commandHistory.push(command);
        } else {
            System.out.println("[" + roomName + "] Invalid slot number: " + slot);
        }
    }

    public void undoLastCommand() {
        if (!commandHistory.isEmpty()) {
            ICommand lastCommand = commandHistory.pop();
            System.out.println("[" + roomName + "] Undoing last command: " + lastCommand.getDescription());
            lastCommand.undo();
        } else {
            System.out.println("[" + roomName + "] No commands to undo");
        }
    }

    public void showCommandSlots() {
        System.out.println("[" + roomName + "] Command Slots:");
        for (int i = 0; i < SLOT_COUNT; i++) {
            System.out.println("  Slot " + i + ": " + commandSlots[i].getDescription());
        }
    }

    public void showCommandHistory() {
        System.out.println("[" + roomName + "] Command History (" + commandHistory.size() + " commands):");
        if (commandHistory.isEmpty()) {
            System.out.println("  No commands executed yet");
        } else {
            Stack<ICommand> temp = new Stack<>();
            temp.addAll(commandHistory);
            int index = 1;
            while (!temp.isEmpty()) {
                ICommand cmd = temp.pop();
                System.out.println("  " + index + ". " + cmd.getDescription());
                index++;
            }
        }
    }

    public String getRoomName() {
        return roomName;
    }
}

/**
 * Smart Home Controller - manages multiple rooms and provides high-level operations
 */
class SmartHomeController {
    private Map<String, Room> rooms;
    private Map<String, Light> lights;

    public SmartHomeController() {
        this.rooms = new HashMap<>();
        this.lights = new HashMap<>();
    }

    public void addRoom(String roomName) {
        rooms.put(roomName, new Room(roomName));
        System.out.println("[SmartHome] Room '" + roomName + "' added to system");
    }

    public void addLight(String lightName, String location) {
        lights.put(lightName, new Light(location));
        System.out.println("[SmartHome] Light '" + lightName + "' added at " + location);
    }

    public void setupRoomCommand(String roomName, int slot, String lightName, String action) {
        Room room = rooms.get(roomName);
        Light light = lights.get(lightName);

        if (room != null && light != null) {
            ICommand command;
            if ("ON".equalsIgnoreCase(action)) {
                command = new SwitchLightOnCommand(light);
            } else if ("OFF".equalsIgnoreCase(action)) {
                command = new SwitchLightOffCommand(light);
            } else {
                command = new NoCommand();
            }

            room.setCommand(slot, command);
        } else {
            System.out.println("[SmartHome] Room or Light not found");
        }
    }

    public void executeRoomCommand(String roomName, int slot) {
        Room room = rooms.get(roomName);
        if (room != null) {
            room.executeCommand(slot);
        } else {
            System.out.println("[SmartHome] Room '" + roomName + "' not found");
        }
    }

    public void undoLastCommand(String roomName) {
        Room room = rooms.get(roomName);
        if (room != null) {
            room.undoLastCommand();
        } else {
            System.out.println("[SmartHome] Room '" + roomName + "' not found");
        }
    }

    public void showSystemStatus() {
        System.out.println("\n=== Smart Home System Status ===");

        System.out.println("Lights Status:");
        for (Light light : lights.values()) {
            light.getStatus();
        }

        System.out.println("\nRoom Configurations:");
        for (Room room : rooms.values()) {
            room.showCommandSlots();
        }
    }

    public Room getRoom(String roomName) {
        return rooms.get(roomName);
    }

    public Light getLight(String lightName) {
        return lights.get(lightName);
    }
}