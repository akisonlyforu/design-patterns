# Observer Pattern Implementation

## Overview
This implementation demonstrates the Observer design pattern with email and mobile app notification systems. The pattern allows multiple objects (subscribers/listeners) to be notified about any events that happen to the object they're observing (publisher).

## Design Components

### Core Interfaces and Classes

1. **EventListener Interface** - Defines the notification interface with `update()` method
2. **Publisher Class** - Manages subscribers and handles notifications
3. **EmailMsgListener** - Concrete implementation for email notifications
4. **MobileAppListener** - Concrete implementation for mobile push notifications
5. **ConcreteSubscriber** - Event publisher that extends Publisher functionality

## Key Features

- **Multiple Subscriber Types**: Supports both email and mobile app notifications
- **Dynamic Subscription Management**: Subscribers can be added/removed at runtime
- **Notification Interface**: Standardized update mechanism for all listener types
- **Concrete Subscriber Actions**: Performs specific actions in response to notifications

## UML Class Diagram

```
┌─────────────────┐       ┌──────────────────┐
│   EventListener │◄──────┤    Publisher     │
│                 │       │                  │
│ + update()      │       │ - subscribers    │
└─────────────────┘       │ + subscribe()    │
         ▲                │ + unsubscribe()  │
         │                │ + notify()       │
    ┌────┴────┐           └─────────┬────────┘
    │         │                     ▲
┌───▼──────┐ ┌▼────────────┐        │
│EmailMsg  │ │MobileApp    │   ┌────┴───────────────┐
│Listener  │ │Listener     │   │ConcreteSubscriber  │
│          │ │             │   │                    │
│-email    │ │-deviceId    │   │                    │
│+update() │ │+update()    │   │-name               │
└──────────┘ └─────────────┘   │+performAction      │
                               └────────────────────┘
```

## Expected Output

The demo will show:
- Initial subscription of multiple listeners
- Event notifications sent to all subscribers
- Dynamic unsubscription and re-subscription
- Different notification types (email vs mobile) being triggered

## Pattern Benefits

- **Loose Coupling**: Publishers don't need to know concrete subscriber classes
- **Dynamic Relationships**: Subscribers can be added/removed at runtime
- **Broadcast Communication**: Single event can notify multiple subscribers
- **Open/Closed Principle**: Easy to add new subscriber types without modifying existing code

## Observer Pattern vs Publish-Subscribe Pattern
While Observer and Publish-Subscribe patterns are related, they have key differences:
Observer Pattern (This Implementation)

- **Direct Coupling**: Publishers maintain direct references to observers
- **Synchronous**: Notifications happen immediately and synchronously
- **Same Process**: Publisher and observers exist in the same application/process
- **Type Safety**: Compile-time type checking between publisher and observers
- **Simple Structure**: Direct relationship between subject and observers

## Publish-Subscribe Pattern

- **Loose Coupling**: Publishers and subscribers don't know about each other directly
- **Message Broker**: Uses an intermediary (message queue, event bus) to route messages
- **Asynchronous**: Often asynchronous with message queuing
- **Distributed**: Can work across different processes, services, or networks
- **Topic-Based**: Uses topics/channels to categorize and route messages
- **More Complex**: Requires additional infrastructure (message brokers, queues)

## When to Use Each
### Use Observer Pattern when:

- Components are in the same application
- You need immediate, synchronous notifications
- Simple one-to-many relationships
- Direct control over notification flow

### Use Publish-Subscribe when:

- Distributed systems or microservices
- Asynchronous processing needed
- Decoupling publishers from subscribers completely
- Multiple message types/topics
- Scalability across networks

## Real-World Applications

- Newsletter subscriptions
- Stock price notifications
- Social media updates
- System monitoring alerts
- Mobile app push notifications