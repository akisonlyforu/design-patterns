# Null Object Pattern

## What is Null Object Pattern?

Null Object Pattern is a behavioral design pattern that encapsulates the absence of an object by providing an alternative that offers suitable default "do nothing" behavior. It eliminates the need for null checks by providing an object that represents nothing but behaves correctly.

## Purpose

- Encapsulate absence of an object by providing alternative that offers suitable default behavior
- Eliminate null checks from client code
- Provide safe default behavior when no real object is available
- Treat null objects the same way as real objects that actually provide behavior
- Prevent NullPointerException by design
- Handle cases where a collaborator may not be available

## Structure

The pattern consists of:
- **Abstract Class/Interface** - Defines common interface for real and null objects
- **Real Object** - Provides actual functionality and behavior
- **Null Object** - Provides default "do nothing" behavior, should be singleton and stateless
- **Client** - Uses objects without null checks

## Key Characteristics

**Null Object should be:**
- **Singleton** - Single instance shared across application
- **Stateless** - No state since its state cannot change
- **Immutable** - Cannot be modified after creation
- **Safe** - Calling methods should have no side effects

**Special Case of Strategy Pattern:**
Null behavior is not designed to be mixed into an object that needs some "do nothing" behavior. The null object does not transform to become a real object as state cannot change.

## Benefits

- **Eliminates Null Checks** - No need for `if (object != null)` statements
- **Prevents NPE** - NullPointerException cannot occur
- **Cleaner Code** - Client code is simpler and more readable
- **Polymorphic Usage** - Null objects work seamlessly with real objects
- **Default Behavior** - Provides safe fallback when no real object available
- **Consistent Interface** - All objects follow same contract

## When to Use

- When you need default behavior for missing objects
- When null checks clutter your code
- When you want to eliminate NullPointerException risks
- When absence of an object is a valid state
- When you need polymorphic treatment of null cases
- When default "do nothing" behavior is meaningful

## UML Diagram

```
┌─────────────────────┐
│   <<abstract>>      │
│  AbstractObject     │
├─────────────────────┤
│ + request()         │
└─────────────────────┘
           △
           │
    ┌──────┴────────┐
    │               │
┌───▽──────┐   ┌────▽────────┐
│  RealObj │   │  NullObj    │
├──────────┤   ├─────────────┤
│+request()│   │+request()   │
└──────────┘   │{do nothing} │
               └─────────────┘

┌─────────────────────┐
│      Client         │
├─────────────────────┤
│ - object: Abstract  │
├─────────────────────┤
│ + operation()       │──────uses──────┐
└─────────────────────┘                │
                                       ▼
                            ┌─────────────────────┐
                            │  AbstractObject     │
                            └─────────────────────┘
```

## Example Scenario

A logging system where some components need logging while others don't. Instead of checking `if (logger != null)` before every log call, the system uses a NullLogger that safely ignores log messages. This way, all components can call `logger.log()` without worry, and the actual logging behavior is determined by which logger implementation is provided.

## Implementation Notes

- Null objects should implement the same interface as real objects
- Methods in null objects typically do nothing or return neutral values
- Consider using singleton pattern for null object instances
- Null objects should not throw exceptions
- State changes should not be allowed in null objects