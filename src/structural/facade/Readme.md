# Facade Pattern

## What is Facade Pattern?

Facade Pattern is a structural design pattern that applies the Single Responsibility Principle. It provides a class that serves as front facing interface that masks complex underlying structural code, improving readability and usability of software by hiding interactions of its components.

## Purpose

- Apply Single Responsibility Principle
- Provide a class that serves as front facing interface that masks complex underlying structural code
- Improve readability and usability of software by hiding interactions of its components
- Define entry points to each level of subsystem thus decoupling multiple subsystems and forcing them to communicate only through facades
- Restrict direct access to inner workings of factory

## Structure

The pattern consists of:
- **Facade** - Provides simple interface to complex subsystem
- **Subsystem Classes** - Complex classes that perform the actual work
- **Client** - Uses the facade instead of interacting with subsystem directly

The pattern relies on composition and delegation. The facade coordinates multiple subsystem objects and provides a unified interface to clients.

## Benefits

- **Simplified Interface** - Provides easy-to-use interface to complex subsystem
- **Loose Coupling** - Clients are decoupled from subsystem implementation details
- **Subsystem Independence** - Subsystem can evolve without affecting clients
- **Single Entry Point** - Centralized access to subsystem functionality
- **Improved Readability** - Client code becomes cleaner and more maintainable
- **Layered Architecture** - Supports building applications in layers

## When to Use

- When you have a complex subsystem that is difficult to use
- When you want to provide a simple interface to a complex system
- When you need to decouple clients from subsystem implementation
- When you want to layer your subsystems
- When you need to wrap poorly designed APIs
- When you want to reduce dependencies between client and subsystem

## UML Diagram

```
┌─────────┐ ┌─────────┐ ┌─────────┐
│ClientA  │ │ClientB  │ │ClientC  │
└────┬────┘ └────┬────┘ └────┬────┘
     │           │           │
     └───────────┼───────────┘
                 │
         ┌───────▽────────┐
         │    Facade      │
         ├────────────────┤
         │ + doSomething()│
         └────────────────┘
                 │
                 │ coordinates
                 ▼
    ┌──────────────────────────────────┐
    │            Subsystem             │
    ├──────┬──────┬──────┬──────┬──────┤
    │Class1│Class2│Class3│Class4│Class5│
    └──────┴──────┴──────┴──────┴──────┘
```

## Example Scenario

A hotel management system has complex subsystems for room booking, housekeeping, restaurant services, and billing. Without a facade, clients would need to interact with multiple services, understand their dependencies, and coordinate their interactions. The HotelKeeper facade provides simple methods like `bookRoom()` and `orderRoomService()` that internally coordinate all necessary subsystem operations, hiding the complexity from clients.