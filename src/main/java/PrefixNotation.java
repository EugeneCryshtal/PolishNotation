import utils.NotationUtils;

import java.util.*;
import java.util.stream.Collectors;


/**
 * Created by ugncry on 07.08.2016.
 */
public class PrefixNotation extends Notation {

    public PrefixNotation(List<String> inputExpression) {
        super(inputExpression);
    }

    private void reverseExpression(List<String> expression) {
        expression = reverseElements(expression);
        expression = replaceBrackets(expression);
        setInputExpression(expression);
    }

    private List<String> reverseElements(List<String> expression) {
        Collections.reverse(expression);
        return expression;
    }

    private List<String> replaceBrackets(List<String> expression) {
        Collections.replaceAll(expression, "(", "!");
        Collections.replaceAll(expression, ")", "(");
        Collections.replaceAll(expression, "!", ")");
        return expression;
    }

    public void convert() {
        reverseExpression(getInputExpression());
        super.convert();
        reverseExpression(getInputExpression());
    }

    public void calculate() {
        super.calculate();
        setOutputExpression(reverseElements(getOutputExpression()));
        List<String> resultOutputList = getOutputExpression();
        setResultOutput(resultOutputList.stream().map(Object::toString).collect(Collectors.joining(" ")));
    }

    @Override
    public Boolean getNotationComparingCondition(String operand1, String operand2) {
        return NotationUtils.getPriority(operand1) >= NotationUtils.getPriority(operand2);
    }

    @Override
    public void manipulateNotationDependent(Stack<Double> resultStack, String token, Double operator1, Double operator2) {
        resultStack.push(NotationUtils.manipulate(token, operator1, operator2));
    }
}
