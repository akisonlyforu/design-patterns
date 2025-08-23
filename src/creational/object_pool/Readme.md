# Object Pool Pattern

## What is Object Pool Pattern?

Object Pool Pattern is a creational design pattern that offers performance boost by reusing expensive objects instead of creating new ones. It maintains a pool of reusable objects and provides them to clients on demand, returning them to the pool when no longer needed.

## Purpose

- Offers performance boost
- Most effective in situations where cost of initializing a class instance is high, rate of instantiation of class is high, and number of instantiations in use at any one time is low
- Client will have access to object pool
- Reusable pool class is designed to be singleton
- Objects are taken when needed and objects are released back to pool
- Periodically clean up unused objects

## Structure

The pattern consists of:
- **Reusable Interface** - Defines reset method for object cleanup
- **Reusable Object** - Expensive object that can be reset and reused
- **Object Pool** - Manages available and in-use objects
- **Object Factory** - Creates new instances when pool is empty
- **Client** - Acquires objects from pool, uses them, and releases back

The pattern relies on object lifecycle management and state reset. Objects must be able to clean their state for safe reuse.

## Benefits

- **Performance Improvement** - Avoids expensive object creation
- **Resource Management** - Controls number of objects in memory
- **Memory Efficiency** - Reuses existing objects instead of creating new ones
- **Reduced Garbage Collection** - Fewer objects created means less GC pressure
- **Controlled Resource Usage** - Limits maximum number of expensive objects

## When to Use

- When object creation is expensive or time-consuming
- When you need many similar objects with short lifespans
- When object initialization involves heavy resources (database connections, threads)
- When garbage collection of many objects impacts performance
- When you want to limit resource usage (connection limits, memory constraints)

## Key Components Explained

### Why reset() is Mandatory
Objects retain state from previous use. Without reset(), the next user gets a "dirty" object with leftover data, causing data leakage and security issues.

### Why ObjectFactory<T> create() is Critical
Java generics cannot be instantiated directly (no 'new T()'). The factory.create() method tells the pool HOW to create new objects when the pool is empty, enabling generic and flexible pool implementations.

## UML Diagram

```
┌─────────────────────┐
│   <<interface>>     │
│     Reusable        │
├─────────────────────┤
│ + reset(): void     │
└─────────────────────┘
           △
           │ implements
           │
┌──────────▽──────────┐
│   ReusableObject    │
├─────────────────────┤
│ - inUse: boolean    │
│ - state: Object     │
├─────────────────────┤
│ + reset(): void     │
│ + isInUse(): boolean│
│ + doWork(): void    │
└─────────────────────┘

┌─────────────────────┐         ┌─────────────────────┐
│   <<interface>>     │         │   ObjectPool<T>     │
│  ObjectFactory<T>   │         ├─────────────────────┤
├─────────────────────┤         │ - available: List<T>│
│ + create(): T       │◄────────┤ - inUse: List<T>    │
└─────────────────────┘         │ - factory: Factory  │
                                ├─────────────────────┤
                                │ + acquire(): T      │
                                │ + release(T): void  │
                                │ + showStats(): void │
                                └─────────────────────┘
                                         │
                                         │ manages
                                         ▼
                                ┌─────────────────────┐
                                │   ReusableObject    │
                                │      (Pooled)       │
                                └─────────────────────┘
```

## Example Scenario

A web application needs database connections for handling user requests. Creating new database connections is expensive (network setup, authentication, resource allocation). The Object Pool maintains a set of pre-created connections that can be reused across multiple requests, dramatically improving performance and resource utilization.

## Trade-offs

### Benefits
- **Performance** - Eliminates expensive object creation
- **Resource Control** - Limits maximum resource usage
- **Memory Efficiency** - Reduces object allocation overhead

### Considerations
- **Thread Safety** - Requires careful handling of concurrent access to pool
- **Memory Usage** - Pool holds objects in memory even when not needed
- **Complexity** - Adds complexity compared to simple object creation