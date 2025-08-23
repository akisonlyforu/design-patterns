# Bridge Pattern

## What is Bridge Pattern?

Bridge Pattern is a structural design pattern that separates an abstraction from its implementation so that both can vary independently. It applies Single Responsibility and Open-Closed Principles by switching from inheritance to composition, allowing both hierarchies to be developed independently without affecting each other.

## Purpose

- Decouple abstraction from implementation so both can vary independently
- Avoid permanent binding between abstraction and implementation
- Share implementation among multiple objects while hiding implementation details from client
- Extend abstractions and implementations independently through subclassing
- Switch implementations at runtime
- Prevent class explosion when you have multiple dimensions of variation
- Allow abstraction and implementation to be selected or configured at runtime

## Structure

The pattern consists of:
- **Abstraction** - Defines abstraction interface and maintains reference to implementor object
- **Refined Abstraction** - Extends abstraction interface with additional features
- **Implementor** - Defines interface for implementation classes, doesn't need to correspond exactly to abstraction interface
- **Concrete Implementor** - Contains concrete implementation of implementor interface

The pattern relies on composition rather than inheritance. The abstraction contains a reference to the implementor and delegates the actual work to the implementor object.

## Benefits

- **Independent Development** - Abstraction and implementation hierarchies can be developed separately
- **Runtime Flexibility** - Implementation can be selected or switched at runtime
- **No Class Explosion** - Avoids n×m class combinations, reduces to n+m classes
- **Implementation Hiding** - Client code is completely decoupled from implementation details
- **Easy Extension** - New abstractions and implementations can be added without affecting existing code
- **Interface Stability** - Changes in implementation don't affect client code

## When to Use

- When you want to avoid permanent binding between abstraction and implementation
- When both abstractions and implementations should be extensible through subclassing
- When changes in implementation should have no impact on clients
- When you want to share implementation among multiple objects
- When you have proliferation of classes due to multiple inheritance hierarchies
- When you want to switch implementations at runtime

## UML Diagram

```
┌─────────────────────┐                    ┌─────────────────────┐
│    Abstraction      │                    │   <<interface>>     │
│     (Vehicle)       │                    │   Implementor       │
├─────────────────────┤                    │   (Workshop)        │
│ - implementor       │◆───────────────────├─────────────────────┤
├─────────────────────┤                    │ + work()            │
│ + operation()       │                    │ + getWorkshopType() │
│ + manufacture()     │                    └─────────────────────┘
└─────────────────────┘                              △
            △                                        │
            │                              ┌─────────┴─────────┐
    ┌───────┴───────┐                      │                   │
    │               │                ┌─────▽──────┐    ┌───────▽────┐
┌───▽────┐    ┌─────▽───┐            │  Produce   │    │  Assemble  │
│  Car   │    │  Bike   │            ├────────────┤    ├────────────┤
├────────┤    ├─────────┤            │ +work()    │    │ +work()    │
│        │    │         │            │ +getType() │    │ +getType() │
└────────┘    └─────────┘            └────────────┘    └────────────┘
```

## Example Scenario

A vehicle manufacturing system where different types of vehicles (Car, Bike, Truck) need to go through different workshop operations (Produce, Assemble, Paint, Inspect). Without Bridge pattern, you'd need CarProduce, CarAssemble, BikeProduce, BikeAssemble, etc., leading to class explosion. The Bridge pattern allows any vehicle type to use any workshop combination while keeping the hierarchies separate and extensible.

## Key Differences from Other Patterns

### Bridge vs Adapter
- **Bridge**: Designed upfront to let abstraction and implementation vary independently
- **Adapter**: Applied retroactively to make unrelated classes work together

## Real-World Applications

- **GUI Frameworks** - UI abstractions with platform-specific implementations (Windows, macOS, Linux)
- **Database Drivers** - Database abstraction with different database implementations (MySQL, PostgreSQL, Oracle)
- **Graphics Libraries** - Drawing abstractions with different rendering backends (OpenGL, DirectX, Software)
- **Message Queue Systems** - Messaging abstraction with different transport implementations (TCP, HTTP, WebSocket)
- **Payment Systems** - Payment abstraction with different gateway implementations (PayPal, Stripe, Square)