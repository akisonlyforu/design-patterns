package behavioral.visitor;

import java.util.*;

/**
 * Visitor interface - declares set of visiting methods for concrete elements
 * Each method corresponds to different element type in object structure
 */
interface IVisitor {
    void visitBank(Bank bank);
    void visitCompany(Company company);
    void visitRestaurant(Restaurant restaurant);
}

/**
 * Element interface - declares accept method that takes visitor
 * All concrete elements must implement this interface
 */
interface IElement {
    void accept(IVisitor visitor);
}

/**
 * Bank element - represents a bank in the financial system
 */
class Bank implements IElement {
    private String bankName;
    private double totalDeposits;
    private double totalLoans;
    private int numberOfCustomers;

    public Bank(String bankName, double totalDeposits, double totalLoans, int numberOfCustomers) {
        this.bankName = bankName;
        this.totalDeposits = totalDeposits;
        this.totalLoans = totalLoans;
        this.numberOfCustomers = numberOfCustomers;
    }

    @Override
    public void accept(IVisitor visitor) {
        System.out.println("[Bank] " + bankName + " accepting visitor");
        visitor.visitBank(this);
    }

    // Getters for visitor access
    public String getBankName() { return bankName; }
    public double getTotalDeposits() { return totalDeposits; }
    public double getTotalLoans() { return totalLoans; }
    public int getNumberOfCustomers() { return numberOfCustomers; }

    public void displayInfo() {
        System.out.println("Bank: " + bankName +
                " | Deposits: $" + totalDeposits +
                " | Loans: $" + totalLoans +
                " | Customers: " + numberOfCustomers);
    }
}

/**
 * Company element - represents a company in the financial system
 */
class Company implements IElement {
    private String companyName;
    private double revenue;
    private double expenses;
    private int numberOfEmployees;
    private String industry;

    public Company(String companyName, double revenue, double expenses,
                   int numberOfEmployees, String industry) {
        this.companyName = companyName;
        this.revenue = revenue;
        this.expenses = expenses;
        this.numberOfEmployees = numberOfEmployees;
        this.industry = industry;
    }

    @Override
    public void accept(IVisitor visitor) {
        System.out.println("[Company] " + companyName + " accepting visitor");
        visitor.visitCompany(this);
    }

    // Getters for visitor access
    public String getCompanyName() { return companyName; }
    public double getRevenue() { return revenue; }
    public double getExpenses() { return expenses; }
    public int getNumberOfEmployees() { return numberOfEmployees; }
    public String getIndustry() { return industry; }

    public double getProfit() {
        return revenue - expenses;
    }

    public void displayInfo() {
        System.out.println("Company: " + companyName +
                " | Revenue: $" + revenue +
                " | Expenses: $" + expenses +
                " | Profit: $" + getProfit() +
                " | Employees: " + numberOfEmployees +
                " | Industry: " + industry);
    }
}

/**
 * Restaurant element - represents a restaurant in the financial system
 */
class Restaurant implements IElement {
    private String restaurantName;
    private double dailyRevenue;
    private double operatingCosts;
    private int numberOfTables;
    private String cuisineType;
    private double averageOrderValue;

    public Restaurant(String restaurantName, double dailyRevenue, double operatingCosts,
                      int numberOfTables, String cuisineType, double averageOrderValue) {
        this.restaurantName = restaurantName;
        this.dailyRevenue = dailyRevenue;
        this.operatingCosts = operatingCosts;
        this.numberOfTables = numberOfTables;
        this.cuisineType = cuisineType;
        this.averageOrderValue = averageOrderValue;
    }

    @Override
    public void accept(IVisitor visitor) {
        System.out.println("[Restaurant] " + restaurantName + " accepting visitor");
        visitor.visitRestaurant(this);
    }

