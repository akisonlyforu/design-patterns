package behavioral.command;

import java.util.*;

public class TestLightCommand {
    public static void main(String[] args) {
        System.out.println("=== Testing Command Pattern ===\n");

        // Test 1: Basic command creation and execution
        System.out.println("Test 1: Basic command creation and execution");
        Light livingRoomLight = new Light("Living Room");
        Light bedroomLight = new Light("Bedroom");

        ICommand turnOnLiving = new SwitchLightOnCommand(livingRoomLight);
        ICommand turnOffLiving = new SwitchLightOffCommand(livingRoomLight);

        System.out.println("✓ Commands created successfully:");
        System.out.println("Command 1: " + turnOnLiving.getDescription());
        System.out.println("Command 2: " + turnOffLiving.getDescription());

        livingRoomLight.getStatus();
        turnOnLiving.execute();
        livingRoomLight.getStatus();
        turnOffLiving.execute();
        livingRoomLight.getStatus();
        System.out.println();

        // Test 2: Room invoker with command slots
        System.out.println("Test 2: Room invoker with command slots");
        Room livingRoom = new Room("Living Room");

        livingRoom.setCommand(0, turnOnLiving);
        livingRoom.setCommand(1, turnOffLiving);
        livingRoom.setCommand(2, new SwitchLightOnCommand(bedroomLight));

        System.out.println("✓ Commands assigned to room slots:");
        livingRoom.showCommandSlots();

        System.out.println("\nExecuting commands from slots:");
        livingRoom.executeCommand(0); // Turn on living room light
        livingRoom.executeCommand(2); // Turn on bedroom light
        livingRoom.executeCommand(1); // Turn off living room light
        System.out.println();

        // Test 3: Undo functionality
        System.out.println("Test 3: Undo functionality");

        System.out.println("✓ Testing undo operations:");
        livingRoom.showCommandHistory();

        System.out.println("Current light states:");
        livingRoomLight.getStatus();
        bedroomLight.getStatus();

        System.out.println("Undoing last 3 commands:");
        livingRoom.undoLastCommand();
        livingRoom.undoLastCommand();
        livingRoom.undoLastCommand();

        System.out.println("Light states after undo:");
        livingRoomLight.getStatus();
        bedroomLight.getStatus();
        System.out.println();

        // Test 4: Macro commands
        System.out.println("Test 4: Macro commands");

        Light kitchenLight = new Light("Kitchen");
        Light bathroomLight = new Light("Bathroom");

        MacroCommand allLightsOn = new MacroCommand("All Lights ON");
        allLightsOn.addCommand(new SwitchLightOnCommand(livingRoomLight));
        allLightsOn.addCommand(new SwitchLightOnCommand(bedroomLight));
        allLightsOn.addCommand(new SwitchLightOnCommand(kitchenLight));
        allLightsOn.addCommand(new SwitchLightOnCommand(bathroomLight));

        MacroCommand allLightsOff = new MacroCommand("All Lights OFF");
        allLightsOff.addCommand(new SwitchLightOffCommand(livingRoomLight));
        allLightsOff.addCommand(new SwitchLightOffCommand(bedroomLight));
        allLightsOff.addCommand(new SwitchLightOffCommand(kitchenLight));
        allLightsOff.addCommand(new SwitchLightOffCommand(bathroomLight));

        System.out.println("✓ Macro commands created:");
        System.out.println("Macro 1: " + allLightsOn.getDescription());
        System.out.println("Macro 2: " + allLightsOff.getDescription());

        System.out.println("\nExecuting 'All Lights ON' macro:");
        allLightsOn.execute();

        System.out.println("\nExecuting 'All Lights OFF' macro:");
        allLightsOff.execute();

        System.out.println("\nUndoing 'All Lights OFF' macro:");
        allLightsOff.undo();
        System.out.println();

        // Test 5: Smart Home Controller
        System.out.println("Test 5: Smart Home Controller");

        SmartHomeController homeController = new SmartHomeController();

        // Setup home
        homeController.addRoom("Living Room");
        homeController.addRoom("Bedroom");
        homeController.addLight("main_living", "Living Room");
        homeController.addLight("main_bedroom", "Bedroom");
        homeController.addLight("desk_light", "Office");

        // Configure room commands
        homeController.setupRoomCommand("Living Room", 0, "main_living", "ON");
        homeController.setupRoomCommand("Living Room", 1, "main_living", "OFF");
        homeController.setupRoomCommand("Bedroom", 0, "main_bedroom", "ON");
        homeController.setupRoomCommand("Bedroom", 1, "main_bedroom", "OFF");

        System.out.println("✓ Smart home system configured");
        homeController.showSystemStatus();

        System.out.println("\nTesting room commands:");
        homeController.executeRoomCommand("Living Room", 0);
        homeController.executeRoomCommand("Bedroom", 0);
        homeController.showSystemStatus();
        System.out.println();

        // Test 6: Command queuing and batch execution
        System.out.println("Test 6: Command queuing and batch execution");

        Queue<ICommand> commandQueue = new LinkedList<>();
        commandQueue.offer(new SwitchLightOnCommand(kitchenLight));
        commandQueue.offer(new SwitchLightOffCommand(bathroomLight));
        commandQueue.offer(new SwitchLightOnCommand(bedroomLight));

        System.out.println("✓ Commands queued for batch execution:");
        System.out.println("Queue size: " + commandQueue.size());

        while (!commandQueue.isEmpty()) {
            ICommand command = commandQueue.poll();
            System.out.println("Executing queued command: " + command.getDescription());
            command.execute();
        }
        System.out.println();

        // Test 7: Parameterized commands
        System.out.println("Test 7: Parameterized commands");

        // Command with parameters
        class DimLightCommand implements ICommand {
            private Light light;
            private int brightness;
            private int previousBrightness;

            public DimLightCommand(Light light, int brightness) {
                this.light = light;
                this.brightness = brightness;
                this.previousBrightness = 100; // Default full brightness
            }

            @Override
            public void execute() {
                System.out.println("[DimCommand] Setting " + light.getLocation() +
                        " light brightness to " + brightness + "%");
                if (brightness > 0 && !light.isSwitchedOn()) {
                    light.switchOn();
                }
                if (brightness == 0) {
                    light.switchOff();
                }
            }

            @Override
            public void undo() {
                System.out.println("[DimCommand] Restoring " + light.getLocation() +
                        " light brightness to " + previousBrightness + "%");
                if (previousBrightness > 0 && !light.isSwitchedOn()) {
                    light.switchOn();
                }
            }

            @Override
            public String getDescription() {
                return "Dim " + light.getLocation() + " light to " + brightness + "%";
            }
        }

        DimLightCommand dimCommand = new DimLightCommand(livingRoomLight, 30);

        System.out.println("✓ Parameterized command created: " + dimCommand.getDescription());
        dimCommand.execute();
        dimCommand.undo();
        System.out.println();

        // Test 8: Command serialization simulation
        System.out.println("Test 8: Command serialization simulation");

        class SerializableCommand implements ICommand {
            private String commandData;
            private Light light;

            public SerializableCommand(String commandData, Light light) {
                this.commandData = commandData;
                this.light = light;
            }

            @Override
            public void execute() {
                System.out.println("[SerializableCommand] Executing serialized command: " + commandData);
                if (commandData.contains("ON")) {
                    light.switchOn();
                } else if (commandData.contains("OFF")) {
                    light.switchOff();
                }
            }

            @Override
            public void undo() {
                System.out.println("[SerializableCommand] Undoing serialized command");
                if (commandData.contains("ON")) {
                    light.switchOff();
                } else if (commandData.contains("OFF")) {
                    light.switchOn();
                }
            }

            @Override
            public String getDescription() {
                return "Serialized: " + commandData;
            }

            public String serialize() {
                return commandData + "|" + light.getLocation();
            }
        }

        SerializableCommand serCmd = new SerializableCommand("SWITCH_ON_KITCHEN", kitchenLight);

        System.out.println("✓ Command serialization:");
        System.out.println("Serialized data: " + serCmd.serialize());
        serCmd.execute();
        System.out.println();

        // Test 9: Remote control simulation
        System.out.println("Test 9: Remote control simulation");

        class RemoteControl {
            private ICommand[] onCommands;
            private ICommand[] offCommands;
            private ICommand lastCommand;

            public RemoteControl(int slots) {
                onCommands = new ICommand[slots];
                offCommands = new ICommand[slots];

                for (int i = 0; i < slots; i++) {
                    onCommands[i] = new NoCommand();
                    offCommands[i] = new NoCommand();
                }
                lastCommand = new NoCommand();
            }

            public void setCommand(int slot, ICommand onCommand, ICommand offCommand) {
                onCommands[slot] = onCommand;
                offCommands[slot] = offCommand;
            }

            public void pressOnButton(int slot) {
                onCommands[slot].execute();
                lastCommand = onCommands[slot];
            }

            public void pressOffButton(int slot) {
                offCommands[slot].execute();
                lastCommand = offCommands[slot];
            }

            public void pressUndoButton() {
                lastCommand.undo();
            }
        }

        RemoteControl remote = new RemoteControl(3);
        remote.setCommand(0,
                new SwitchLightOnCommand(livingRoomLight),
                new SwitchLightOffCommand(livingRoomLight));
        remote.setCommand(1,
                new SwitchLightOnCommand(bedroomLight),
                new SwitchLightOffCommand(bedroomLight));

        System.out.println("✓ Remote control configured with 2 light controls");
        System.out.println("Testing remote control operations:");

        remote.pressOnButton(0);   // Living room on
        remote.pressOnButton(1);   // Bedroom on
        remote.pressOffButton(0);  // Living room off
        remote.pressUndoButton();  // Undo last (living room should turn on)

        System.out.println("Final light states:");
        livingRoomLight.getStatus();
        bedroomLight.getStatus();
        System.out.println();

        // Test 10: Command pattern with different receivers
        System.out.println("Test 10: Command pattern with different receivers");

        // Different receiver type - Fan
        class Fan {
            private boolean running;
            private String location;

            public Fan(String location) {
                this.location = location;
                this.running = false;
            }

            public void turnOn() {
                running = true;
                System.out.println("[" + location + " Fan] Started");
            }

            public void turnOff() {
                running = false;
                System.out.println("[" + location + " Fan] Stopped");
            }

            public boolean isRunning() { return running; }
            public String getLocation() { return location; }
        }

        class FanOnCommand implements ICommand {
            private Fan fan;

            public FanOnCommand(Fan fan) { this.fan = fan; }

            @Override
            public void execute() { fan.turnOn(); }

            @Override
            public void undo() { fan.turnOff(); }

            @Override
            public String getDescription() { return "Turn ON " + fan.getLocation() + " fan"; }
        }

        Fan ceilingFan = new Fan("Ceiling");
        ICommand fanCommand = new FanOnCommand(ceilingFan);

        Room testRoom = new Room("Test Room");
        testRoom.setCommand(0, fanCommand);
        testRoom.setCommand(1, new SwitchLightOnCommand(kitchenLight));

        System.out.println("✓ Mixed device commands in same room:");
        testRoom.showCommandSlots();
        testRoom.executeCommand(0); // Fan
        testRoom.executeCommand(1); // Light
        System.out.println();

        System.out.println("=== Test Summary ===");
        System.out.println("Command Pattern verified:");
        System.out.println("- Commands encapsulate requests as objects with all necessary information");
        System.out.println("- Invoker (Room) is decoupled from Receiver (Light)");
        System.out.println("- Commands can be stored, queued, and executed at different times");
        System.out.println("- Undo functionality works correctly for all command types");
        System.out.println("- Macro commands can combine multiple operations");
        System.out.println("- Command slots allow flexible command assignment");
        System.out.println("- Different receiver types can work with same command infrastructure");
        System.out.println("- Commands can be parameterized for flexible behavior");
        System.out.println("- Command history enables undo/redo operations");

        demonstrateCommandBenefits();
    }

