# Builder Pattern

## What is Builder Pattern?

Builder Pattern is a creational design pattern that provides a flexible solution for constructing complex objects step by step. It separates the construction of an object from its representation, allowing the same construction process to create different representations.

## Purpose

- Construct complex objects with many parameters
- Handle required and optional parameters elegantly
- Create immutable objects safely
- Avoid telescoping constructor problem
- Provide readable and maintainable object creation

## Structure

The pattern consists of:
- **Product** - The complex object being built
- **Builder** - Interface or class that defines construction steps
- **Concrete Builder** - Implements the building steps and returns the product
- **Director** - Optional class that orchestrates the building process

The pattern relies on encapsulation and method chaining. The builder accumulates parameters and constructs the final object when requested.

## Benefits

- **Readable Code** - Fluent interface makes object creation clear
- **Flexible Construction** - Handle optional parameters without multiple constructors
- **Immutable Objects** - Create objects that cannot be modified after construction
- **Parameter Validation** - Validate parameters before object creation
- **Step-by-Step Construction** - Build complex objects incrementally

## When to Use

- When you have classes with many parameters
- When you need to create immutable objects
- When some parameters are optional
- When constructor parameter lists become unwieldy
- When you want to enforce object creation constraints

## UML Diagram

```
                    ┌─────────────────────┐
                    │     <<interface>>   │
                    │       Builder       │
                    ├─────────────────────┤
                    │ + buildPartA(): void│
                    │ + buildPartB(): void│
                    │ + buildPartC(): void│
                    │ + getResult(): Product│
                    └─────────────────────┘
                              ▲
                              │
                              │
            ┌─────────────────┴─────────────────┐
            │                                   │
   ┌────────▽────────┐                ┌───────▽────────┐
   │ ConcreteBuilderA│                │ConcreteBuilderB│
   ├─────────────────┤                ├────────────────┤
   │ -product:Product│                │-product:Product│
   ├─────────────────┤                ├────────────────┤
   │ + buildPartA()  │                │ + buildPartA() │
   │ + buildPartB()  │                │ + buildPartB() │
   │ + buildPartC()  │                │ + buildPartC() │
   │ + getResult()   │                │ + getResult()  │
   └─────────────────┘                └────────────────┘
            │                                   │
            │ creates                           │ creates
            ▼                                   ▼
   ┌─────────────────┐                ┌────────────────┐
   │    ProductA     │                │    ProductB    │
   ├─────────────────┤                ├────────────────┤
   │ - partA         │                │ - partA        │
   │ - partB         │                │ - partB        │
   │ - partC         │                │ - partC        │
   ├─────────────────┤                ├────────────────┤
   │ + getPartA()    │                │ + getPartA()   │
   │ + getPartB()    │                │ + getPartB()   │
   │ + getPartC()    │                │ + getPartC()   │
   └─────────────────┘                └────────────────┘

┌─────────────────┐
│    Director     │      ┌─────────────────────────────────┐
├─────────────────┤      │         Optional Component      │
│ - builder       │ ────▶│   (orchestrates build process)  │
├─────────────────┤      └─────────────────────────────────┘
│ + construct()   │
└─────────────────┘
```

## Example Scenario

A vehicle manufacturing system needs to create different types of vehicles with various configurations. Some parameters like engine and wheels are required, while others like airbags are optional. The Builder pattern allows creating vehicles with different combinations of features without having multiple constructors.