    // Getters for visitor access
    public String getRestaurantName() { return restaurantName; }
    public double getDailyRevenue() { return dailyRevenue; }
    public double getOperatingCosts() { return operatingCosts; }
    public int getNumberOfTables() { return numberOfTables; }
    public String getCuisineType() { return cuisineType; }
    public double getAverageOrderValue() { return averageOrderValue; }

    public double getDailyProfit() {
        return dailyRevenue - operatingCosts;
    }

    public void displayInfo() {
        System.out.println("Restaurant: " + restaurantName +
                " | Daily Revenue: $" + dailyRevenue +
                " | Operating Costs: $" + operatingCosts +
                " | Daily Profit: $" + getDailyProfit() +
                " | Tables: " + numberOfTables +
                " | Cuisine: " + cuisineType +
                " | Avg Order: $" + averageOrderValue);
    }
}

/**
 * Insurance Messaging Visitor - sends insurance messages to different institutions
 */
class InsuranceMessagingVisitor implements IVisitor {
    private List<String> messages;

    public InsuranceMessagingVisitor() {
        this.messages = new ArrayList<>();
    }

    @Override
    public void visitBank(Bank bank) {
        String message = "Dear " + bank.getBankName() +
                ", we offer comprehensive banking insurance covering " +
                bank.getNumberOfCustomers() + " customers and $" +
                bank.getTotalDeposits() + " in deposits.";

        messages.add(message);
        System.out.println("[InsuranceVisitor] Bank message: " + message);
    }

    @Override
    public void visitCompany(Company company) {
        String message = "Dear " + company.getCompanyName() +
                ", protect your " + company.getIndustry() +
                " business with our corporate insurance. Coverage for " +
                company.getNumberOfEmployees() + " employees and $" +
                company.getRevenue() + " annual revenue.";

        messages.add(message);
        System.out.println("[InsuranceVisitor] Company message: " + message);
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        String message = "Dear " + restaurant.getRestaurantName() +
                ", secure your " + restaurant.getCuisineType() +
                " restaurant with our hospitality insurance. Coverage for " +
                restaurant.getNumberOfTables() + " tables and $" +
                restaurant.getDailyRevenue() + " daily operations.";

        messages.add(message);
        System.out.println("[InsuranceVisitor] Restaurant message: " + message);
    }

    public List<String> getMessages() {
        return new ArrayList<>(messages);
    }

    public void showAllMessages() {
        System.out.println("\n=== Insurance Messages Generated ===");
        for (int i = 0; i < messages.size(); i++) {
            System.out.println((i + 1) + ". " + messages.get(i));
        }
        System.out.println("=======================================\n");
    }
}

/**
 * Tax Assessment Visitor - calculates taxes for different institution types
 */
class TaxAssessmentVisitor implements IVisitor {
    private double totalTaxCollected;
    private Map<String, Double> taxBreakdown;

    public TaxAssessmentVisitor() {
        this.totalTaxCollected = 0.0;
        this.taxBreakdown = new HashMap<>();
    }

    @Override
    public void visitBank(Bank bank) {
        // Banks taxed on profit (deposits - loans) at 25%
        double profit = bank.getTotalDeposits() - bank.getTotalLoans();
        double tax = profit * 0.25;

        totalTaxCollected += tax;
        taxBreakdown.put(bank.getBankName(), tax);

        System.out.println("[TaxVisitor] Bank " + bank.getBankName() +
                " - Profit: $" + profit + " | Tax (25%): $" + tax);
    }

    @Override
    public void visitCompany(Company company) {
        // Companies taxed on profit at 30%
        double profit = company.getProfit();
        double tax = profit * 0.30;

        totalTaxCollected += tax;
        taxBreakdown.put(company.getCompanyName(), tax);

        System.out.println("[TaxVisitor] Company " + company.getCompanyName() +
                " - Profit: $" + profit + " | Tax (30%): $" + tax);
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        // Restaurants taxed on daily profit * 365 at 20%
        double annualProfit = restaurant.getDailyProfit() * 365;
        double tax = annualProfit * 0.20;

        totalTaxCollected += tax;
        taxBreakdown.put(restaurant.getRestaurantName(), tax);

        System.out.println("[TaxVisitor] Restaurant " + restaurant.getRestaurantName() +
                " - Annual Profit: $" + annualProfit + " | Tax (20%): $" + tax);
    }

