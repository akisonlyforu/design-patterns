# Two Singleton Implementations That Actually Work

Spent way too much time debugging broken singletons, so here are two versions I use that haven't failed me yet. Complete with the comments that remind me why each line matters.

## What's This Singleton Pattern Anyway?

The Singleton is one of those Gang of Four creational patterns that everyone learns and half the people hate. It's dead simple in concept: ensure only one instance of a class exists and give global access to it.

**The problem it solves:** Sometimes you genuinely need exactly one thing. Database connection pools, logging systems, configuration managers - having multiple instances would either waste resources or cause conflicts.

**Why it's controversial:** Global state is generally bad, makes testing harder, and can hide dependencies. But sometimes you actually need it, and when you do, you need to do it right.

The pattern falls under "creational patterns" because it controls *how* objects get created - specifically, by preventing multiple creations and managing the single instance's lifecycle.

## The Basic Structure

```
┌─────────────────────────┐
│       Singleton         │
├─────────────────────────┤
│ - instance: Singleton   │
│ - data: String          │
├─────────────────────────┤
│ - Singleton()           │
│ + getInstance(): Single │
│ + getData(): String     │
└─────────────────────────┘
```

The key pieces:
- **Static instance field** - holds the one and only instance
- **Private constructor** - prevents external instantiation
- **Public static method** - controlled access point to get the instance
- **Instance data** - whatever state your singleton needs to hold

## Why These Work

**The `volatile` keyword** stops the JVM from doing sneaky optimizations that can break your singleton. Without it, other threads might see a half-constructed object. I learned this the hard way when my database connections were randomly throwing NPEs under load.

**Double-checked locking** means threads only wait in line when they actually need to. Once the singleton exists, everyone gets it immediately without any synchronization overhead. The first `if` check saves you from unnecessary synchronization 99% of the time.

**Private constructors** prevent some wise guy from calling `new Singleton()` and breaking your whole pattern. Been there, had a coworker do exactly that.

## The Parameterized Version Gotcha

With `SingletonWithParameter`, only the first call matters:

```java
SingletonWithParameter config = SingletonWithParameter.getInstance("production-db");
SingletonWithParameter same = SingletonWithParameter.getInstance("test-db");

System.out.println(config.getData()); // "production-db"
        System.out.println(same.getData());   // "production-db" (NOT "test-db"!)
```

This caught me off guard initially, but it makes sense. Once you have a singleton, you have *the* singleton. Later parameters get ignored.

## When to Use Which

**No-parameter version** is perfect for:
- Loggers that don't need configuration
- Caches that configure themselves
- Utility classes with no state

**Parameter version** works great for:
- Database connections with specific URLs
- Configuration managers that load from a file
- API clients that need authentication tokens

## Design Decisions That Matter

**Why `final` for the data field?** Because once your singleton is configured, you probably don't want its core state changing. Makes debugging way easier when you know the data can't be modified after creation.

**Why no no-args constructor in the parameterized version?** The `final` field needs to be initialized somewhere. A no-args constructor would leave it null, defeating the whole purpose.

## Performance Notes

These implementations are fast. The volatile read is cheap on modern JVMs, and the synchronization only happens during the first initialization. After that, it's just a memory read.

I've used both patterns in production systems handling thousands of requests per second without any performance issues.

## Alternative: Enum Singleton

If you don't want to think about any of this threading stuff:

```java
public enum DatabaseConfig {
    INSTANCE;

    public void connect() { /* your logic */ }
}
```

The JVM handles all the thread safety for you. Sometimes the simple solution is the best solution.