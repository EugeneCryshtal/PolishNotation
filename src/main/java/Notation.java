import utils.NotationUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;


/**
 * Created by ugncry on 08.08.2016.
 */
public abstract class Notation {

    private List<String> inputExpression;
    private List<String> outputExpression = new ArrayList<>();
    private String resultOutput;
    private String result;

    public Notation(List<String> inputExpression) {
        this.inputExpression = inputExpression;
    }

    public Notation operate() throws IncorrectExpressionException {
        try {
            convert();
            calculate();
            return this;
        } catch (Exception e) {
            throw new IncorrectExpressionException();
        }

    }

    public void convert() {
        Stack<String> expressionStack = new Stack<>();

        for (String token : inputExpression) {

            if (NotationUtils.isNumeric(token)) {
                outputExpression.add(token);
                continue;
            }

            if (token.equals("(")) {
                expressionStack.push(token);
                continue;
            }

            if (token.equals(")")) {
                outputExpression.addAll(popUntilBracket(expressionStack));
                continue;
            }

            if (NotationUtils.isOperand(token)) {
                outputExpression.addAll(manipulateOperands(expressionStack, token));
            }
        }

        while (!expressionStack.empty()) {
            outputExpression.add(expressionStack.pop());
        }

        resultOutput = outputExpression.stream().map(Object::toString).collect(Collectors.joining(" "));

    }

    public List<String> manipulateOperands(Stack<String> expressionStack, String operand) {
        List<String> popped = new ArrayList<>();

        while (!(expressionStack.empty() || getNotationComparingCondition(operand, expressionStack.peek()))) {
            popped.add(expressionStack.pop());
        }
        expressionStack.push(operand);

        return popped;
    }

    public abstract Boolean getNotationComparingCondition(String operand1, String operand2);

    public List<String> popUntilBracket(Stack<String> expressionStack) {
        List<String> popped = new ArrayList<>();

        while (!expressionStack.peek().equals("(")) {
            popped.add(expressionStack.pop());
        }
        expressionStack.pop();

        return popped;
    }

    public void calculate() {
        Stack<Double> resultStack = new Stack<>();

        for (String token : outputExpression) {
            if (NotationUtils.isNumeric(token)) {
                resultStack.push(Double.valueOf(token));
                continue;
            }
            if (NotationUtils.isOperand(token)) {
                Double operator1 = resultStack.pop();
                Double operator2 = resultStack.pop();
                manipulateNotationDependent(resultStack, token, operator1, operator2);
            }
        }
        result = String.valueOf(popAndCastResult(resultStack));
    }

    public abstract void manipulateNotationDependent(Stack<Double> resultStack, String token,
                                                     Double operator1, Double operator2);

    public Number popAndCastResult(Stack<Double> resultStack) {
        Double result = resultStack.pop();
        if (result == Math.floor(result)) {
            return result.intValue();
        }
        return result;
    }

    public List<String> getInputExpression() {
        return inputExpression;
    }

    public Notation setInputExpression(List<String> inputExpression) {
        this.inputExpression = inputExpression;
        return this;
    }

    public List<String> getOutputExpression() {
        return outputExpression;
    }

    public Notation setOutputExpression(List<String> outputExpression) {
        this.outputExpression = outputExpression;
        return this;
    }

    public String getResult() {
        return result;
    }

    public Notation setResult(String result) {
        this.result = result;
        return this;
    }

    public String getResultOutput() {
        return resultOutput;
    }

    public Notation setResultOutput(String resultOutput) {
        this.resultOutput = resultOutput;
        return this;
    }
}
