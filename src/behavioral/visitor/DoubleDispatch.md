# Double Dispatch in Visitor Pattern

## What is Double Dispatch?

Double Dispatch is a mechanism that dispatches a function call to different concrete functions depending on the runtime types of **two objects** involved in the call. Unlike single dispatch (normal method calls) which depends on the type of one object (the receiver), double dispatch considers the types of both the caller and the parameter.

## Why Double Dispatch?

In object-oriented programming, method calls are typically resolved using **single dispatch** - the method to call is determined by the runtime type of the object on which the method is called. However, sometimes we need to select a method based on the types of **two objects**, which requires double dispatch.

## Single Dispatch vs Double Dispatch

### Single Dispatch (Normal Method Call)
```java
// Method selection based on ONE object type (the receiver)
animal.makeSound(); 

// If animal is Dog -> Dog.makeSound()
// If animal is Cat -> Cat.makeSound()
// Runtime type of 'animal' determines which method is called
```

### Double Dispatch (Visitor Pattern)
```java
// Method selection based on TWO object types
element.accept(visitor);

// Step 1: element.accept() - dispatched on element type
// Step 2: visitor.visitElement() - dispatched on visitor type
// Both element type AND visitor type determine which method is called
```

## How Double Dispatch Works in Visitor Pattern

### The Two-Step Process

#### Step 1: First Dispatch (Element Type)
```java
IElement element = new Bank("Central Bank", 1000000, 800000, 5000);
IVisitor visitor = new TaxAssessmentVisitor();

element.accept(visitor); // First dispatch based on element's actual type
```

When `element.accept(visitor)` is called:
- Java looks at the **runtime type** of `element`
- Since `element` is actually a `Bank` object, it calls `Bank.accept(visitor)`

#### Step 2: Second Dispatch (Visitor Type)
```java
// Inside Bank.accept() method:
public void accept(IVisitor visitor) {
    visitor.visitBank(this); // Second dispatch based on visitor type AND element type
}
```

When `visitor.visitBank(this)` is called:
- Java looks at the **runtime type** of `visitor`
- Since `visitor` is actually a `TaxAssessmentVisitor`, it calls `TaxAssessmentVisitor.visitBank(Bank)`
- The `this` parameter ensures the visitor receives the correct element type

## Complete Double Dispatch Flow

```java
// Client code
Bank bank = new Bank("Central Bank", 1000000, 800000, 5000);
TaxAssessmentVisitor taxVisitor = new TaxAssessmentVisitor();

bank.accept(taxVisitor);
```

### Detailed Execution Flow:

1. **Client calls**: `bank.accept(taxVisitor)`

2. **First Dispatch**: Java determines that `bank` is of type `Bank`
    - Calls: `Bank.accept(IVisitor visitor)`

3. **Inside Bank.accept()**:
   ```java
   public void accept(IVisitor visitor) {
       visitor.visitBank(this); // 'this' is Bank instance
   }
   ```

4. **Second Dispatch**: Java determines that `visitor` is of type `TaxAssessmentVisitor`
    - Calls: `TaxAssessmentVisitor.visitBank(Bank bank)`

5. **Final Method Execution**:
   ```java
   public void visitBank(Bank bank) {
       // Tax calculation specific to banks
       double profit = bank.getTotalDeposits() - bank.getTotalLoans();
       double tax = profit * 0.25; // 25% tax rate for banks
   }
   ```

## Why Not Just Use instanceof?

### Without Double Dispatch (Bad Approach):
```java
public class OperationProcessor {
    public void processElement(IElement element, String operation) {
        if (element instanceof Bank) {
            Bank bank = (Bank) element;
            if (operation.equals("TAX")) {
                // Tax calculation for bank
            } else if (operation.equals("INSURANCE")) {
                // Insurance calculation for bank
            }
        } else if (element instanceof Company) {
            Company company = (Company) element;
            if (operation.equals("TAX")) {
                // Tax calculation for company
            } else if (operation.equals("INSURANCE")) {
                // Insurance calculation for company
            }
        }
        // This approach violates Open/Closed Principle
    }
}
```

**Problems with instanceof approach:**
- Violates Open/Closed Principle
- Creates long if-else chains
- Difficult to maintain and extend
- No compile-time type safety
- Operations scattered across different classes

### With Double Dispatch (Good Approach):
```java
// Each visitor encapsulates one type of operation
public class TaxAssessmentVisitor implements IVisitor {
    public void visitBank(Bank bank) {
        // Tax logic for banks only
    }
    
    public void visitCompany(Company company) {
        // Tax logic for companies only  
    }
    
    public void visitRestaurant(Restaurant restaurant) {
        // Tax logic for restaurants only
    }
}
```

**Benefits of double dispatch:**
- Follows Open/Closed Principle
- Type-safe method selection
- Operations grouped logically in visitor classes
- Easy to add new operations (new visitors)
- No instanceof checks needed

## Method Selection Matrix

| Element Type ↓ \ Visitor Type → | TaxAssessmentVisitor | InsuranceMessagingVisitor | FinancialAnalysisVisitor |
|----------------------------------|---------------------|--------------------------|-------------------------|
| **Bank**                        | `visitBank()` with tax logic | `visitBank()` with insurance logic | `visitBank()` with analysis logic |
| **Company**                     | `visitCompany()` with tax logic | `visitCompany()` with insurance logic | `visitCompany()` with analysis logic |
| **Restaurant**                  | `visitRestaurant()` with tax logic | `visitRestaurant()` with insurance logic | `visitRestaurant()` with analysis logic |

Each cell represents a **different method implementation** selected through double dispatch.

## Benefits of Double Dispatch in Visitor

### 1. **Type Safety**
- Compile-time guarantee that correct method will be called
- No casting or instanceof checks needed
- Compiler ensures all visitor methods are implemented

### 2. **Extensibility**
- New operations added by creating new visitors
- No modification to existing element classes
- Each visitor can focus on single responsibility

### 3. **Maintainability**
- Related operations grouped together in visitor classes
- Easy to understand and modify specific operation logic
- Clear separation between data (elements) and operations (visitors)

### 4. **Performance**
- No runtime type checking with instanceof
- Direct method dispatch through polymorphism
- Efficient method resolution

## Limitations of Double Dispatch

### 1. **Adding New Element Types**
- Requires adding new method to visitor interface
- All existing visitors must be updated
- Breaks existing visitor implementations

### 2. **Circular Dependencies**
- Visitor interface depends on element types
- Element classes depend on visitor interface
- Can make system harder to understand

### 3. **Access to Element Internals**
- Visitors often need access to element private data
- May require public getters that break encapsulation
- Can create tight coupling between visitors and elements

## Conclusion

Double dispatch in the Visitor pattern provides a powerful mechanism for implementing type-specific operations without violating the Open/Closed Principle. It enables clean separation of algorithms from data structures while maintaining type safety and performance. Understanding double dispatch is crucial for effectively using the Visitor pattern and appreciating its benefits over simpler alternatives like instanceof checks or switch statements.

The key insight is that double dispatch allows method selection based on **two runtime types** rather than just one, enabling more sophisticated and flexible operation selection while maintaining the benefits of polymorphism and type safety.