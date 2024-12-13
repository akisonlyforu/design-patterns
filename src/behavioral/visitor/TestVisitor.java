package behavioral.visitor;

import java.util.*;

public class TestVisitor {
    public static void main(String[] args) {
        System.out.println("=== Testing Visitor Pattern ===\n");

        // Test 1: Basic element creation and visitor acceptance
        System.out.println("Test 1: Basic element creation and visitor acceptance");

        Bank centralBank = new Bank("Central Bank", 1000000, 800000, 5000);
        Company techCorp = new Company("TechCorp", 2000000, 1500000, 150, "Technology");
        Restaurant fineRestaurant = new Restaurant("Fine Dining", 5000, 3000, 20, "French", 85.0);

        InsuranceMessagingVisitor insuranceVisitor = new InsuranceMessagingVisitor();

        System.out.println("✓ Elements created successfully:");
        centralBank.displayInfo();
        techCorp.displayInfo();
        fineRestaurant.displayInfo();

        System.out.println("\nTesting visitor acceptance:");
        centralBank.accept(insuranceVisitor);
        techCorp.accept(insuranceVisitor);
        fineRestaurant.accept(insuranceVisitor);

        insuranceVisitor.showAllMessages();

        // Test 2: Financial System with multiple institutions
        System.out.println("Test 2: Financial System with multiple institutions");

        FinancialSystem financialSystem = new FinancialSystem();

        // Add various institutions
        financialSystem.addInstitution(centralBank);
        financialSystem.addInstitution(techCorp);
        financialSystem.addInstitution(fineRestaurant);
        financialSystem.addInstitution(new Bank("Community Bank", 500000, 400000, 2000));
        financialSystem.addInstitution(new Company("Manufacturing Inc", 1200000, 1000000, 80, "Manufacturing"));
        financialSystem.addInstitution(new Restaurant("Pizza Palace", 1500, 1200, 15, "Italian", 25.0));

        System.out.println("✓ Financial system populated:");
        financialSystem.showSystemStatus();

        // Test 3: Tax Assessment Visitor
        System.out.println("Test 3: Tax Assessment Visitor");

        TaxAssessmentVisitor taxVisitor = new TaxAssessmentVisitor();

        System.out.println("✓ Processing tax assessments for all institutions:");
        financialSystem.acceptVisitor(taxVisitor);

        taxVisitor.showTaxReport();
        System.out.println("Total tax collected: $" + taxVisitor.getTotalTaxCollected());

        // Test 4: Financial Analysis Visitor
        System.out.println("Test 4: Financial Analysis Visitor");

        FinancialAnalysisVisitor analysisVisitor = new FinancialAnalysisVisitor();

        System.out.println("✓ Performing financial analysis on all institutions:");
        financialSystem.acceptVisitor(analysisVisitor);

        analysisVisitor.showReport();

        // Test 5: Multiple visitors on same structure
        System.out.println("Test 5: Multiple visitors on same structure");

        AuditVisitor auditVisitor = new AuditVisitor();

        System.out.println("✓ Running multiple visitors on same financial system:");

        System.out.println("--- Insurance Messaging ---");
        InsuranceMessagingVisitor newInsuranceVisitor = new InsuranceMessagingVisitor();
        financialSystem.acceptVisitor(newInsuranceVisitor);

        System.out.println("--- Audit Process ---");
        financialSystem.acceptVisitor(auditVisitor);

        auditVisitor.showAuditReport();

        // Test 6: Visitor extensibility
        System.out.println("Test 6: Visitor extensibility");

        // Create new visitor type
        class MarketingVisitor implements IVisitor {
            private int campaignsCreated = 0;

            @Override
            public void visitBank(Bank bank) {
                System.out.println("[MarketingVisitor] Creating savings campaign for " + bank.getBankName() +
                        " targeting " + bank.getNumberOfCustomers() + " customers");
                campaignsCreated++;
            }

            @Override
            public void visitCompany(Company company) {
                System.out.println("[MarketingVisitor] Creating B2B campaign for " + company.getCompanyName() +
                        " in " + company.getIndustry() + " industry");
                campaignsCreated++;
            }

            @Override
            public void visitRestaurant(Restaurant restaurant) {
                System.out.println("[MarketingVisitor] Creating food promotion for " + restaurant.getRestaurantName() +
                        " featuring " + restaurant.getCuisineType() + " cuisine");
                campaignsCreated++;
            }

            public int getCampaignsCreated() { return campaignsCreated; }
        }

        MarketingVisitor marketingVisitor = new MarketingVisitor();

        System.out.println("✓ New visitor type added without modifying existing elements:");
        financialSystem.acceptVisitor(marketingVisitor);
        System.out.println("Total marketing campaigns created: " + marketingVisitor.getCampaignsCreated());

        // Test 7: Element extensibility
        System.out.println("\nTest 7: Element extensibility");

        // Add new element type
        class CreditUnion implements IElement {
            private String name;
            private double memberDeposits;
            private int memberCount;

            public CreditUnion(String name, double memberDeposits, int memberCount) {
                this.name = name;
                this.memberDeposits = memberDeposits;
                this.memberCount = memberCount;
            }

            @Override
            public void accept(IVisitor visitor) {
                // For existing visitors, we'd need to extend the visitor interface
                // This demonstrates the limitation of visitor pattern
                System.out.println("[CreditUnion] " + name + " cannot accept current visitors " +
                        "(would need visitor interface extension)");
            }

            public String getName() { return name; }
            public double getMemberDeposits() { return memberDeposits; }
            public int getMemberCount() { return memberCount; }
        }

        CreditUnion creditUnion = new CreditUnion("Community Credit Union", 300000, 800);

        System.out.println("✓ New element type created:");
        System.out.println("Credit Union: " + creditUnion.getName() +
                " | Deposits: $" + creditUnion.getMemberDeposits() +
                " | Members: " + creditUnion.getMemberCount());

        // Demonstrate visitor pattern limitation
        creditUnion.accept(insuranceVisitor);

        // Test 8: Visitor state accumulation
        System.out.println("\nTest 8: Visitor state accumulation");

        // Create visitor that accumulates data across visits
        class StatisticsVisitor implements IVisitor {
            private int totalCustomers = 0;
            private double totalRevenue = 0.0;
            private int totalEmployees = 0;
            private Map<String, Integer> industryCount = new HashMap<>();

            @Override
            public void visitBank(Bank bank) {
                totalCustomers += bank.getNumberOfCustomers();
                totalRevenue += bank.getTotalDeposits(); // Consider deposits as revenue
                industryCount.merge("Banking", 1, Integer::sum);
            }

            @Override
            public void visitCompany(Company company) {
                totalEmployees += company.getNumberOfEmployees();
                totalRevenue += company.getRevenue();
                industryCount.merge(company.getIndustry(), 1, Integer::sum);
            }

            @Override
            public void visitRestaurant(Restaurant restaurant) {
                totalRevenue += restaurant.getDailyRevenue() * 365; // Annualize
                industryCount.merge("Food Service", 1, Integer::sum);
            }

            public void showStatistics() {
                System.out.println("=== System Statistics ===");
                System.out.println("Total Customers: " + totalCustomers);
                System.out.println("Total Employees: " + totalEmployees);
                System.out.println("Total Revenue: $" + String.format("%.2f", totalRevenue));
                System.out.println("Industries: " + industryCount);
                System.out.println("========================");
            }
        }

        StatisticsVisitor statsVisitor = new StatisticsVisitor();

        System.out.println("✓ Visitor accumulating statistics across all visits:");
        financialSystem.acceptVisitor(statsVisitor);
        statsVisitor.showStatistics();

        // Test 9: Visitor comparison and different behaviors
        System.out.println("\nTest 9: Visitor comparison and different behaviors");

        System.out.println("✓ Comparing different visitor behaviors on same elements:");

        // Single bank tested with different visitors
        Bank testBank = new Bank("Test Bank", 750000, 600000, 3000);

        System.out.println("Same bank processed by different visitors:");

        InsuranceMessagingVisitor msgVisitor = new InsuranceMessagingVisitor();
        testBank.accept(msgVisitor);

        TaxAssessmentVisitor taxTestVisitor = new TaxAssessmentVisitor();
        testBank.accept(taxTestVisitor);

        FinancialAnalysisVisitor analysisTestVisitor = new FinancialAnalysisVisitor();
        testBank.accept(analysisTestVisitor);

        System.out.println("Results:");
        System.out.println("Insurance messages: " + msgVisitor.getMessages().size());
        System.out.println("Tax collected: $" + taxTestVisitor.getTotalTaxCollected());
        System.out.println("Assets analyzed: $" + analysisTestVisitor.getTotalAssetsAnalyzed());

        // Test 10: Performance and scalability
        System.out.println("\nTest 10: Performance and scalability");

        // Create large financial system
        FinancialSystem largeSystem = new FinancialSystem();

        System.out.println("✓ Creating large financial system for performance testing:");

        // Add many institutions
        for (int i = 1; i <= 50; i++) {
            largeSystem.addInstitution(new Bank("Bank" + i, 100000 * i, 80000 * i, 1000 + i));
            largeSystem.addInstitution(new Company("Company" + i, 500000 * i, 400000 * i, 50 + i, "Industry" + (i % 5)));
            largeSystem.addInstitution(new Restaurant("Restaurant" + i, 1000 + (i * 10), 800 + (i * 8), 10 + (i % 5), "Cuisine" + (i % 3), 30 + i));
        }

        System.out.println("Large system created with " + largeSystem.getInstitutionCount() + " institutions");

        // Performance test
        long startTime = System.currentTimeMillis();

        TaxAssessmentVisitor perfTaxVisitor = new TaxAssessmentVisitor();
        largeSystem.acceptVisitor(perfTaxVisitor);

        long endTime = System.currentTimeMillis();

        System.out.println("Performance test results:");
        System.out.println("Processed " + largeSystem.getInstitutionCount() + " institutions in " +
                (endTime - startTime) + "ms");
        System.out.println("Total tax calculated: $" + String.format("%.2f", perfTaxVisitor.getTotalTaxCollected()));

        System.out.println("\n=== Test Summary ===");
        System.out.println("Visitor Pattern verified:");
        System.out.println("- Elements accept visitors and delegate operations to them");
        System.out.println("- Visitors implement different operations on same element structure");
        System.out.println("- Double dispatch mechanism works correctly (element type + visitor type)");
        System.out.println("- Operations separated from element classes using visitor objects");
        System.out.println("- New operations can be added by creating new visitors");
        System.out.println("- Visitors can accumulate state across multiple element visits");
        System.out.println("- Same element structure supports multiple different algorithms");
        System.out.println("- Object structure (FinancialSystem) provides unified visitor interface");
        System.out.println("- Elements maintain their core responsibility while visitors handle operations");

        demonstrateVisitorAdvantages();
    }

