# Shallow Copy vs Deep Copy

Object copying is a fundamental concept in programming that determines how objects and their references are duplicated.

## Shallow Copy
- Copies object fields but not the objects they reference
- Original and clone share references to the same objects
- Changes to referenced objects affect both original and clone
- Faster and uses less memory
- Suitable when objects contain only primitive fields or immutable references

## Deep Copy
- Copies object fields and recursively copies all referenced objects
- Original and clone have completely independent object graphs
- Changes to referenced objects don't affect the other
- Slower and uses more memory
- Required when objects contain mutable references that should be independent

## Choosing Copy Strategy

**Use Shallow Copy when:**
- Object contains only primitive fields (int, String, etc.)
- Referenced objects are immutable
- Shared references are acceptable for your use case
- Performance is critical and independence isn't required

**Use Deep Copy when:**
- Object contains mutable references (Lists, custom objects, etc.)
- Complete independence between original and clone is required
- Modifying clone should not affect original object's state
- Object graph contains nested mutable objects

## Copy Strategy in Prototype Pattern

The Prototype pattern typically uses **shallow copy** for objects with primitive fields and immutable references (like String). **Deep copy** is required when prototype objects contain mutable references that need to be independent between original and clone.

#### Shallow Copy in Prototype Pattern
- Most common approach when prototypes contain primitive types (int, boolean, char) and immutable objects (String, Integer)
- Uses Java's `Object.clone()` or copy constructors that copy field values directly
- Efficient for simple objects without complex nested structures
- Example: Vehicle with engine (String), wheels (int), color (String) - all safe for shallow copy

#### Deep Copy in Prototype Pattern
- Required when prototypes contain mutable objects like Lists, Arrays, or custom objects
- Must recursively clone all referenced objects to ensure complete independence
- Prevents unintended sharing of mutable state between prototype and clones
- Example: Vehicle with `List<String> features` - requires deep copy to avoid shared list reference

## Choosing Strategy for Prototypes
- Analyze object structure to determine if mutable references exist
- Consider whether clones should share or own their referenced objects
- Balance performance needs with independence requirements
- Document copy behavior clearly for maintainability