    public double getTotalTaxCollected() {
        return totalTaxCollected;
    }

    public void showTaxReport() {
        System.out.println("\n=== Tax Assessment Report ===");
        for (Map.Entry<String, Double> entry : taxBreakdown.entrySet()) {
            System.out.println(entry.getKey() + ": $" + entry.getValue());
        }
        System.out.println("Total Tax Collected: $" + totalTaxCollected);
        System.out.println("============================\n");
    }
}

/**
 * Financial Analysis Visitor - performs detailed financial analysis
 */
class FinancialAnalysisVisitor implements IVisitor {
    private StringBuilder analysisReport;
    private double totalAssetsAnalyzed;

    public FinancialAnalysisVisitor() {
        this.analysisReport = new StringBuilder();
        this.totalAssetsAnalyzed = 0.0;
    }

    @Override
    public void visitBank(Bank bank) {
        analysisReport.append("BANK ANALYSIS - ").append(bank.getBankName()).append(":\n");

        double loanToDepositRatio = bank.getTotalLoans() / bank.getTotalDeposits();
        double assetsPerCustomer = bank.getTotalDeposits() / bank.getNumberOfCustomers();

        analysisReport.append("  Loan-to-Deposit Ratio: ").append(String.format("%.2f", loanToDepositRatio)).append("\n");
        analysisReport.append("  Assets per Customer: $").append(String.format("%.2f", assetsPerCustomer)).append("\n");

        if (loanToDepositRatio > 0.8) {
            analysisReport.append("  Risk Level: HIGH (loan ratio > 80%)\n");
        } else if (loanToDepositRatio > 0.6) {
            analysisReport.append("  Risk Level: MEDIUM\n");
        } else {
            analysisReport.append("  Risk Level: LOW\n");
        }

        totalAssetsAnalyzed += bank.getTotalDeposits();
        analysisReport.append("\n");

        System.out.println("[FinancialVisitor] Bank analysis completed for " + bank.getBankName());
    }

    @Override
    public void visitCompany(Company company) {
        analysisReport.append("COMPANY ANALYSIS - ").append(company.getCompanyName()).append(":\n");

        double profitMargin = (company.getProfit() / company.getRevenue()) * 100;
        double revenuePerEmployee = company.getRevenue() / company.getNumberOfEmployees();

        analysisReport.append("  Profit Margin: ").append(String.format("%.2f", profitMargin)).append("%\n");
        analysisReport.append("  Revenue per Employee: $").append(String.format("%.2f", revenuePerEmployee)).append("\n");
        analysisReport.append("  Industry: ").append(company.getIndustry()).append("\n");

        if (profitMargin > 20) {
            analysisReport.append("  Performance: EXCELLENT\n");
        } else if (profitMargin > 10) {
            analysisReport.append("  Performance: GOOD\n");
        } else if (profitMargin > 0) {
            analysisReport.append("  Performance: AVERAGE\n");
        } else {
            analysisReport.append("  Performance: POOR (LOSS)\n");
        }

        totalAssetsAnalyzed += company.getRevenue();
        analysisReport.append("\n");

        System.out.println("[FinancialVisitor] Company analysis completed for " + company.getCompanyName());
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        analysisReport.append("RESTAURANT ANALYSIS - ").append(restaurant.getRestaurantName()).append(":\n");

        double dailyProfitMargin = (restaurant.getDailyProfit() / restaurant.getDailyRevenue()) * 100;
        double revenuePerTable = restaurant.getDailyRevenue() / restaurant.getNumberOfTables();
        double annualRevenue = restaurant.getDailyRevenue() * 365;

        analysisReport.append("  Daily Profit Margin: ").append(String.format("%.2f", dailyProfitMargin)).append("%\n");
        analysisReport.append("  Revenue per Table: $").append(String.format("%.2f", revenuePerTable)).append("\n");
        analysisReport.append("  Projected Annual Revenue: $").append(String.format("%.2f", annualRevenue)).append("\n");
        analysisReport.append("  Cuisine Type: ").append(restaurant.getCuisineType()).append("\n");
        analysisReport.append("  Average Order Value: $").append(restaurant.getAverageOrderValue()).append("\n");

        if (dailyProfitMargin > 15) {
            analysisReport.append("  Status: HIGHLY PROFITABLE\n");
        } else if (dailyProfitMargin > 5) {
            analysisReport.append("  Status: PROFITABLE\n");
        } else if (dailyProfitMargin > 0) {
            analysisReport.append("  Status: BREAK-EVEN\n");
        } else {
            analysisReport.append("  Status: LOSING MONEY\n");
        }

        totalAssetsAnalyzed += annualRevenue;
        analysisReport.append("\n");

        System.out.println("[FinancialVisitor] Restaurant analysis completed for " + restaurant.getRestaurantName());
    }