    private static void demonstrateVisitorAdvantages() {
        System.out.println("\n=== Visitor Pattern Advantages ===");

        System.out.println("1. Open/Closed Principle:");
        System.out.println("   - Can add new operations (visitors) without modifying existing elements");
        System.out.println("   - Elements remain closed for modification but open for extension");

        System.out.println("\n2. Single Responsibility:");
        System.out.println("   - Elements focus on their core data and behavior");
        System.out.println("   - Visitors focus on specific operations/algorithms");
        System.out.println("   - Clean separation of concerns");

        System.out.println("\n3. Algorithm Centralization:");
        System.out.println("   - Related operations grouped together in visitor classes");
        System.out.println("   - Easy to understand and maintain algorithm implementations");
        System.out.println("   - Algorithm variations implemented as different visitors");

        System.out.println("\n4. Double Dispatch:");
        System.out.println("   - Operation selected based on both element type and visitor type");
        System.out.println("   - Enables type-specific behavior without instanceof checks");
        System.out.println("   - Compile-time type safety for operations");

        System.out.println("\nPattern Limitations:");
        System.out.println("- Adding new element types requires modifying all existing visitors");
        System.out.println("- Breaking encapsulation as visitors need access to element internals");
        System.out.println("- Circular dependency between visitor and element hierarchies");

        System.out.println("\nWhen to Use Visitor:");
        System.out.println("- Object structure is stable (elements rarely change)");
        System.out.println("- Need to perform many different operations on object structure");
        System.out.println("- Operations are unrelated to element classes");
        System.out.println("- Want to gather related operations in one place");

        System.out.println("\nReal-World Examples:");
        System.out.println("- Compiler Design (AST processing with different visitors)");
        System.out.println("- Document Processing (export to different formats)");
        System.out.println("- Graphics Applications (rendering, hit-testing, bounding box calculation)");
        System.out.println("- File System Operations (copy, delete, compress operations)");
        System.out.println("- Game Development (AI behavior, rendering, collision detection)");

        System.out.println("\nVisitor vs Other Patterns:");
        System.out.println("- Visitor vs Command: Visitor operates on existing objects, Command encapsulates requests");
        System.out.println("- Visitor vs Strategy: Visitor changes operations on objects, Strategy changes object behavior");
        System.out.println("- Visitor vs Observer: Visitor performs operations, Observer handles notifications");
    }
}
