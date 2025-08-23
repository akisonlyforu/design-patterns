package behavioral.iterator;

import java.util.*;

interface Iterator<T> {
    boolean hasNext();
    T next();
    void reset();
}

// Concrete Iterator with reset functionality
class EmployeeIterator implements Iterator<Employee> {
    private int currentIndex = 0;
    private List<Employee> employees;

    public EmployeeIterator(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public boolean hasNext() {
        return currentIndex < employees.size();
    }

    @Override
    public Employee next() {
        if (!hasNext()) {
            throw new NoSuchElementException("No more employees to iterate");
        }
        return employees.get(currentIndex++);
    }

    @Override
    public void reset() {
        currentIndex = 0;
        System.out.println("Iterator reset to beginning");
    }

    // Additional utility method to get current position
    public int getCurrentPosition() {
        return currentIndex;
    }

    // Method to check if iterator is at beginning
    public boolean isAtBeginning() {
        return currentIndex == 0;
    }
}

// Aggregate interface - Provides a common contract for all collections to create their appropriate iterators
interface Aggregate<T> {
    Iterator<T> createIterator();
}

// Concrete Aggregate
class Company implements Aggregate<Employee> {
    private List<Employee> employees;

    public Company(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public Iterator<Employee> createIterator() {
        return new EmployeeIterator(employees);
    }

    // Additional methods for company operations
    public int getEmployeeCount() {
        return employees.size();
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
}

class Employee {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    @Override
    public String toString() {
        return "Employee{name='" + name + "', salary=" + salary + "}";
    }
}