# State Pattern Implementation - Phone States

## Overview
This implementation demonstrates the State design pattern using a phone state system. The pattern allows an object to alter its behavior when its internal state changes, making the object appear as if it changed its class.

## Key Concepts from Notes

**Behavioral Pattern**: Allows an object to change its behavior when its internal state changes. Concrete states provide their own implementation for state-specific methods. You can jump from one state to another so that you can do different things based on the state.

**Finite States**: At any given moment, there's a finite number of states which a program can be in, and within any unique state, the program behaves differently.

## Design Components

### Core Classes

1. **State (Abstract Class)** - Defines the interface for state-specific behavior
2. **Phone (Context)** - Maintains reference to current state and delegates behavior
3. **LockedState** - Concrete state when phone is locked
4. **ReadyState** - Concrete state when phone is unlocked and ready
5. **OffState** - Concrete state when phone is powered off
6. **IState Interface** - Alternative interface-based approach for states

## UML Class Diagram

```
┌─────────────────┐       ┌──────────────────┐
│     State       │◄──────┤      Phone       │
│   (Abstract)    │       │    (Context)     │
│                 │       │                  │
│ # phone: Phone  │       │ - state: State   │
│ + onHome()      │       │ + setState()     │
│ + onOffOn()     │       │ + getCurrentState│
│ + lock()        │       │ + onHome()       │
│ + home()        │       │ + onOffOn()      │
│ + unlock()      │       │ + lock()         │
│ + turnOn()      │       │ + home()         │
└─────────────────┘       │ + unlock()       │
         ▲                │ + turnOn()       │
         │                └──────────────────┘
    ┌────┴─────┬─────────┬──────────┐
    │          │         │          │
┌───▼──────┐ ┌─▼─────┐ ┌─▼────────┐ │
│OffState  │ │Ready  │ │Locked    │ │
│          │ │State  │ │State     │ │
│+onHome() │ │       │ │          │ │
│+onOffOn()│ │+all   │ │+all      │ │
│+lock()   │ │methods│ │methods   │ │
│+home()   │ │       │ │          │ │
│+unlock() │ │       │ │          │ │
│+turnOn() │ │       │ │          │ │
└──────────┘ └───────┘ └──────────┘ │
                                    │
          ┌─────────────────────────┘
          │
      ┌───▼──────────┐
      │   IState     │
      │ (Interface)  │
      │              │
      │+doSomething()│
      │+doMoreStuff()│
      └──────────────┘
             ▲
             │
      ┌──────▼──────┐
      │ConcreteState│
      │             │
      │- context    │
      │+setContext()│
      │+doSomething │
      │+doMoreStuff │
      └─────────────┘
```

## State Transitions

The phone can transition between three main states:

```
       turnOn()       lock()
OffState ──→ ReadyState ──→ LockedState
    ▲           ▲               │
    │           └─── unlock() ──┘
    │
onOffOn()
```

## State-Specific Behaviors

### OffState
- **turnOn()** / **onOffOn()**: Transitions to ReadyState
- **All other actions**: Return "Phone is off" messages

### ReadyState
- **lock()**: Transitions to LockedState
- **onOffOn()**: Transitions to OffState
- **onHome()** / **home()**: Normal home screen access
- **unlock()**: Already unlocked message

### LockedState
- **unlock()**: Transitions to ReadyState
- **onOffOn()**: Transitions to OffState
- **onHome()** / **home()**: Locked access denied messages
- **lock()**: Already locked message

## Expected Output

The demo will show:
- Initial state behavior (OffState)
- State transitions with different behaviors
- How same method calls produce different results based on current state
- Interface-based state implementation example

## Pattern Benefits

- **Eliminates Complex Conditionals**: No need for large if/switch statements
- **Single Responsibility**: Each state handles its own behavior
- **Open/Closed Principle**: Easy to add new states without modifying existing code
- **State-Specific Behavior**: Clear separation of what each state can do
- **Clean Transitions**: Explicit state change logic

## State vs Strategy Pattern

While both patterns involve composition and delegation:

### State Pattern
- **Purpose**: Change behavior based on internal state
- **Context Awareness**: States know about context and can change it
- **State Transitions**: States can trigger transitions to other states
- **Single Active State**: Only one state active at a time

### Strategy Pattern
- **Purpose**: Choose algorithm/behavior at runtime
- **Independence**: Strategies are independent of context
- **No Transitions**: Strategies don't change each other
- **Interchangeable**: Strategies can be swapped without state concept

## Real-World Applications

- Phone/device state management
- Game character states (idle, running, jumping, attacking)
- Network connection states (connecting, connected, disconnected)
- Order processing states (pending, processing, shipped, delivered)
- Media player states (playing, paused, stopped, buffering)
- Traffic light systems
- Vending machine operations