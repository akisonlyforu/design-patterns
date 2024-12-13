package behavioral.iterator;

import java.util.*;

public class TestIterator {
    public static void main(String[] args) {
        System.out.println("=== Iterator Pattern Demo - Employee Management ===\n");

        // Create employees
        List<Employee> employeeList = Arrays.asList(
                new Employee("Alice Johnson", 75000),
                new Employee("Bob Smith", 82000),
                new Employee("Carol Davis", 68000),
                new Employee("David Wilson", 91000),
                new Employee("Eva Brown", 77500)
        );

        // Create company aggregate
        Company company = new Company(new ArrayList<>(employeeList));
        System.out.println("Company created with " + company.getEmployeeCount() + " employees\n");

        // Test 1: Basic iteration
        System.out.println("1. Basic Iterator Usage:");
        System.out.println("------------------------");
        Iterator<Employee> iterator = company.createIterator();

        while (iterator.hasNext()) {
            Employee emp = iterator.next();
            System.out.println("Processing: " + emp);
        }

        // Test 2: Reset functionality
        System.out.println("\n2. Testing Reset Functionality:");
        System.out.println("-------------------------------");
        EmployeeIterator empIterator = (EmployeeIterator) company.createIterator();

        System.out.println("Current position: " + empIterator.getCurrentPosition());
        System.out.println("Is at beginning: " + empIterator.isAtBeginning());

        // Move through a few employees
        System.out.println("\nIterating through first 3 employees:");
        for (int i = 0; i < 3 && empIterator.hasNext(); i++) {
            Employee emp = empIterator.next();
            System.out.println("  " + (i + 1) + ". " + emp.getName());
        }

        System.out.println("Current position after iteration: " + empIterator.getCurrentPosition());
        System.out.println("Is at beginning: " + empIterator.isAtBeginning());

        // Reset and iterate again
        empIterator.reset();
        System.out.println("Current position after reset: " + empIterator.getCurrentPosition());
        System.out.println("Is at beginning: " + empIterator.isAtBeginning());

        // Test 3: Multiple iterators
        System.out.println("\n3. Testing Multiple Independent Iterators:");
        System.out.println("------------------------------------------");
        Iterator<Employee> iterator1 = company.createIterator();
        Iterator<Employee> iterator2 = company.createIterator();

        System.out.println("Iterator 1 - First employee: " + iterator1.next().getName());
        System.out.println("Iterator 2 - First employee: " + iterator2.next().getName());
        System.out.println("Iterator 1 - Second employee: " + iterator1.next().getName());
        System.out.println("Iterator 2 - Second employee: " + iterator2.next().getName());

        // Test 4: Salary filtering during iteration
        System.out.println("\n4. Filtering High-Salary Employees (>$80k):");
        System.out.println("--------------------------------------------");
        Iterator<Employee> salaryIterator = company.createIterator();

        while (salaryIterator.hasNext()) {
            Employee emp = salaryIterator.next();
            if (emp.getSalary() > 80000) {
                System.out.println("High earner: " + emp);
            }
        }

        // Test 5: Adding new employee and iterating
        System.out.println("\n5. Dynamic Collection - Adding New Employee:");
        System.out.println("--------------------------------------------");
        company.addEmployee(new Employee("Frank Miller", 95000));
        System.out.println("Added new employee. Total count: " + company.getEmployeeCount());

        Iterator<Employee> newIterator = company.createIterator();
        System.out.println("Iterating through updated employee list:");
        int count = 1;
        while (newIterator.hasNext()) {
            Employee emp = newIterator.next();
            System.out.println("  " + count + ". " + emp.getName() + " - $" + emp.getSalary());
            count++;
        }

        // Test 6: Exception handling
        System.out.println("\n6. Testing Iterator Bounds:");
        System.out.println("---------------------------");
        Iterator<Employee> boundIterator = company.createIterator();

        // Consume all elements
        while (boundIterator.hasNext()) {
            boundIterator.next();
        }

        // Try to get next element when none available
        try {
            System.out.println("Attempting to get next element when iterator is exhausted...");
            boundIterator.next();
        } catch (NoSuchElementException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // Test 7: Performance comparison
        System.out.println("\n7. Performance Comparison:");
        System.out.println("--------------------------");

        // Create larger dataset for performance test
        List<Employee> largeEmployeeList = new ArrayList<>();
        for (int i = 1; i <= 1000; i++) {
            largeEmployeeList.add(new Employee("Employee" + i, 50000 + (i * 10)));
        }
        Company largeCompany = new Company(largeEmployeeList);

        // Test iterator performance
        long startTime = System.nanoTime();
        Iterator<Employee> perfIterator = largeCompany.createIterator();
        int employeeCount = 0;
        while (perfIterator.hasNext()) {
            perfIterator.next();
            employeeCount++;
        }
        long endTime = System.nanoTime();

        System.out.println("Iterated through " + employeeCount + " employees");
        System.out.println("Time taken: " + (endTime - startTime) / 1_000_000.0 + " ms");

        // Test 8: Iterator pattern benefits demonstration
        System.out.println("\n8. Iterator Pattern Benefits:");
        System.out.println("-----------------------------");
        demonstrateEncapsulation(company);
        demonstrateUniformInterface(company);

        System.out.println("\n=== Iterator Pattern Demo Complete ===");
    }

    // Helper method to demonstrate encapsulation
    private static void demonstrateEncapsulation(Company company) {
        System.out.println("Encapsulation: Client doesn't need to know internal structure");
        Iterator<Employee> iterator = company.createIterator();

        // Client just uses hasNext() and next() - doesn't care about List, Array, etc.
        System.out.println("Processing employees without knowing collection type:");
        int processed = 0;
        while (iterator.hasNext() && processed < 2) {
            Employee emp = iterator.next();
            System.out.println("  - " + emp.getName());
            processed++;
        }
    }

    // Helper method to demonstrate uniform interface
    private static void demonstrateUniformInterface(Company company) {
        System.out.println("\nUniform Interface: Same iteration pattern regardless of collection");

        // Same iteration code works for any collection that implements Aggregate
        processCollection(company);

        // Could work with other aggregate implementations too
        System.out.println("  (Would work identically with Department, Team, etc.)");
    }

    // Generic method that works with any Aggregate
    private static void processCollection(Aggregate<Employee> aggregate) {
        Iterator<Employee> iterator = aggregate.createIterator();
        System.out.println("  Processing collection using uniform interface:");

        int count = 0;
        while (iterator.hasNext() && count < 2) {
            Employee emp = iterator.next();
            System.out.println("    → " + emp.getName());
            count++;
        }
    }
}