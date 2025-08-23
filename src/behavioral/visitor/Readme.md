# Visitor Pattern

## What is Visitor Pattern?

Visitor Pattern is a behavioral design pattern that solves the open-closed principle by separating algorithms from the objects on which they operate. It represents an operation to be performed on elements of an object structure and lets you define new operations without changing the classes of elements on which it operates, using double dispatch to delegate choosing proper method to object itself.

## Purpose

- Define new operations on object structure without modifying element classes
- Separate algorithms from objects on which they operate
- Gather related operations together and separate them from unrelated ones
- Add new operations to existing object structures without modifying structures
- Enable double dispatch mechanism for type-specific operation selection
- Centralize operations that work across different element types
- Support operations that require access to multiple element types

## Structure

The pattern consists of:
- **Visitor Interface** - Declares visiting methods for each concrete element type
- **Concrete Visitor** - Implements operations to be performed on elements
- **Element Interface** - Declares accept method that takes visitor as parameter
- **Concrete Element** - Implements accept method and provides interface for visitor to access its state
- **Object Structure** - Collection of elements that can be enumerated and provides interface to allow visitor to visit elements

The pattern relies on double dispatch and polymorphism. Elements accept visitors and visitors perform type-specific operations.

## Benefits

- **Open/Closed Principle** - New operations added without modifying existing element classes
- **Single Responsibility** - Related operations grouped together in visitor classes
- **Operation Centralization** - Operations that span multiple element types kept together
- **Type Safety** - Compile-time type checking for visitor operations
- **Algorithm Reuse** - Visitors can be applied to different object structures
- **Clean Separation** - Business logic separated from data structure

## When to Use

- When object structure is stable but operations on it change frequently
- When you need to perform many distinct and unrelated operations on objects
- When object structure contains many classes with differing interfaces
- When you want to avoid polluting element classes with unrelated operations
- When operations need to work across different element types
- When you need to gather related operations and separate them from unrelated ones

## UML Diagram

```
┌─────────────────────────┐       ┌─────────────────────────┐
│    <<interface>>        │       │    <<interface>>        │
│       Visitor           │       │       Element           │
├─────────────────────────┤       ├─────────────────────────┤
│ + visitElementA()       │       │ + accept(Visitor)       │
│ + visitElementB()       │       └─────────────────────────┘
│ + visitElementC()       │                   △
└─────────────────────────┘                   │
            △                         ┌───────┼──────┐
            │                         │              │
    ┌───────▽───────────┐       ┌─────▽──────┐ ┌─────▽─────────┐
    │ ConcreteVisitor   │       │ElementA    │ │ElementB       │
    ├───────────────────┤       ├────────────┤ ├───────────────┤
    │ + visitElementA() │       │+accept()   │ │+accept()      │
    │ + visitElementB() │       │+operation()│ │+operation()   │
    │ + visitElementC() │       └────────────┘ └───────────────┘
    └───────────────────┘

Double Dispatch Flow:
Client → Element.accept(visitor) → Visitor.visitElement(this)
```

## Example Scenario

A system with stable object structure where different types of operations need to be performed on the same elements. The visitor pattern allows adding new operations without modifying the element classes, enabling flexible algorithm implementation while maintaining separation of concerns.

## Real-World Applications

- **Compiler Design** - AST processing (code generation, optimization, type checking)
- **Document Processing** - Export to different formats (PDF, HTML, XML)
- **Graphics Applications** - Rendering, hit-testing, bounding box calculations
- **File System Operations** - Copy, delete, compress operations on file hierarchies
- **XML/JSON Processing** - Different serialization and validation operations
- **Game Development** - AI behavior, rendering, collision detection on game objects
- **Report Generation** - Different report formats from same data structure
- **Code Analysis Tools** - Static analysis, metrics calculation, refactoring operations

## Pattern Variations

### Basic Visitor
- Simple visitor with operations for each element type
- Direct operation implementation in visitor methods

