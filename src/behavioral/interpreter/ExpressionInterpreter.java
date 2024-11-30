package behavioral.interpreter;

import java.util.*;

/**
 * Context class that holds additional information needed during interpretation
 * Contains variable assignments and state information
 */
class Context {
    private Map<String, Integer> variables;

    public Context() {
        this.variables = new HashMap<>();
    }

    public void setVariable(String name, int value) {
        variables.put(name, value);
    }

    public int getVariable(String name) {
        return variables.getOrDefault(name, 0);
    }

    public boolean hasVariable(String name) {
        return variables.containsKey(name);
    }

    public void displayVariables() {
        System.out.println("Context variables: " + variables);
    }
}

/**
 * Abstract Expression interface
 * Defines interpret method that all expressions must implement
 */
interface AbstractExpression {
    int interpret(Context context);
    String toString(); // For debugging and display purposes
}

/**
 * Terminal Expression for numbers
 * Represents leaf nodes in expression tree
 */
class NumberExpression implements AbstractExpression {
    private int number;

    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    public int interpret(Context context) {
        return number;
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }
}

class VariableExpression implements AbstractExpression {
    private String variableName;

    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public int interpret(Context context) {
        return context.getVariable(variableName);
    }

    @Override
    public String toString() {
        return variableName;
    }
}

/**
 * Non-Terminal Expression for addition
 * Represents internal nodes that combine other expressions
 */
class AddExpression implements AbstractExpression {
    private AbstractExpression leftExpression;
    private AbstractExpression rightExpression;

    public AddExpression(AbstractExpression left, AbstractExpression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) + rightExpression.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " + " + rightExpression.toString() + ")";
    }
}

class SubtractExpression implements AbstractExpression {
    private AbstractExpression leftExpression;
    private AbstractExpression rightExpression;

    public SubtractExpression(AbstractExpression left, AbstractExpression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) - rightExpression.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " - " + rightExpression.toString() + ")";
    }
}

class MultiplyExpression implements AbstractExpression {
    private AbstractExpression leftExpression;
    private AbstractExpression rightExpression;

    public MultiplyExpression(AbstractExpression left, AbstractExpression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret(Context context) {
        return leftExpression.interpret(context) * rightExpression.interpret(context);
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " * " + rightExpression.toString() + ")";
    }
}

class DivideExpression implements AbstractExpression {
    private AbstractExpression leftExpression;
    private AbstractExpression rightExpression;

    public DivideExpression(AbstractExpression left, AbstractExpression right) {
        this.leftExpression = left;
        this.rightExpression = right;
    }

    @Override
    public int interpret(Context context) {
        int rightValue = rightExpression.interpret(context);
        if (rightValue == 0) {
            throw new ArithmeticException("Division by zero");
        }
        return leftExpression.interpret(context) / rightValue;
    }

    @Override
    public String toString() {
        return "(" + leftExpression.toString() + " / " + rightExpression.toString() + ")";
    }
}