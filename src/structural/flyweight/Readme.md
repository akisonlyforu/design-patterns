# Flyweight Pattern

## What is Flyweight Pattern?

Flyweight Pattern is a structural design pattern that optimizes memory usage by sharing common portions among multiple objects. It reduces the number of objects created and to decrease memory footprint which is particularly useful when dealing with large number of similar objects.

## Purpose

- Optimize memory usage by sharing common portions among multiple objects
- Reduce number of objects created and to decrease memory footprint
- Particularly useful when dealing with large number of similar objects
- Reuse existing ones whenever possible, sharing common parts
- Objects are divided into 2 parts - shared and unique data
- Flyweight contains a portion of original object state

## Key Concepts

### Intrinsic State (Shared Data)
- Data that can be shared among multiple objects
- Stored inside the flyweight object
- Independent of flyweight's context
- Immutable and context-independent

### Extrinsic State (Unique Data)
- Data that is unique to each object instance
- Passed to flyweight methods as parameters
- Depends on flyweight's context
- Stored and managed by client context

## Structure

The pattern consists of:
- **Flyweight Interface** - Defines operations that can accept extrinsic state
- **Concrete Flyweight** - Implements flyweight interface and stores intrinsic state
- **Flyweight Factory** - Manages flyweight instances and ensures sharing
- **Context** - Contains extrinsic state and maintains reference to flyweight

The pattern relies on object sharing and separation of concerns. Intrinsic state is shared through flyweights while extrinsic state is managed by context objects.

## Benefits

- **Memory Optimization** - Significantly reduces memory usage for large object collections
- **Performance Improvement** - Fewer objects mean less garbage collection overhead
- **Shared Resources** - Common data shared efficiently among objects
- **Scalability** - Handles large numbers of similar objects efficiently
- **Cache Benefits** - Factory provides built-in caching mechanism

## When to Use

- When you need to create a large number of similar objects
- When object creation is expensive in terms of memory
- When most object state can be made extrinsic
- When groups of objects can share common intrinsic state
- When you want to reduce memory footprint of your application

## UML Diagram

```
┌─────────────────┐         ┌─────────────────┐
│     Client      │────────▶│ FlyweightFactory│
└─────────────────┘         ├─────────────────┤
                            │ - flyweights    │
                            ├─────────────────┤
                            │ + getFlyweight()│
                            └─────────────────┘
                                     │
                                     │ creates/manages
                                     ▼
┌─────────────────┐         ┌─────────────────┐
│    Context      │────────▶│   Flyweight     │
├─────────────────┤         ├─────────────────┤
│ - extrinsicState│         │ - intrinsicState│
├─────────────────┤         ├─────────────────┤
│ + operation()   │         │ + operation()   │
└─────────────────┘         └─────────────────┘
```

## Example Scenario

A file explorer application displays thousands of files and folders. Without flyweight pattern, each file icon would be a separate object containing image data, resulting in massive memory usage. With flyweight pattern, all files of the same type (e.g., all PDF files) share the same flyweight object containing the common icon image. Only the position and name (extrinsic state) are stored separately for each file, dramatically reducing memory usage while maintaining the same visual functionality.