### Parameterized Visitor
- Visitors that accept parameters for operation customization
- Enables configurable behavior for same visitor type

### Hierarchical Visitor
- Visitors that work with element hierarchies
- Supports recursive traversal of complex structures

### Accumulating Visitor
- Visitors that accumulate results across multiple element visits
- Useful for statistics, aggregation, and analysis operations

### Filtering Visitor
- Visitors that process only specific element types or conditions
- Enables selective operation application

## Implementation Guidelines

### Do's
- Keep visitor interface focused on element types, not operations
- Provide meaningful names for visitor methods (visitBank, visitCompany)
- Consider visitor state for operations that need to accumulate data
- Implement proper error handling in visitor operations
- Document visitor purpose and expected element structure

### Don'ts
- Don't add new element types frequently (requires visitor interface changes)
- Don't put element-specific logic in visitor base classes
- Don't create visitors that depend on specific element implementation details
- Don't ignore the coupling between visitor and element hierarchies
- Don't use visitor when element structure changes frequently

## Performance Considerations

### Double Dispatch Overhead
- Visitor pattern involves two method calls (accept + visit)
- Minimal performance impact for most applications
- Consider direct method calls for performance-critical scenarios

### Memory Usage
- Visitors may accumulate state during traversal
- Monitor memory usage for visitors processing large structures
- Consider streaming or chunked processing for very large structures

### Traversal Efficiency
- Object structure traversal can be expensive for large hierarchies
- Consider caching or indexing strategies
- Implement efficient iteration mechanisms

## Common Pitfalls Avoided

- **Visitor Interface Explosion**: Keep visitor interface focused and stable
- **Element Coupling**: Minimize visitor dependency on element internals
- **Circular Dependencies**: Manage dependencies between visitor and element hierarchies
- **State Management**: Handle visitor state properly during traversal
- **Error Propagation**: Implement proper error handling across visitor operations

## Testing Strategy

The tests verify:
- **Basic visitor acceptance** - Elements properly accept visitors
- **Operation execution** - Visitors perform correct operations on each element type
- **State accumulation** - Visitors correctly maintain state across visits
- **Multiple visitors** - Same structure supports different visitor types
- **Object structure** - Collection properly manages elements and visitor traversal
- **Double dispatch** - Correct method selection based on element and visitor types

## Thread Safety Considerations

### Stateless Visitors
- Visitors should be stateless when possible for thread safety
- Use local variables or parameters for operation-specific data

### Concurrent Traversal
- Consider synchronization when multiple visitors traverse same structure
- Use immutable structures or proper locking for concurrent access

### Visitor State
- If visitors maintain state, ensure proper synchronization
- Consider thread-local storage for visitor-specific data

## Modern Applications

### Abstract Syntax Trees
- Compiler passes implemented as visitors
- Code analysis tools using visitor pattern
- Refactoring operations on code structures

### Document Object Models
- HTML/XML processing with different visitors
- Document transformation and validation
- Content extraction and analysis

### Game Engine Architecture
- Component systems with visitor-based operations
- Scene graph processing with different visitors
- AI behavior implementation using visitors

## Alternatives to Consider

### For Frequently Changing Structures
- **Strategy Pattern**: When object behavior needs to change
- **Command Pattern**: When operations should be encapsulated as objects
- **Function Objects**: In functional programming languages

### For Simple Operations
- **Direct Method Calls**: When operations are simple and stable
- **Polymorphism**: When operations naturally belong to element classes
- **Switch Statements**: For simple type-based dispatch

## Conclusion

The Visitor pattern provides excellent solution for adding operations to stable object structures without modifying existing classes. It excels when you have complex operations that need to work across multiple element types while maintaining clean separation between data and algorithms. The pattern's double dispatch mechanism ensures type-safe operation selection while enabling flexible algorithm implementation and easy extension with new operations.