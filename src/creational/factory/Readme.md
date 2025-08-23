# Factory Pattern

## What is Factory Pattern?

Factory Pattern is a creational design pattern that provides an interface for creating objects without specifying their exact classes. It creates objects based on given input or conditions.

## Purpose

- Hide object creation logic from client code
- Create objects without exposing instantiation logic
- Return objects through a common interface
- Make code more flexible and maintainable

## Structure

The pattern consists of:
- **Product Interface** - Common interface for all products
- **Concrete Products** - Specific implementations of the interface
- **Factory Class** - Contains the creation logic

## Benefits

- **Loose Coupling** - Client code doesn't depend on concrete classes
- **Single Responsibility** - Object creation logic is centralized
- **Open/Closed Principle** - Easy to add new product types
- **Code Reusability** - Factory can be used by multiple clients

## When to Use

- When you have multiple classes that implement the same interface
- When object creation logic is complex
- When you want to hide instantiation details from clients
- When you need to create different objects based on input parameters

## UML Diagram

```
┌─────────────────┐
│  <<interface>>  │
│    Product      │
├─────────────────┤
│   +execute()    │
└─────────────────┘
         △
         │
    ┌────┴──────────┐
    │               │
┌───▽──────┐ ┌──────▽──────┐
│ConcreteA │ │ConcreteB    │
├──────────┤ ├─────────────┤
│+execute()│ │ +execute()  │
└──────────┘ └─────────────┘
                              
┌─────────────────────┐
│     Factory         │
├─────────────────────┤
│+createProduct(type) │───┐
└─────────────────────┘   │
                          │ creates
                          ▽
                ┌─────────────────┐
                │     Product     │
                └─────────────────┘
```

## Example Scenario

A media player application needs to support different player types (VLC, Windows Media Player). Instead of client code deciding which concrete class to instantiate, a factory handles this decision based on user preference or system requirements.