    public String getAnalysisReport() {
        return analysisReport.toString();
    }

    public double getTotalAssetsAnalyzed() {
        return totalAssetsAnalyzed;
    }

    public void showReport() {
        System.out.println("\n=== Financial Analysis Report ===");
        System.out.println(analysisReport.toString());
        System.out.println("Total Assets Analyzed: $" + String.format("%.2f", totalAssetsAnalyzed));
        System.out.println("================================\n");
    }
}

/**
 * Audit Visitor - performs compliance and audit checks
 */
class AuditVisitor implements IVisitor {
    private List<String> auditFindings;
    private int totalInstitutionsAudited;

    public AuditVisitor() {
        this.auditFindings = new ArrayList<>();
        this.totalInstitutionsAudited = 0;
    }

    @Override
    public void visitBank(Bank bank) {
        System.out.println("[AuditVisitor] Auditing bank: " + bank.getBankName());

        // Bank-specific audit checks
        double loanToDepositRatio = bank.getTotalLoans() / bank.getTotalDeposits();

        if (loanToDepositRatio > 0.9) {
            auditFindings.add("CRITICAL: " + bank.getBankName() +
                    " has excessive loan-to-deposit ratio: " +
                    String.format("%.2f", loanToDepositRatio));
        }

        if (bank.getNumberOfCustomers() < 100) {
            auditFindings.add("WARNING: " + bank.getBankName() +
                    " has low customer base: " + bank.getNumberOfCustomers());
        }

        auditFindings.add("INFO: " + bank.getBankName() + " audit completed - " +
                auditFindings.size() + " findings");

        totalInstitutionsAudited++;
    }

    @Override
    public void visitCompany(Company company) {
        System.out.println("[AuditVisitor] Auditing company: " + company.getCompanyName());

        // Company-specific audit checks
        if (company.getProfit() < 0) {
            auditFindings.add("CRITICAL: " + company.getCompanyName() +
                    " is operating at a loss: $" + company.getProfit());
        }

        double revenuePerEmployee = company.getRevenue() / company.getNumberOfEmployees();
        if (revenuePerEmployee < 50000) {
            auditFindings.add("WARNING: " + company.getCompanyName() +
                    " has low revenue per employee: $" +
                    String.format("%.2f", revenuePerEmployee));
        }

        auditFindings.add("INFO: " + company.getCompanyName() + " audit completed");
        totalInstitutionsAudited++;
    }

