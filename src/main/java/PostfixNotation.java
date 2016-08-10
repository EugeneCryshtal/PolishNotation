import utils.NotationUtils;

import java.util.List;
import java.util.Stack;


/**
 * Created by ugncry on 06.08.2016.
 */


public class PostfixNotation extends Notation {

    public PostfixNotation(List<String> inputExpression) {
        super(inputExpression);
    }

    @Override
    public Boolean getNotationComparingCondition(String operand1, String operand2) {
        return NotationUtils.getPriority(operand1) > NotationUtils.getPriority(operand2);
    }

    @Override
    public void manipulateNotationDependent(Stack<Double> resultStack, String token, Double operator1, Double operator2) {
        resultStack.push(NotationUtils.manipulate(token, operator2, operator1));
    }

}
