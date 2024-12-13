# Strategy and Iterator Patterns

## Strategy Pattern

### What is Strategy Pattern?

Strategy Pattern is a behavioral design pattern that defines class of algorithms, puts each of them in separate class and makes their objects interchangeable. The context class has no visibility on how payment is being conducted as it is making use of strategy interface. Context doesn't know how algorithm is executed - strategies are independent and unaware of each other.

### Purpose

- Define family of algorithms and make them interchangeable at runtime
- Encapsulate algorithms in separate classes with common interface
- Allow algorithm selection without changing client code that uses it
- Enable addition of new algorithms without modifying existing context
- Apply Single Responsibility and Open/Closed Principles
- Replace conditional statements with polymorphic strategy objects

### Strategy Pattern Structure

```
┌─────────────────────────┐       ┌─────────────────────────┐
│       Context           │       │    <<interface>>        │
│   (PaymentService)      │       │      Strategy           │
├─────────────────────────┤       │  (IPaymentStrategy)     │
│ - strategy: Strategy    │◄──────├─────────────────────────┤
├─────────────────────────┤       │ + execute()             │
│ + setStrategy()         │       └─────────────────────────┘
│ + execute()             │                   △
└─────────────────────────┘           ┌───────┼─────────────┐
                                      │               │     │
                              ┌───────▽────┐    ┌─────▽─────────┐
                              │ConcreteA   │    │ConcreteB      │
                              │(PayPal)    │    │(CreditCard)   │
                              ├────────────┤    ├───────────────┤
                              │+execute()  │    │+execute()     │
                              └────────────┘    └───────────────┘
```

### When to Use Strategy Pattern

- When you have multiple ways to perform a task
- When you want to switch algorithms at runtime
- When you have complex conditional statements for algorithm selection
- When algorithms should be independent and interchangeable
- When you need to add new algorithms without changing existing code

## Iterator Pattern

### What is Iterator Pattern?

Iterator Pattern is a behavioral design pattern that extracts traversal behavior of collection into separate object called an iterator. It provides a way to traverse elements of collection without exposing its underlying representation. Several iterators can go through same collection at same time.

### Purpose

- Provide uniform way to access elements of different collection types
- Hide internal structure of collections from client code
- Support multiple simultaneous traversals of same collection
- Enable different traversal algorithms (forward, reverse, filtered)
- Separate collection management from traversal logic
- Follow Single Responsibility Principle for collection operations

### Iterator Pattern Structure

```
┌─────────────────────────┐       ┌─────────────────────────┐
│    <<interface>>        │       │    <<interface>>        │
│      Iterator           │       │     Aggregate          │
├─────────────────────────┤       ├─────────────────────────┤
│ + hasNext(): boolean    │       │ + createIterator()      │
│ + getNext(): Object     │       └─────────────────────────┘
│ + reset(): void         │                   △
└─────────────────────────┘                   │
            △                         ┌───────▽──────────┐
            │                         │ ConcreteAggregate │
    ┌───────▽──────────┐              │   (Company)       │
    │ ConcreteIterator  │◄─────────────├───────────────────┤
    │(EmployeeIterator) │              │ - items: List     │
    ├───────────────────┤              ├───────────────────┤
    │ - currentIndex    │              │ + createIterator()│
    │ + hasNext()       │              │ + addItem()       │
    │ + getNext()       │              │ + removeItem()    │
    │ + reset()         │              └───────────────────┘
    └───────────────────┘
```

### When to Use Iterator Pattern

- When you need to traverse collections without exposing internal structure
- When you want to support multiple simultaneous traversals
- When you need different ways to traverse same collection
- When you want uniform traversal interface for different collection types
- When collection structure is complex and should be hidden from client

## Pattern Benefits

### Strategy Pattern Benefits

- **Runtime Flexibility**: Algorithms can be switched at runtime
- **Open/Closed Principle**: New strategies added without changing context
- **Single Responsibility**: Each strategy handles one algorithm
- **Testability**: Strategies can be tested independently
- **Maintainability**: Algorithm changes isolated to specific strategy classes

### Iterator Pattern Benefits

- **Encapsulation**: Internal collection structure hidden from clients
- **Multiple Traversals**: Several iterators can work simultaneously
- **Uniform Interface**: Same interface for different collection types
- **Traversal Flexibility**: Different iteration strategies supported
- **Independence**: Collection can change without affecting iteration

## Real-World Applications

### Strategy Pattern Applications

- **Payment Processing**: PayPal, Credit Card, Bank Transfer strategies
- **Sorting Algorithms**: QuickSort, MergeSort, BubbleSort strategies
- **Compression**: ZIP, RAR, 7Z compression strategies
- **Transportation**: Car, Transit, Walking route strategies
- **Pricing**: Regular, Premium, Discount pricing strategies
- **Validation**: Email, Phone, Credit Card validation strategies

### Iterator Pattern Applications

- **Java Collections**: ArrayList, LinkedList, HashSet iterators
- **Database Access**: ResultSet iteration in JDBC
- **File Systems**: Directory and file tree traversal
- **Social Media**: Posts, comments, message iteration
- **Game Development**: Game object collection traversal
- **Document Processing**: Paragraph, sentence, word iteration

## Performance Considerations

### Strategy Pattern Performance

- **Minimal Overhead**: Strategy selection has negligible performance cost
- **Memory Usage**: Each strategy instance consumes memory
- **Initialization**: Strategy objects can be created on-demand or cached
- **Thread Safety**: Stateless strategies are inherently thread-safe

### Iterator Pattern Performance

- **Traversal Cost**: Iterator adds slight overhead compared to direct access
- **Memory Usage**: Iterator maintains traversal state
- **Concurrent Modification**: Consider fail-fast behavior for safety
- **Large Collections**: Iterator enables memory-efficient traversal

## Anti-Patterns to Avoid

### Strategy Pattern Anti-Patterns

- **Strategy Explosion**: Too many strategy classes for minor variations
- **Inappropriate Strategy**: Using strategy when simple conditional suffices
- **Stateful Strategies**: Strategies that maintain state between uses
- **Complex Context**: Context becoming too complex or strategy-aware

### Iterator Pattern Anti-Patterns

- **Exposing Internal Structure**: Iterator revealing collection internals
- **Inefficient Traversal**: Iterator causing unnecessary performance overhead
- **Concurrent Modification**: Not handling concurrent collection modifications
- **Heavy Iterator**: Iterator carrying too much state or logic

## Modern Alternatives

### Strategy Pattern Alternatives

- **Lambda Expressions**: For simple strategies in functional languages
- **Method References**: Direct method references instead of strategy objects
- **Enum with Methods**: Enum constants with behavioral methods

### Iterator Pattern Alternatives

- **Streams API**: Java 8+ streams for functional-style iteration
- **For-Each Loops**: Enhanced for loops with Iterable collections
- **Reactive Streams**: Asynchronous stream processing

## Conclusion

Strategy and Iterator patterns provide complementary solutions for behavioral design problems. Strategy pattern enables flexible algorithm selection and execution, while Iterator pattern provides uniform collection traversal. Both patterns promote loose coupling, enhance maintainability, and support extensibility. When used together, they create powerful combinations for processing collections with configurable algorithms, as demonstrated in payroll processing systems where Iterator provides employee traversal and Strategy handles payment processing.