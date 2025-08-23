# Adapter Pattern

## What is Adapter Pattern?

Adapter Pattern is a structural design pattern that allows incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

## Purpose

- Allow two incompatible interfaces to work together
- Integrate legacy code with new systems without modifying existing code
- Convert the interface of a class into another interface that clients expect
- Enable classes to work together that couldn't otherwise due to incompatible interfaces
- Provide a wrapper to make old interfaces compatible with new requirements

## Structure

The pattern consists of:
- **Target Interface** - The interface that the client expects to use
- **Adaptee** - The existing class with incompatible interface that needs to be adapted
- **Adapter** - The class that implements the target interface and wraps the adaptee
- **Client** - Uses objects through the target interface

The pattern relies on composition and delegation. The adapter holds a reference to the adaptee and translates calls from the target interface to the adaptee's interface.

## Benefits

- **Legacy Integration** - Use existing classes without modifying their source code
- **Interface Compatibility** - Make incompatible interfaces work together
- **Code Reusability** - Reuse existing functionality in new contexts
- **Separation of Concerns** - Keep interface conversion logic separate from business logic
- **Single Responsibility** - Adapter handles only interface translation

## When to Use

- When you need to use an existing class with incompatible interface
- When you want to integrate legacy code with new applications
- When you need to make third-party libraries work with your code
- When you want to create a reusable class that cooperates with unrelated classes
- When you need to convert data from one format to another

## Types of Adapters

### Object Adapter (Composition)
- Uses composition to wrap the adaptee
- Adapter holds reference to adaptee object
- More flexible as it can adapt multiple adaptees
- Can override adaptee behavior if needed

### Class Adapter (Inheritance)
- Uses inheritance to adapt the interface
- Adapter extends the adaptee class
- Less flexible but more efficient
- Cannot adapt multiple classes simultaneously

## UML Diagram

```
┌─────────────────┐         ┌─────────────────┐
│     Client      │────────▶│ Target Interface│
└─────────────────┘         ├─────────────────┤
                            │ + request()     │
                            └─────────────────┘
                                     △
                                     │
                            ┌────────▽────────┐
                            │    Adapter      │
                            ├─────────────────┤
                            │ - adaptee       │
                            ├─────────────────┤
                            │ + request()     │
                            └─────────────────┘
                                     │
                                     │ delegates to
                                     ▼
                            ┌─────────────────┐
                            │    Adaptee      │
                            ├─────────────────┤
                            │ + specificReq() │
                            └─────────────────┘
```

## Example Scenario

A modern web application needs to integrate with a legacy web service that has an old interface. The new system expects methods like `get()` and `select()`, but the legacy service has methods like `oldGet()` and `oldSelect()`. An adapter wraps the legacy service and translates the new method calls to the old interface, allowing seamless integration without modifying either system.