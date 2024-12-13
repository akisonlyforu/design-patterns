# Command Pattern

## What is Command Pattern?

Command Pattern is a behavioral design pattern that turns requests/behaviors into stand-alone objects that contain everything about that request. This transformation lets you encapsulate all relevant information needed to perform an action or trigger an event, allowing you to pass commands as method arguments, delay command execution, queue operations, and support undoable operations.

## Purpose

- Encapsulate request as object containing all information needed to perform action
- Decouple invoker (object that invokes request) from receiver (object that performs request)
- Parameterize objects with different requests, queue operations, and log requests
- Support undoable operations by storing state necessary to reverse operation
- Enable macro commands that combine multiple operations into single command
- Allow commands to be stored, serialized, and executed at different times
- Support queuing, scheduling, and remote execution of operations

## Structure

The pattern consists of:
- **Command Interface** - Declares method for executing commands (execute(), undo())
- **Concrete Command** - Implements command interface and defines binding between receiver and action
- **Receiver** - Contains business logic and knows how to perform operations
- **Invoker** - Asks command to carry out request, stores commands and can execute them
- **Client** - Creates concrete command objects and sets their receivers

The pattern relies on encapsulation and composition. Commands encapsulate method calls as objects, invokers store and execute commands without knowing receiver details.

## Benefits

- **Decoupling** - Invoker is completely decoupled from receiver of request
- **Flexibility** - Commands can be stored, queued, executed at different times
- **Undo Support** - Built-in support for reversible operations
- **Macro Operations** - Multiple commands can be combined into macro commands
- **Logging and Auditing** - Command execution can be logged for audit trails
- **Remote Execution** - Commands can be transmitted and executed remotely
- **Queuing** - Commands can be queued for batch processing or scheduling

## When to Use

- When you want to parameterize objects with operations to perform
- When you need to queue operations, schedule them, or execute them remotely
- When you want to support undo operations
- When you need to log changes so they can be reapplied in case of system crash
- When you want to structure system around high-level operations built on primitive operations
- When you need to decouple classes that invoke operations from classes that perform operations
- When you want to support macro recording and playback

## UML Diagram

```
┌─────────────────────────┐       ┌─────────────────────────┐
│       Client            │       │       Invoker           │
└─────────────────────────┘       ├─────────────────────────┤
            │                     │ - command: Command      │
            │ creates             ├─────────────────────────┤
            ▼                     │ + setCommand()          │
┌─────────────────────────┐       │ + executeCommand()      │
│    <<interface>>        │◄──────┴─────────────────────────┘
│       Command           │
├─────────────────────────┤
│ + execute()             │
│ + undo()                │
└─────────────────────────┘
            △
            │
    ┌───────▽──────────┐
    │ ConcreteCommand  │
    ├──────────────────┤
    │ - receiver       │
    ├──────────────────┤
    │ + execute()      │────┐
    │ + undo()         │    │
    └──────────────────┘    │
                            │ calls
                            ▼
                    ┌─────────────────────────┐
                    │       Receiver          │
                    ├─────────────────────────┤
                    │ + action()              │
                    │ + getState()            │
                    └─────────────────────────┘
```

## Example Scenario

A smart home automation system where different devices need to be controlled through various interfaces (remote controls, mobile apps, voice commands). The command pattern encapsulates each device operation as a command object, allowing the same operations to be triggered from different sources, queued for later execution, and undone if necessary.

## Real-World Applications

- **GUI Applications** - Menu items, toolbar buttons, keyboard shortcuts
- **Text Editors** - Undo/redo functionality, macro recording
- **Remote Controls** - TV remotes, smart home controllers, IoT device management
- **Database Systems** - Transaction processing, rollback operations
- **Job Scheduling** - Task queues, batch processing systems
- **Game Development** - Player actions, AI commands, replay systems
- **Web Applications** - Request processing, form submissions, API operations
- **Automation Systems** - Industrial control, robotic programming

## Pattern Variations

### Simple Command
- Basic command with execute() method
- No undo support or complex state management

### Undoable Command
- Includes undo() method and state preservation
- Supports reversible operations

