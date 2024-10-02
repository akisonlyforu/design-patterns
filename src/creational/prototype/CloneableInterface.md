# Java Cloneable Interface and Object Cloning

## What is Cloneable Interface?

Cloneable is a marker interface in Java that indicates a class allows cloning of its instances. It works with the `Object.clone()` method to create copies of objects. Classes must implement Cloneable to use the built-in cloning mechanism.

## How Cloneable Works

The Cloneable interface has no methods - it's a marker interface that tells the JVM that `Object.clone()` is permitted for instances of the class. Without implementing Cloneable, calling `clone()` throws `CloneNotSupportedException`.

## Basic Implementation

```java
class MyClass implements Cloneable {
    private int value;
    private String name;
    
    @Override
    public MyClass clone() {
        try {
            return (MyClass) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Clone not supported", e);
        }
    }
}
```

## Shallow Copy with Cloneable

By default, `Object.clone()` performs shallow copying:

- Copies all primitive fields directly
- Copies object references but not the referenced objects themselves
- Original and clone share the same referenced objects

### Example of Shallow Copy Issue

```java
class Person implements Cloneable {
    private String name;
    private List<String> hobbies;  // Mutable reference
    
    public Person clone() {
        try {
            return (Person) super.clone();  // Shallow copy
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

// Problem: Both original and clone share the same hobbies list
Person original = new Person("John", Arrays.asList("Reading"));
Person clone = original.clone();
clone.getHobbies().add("Swimming");  // Also adds to original's hobbies!
```

## Deep Copy with Cloneable

To achieve deep copying, manually clone mutable references in the `clone()` method:

```java
class Person implements Cloneable {
    private String name;
    private List<String> hobbies;
    
    public Person clone() {
        try {
            Person cloned = (Person) super.clone();
            // Deep copy the mutable list
            cloned.hobbies = new ArrayList<>(this.hobbies);
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Cloneable in Prototype Pattern

### Basic Prototype with Cloneable

```java
abstract class Prototype implements Cloneable {
    public Prototype clone() {
        try {
            return (Prototype) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

class ConcretePrototype extends Prototype {
    private String data;
    
    // Inherits clone() method from parent
    // Works for shallow copy scenarios
}
```

### Advanced Prototype with Deep Copy

```java
class ComplexPrototype implements Cloneable {
    private String name;
    private List<String> items;
    private Map<String, Object> properties;
    
    public ComplexPrototype clone() {
        try {
            ComplexPrototype cloned = (ComplexPrototype) super.clone();
            
            // Deep copy mutable collections
            cloned.items = new ArrayList<>(this.items);
            cloned.properties = new HashMap<>(this.properties);
            
            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Benefits of Using Cloneable

- **Native Java Support** - Built into the language
- **Performance** - `Object.clone()` is optimized for copying
- **Convention** - Standard Java approach for cloning
- **Covariant Return Types** - Override to return specific types

## Limitations of Cloneable

- **Checked Exception** - Must handle `CloneNotSupportedException`
- **Shallow Copy Default** - Requires manual work for deep copying
- **No Interface Contract** - Marker interface provides no method signatures
- **Inheritance Issues** - Can be problematic with inheritance hierarchies