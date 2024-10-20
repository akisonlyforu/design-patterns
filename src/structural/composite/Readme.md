# Composite Pattern

## What is Composite Pattern?

Composite Pattern is a structural design pattern that allows you to compose objects into tree structures to represent part-whole hierarchies. It lets clients treat individual objects and compositions of objects uniformly through a common interface. The goal is to encapsulate absence of an object by providing alternative that offers suitable default "do nothing" behavior.

## Purpose

- Build tree structure of objects where individual and composite objects share common interface
- Allow clients to interact with both types of objects without needing to distinguish between them
- Compose objects into tree structures to represent part-whole hierarchies
- Introduce new element types into application without breaking existing code
- Enable recursive operations on tree structures
- Most effective in situations where cost of initializing a class instance is high, rate of instantiation of class is high, and number of instantiations in use at any one time is low
- Client will have access to object pool
- Reusable pool class is designed to be singleton
- Objects are taken when needed and objects are released back to pool
- Periodically clean up unused objects

## Structure

The pattern consists of:
- **Component Interface** - Common interface for both leaf and composite objects
- **Leaf** - Individual objects that cannot contain other objects
- **Composite** - Container objects that can hold both leaf and other composite objects, has sub-elements but doesn't know concrete classes of its children
- **Client** - Interacts with objects through the component interface

The pattern relies on polymorphism and recursion. Composite objects delegate operations to their children, creating recursive behavior throughout the tree structure.

## Benefits

- **Uniform Treatment** - Client code treats individual and composite objects the same way
- **Simplified Client Code** - No need to distinguish between object types
- **Easy Extension** - New component types can be added without changing existing code
- **Recursive Operations** - Operations naturally work on entire tree structures
- **Flexible Hierarchy** - Can create complex nested structures dynamically
- **Thread Safety** - Requires careful handling of concurrent access to pool

## When to Use

- When you need to represent part-whole hierarchies of objects
- When you want clients to ignore the difference between individual and composite objects
- When you need to work with tree structures
- When you want to apply the same operations to individual objects and groups of objects
- When object structure can be represented as a tree

## UML Diagram

```
┌─────────────────────┐
│   <<interface>>     │
│    Component        │
├─────────────────────┤
│ + operation()       │
│ + add(Component)    │
│ + remove(Component) │
│ + getChild(int)     │
└─────────────────────┘
             △
             │
      ┌──────┴──────────────┐
      │                     │
┌─────▽──────┐       ┌──────▽─────┐
│    Leaf    │       │ Composite  │
├────────────┤       ├────────────┤
│            │       │ - children │
├────────────┤       ├────────────┤
│+operation()│       │+operation()│
└────────────┘       │+add()      │
                     │+remove()   │
                     │+getChild() │
                     └────────────┘
                           │
                           │ contains
                           ▼
                    ┌─────────────┐
                    │ Component[] │
                    └─────────────┘
```

## Example Scenario

A file system where directories can contain both files and other directories. Users need to perform operations like display, copy, or delete on both individual files and entire directory trees. The Composite pattern allows treating a single file the same way as a directory containing hundreds of files and subdirectories.