    private static void demonstrateCommandBenefits() {
        System.out.println("\n=== Command Pattern Benefits Demonstration ===");

        System.out.println("1. Decoupling:");
        System.out.println("   - Invoker (Room) doesn't know about Receiver (Light) details");
        System.out.println("   - Can change Light implementation without affecting Room");
        System.out.println("   - Commands act as abstraction layer between invoker and receiver");

        System.out.println("\n2. Queuing and Scheduling:");
        System.out.println("   - Commands can be stored in queues for later execution");
        System.out.println("   - Supports batch processing of multiple commands");
        System.out.println("   - Enables scheduling commands for future execution");

        System.out.println("\n3. Undo/Redo Operations:");
        System.out.println("   - Each command knows how to undo its operation");
        System.out.println("   - Command history enables multiple undo levels");
        System.out.println("   - Supports complex undo scenarios with macro commands");

        System.out.println("\n4. Logging and Auditing:");
        System.out.println("   - Commands can log their execution for audit trails");
        System.out.println("   - Command descriptions provide human-readable operation logs");
        System.out.println("   - Command serialization enables persistent operation logs");

        System.out.println("\n5. Macro Operations:");
        System.out.println("   - Multiple commands can be combined into macro commands");
        System.out.println("   - Macros support undo operations on entire command groups");
        System.out.println("   - Enables complex operations built from simple commands");

        System.out.println("\nReal-World Applications:");
        System.out.println("- GUI Button Actions (menu items, toolbar buttons)");
        System.out.println("- Undo/Redo in Text Editors");
        System.out.println("- Remote Controls and IoT Device Control");
        System.out.println("- Database Transaction Processing");
        System.out.println("- Job Queues and Task Scheduling");
        System.out.println("- Wizard and Multi-Step Forms");
        System.out.println("- Game Action Systems (player commands, AI actions)");

        System.out.println("\nPattern Comparison:");
        System.out.println("Without Command Pattern:");
        System.out.println("- Direct method calls: room.turnOnLight(), room.turnOffLight()");
        System.out.println("- Tight coupling between invoker and receiver");
        System.out.println("- No undo capability");
        System.out.println("- Hard to implement queuing or macro operations");

        System.out.println("\nWith Command Pattern:");
        System.out.println("- Encapsulated requests: ICommand objects");
        System.out.println("- Loose coupling through command interface");
        System.out.println("- Built-in undo support");
        System.out.println("- Easy queuing, logging, and macro operations");
    }
}