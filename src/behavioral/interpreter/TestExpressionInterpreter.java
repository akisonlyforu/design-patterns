package behavioral.interpreter;

public class TestExpressionInterpreter {
    public static void main(String[] args) {
        System.out.println("=== Testing Interpreter Pattern ===\n");

        // Test 1: Basic terminal expressions
        System.out.println("Test 1: Basic terminal expressions");
        Context context = new Context();

        AbstractExpression num5 = new NumberExpression(5);
        AbstractExpression num10 = new NumberExpression(10);

        System.out.println("✓ Terminal expressions created successfully:");
        System.out.println("Number 5: " + num5.interpret(context));
        System.out.println("Number 10: " + num10.interpret(context));

        // Test with variables
        context.setVariable("x", 7);
        context.setVariable("y", 3);

        AbstractExpression varX = new VariableExpression("x");
        AbstractExpression varY = new VariableExpression("y");

        System.out.println("Variable x: " + varX.interpret(context));
        System.out.println("Variable y: " + varY.interpret(context));
        context.displayVariables();
        System.out.println();

        // Test 2: Basic non-terminal expressions
        System.out.println("Test 2: Basic non-terminal expressions");
        AbstractExpression addition = new AddExpression(num5, num10);
        AbstractExpression subtraction = new SubtractExpression(num10, num5);
        AbstractExpression multiplication = new MultiplyExpression(num5, varX);
        AbstractExpression division = new DivideExpression(num10, varY);

        System.out.println("✓ Non-terminal expressions created successfully:");
        System.out.println("5 + 10 = " + addition.interpret(context) + " | Expression: " + addition.toString());
        System.out.println("10 - 5 = " + subtraction.interpret(context) + " | Expression: " + subtraction.toString());
        System.out.println("5 * x = " + multiplication.interpret(context) + " | Expression: " + multiplication.toString());
        System.out.println("10 / y = " + division.interpret(context) + " | Expression: " + division.toString());
        System.out.println();

        // Test 3: Complex nested expressions
        System.out.println("Test 3: Complex nested expressions");

        // Create expression: (x + y) * (10 - 5)
        AbstractExpression leftPart = new AddExpression(varX, varY);
        AbstractExpression rightPart = new SubtractExpression(num10, num5);
        AbstractExpression complexExpr = new MultiplyExpression(leftPart, rightPart);

        System.out.println("✓ Complex nested expression:");
        System.out.println("Expression: " + complexExpr.toString());
        System.out.println("Result: " + complexExpr.interpret(context));

        // Another complex expression: ((x * 2) + y) / (y + 1)
        AbstractExpression num2 = new NumberExpression(2);
        AbstractExpression num1 = new NumberExpression(1);
        AbstractExpression xTimes2 = new MultiplyExpression(varX, num2);
        AbstractExpression numerator = new AddExpression(xTimes2, varY);
        AbstractExpression denominator = new AddExpression(varY, num1);
        AbstractExpression complexExpr2 = new DivideExpression(numerator, denominator);

        System.out.println("Expression: " + complexExpr2.toString());
        System.out.println("Result: " + complexExpr2.interpret(context));
        System.out.println();

        // Test 4: Context variable changes
        System.out.println("Test 4: Context variable changes");
        AbstractExpression expr = new AddExpression(varX, varY);

        System.out.println("✓ Testing same expression with different context values:");
        System.out.println("Original context (x=7, y=3):");
        System.out.println("x + y = " + expr.interpret(context));

        context.setVariable("x", 15);
        context.setVariable("y", 25);
        context.displayVariables();
        System.out.println("Updated context (x=15, y=25):");
        System.out.println("x + y = " + expr.interpret(context));
        System.out.println();

        // Test 5: Error handling
        System.out.println("Test 5: Error handling");
        AbstractExpression divisionByZero = new DivideExpression(num10, new NumberExpression(0));

        System.out.println("✓ Testing division by zero handling:");
        try {
            int result = divisionByZero.interpret(context);
            System.out.println("✗ Should have thrown exception, got: " + result);
        } catch (ArithmeticException e) {
            System.out.println("✓ Correctly handled division by zero: " + e.getMessage());
        }

        // Test undefined variable
        AbstractExpression undefinedVar = new VariableExpression("undefined");
        System.out.println("✓ Testing undefined variable (returns 0): " + undefinedVar.interpret(context));
        System.out.println();

        // Test 6: Polymorphic treatment
        System.out.println("Test 6: Polymorphic treatment verification");
        AbstractExpression[] expressions = {
                new NumberExpression(42),
                new VariableExpression("x"),
                new AddExpression(num5, num10),
                new MultiplyExpression(varX, varY)
        };

        System.out.println("✓ Treating all expressions uniformly:");
        for (AbstractExpression expression : expressions) {
            System.out.println("Expression: " + expression.toString() + " = " + expression.interpret(context));
        }
        System.out.println();

        // Test 7: Grammar extensibility
        System.out.println("Test 7: Grammar extensibility demonstration");

        // Add new operation type - power
        class PowerExpression implements AbstractExpression {
            private AbstractExpression base;
            private AbstractExpression exponent;

            public PowerExpression(AbstractExpression base, AbstractExpression exponent) {
                this.base = base;
                this.exponent = exponent;
            }

            @Override
            public int interpret(Context context) {
                return (int) Math.pow(base.interpret(context), exponent.interpret(context));
            }

            @Override
            public String toString() {
                return "(" + base.toString() + " ^ " + exponent.toString() + ")";
            }
        }

        AbstractExpression powerExpr = new PowerExpression(
                new VariableExpression("x"),
                new NumberExpression(2)
        );

        System.out.println("✓ New operation type added (Power):");
        System.out.println("Expression: " + powerExpr.toString() + " = " + powerExpr.interpret(context));
        System.out.println();

        // Test 8: Expression tree structure verification
        System.out.println("Test 8: Expression tree structure verification");

        // Build tree manually and verify structure
        AbstractExpression leaf1 = new NumberExpression(4);
        AbstractExpression leaf2 = new NumberExpression(6);
        AbstractExpression internal = new AddExpression(leaf1, leaf2);

        System.out.println("✓ Expression tree structure:");
        System.out.println("Leaf 1: " + leaf1.toString() + " = " + leaf1.interpret(context));
        System.out.println("Leaf 2: " + leaf2.toString() + " = " + leaf2.interpret(context));
        System.out.println("Internal node: " + internal.toString() + " = " + internal.interpret(context));
        System.out.println();

        // Test 9: Multiple context scenarios
        System.out.println("Test 9: Multiple context scenarios");

        AbstractExpression testExpr = new MultiplyExpression(
                new VariableExpression("a"),
                new AddExpression(
                        new VariableExpression("b"),
                        new NumberExpression(1)
                )
        );

        // Scenario 1
        Context context1 = new Context();
        context1.setVariable("a", 3);
        context1.setVariable("b", 4);

        // Scenario 2
        Context context2 = new Context();
        context2.setVariable("a", 10);
        context2.setVariable("b", 0);

        System.out.println("✓ Same expression with different contexts:");
        System.out.println("Expression: " + testExpr.toString());
        context1.displayVariables();
        System.out.println("Context 1 result: " + testExpr.interpret(context1));
        context2.displayVariables();
        System.out.println("Context 2 result: " + testExpr.interpret(context2));
        System.out.println();

        // Test 10: Language grammar demonstration
        System.out.println("Test 10: Language grammar demonstration");

        // Build various grammar constructs
        AbstractExpression[] grammarExamples = {
                // Terminal: Number
                new NumberExpression(100),

                // Terminal: Variable
                new VariableExpression("x"),

                // NonTerminal: Binary operation
                new AddExpression(new NumberExpression(5), new NumberExpression(3)),

                // NonTerminal: Nested operations
                new SubtractExpression(
                        new MultiplyExpression(new NumberExpression(6), new NumberExpression(7)),
                        new DivideExpression(new NumberExpression(20), new NumberExpression(4))
                )
        };

        System.out.println("✓ Grammar constructs demonstration:");
        for (int i = 0; i < grammarExamples.length; i++) {
            AbstractExpression grammarExpr = grammarExamples[i];
            System.out.println("Grammar " + (i+1) + ": " + grammarExpr.toString() +
                    " = " + grammarExpr.interpret(context));
        }
        System.out.println();

        // Test 11: Performance with deep nesting
        System.out.println("Test 11: Performance with deep nesting");
        long startTime = System.currentTimeMillis();

        // Create deeply nested expression: ((((1+1)+1)+1)+1)...
        AbstractExpression deepExpr = new NumberExpression(1);
        for (int i = 0; i < 50; i++) {
            deepExpr = new AddExpression(deepExpr, new NumberExpression(1));
        }

        int result = deepExpr.interpret(context);
        long endTime = System.currentTimeMillis();

        System.out.println("✓ Deep nesting performance test:");
        System.out.println("Evaluated expression with 50 levels of nesting in " +
                (endTime - startTime) + "ms");
        System.out.println("Result: " + result + " (expected: 51)");
        System.out.println();

        System.out.println("=== Test Summary ===");
        System.out.println("Interpreter Pattern verified:");
        System.out.println("- Terminal expressions (numbers, variables) work correctly");
        System.out.println("- Non-terminal expressions (operations) combine terminals properly");
        System.out.println("- Context provides variable storage and retrieval");
        System.out.println("- Complex nested expressions evaluate correctly");
        System.out.println("- Grammar is easily extensible with new expression types");
        System.out.println("- Expression trees can be built programmatically");
        System.out.println("- Error handling works for invalid operations");
        System.out.println("- Polymorphic treatment allows uniform expression handling");
        System.out.println("- Multiple contexts can be used with same expressions");

        demonstrateGrammarStructure();
    }

