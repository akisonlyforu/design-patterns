# Prototype Pattern

## What is Prototype Pattern?

Prototype Pattern is a creational design pattern that creates objects by cloning existing instances rather than creating new ones from scratch. It produces new objects by copying a prototype instance and allows creating objects without knowing their specific classes.

## Purpose

- Create objects by copying existing instances
- Avoid expensive object creation processes
- Create objects without knowing their concrete classes
- Reduce the number of subclasses needed
- Configure objects at runtime by cloning prototypes
- Handle cases where object creation is more expensive than copying

## Structure

The pattern consists of:
- **Prototype Interface** - Declares cloning method for all prototypes
- **Concrete Prototypes** - Implement cloning method and define how to copy themselves
- **Client** - Creates new objects by asking prototypes to clone themselves
- **Prototype Registry** - Optional component that maintains available prototypes

The pattern relies on object copying and polymorphism. Objects must know how to copy themselves and return the copy through a common interface.

## Benefits

- **Performance** - Faster than creating objects from scratch when creation is expensive
- **Flexibility** - Add and remove prototypes at runtime
- **Reduced Subclassing** - Avoid creating subclasses just for object creation
- **Dynamic Configuration** - Clone and modify objects as needed
- **Hide Creation Complexity** - Client doesn't need to know construction details

## When to Use

- When object creation is expensive or complex
- When you need to create many similar objects
- When you want to avoid subclassing just for object creation
- When object configuration is determined at runtime
- When you need to create objects without knowing their exact types

## UML Diagram

```
┌─────────────────────┐
│   <<interface>>     │
│    Prototype        │
├─────────────────────┤
│ + clone(): Prototype│
└─────────────────────┘
           △
           │
           │
┌──────────▽──────────┐
│  ConcretePrototype  │
├─────────────────────┤
│ - field1: Type      │
│ - field2: Type      │
├─────────────────────┤
│ + clone(): Prototype│
│ + operation()       │
└─────────────────────┘
           │
           │ creates copy
           ▼
┌─────────────────────┐
│  ConcretePrototype  │
│     (Clone)         │
├─────────────────────┤
│ - field1: Type      │
│ - field2: Type      │
├─────────────────────┤
│ + clone(): Prototype│
│ + operation()       │
└─────────────────────┘
```

## Example Scenario

A vehicle manufacturing system needs to create many similar vehicles with slight variations. Instead of creating each vehicle from scratch (expensive process involving engine configuration, wheel setup, etc.), the system maintains prototype vehicles and clones them, then modifies specific properties as needed. This is much faster than rebuilding vehicles from components each time.

## Best Practices

- Always override `clone()` when implementing Cloneable
- Handle deep copying for mutable references explicitly
- Consider copy constructors for simpler, cleaner code
- Document whether your clone implementation is shallow or deep
- Use covariant return types for type safety
- Consider immutable design to avoid cloning complexity