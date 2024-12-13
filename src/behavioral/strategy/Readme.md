# Strategy Pattern Implementation

## Overview
The Strategy design pattern defines a class of algorithms, puts each of them in a separate class, and makes their objects interchangeable. This behavioral pattern allows an object to alter its behavior when its internal state changes.

## Key Concepts from Notes

**Behavioral Pattern**: Allows an object to alter its behavior when its internal state changes. The context class has no visibility on how the algorithm is being conducted as it is making use of the strategy interface. Context doesn't know how algorithm is executed. Strategies are independent and unaware of each other.

**Core Principles**:
- Pattern is about having different implementations that accomplish the same thing
- Replaceable and interchangeable by clients at runtime
- Applies single responsibility and open/closed principles
- You can jump from one strategy to another so that you can do different things based on the strategy
- At any given moment, there's a finite number of strategies which a program can be in, and without any unique strategy, the program behaves differently

## Design Components

### Core Classes

1. **Strategy Interface** - Defines the common interface for all algorithms
2. **Context Class** - Maintains reference to current strategy and delegates execution
3. **Concrete Strategies** - Implement specific algorithms that accomplish the same goal
4. **Client** - Selects and configures appropriate strategies at runtime

## Strategy Selection Flow

```
Client Request → Context → Strategy Selection → Algorithm Execution

1. Client determines which algorithm to use
2. Context receives strategy reference  
3. Context delegates execution to current strategy
4. Strategy executes its specific algorithm
5. Result returned through context to client
```

## UML Class Diagram

```
┌─────────────────┐       ┌──────────────────┐
│    Context      │──────►│   <<interface>>  │
│                 │       │    Strategy      │
│ - strategy      │       │                  │
│ + setStrategy() │       │ + execute()      │
│ + execute()     │       └─────────┬────────┘
└─────────────────┘                 ▲
                                    │
                        ┌───────────┼───────────┐
                        │                       │
              ┌─────────▼─────────┐   ┌─────────▼─────────┐
              │   ConcreteA       │   │   ConcreteB       │
              │                   │   │                   │
              │ + execute()       │   │ + execute()       │
              └───────────────────┘   └───────────────────┘

┌─────────────────┐
│     Client      │
│                 │
│ + operation()   │
└─────────────────┘
```

## Strategy Pattern vs State Pattern

While both patterns involve composition and delegation, they serve different purposes:

### Strategy Pattern (This Implementation)
- **Purpose**: Choose algorithm/behavior at runtime
- **Independence**: Strategies are independent of context and each other
- **No Transitions**: Strategies don't change each other
- **Interchangeable**: Strategies can be swapped without state concept
- **Client Control**: Client typically chooses which strategy to use
- **Same Interface**: All strategies accomplish the same goal differently

### State Pattern
- **Purpose**: Change behavior based on internal state
- **Context Awareness**: States know about context and can change it
- **State Transitions**: States can trigger transitions to other states
- **Single Active State**: Only one state active at a time
- **Internal Control**: Object changes its own state based on conditions
- **Different Behaviors**: Each state may have completely different behaviors

## Strategy Characteristics

### Context Behavior
- **No Algorithm Visibility**: Context has no knowledge of how algorithms are implemented
- **Strategy Delegation**: All algorithm execution is delegated to current strategy
- **Runtime Selection**: Strategies can be switched at runtime
- **Interface Dependency**: Context only depends on strategy interface, not concrete implementations

### Strategy Independence
- **Isolated Algorithms**: Each strategy is unaware of other strategies
- **Self-Contained**: Strategies handle their own algorithm logic independently
- **Interchangeable**: Any strategy implementing the interface can be used
- **Separate Responsibilities**: Each strategy has single responsibility for its algorithm

## Expected Behavior

The demonstration will show:
- Runtime strategy selection and switching
- Context delegation to different algorithms
- Independent strategy execution
- Algorithm interchangeability without context modification
- Error handling for invalid strategy states

## Pattern Benefits

- **Runtime Algorithm Selection**: Choose payment method at runtime
- **Easy Extension**: Add new payment methods without modifying existing code
- **Single Responsibility**: Each payment method handles its own logic
- **Open/Closed Principle**: Open for extension, closed for modification
- **Eliminates Conditional Logic**: No need for large if/switch statements
- **Independent Strategies**: Payment methods don't depend on each other

## Real-World Applications

- **Algorithm Selection**: Sorting algorithms, search algorithms, compression methods
- **Business Rules**: Tax calculation strategies, pricing strategies, discount policies
- **Authentication**: OAuth, LDAP, database authentication methods
- **Data Processing**: File format handlers, data validation strategies
- **Communication**: Network protocols, message formatting strategies
- **UI Behavior**: Layout strategies, rendering strategies, input validation
- **Game Development**: AI behavior strategies, movement strategies, combat strategies

## Extending the System

To add a new algorithm implementation:

1. **Create new strategy class** implementing the strategy interface
2. **Update client selection logic** to include new strategy option
3. **No changes needed** to existing context or other strategies

This demonstrates the Open/Closed Principle - the system is open for extension but closed for modification.