### Macro Command
- Composite command that contains multiple sub-commands
- Executes all sub-commands in sequence

### Queued Command
- Commands stored in queues for batch processing
- Supports scheduling and delayed execution

### Logged Command
- Commands that log their execution for auditing
- Supports replay and crash recovery

## Implementation Guidelines

### Do's
- Keep commands focused on single operations
- Store all necessary information in command object
- Implement meaningful toString() methods for debugging
- Consider thread safety for commands that modify shared state
- Use Null Object pattern for empty command slots

### Don'ts
- Don't put business logic in invoker - keep it in receiver
- Don't create commands that are too complex or do multiple unrelated things
- Don't forget to handle error conditions in command execution
- Don't ignore memory implications of storing command history
- Don't make commands dependent on external state that might change

## Performance Considerations

### Memory Usage
- Command objects consume memory, especially with undo support
- Consider limiting command history size for long-running applications
- Use flyweight pattern for frequently used commands

### Execution Overhead
- Command pattern adds indirection which has slight performance cost
- For performance-critical code, consider direct method calls
- Batch command execution can improve performance for multiple operations

### Undo Support Complexity
- Undo operations may require storing significant state information
- Consider memento pattern for complex undo scenarios
- Implement efficient state storage strategies

## Common Pitfalls Avoided

- **Command State Dependencies**: Commands should be self-contained
- **Receiver Coupling**: Commands should work with any compatible receiver
- **Undo Complexity**: Keep undo logic simple and reliable
- **Memory Leaks**: Properly manage command history and references
- **Thread Safety**: Handle concurrent command execution appropriately

## Testing Strategy

The tests verify:
- **Basic command execution** - Commands perform expected operations
- **Undo functionality** - Commands can be reversed correctly
- **Macro command behavior** - Multiple commands execute as single unit
- **Invoker independence** - Invokers work with any command type
- **Receiver decoupling** - Commands work with different receiver instances
- **Command queuing** - Commands can be stored and executed later
- **Error handling** - Invalid operations handled gracefully

## Advanced Features

### Command Validation
```java
interface Command {
    boolean canExecute(Context context);
    void execute();
    void undo();
}
```

### Parameterized Commands
```java
class ParameterizedCommand implements Command {
    private Map<String, Object> parameters;
    // Command can be configured with parameters
}
```

### Asynchronous Commands
```java
class AsyncCommand implements Command {
    public CompletableFuture<Void> executeAsync() {
        // Asynchronous command execution
    }
}
```

### Command Composition
```java
class CompositeCommand implements Command {
    private List<Command> commands;
    // Combine multiple commands with complex execution logic
}
```

## Integration Patterns

### With Memento Pattern
- Commands use memento to store receiver state for undo operations
- Enables complex undo scenarios with full state restoration

### With Observer Pattern
- Commands notify observers when executed
- Supports event-driven architectures with command execution

### With Factory Pattern
- Command factories create appropriate commands based on input
- Enables dynamic command creation and configuration

## Thread Safety Considerations

### Stateless Commands
- Commands should be stateless when possible for thread safety
- State should be stored in receiver or passed as parameters

### Concurrent Execution
- Consider synchronization when commands modify shared resources
- Use thread-safe collections for command queues
- Implement proper locking for undo operations

### Command Queues
- Use thread-safe queue implementations for multi-threaded environments
- Consider producer-consumer patterns for command processing
- Implement proper error handling for failed command execution

## Modern Applications

### Microservices
- Commands represent service operations
- Command queues enable asynchronous processing
- Event sourcing uses command-like events

### Event Sourcing
- Commands become events stored in event log
- System state rebuilt by replaying commands
- Natural fit for audit trails and temporal queries

### CQRS (Command Query Responsibility Segregation)
- Commands handle write operations
- Queries handle read operations
- Clear separation of concerns

## Conclusion

The Command pattern provides powerful abstraction for encapsulating operations as objects, enabling flexible request handling, undo support, and complex operation composition. It excels in scenarios requiring operation queuing, logging, undo functionality, and decoupling of request invokers from processors. While it adds slight complexity through additional objects, the benefits of flexibility, maintainability, and feature richness make it valuable for building sophisticated interactive systems.