    @Override
    public void visitRestaurant(Restaurant restaurant) {
        System.out.println("[AuditVisitor] Auditing restaurant: " + restaurant.getRestaurantName());

        // Restaurant-specific audit checks
        if (restaurant.getDailyProfit() < 0) {
            auditFindings.add("CRITICAL: " + restaurant.getRestaurantName() +
                    " has negative daily profit: $" + restaurant.getDailyProfit());
        }

        double revenuePerTable = restaurant.getDailyRevenue() / restaurant.getNumberOfTables();
        if (revenuePerTable < 100) {
            auditFindings.add("WARNING: " + restaurant.getRestaurantName() +
                    " has low revenue per table: $" +
                    String.format("%.2f", revenuePerTable));
        }

        if (restaurant.getAverageOrderValue() < 15) {
            auditFindings.add("INFO: " + restaurant.getRestaurantName() +
                    " has low average order value: $" + restaurant.getAverageOrderValue());
        }

        auditFindings.add("INFO: " + restaurant.getRestaurantName() + " audit completed");
        totalInstitutionsAudited++;
    }

    public void showAuditReport() {
        System.out.println("\n=== Audit Report ===");
        System.out.println("Institutions Audited: " + totalInstitutionsAudited);
        System.out.println("Total Findings: " + auditFindings.size());
        System.out.println("\nFindings:");

        for (int i = 0; i < auditFindings.size(); i++) {
            System.out.println((i + 1) + ". " + auditFindings.get(i));
        }
        System.out.println("===================\n");
    }

    public List<String> getAuditFindings() {
        return new ArrayList<>(auditFindings);
    }

    public int getTotalInstitutionsAudited() {
        return totalInstitutionsAudited;
    }
}

// ============================================
// OBJECT STRUCTURE
// ============================================

/**
 * Financial System - represents collection of financial institutions
 * Provides methods to add institutions and accept visitors
 */
class FinancialSystem {
    private List<IElement> institutions;

    public FinancialSystem() {
        this.institutions = new ArrayList<>();
    }

    public void addInstitution(IElement institution) {
        institutions.add(institution);

        String institutionType = institution.getClass().getSimpleName();
        String institutionName = "";

        if (institution instanceof Bank) {
            institutionName = ((Bank) institution).getBankName();
        } else if (institution instanceof Company) {
            institutionName = ((Company) institution).getCompanyName();
        } else if (institution instanceof Restaurant) {
            institutionName = ((Restaurant) institution).getRestaurantName();
        }

        System.out.println("[FinancialSystem] Added " + institutionType + ": " + institutionName);
    }

    public void removeInstitution(IElement institution) {
        institutions.remove(institution);
        System.out.println("[FinancialSystem] Institution removed from system");
    }

    public void acceptVisitor(IVisitor visitor) {
        System.out.println("\n[FinancialSystem] Processing visitor: " + visitor.getClass().getSimpleName());
        System.out.println("Visiting " + institutions.size() + " institutions...\n");

        for (IElement institution : institutions) {
            institution.accept(visitor);
        }

        System.out.println("[FinancialSystem] Visitor processing completed\n");
    }

    public void showSystemStatus() {
        System.out.println("\n=== Financial System Status ===");
        System.out.println("Total Institutions: " + institutions.size());

        int banks = 0, companies = 0, restaurants = 0;
        for (IElement institution : institutions) {
            if (institution instanceof Bank) banks++;
            else if (institution instanceof Company) companies++;
            else if (institution instanceof Restaurant) restaurants++;
        }

        System.out.println("Banks: " + banks + " | Companies: " + companies + " | Restaurants: " + restaurants);

        System.out.println("\nInstitution Details:");
        for (IElement institution : institutions) {
            if (institution instanceof Bank) {
                ((Bank) institution).displayInfo();
            } else if (institution instanceof Company) {
                ((Company) institution).displayInfo();
            } else if (institution instanceof Restaurant) {
                ((Restaurant) institution).displayInfo();
            }
        }
        System.out.println("==============================\n");
    }

    public int getInstitutionCount() {
        return institutions.size();
    }

    public List<IElement> getInstitutions() {
        return new ArrayList<>(institutions);
    }
}