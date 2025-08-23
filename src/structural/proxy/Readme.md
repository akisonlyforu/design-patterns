# Proxy Pattern

## What is Proxy Pattern?

Proxy Pattern is a structural design pattern that allows you to control access to a particular object by performing something before or after the request reaches that object. It provides a placeholder or surrogate for another object to control access to it.

## Purpose

- Control access to a particular object by performing something before or after the request reaches that object
- Proxy must implement the same interface as original service object
- Manage lifecycle of object and proxy will work even if the service object is not ready or is not available
- Client and object are connected by proxy object
- Client talks to proxy and proxy forwards to object

## Structure

The pattern consists of:
- **Service Interface** - Common interface that both proxy and real service implement
- **Real Service** - The actual service object that performs the real work
- **Proxy** - Controls access to the real service and may add additional functionality
- **Client** - Uses objects through the service interface

The pattern relies on composition and delegation. The proxy holds a reference to the real service and forwards requests to it while potentially adding extra behavior.

## Benefits

- **Lazy Initialization** - Create expensive objects only when needed
- **Access Control** - Control who can access the real object
- **Caching** - Cache results to improve performance
- **Logging and Monitoring** - Add logging without modifying real service
- **Remote Proxy** - Represent objects in different address spaces
- **Protection** - Add security checks before accessing real object

## Types of Proxies

### Virtual Proxy (Lazy Loading)
- Delays creation of expensive objects until actually needed
- Useful for objects that are expensive to create or load
- Example: Loading large images only when they need to be displayed

### Protection Proxy
- Controls access based on permissions or credentials
- Checks authorization before forwarding requests
- Example: User permission checking before accessing sensitive data

### Remote Proxy
- Represents objects located in different address spaces
- Handles network communication details
- Example: Web service clients, RMI stubs

### Caching Proxy
- Stores results of expensive operations
- Returns cached results for repeated requests
- Example: Database query result caching

## When to Use

- When you need lazy initialization of expensive objects
- When you want to control access to an object
- When you need to add functionality without modifying the original object
- When you want to cache expensive operations
- When working with remote objects or services
- When you need to manage object lifecycle

## UML Diagram

```
┌─────────────────┐         ┌──────────────────┐
│     Client      │────────▶│ Service Interface│
└─────────────────┘         ├──────────────────┤
                            │ + request()      │
                            └──────────────────┘
                                     △
                            ┌────────┴────────┐
                            │                 │
                    ┌───────▽──────┐  ┌───────▽──────┐
                    │     Proxy    │  │ Real Service │
                    ├──────────────┤  ├──────────────┤
                    │ - realService│  │              │
                    ├──────────────┤  ├──────────────┤
                    │ + request()  │  │ + request()  │
                    └──────────────┘  └──────────────┘
                            │                  △
                            │ delegates to     │
                            └──────────────────┘
```

## Example Scenario

An application needs to display high-resolution images but wants to avoid loading all images at startup (which would be slow and memory-intensive). A proxy image is created immediately but the actual image is loaded from disk only when display() is called. This provides lazy loading - images are loaded on-demand, improving application startup time and memory usage.