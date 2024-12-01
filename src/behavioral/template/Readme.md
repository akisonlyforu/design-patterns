# Template Method Pattern

## What is Template Method Pattern?

Template Method Pattern is a behavioral design pattern that defines the skeleton of an algorithm in a base class and lets subclasses override specific steps of the algorithm without changing its structure. It breaks down the algorithm into methods and puts a series of calls to these methods inside a single template method, allowing subclasses to redefine certain steps while keeping the overall algorithm structure intact.

## Purpose

- Define skeleton of algorithm in superclass but let subclasses override specific steps
- Eliminate code duplication by extracting common algorithm structure
- Control which parts of algorithm can be extended by subclasses
- Implement invariant parts of algorithm once and leave variant behavior to subclasses
- Achieve code reuse through inheritance while allowing customization
- Ensure algorithm structure remains consistent across all implementations
- Provide hooks for subclasses to extend behavior at specific points

## Structure

The pattern consists of:
- **Abstract Class** - Defines template method and abstract operations that subclasses must implement
- **Template Method** - Final method that defines algorithm skeleton and calls primitive operations
- **Primitive Operations** - Abstract methods that subclasses must implement for algorithm steps
- **Hook Methods** - Methods with default implementation that subclasses can optionally override
- **Concrete Classes** - Implement primitive operations to carry out subclass-specific steps

The pattern relies on inheritance and method overriding. The template method controls the algorithm flow while primitive operations provide customization points.

## Benefits

- **Code Reuse** - Common algorithm structure shared across all subclasses
- **Algorithm Control** - Template method ensures consistent execution order
- **Flexibility** - Subclasses can customize specific steps without changing overall structure
- **Maintenance** - Changes to algorithm structure made in one place
- **Consistency** - Guarantees that all implementations follow the same algorithm flow
- **Extension Points** - Hook methods provide optional customization opportunities

## When to Use

- When you have multiple classes with similar algorithms that differ only in certain steps
- When you want to control which parts of an algorithm subclasses can override
- When you need to eliminate code duplication in similar algorithms
- When you want to define invariant parts of algorithm once and vary only specific steps
- When you have a multi-step process where some steps are common and others vary
- When you want to apply the Hollywood Principle ("Don't call us, we'll call you")

## UML Diagram

```
┌─────────────────────────┐
│    AbstractClass        │
├─────────────────────────┤
│ + load()                │◄─── final template method
│ # step1()               │◄─── abstract methods
│ # step2()               │
│ # step3()               │
│ # hookMethod()          │◄─── hook method (optional override)
└─────────────────────────┘
            △
            │
    ┌───────┼─────────────┐
    │                     │
┌───▽──────────┐    ┌─────▽─────────┐
│ConcreteClass1│    │ConcreteClass2 │
├──────────────┤    ├───────────────┤
│+step1()      │    │+step1()       │
│+step2()      │    │+step2()       │
│+step3()      │    │+step3()       │
│+hookMethod() │    │+hookMethod()  │◄─── optional override
└──────────────┘    └───────────────┘

Template Method Flow:
load() {
  hookMethod(); ←── calls hook (optional behavior)
  step1();      ←── calls abstract method
  step2();      ←── calls abstract method  
  step3();      ←── calls abstract method
  hookMethod(); ←── calls hook (optional behavior)
}
```

## Example Scenario

A game loading system where different types of loaders (Database, File System, Cloud, Debug) need to follow the same loading process but implement each step differently. The template method ensures all loaders follow the same sequence: step1 → step2 → step3, while each loader type provides its own implementation for these steps. This eliminates code duplication and ensures consistent loading behavior.

## Key Differences from Other Patterns

### Template Method vs Strategy
- **Template Method**: Uses inheritance, defines algorithm skeleton, subclasses implement steps
- **Strategy**: Uses composition, entire algorithm is interchangeable

### Template Method vs Factory Method
- **Template Method**: Defines algorithm structure with customizable steps
- **Factory Method**: Focuses specifically on object creation step within larger algorithm

### Template Method vs Bridge
- **Template Method**: Single hierarchy with algorithm structure and step implementations
- **Bridge**: Two separate hierarchies connected by composition

## Real-World Applications

- **Web Framework Request Processing** - Servlet processing with customizable request handling steps
- **Data Processing Pipelines** - ETL processes with common structure but varying extract/transform/load implementations
- **Testing Frameworks** - Test execution with setup, execute, teardown steps that can be customized
- **Document Generation** - Report generation with common structure but varying content sections
- **Game Engine Initialization** - Engine startup with standard sequence but platform-specific implementations
- **Build Systems** - Build processes with common steps but tool-specific implementations

## Pattern Variations

### Basic Template Method
- Simple abstract methods that must be implemented
- All steps are required

### Hook Methods Template Method
- Optional methods with default implementations
- Subclasses can choose which hooks to override
- Provides more flexibility while maintaining structure

### Parameterized Template Method
- Template method accepts parameters that influence algorithm behavior
- Combines template method with strategy-like flexibility

## Implementation Guidelines

### Do's
- Make template method final to prevent algorithm structure changes
- Use protected access for primitive operations (only subclasses should call them)
- Provide meaningful default implementations for hook methods
- Keep primitive operations focused and single-purpose
- Document the algorithm flow clearly

### Don'ts
- Don't make template method virtual/overridable
- Don't put business logic in template method (delegate to primitive operations)
- Don't make primitive operations public (they're internal to algorithm)
- Don't create too many primitive operations (keep algorithm manageable)
- Don't forget to handle edge cases in template method

## Common Pitfalls Avoided

- **Algorithm Inconsistency**: Template method being final prevents subclasses from breaking algorithm flow
- **Code Duplication**: Common structure shared in base class eliminates repeated code
- **Primitive Operation Access**: Protected methods prevent external classes from calling internal steps
- **Hook Method Confusion**: Clear distinction between required abstract methods and optional hooks
- **Inheritance Misuse**: Pattern uses inheritance appropriately for IS-A relationships

## Testing Strategy

The unit tests verify:
- **Template method immutability** - Method is final and cannot be overridden
- **Algorithm structure consistency** - All implementations follow same flow
- **Step implementation correctness** - Each subclass implements steps properly
- **Hook method behavior** - Optional customizations work as expected
- **Execution order** - Steps are executed in correct sequence
- **Extensibility** - New loader types can be added easily

## Performance Considerations

- **Inheritance Overhead**: Minimal performance impact from method overriding
- **Algorithm Efficiency**: Common structure eliminates redundant operations
- **Memory Usage**: Shared algorithm structure reduces memory footprint
- **Extensibility Cost**: Adding new implementations has minimal impact

## Conclusion

The Template Method pattern successfully eliminates code duplication while providing a flexible framework for algorithm customization. It ensures that all implementations follow the same reliable structure while allowing each subclass to provide its own specific behavior for individual steps. This makes the code more maintainable, consistent, and easier to extend with new implementations.