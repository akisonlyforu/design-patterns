# Iterator Pattern Implementation

## Overview
The Iterator design pattern extracts traversal behavior of collection into separate object called an iterator. This behavioral pattern provides a way to traverse elements of collection without exposing its underlying representation. Several iterators can go through same collection at same time.

## Key Concepts from Notes

**Behavioral Pattern**: Extracts traversal behavior into separate iterator object. Traverse elements without exposing underlying representation. Several iterators can go through same collection simultaneously. The pattern allows sequential access to elements of aggregate object without exposing its underlying representation.

**Core Principles**:
- Provides uniform interface for traversing different aggregate structures
- Encapsulates iteration logic in separate iterator objects
- Supports multiple concurrent iterations over same collection
- Decouples collection classes from traversal algorithms
- Applies single responsibility principle by separating iteration from collection management

## Design Components

### Core Classes

1. **Iterator Interface** - Defines common interface for all iterators with traversal methods
2. **Aggregate Interface** - Provides contract for collections to create their appropriate iterators
3. **Concrete Iterator** - Implements specific traversal behavior for particular collection type
4. **Concrete Aggregate** - Implements collection that can create iterators for its elements
5. **Client** - Uses iterator interface to traverse collections without knowing internal structure

## UML Class Diagram

```
┌─────────────────┐       ┌───────────────────┐
│   <<interface>> │       │   <<interface>>   │
│    Iterator     │       │    Aggregate      │
│                 │       │                   │
│ + hasNext()     │       │ + createIterator()│
│ + next()        │       └─────────┬─────────┘
│ + reset()       │                 ▲
└─────────────────┘                 │
         ▲                          │
         │                 ┌────────▼──────────┐
┌────────▼────────┐        │ConcreteAggregate  │
│ConcreteIterator │        │                   │
│                 │◄───────┤ - collection      │
│ - currentIndex  │        │ + createIterator()│
│ - collection    │        │ + add()           │
│ + hasNext()     │        │ + remove()        │
│ + next()        │        └───────────────────┘
│ + reset()       │
└─────────────────┘        ┌─────────────────┐
                           │     Client      │
                           │                 │
                           │ + traverse()    │
                           └─────────────────┘
```

## Iterator Characteristics

### Traversal Behavior
- **Encapsulated Navigation**: Iterator handles all traversal logic internally
- **Position Management**: Maintains current position in collection independently
- **Boundary Checking**: Provides safe access with bounds validation
- **Reset Capability**: Allows restarting iteration from beginning

### Collection Independence
- **Uniform Interface**: Same iteration methods work across different collection types
- **Hidden Implementation**: Client doesn't need to know if collection is Array, List, Tree, etc.
- **Multiple Iterators**: Several iterators can traverse same collection simultaneously
- **No Structural Exposure**: Collection internal structure remains encapsulated

## Iterator Pattern vs Direct Access

### Iterator Pattern Benefits
- **Encapsulation**: Collection internal structure hidden from client
- **Uniform Interface**: Same traversal code works for different collection types
- **Multiple Traversals**: Support for concurrent iterations
- **Flexible Navigation**: Can implement different traversal orders (forward, backward, filtered)
- **Safety**: Bounds checking and controlled access to elements

### Direct Access Limitations
- **Tight Coupling**: Client must know collection implementation details
- **No Abstraction**: Different collection types require different access patterns
- **Index Management**: Client responsible for managing current position
- **Error Prone**: Manual bounds checking required
- **Limited Flexibility**: Difficult to implement complex traversal patterns

## When to Use Iterator Pattern

### Ideal Scenarios
- When you need to traverse collections without exposing internal structure
- When you want uniform traversal interface across different collection types
- When multiple clients need to iterate simultaneously over same collection
- When you need to implement complex traversal algorithms (filtered, sorted, etc.)
- When collection structure might change but traversal interface should remain stable

### Consider Alternatives When
- Simple collections with straightforward access patterns
- Performance is critical and iterator overhead is significant
- Collection type is fixed and won't change
- Only single-threaded access is needed

## Pattern Benefits

- **Encapsulation**: Hides collection implementation details from clients
- **Uniform Interface**: Same iteration pattern works across different collections
- **Multiple Iterations**: Supports concurrent traversal by multiple clients
- **Flexible Traversal**: Easy to implement different iteration strategies
- **Single Responsibility**: Separates collection management from traversal logic
- **Open/Closed Principle**: New iterator types can be added without modifying collections

## Real-World Applications

- **Data Structure Traversal**: Lists, trees, graphs, hash tables traversal
- **File System Navigation**: Directory and file iteration
- **Database Result Sets**: Row-by-row processing of query results
- **GUI Components**: Traversing widget hierarchies, menu structures
- **Network Protocols**: Packet processing, message iteration
- **Game Development**: Entity iteration, inventory traversal
- **Content Management**: Document collections, media libraries
- **Workflow Systems**: Task queues, process step iteration

## Pattern Variations

### External Iterator (Pull Model)
- Client controls iteration pace
- Iterator provides hasNext() and next() methods
- Client decides when to advance to next element

### Internal Iterator (Push Model)
- Collection controls iteration
- Client provides callback/function to process each element
- Collection manages traversal internally

### Filtered Iterator
- Applies filtering criteria during traversal
- Skips elements that don't match filter conditions
- Maintains clean interface while providing subset access

### Bidirectional Iterator
- Supports both forward and backward traversal
- Provides previous() method in addition to next()
- Useful for undo/redo operations and complex navigation

## Extending the System

To add new collection types or iterator behaviors:

1. **New Collection Type**: Implement Aggregate interface with createIterator() method
2. **New Iterator Type**: Implement Iterator interface with specific traversal logic
3. **No Changes Needed**: Existing client code continues to work unchanged

This demonstrates the Open/Closed Principle - the system is open for extension but closed for modification.