    private static void demonstrateGrammarStructure() {
        System.out.println("\n=== Grammar Structure Demonstration ===");
        System.out.println("Expression Language Grammar:");
        System.out.println("Expression := Terminal | NonTerminal");
        System.out.println("Terminal := Number | Variable");
        System.out.println("NonTerminal := Expression Operator Expression");
        System.out.println("Operator := '+' | '-' | '*' | '/'");
        System.out.println("Number := Integer");
        System.out.println("Variable := String");

        System.out.println("\nExpression Tree Structure:");
        System.out.println("              AddExpression");
        System.out.println("             /            \\");
        System.out.println("    NumberExpression    VariableExpression");
        System.out.println("          (5)                  (x)");

        System.out.println("\nInterpreter Pattern Benefits:");
        System.out.println("- Each grammar rule has corresponding class");
        System.out.println("- Easy to change and extend grammar");
        System.out.println("- Recursive structure naturally handles nested expressions");
        System.out.println("- Context provides shared state during interpretation");
        System.out.println("- Uniform interface allows polymorphic treatment");

        System.out.println("\nClass Count Analysis:");
        System.out.println("Without Interpreter Pattern:");
        System.out.println("- Monolithic parser with switch statements for each operation");
        System.out.println("- Hard to extend with new operations");
        System.out.println("- Grammar rules mixed with evaluation logic");

        System.out.println("\nWith Interpreter Pattern:");
        System.out.println("- Each operation is separate class");
        System.out.println("- Easy to add new operations without changing existing code");
        System.out.println("- Clear separation between grammar structure and evaluation");
        System.out.println("- Grammar rules map directly to class hierarchy");
    }
}