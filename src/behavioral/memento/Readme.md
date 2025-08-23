# Memento Pattern

## What is Memento Pattern?

Memento Pattern is a behavioral design pattern that lets you save and restore the previous state of an object without revealing details of its implementation. The pattern makes full copies of an object's state, makes original objects data safe and secure, and delegates creation of object's state snapshot to the object itself.

## Purpose

- Capture and externalize object's internal state so it can be restored later
- Preserve encapsulation boundaries while enabling state restoration
- Provide ability to restore object to previous state (undo functionality)
- Save snapshots of object state without violating encapsulation
- Enable implementation of undo/redo operations in applications
- Allow object state to be saved and restored without exposing implementation details
- Support state management and recovery operations

## Structure

The pattern consists of:
- **Originator** - Creates mementos containing snapshot of its current internal state and uses mementos to restore state
- **Memento** - Value object that acts as snapshot of originator's state, immutable and accessible only to originator
- **CareTaker** - Manages mementos but never operates on or examines contents of mementos
- **Client** - Uses caretaker to save and restore originator states

The pattern relies on encapsulation and delegation. Originator delegates state management to memento while maintaining control over state access.

## Benefits

- **Encapsulation Preservation** - Object state saved without breaking encapsulation boundaries
- **State Restoration** - Easy restoration of object to previous states
- **Undo Implementation** - Straightforward implementation of undo/redo functionality
- **Snapshot Management** - Clean separation between state usage and state storage
- **Security** - Memento protects saved state from unauthorized access
- **Simplicity** - Originator doesn't need complex state tracking logic

## When to Use

- When you need to save snapshots of object state for later restoration
- When you want to implement undo/redo functionality
- When direct interface to obtain state would expose implementation details and break encapsulation
- When you need to save object state at specific checkpoints
- When you want to provide rollback capability for operations
- When you need to implement backup and recovery mechanisms

## UML Diagram

```
┌─────────────────────────┐       ┌─────────────────────────┐
│      Originator         │       │       Memento           │
├─────────────────────────┤       ├─────────────────────────┤
│ - state                 │       │ - state                 │
├─────────────────────────┤       ├─────────────────────────┤
│ + createMemento()       │◄──────┤ + getState()            │
│ + restore(Memento)      │       │ + setState()            │
│ + setState()            │       └─────────────────────────┘
│ + getState()            │
└─────────────────────────┘
            │
            │ uses
            ▼
┌─────────────────────────┐
│      CareTaker          │
├─────────────────────────┤
│ - mementos: List        │
├─────────────────────────┤
│ + addMemento()          │
│ + getMemento()          │
│ + showHistory()         │
└─────────────────────────┘
```

## Example Scenario

A state management system where objects need to save their current state at specific points and restore to previous states when needed. The memento pattern enables this functionality while maintaining object encapsulation and providing secure state storage mechanisms.

## Real-World Applications

- **Text Editors** - Undo/redo functionality in word processors, code editors
- **Graphics Software** - History states in image editing applications
- **Database Systems** - Transaction rollback and savepoint mechanisms
- **Game Development** - Save/load game states, checkpoint systems
- **Configuration Management** - Backup and restore system settings
- **Version Control Systems** - Commit snapshots and branch management
- **IDE Development** - Project state management and workspace restoration
- **Financial Systems** - Transaction reversal and audit trail maintenance

## Pattern Variations

### Basic Memento
- Simple state snapshot with restore capability
- Single memento per save operation

### Versioned Memento
- Mementos include version information
- Supports selective restoration to specific versions

### Compressed Memento
- Mementos use compression to reduce memory usage
- Useful for large state objects

### Incremental Memento
- Stores only changes since last memento
- More memory efficient for frequently changing objects

### Lazy Memento
- Memento creation deferred until actually needed
- Reduces memory usage for infrequently accessed states

## Implementation Guidelines

### Do's
- Make mementos immutable to prevent state corruption
- Keep memento interface minimal and focused
- Implement proper access control (package-private methods)
- Consider memory usage implications for large state objects
- Provide clear separation between originator and caretaker responsibilities

### Don'ts
- Don't expose memento internal structure to external classes
- Don't make mementos mutable - they should be snapshots
- Don't forget to implement proper cleanup in caretaker
- Don't create mementos too frequently if state is large
- Don't violate encapsulation by allowing direct state access

## Performance Considerations

### Memory Usage
- Mementos can consume significant memory for large objects
- Consider implementing memory limits in caretaker
- Use compression or incremental storage strategies when appropriate

### Creation Cost
- Creating mementos involves copying object state
- For large objects, consider lazy or selective state copying
- Implement efficient serialization for complex state

### Storage Management
- Implement cleanup strategies to prevent memory leaks
- Consider using weak references for automatic cleanup
- Monitor memory usage in long-running applications

## Security Considerations

### State Protection
- Memento should protect saved state from unauthorized modification
- Use immutable objects and defensive copying
- Implement proper access controls

### Sensitive Data
- Consider encryption for mementos containing sensitive information
- Implement secure cleanup for confidential state data
- Use secure serialization mechanisms when needed

## Testing Strategy

The tests verify:
- **State capture accuracy** - Mementos correctly preserve object state
- **Restoration correctness** - Objects restored to exact previous state
- **Encapsulation preservation** - State access properly controlled
- **Caretaker functionality** - History management works correctly
- **Undo/redo operations** - Multi-level undo/redo works as expected
- **Memory management** - History limits and cleanup work properly

## Integration Patterns

### With Command Pattern
- Commands create mementos before execution for undo support
- Combination provides comprehensive undo/redo system

### With Observer Pattern
- Observers notified when mementos created or restored
- Supports event-driven state management

### With Prototype Pattern
- Prototype can use memento for deep copying
- Memento ensures complete state duplication

## Anti-Patterns to Avoid

### Heavy Memento
- **Problem**: Mementos that store too much unnecessary state
- **Solution**: Store only essential state needed for restoration

### Leaky Memento
- **Problem**: Memento exposes internal object structure
- **Solution**: Use proper encapsulation and access controls

### Careless CareTaker
- **Problem**: CareTaker that doesn't manage memory properly
- **Solution**: Implement proper cleanup and size limits

## Conclusion

The Memento pattern provides robust solution for state management and undo functionality while preserving object encapsulation. It excels in applications requiring state restoration, backup/recovery operations, and undo/redo capabilities. The pattern's clear separation of concerns between state creation, storage, and restoration makes it valuable for building reliable and user-friendly applications with comprehensive state management features.