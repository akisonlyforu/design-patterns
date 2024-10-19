package creational.object_pool;

import java.util.*;

// Reusable object interface
interface Reusable {
    // When an object is returned to the pool, it may contain state from its previous use.
    // Without resetting, the next user would get a "dirty" object with leftover data.
    void reset();
}

// Expensive object that will be pooled
class ExpensiveObject implements Reusable {
    private boolean inUse;

    public ExpensiveObject() {
        this.inUse = false;

        // Simulate expensive object creation
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        System.out.println("Expensive object created");
    }

    public void reset() {
        this.inUse = false;
        System.out.println("Object reset for reuse");
    }

    public boolean isInUse() {
        return inUse;
    }

    public void setInUse(boolean inUse) {
        this.inUse = inUse;
    }

    public void doWork() {
        if (!inUse) {
            throw new IllegalStateException("Object not acquired from pool");
        }
        System.out.println("Doing expensive work...");
    }
}

// Object pool class
class ObjectPool<T extends Reusable> {
    private List<T> available = new ArrayList<>();
    private List<T> inUse = new ArrayList<>();

    // Factory method to create new objects
    private ObjectFactory<T> factory;

    public ObjectPool(ObjectFactory<T> factory) {
        this.factory = factory;
    }

    // Get object from pool
    public T acquire() {
        T obj;

        if (available.isEmpty()) {
            // Create new object if pool is empty
            obj = factory.create();
        } else {
            // Reuse existing object
            obj = available.remove(available.size() - 1);
            System.out.println("Reusing object from pool");
        }

        if (obj instanceof ExpensiveObject) {
            ((ExpensiveObject) obj).setInUse(true);
        }
        inUse.add(obj);
        return obj;
    }

    // Return object to pool
    public void release(T obj) {
        if (inUse.remove(obj)) {
            obj.reset();
            available.add(obj);
            System.out.println("Object returned to pool");
        }
    }

    // Get pool status
    public void showStats() {
        System.out.println("Pool - Available: " + available.size() + ", In Use: " + inUse.size());
    }
}

// Factory interface for creating objects
interface ObjectFactory<T> {
    T create();
}