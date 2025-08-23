# Decorator Pattern

## What is Decorator Pattern?

Decorator Pattern is a structural design pattern that wraps an existing object, allowing you to add new functionality to that object without altering its structure. It assigns extra behaviors to objects at runtime without breaking other code that uses that object.

## Purpose

- Add new functionality to objects dynamically without altering their structure
- Assign extra behaviors to objects at runtime without breaking existing code
- Wrap existing objects to extend their functionality
- Each behavior is isolated in separate class and you have the ability to introduce new decorators without modifying existing classes
- Follow open-closed principle as new decorators can be added without modifying existing code

## Structure

The pattern consists of:
- **Component Interface** - Common interface for both concrete components and decorators
- **Concrete Component** - Basic implementation that can be decorated
- **Abstract Decorator** - Base decorator class that wraps a component and delegates operations
- **Concrete Decorators** - Specific decorators that add new functionality to the component

The pattern relies on composition and delegation. Decorators wrap components and can be stacked on top of each other to combine multiple behaviors.

## Benefits

- **Runtime Flexibility** - Add or remove behaviors at runtime
- **Single Responsibility** - Each decorator handles one specific enhancement
- **Open/Closed Principle** - Open for extension, closed for modification
- **Composition over Inheritance** - More flexible than creating subclasses
- **Stackable Behaviors** - Multiple decorators can be combined

## When to Use

- When you need to add functionality to objects dynamically
- When extending functionality through inheritance is impractical
- When you want to add responsibilities to objects without subclassing
- When you need different combinations of behaviors
- When you want to wrap legacy code with new functionality

## UML Diagram

```
┌─────────────────────┐
│   <<interface>>     │
│    Component        │
├─────────────────────┤
│ + operation()       │
└─────────────────────┘
           △
           │
    ┌──────┴──────────────────┐
    │                         │
┌───▽────────┐    ┌───────────▽───────────┐
│ Concrete   │    │   <<abstract>>        │
│ Component  │    │   Decorator           │
├────────────┤    ├───────────────────────┤
│            │    │ - component: Component│
├────────────┤    ├───────────────────────┤
│+operation()│    │ + operation()         │
└────────────┘    └───────────────────────┘
                             △
                             │
                    ┌────────┴──────────┐
                    │                   │
            ┌───────▽──────────┐ ┌──────▽───────────┐
            │ConcreteDecoratorA│ │ConcreteDecoratorB│
            ├──────────────────┤ ├──────────────────┤
            │                  │ │                  │
            ├──────────────────┤ ├──────────────────┤
            │ + operation()    │ + operation()      │
            │ +addedBehavior() │ │ + addedBehavior()│
            └──────────────────┘ └──────────────────┘
```

## Example Scenario

A coffee shop application needs to calculate costs and descriptions for different coffee combinations. Customers can add various ingredients (milk, sugar, whipped cream) to basic coffee. Instead of creating separate classes for every possible combination, decorators wrap the basic coffee and add functionality incrementally. This allows unlimited combinations: coffee with milk, coffee with sugar and milk, coffee with all ingredients, etc.