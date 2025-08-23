# Abstract Factory Pattern

## What is Abstract Factory Pattern?

Abstract Factory Pattern is a creational design pattern that provides an interface for creating families of related objects without specifying their concrete classes. It creates groups of objects that are designed to work together.

## Purpose

- Create families of related objects
- Ensure compatibility between products in the same family
- Hide concrete class instantiation from client code
- Switch between different product families easily
- Maintain consistency across related products

## Structure

The pattern consists of:
- **Abstract Products** - Interfaces for different types of products
- **Concrete Products** - Specific implementations grouped into families
- **Abstract Factory** - Interface for creating families of products
- **Concrete Factories** - Create specific families of related products

The pattern relies on inheritance and polymorphism. Products are organized into families where each family contains related objects that work well together.

## Benefits

- **Product Family Consistency** - Ensures related objects are compatible
- **Loose Coupling** - Client code doesn't depend on concrete classes
- **Easy Family Switching** - Change entire product families at runtime
- **Single Responsibility** - Each factory creates one product family
- **Open/Closed Principle** - Easy to add new product families

## When to Use

- When you need to create families of related products
- When products in a family must be used together
- When you want to enforce consistency across product variants
- When you need to switch between different product families
- When object creation involves multiple related products

## UML Diagram

```
┌─────────────────┐  ┌─────────────────┐
│  <<interface>>  │  │  <<interface>>  │
│   ProductA      │  │   ProductB      │
├─────────────────┤  ├─────────────────┤
│ +operationA()   │  │ +operationB()   │
└─────────────────┘  └─────────────────┘
         △                      △
         │                      │
    ┌────┴────┐            ┌────┴────────┐
    │         │            │             │
┌───▽──────┐ ┌▽─────────┐ ┌▽─────────┐ ┌─▽────────┐
│ProductA1 │ │ProductA2 │ │ProductB1 │ │ProductB2 │
├──────────┤ ├──────────┤ ├──────────┤ ├──────────┤
│+execute()│ │+execute()│ │+execute()│ │+execute()│
└──────────┘ └──────────┘ └──────────┘ └──────────┘

        ┌─────────────────────────┐
        │   <<abstract class>>    │
        │   AbstractFactory       │
        ├─────────────────────────┤
        │ +createProductA()       │
        │ +createProductB()       │
        └─────────────────────────┘
                    △
                    │
       ┌────────────┴─────────────────┐
       │                              │
┌──────▽────────────┐       ┌─────────▽─────────┐
│     Factory1      │       │      Factory2     │
├───────────────────┤       ├───────────────────┤
│+createProductA()  │       │+createProductA()  │
│+createProductB()  │       │+createProductB()  │
└───────────────────┘       └───────────────────┘
```

## Example Scenario

A multimedia application needs to support different media platforms (Windows, VLC). Each platform provides both audio and video players that work best together. The Abstract Factory ensures that when you choose Windows, you get Windows audio and video players that are compatible with each other.