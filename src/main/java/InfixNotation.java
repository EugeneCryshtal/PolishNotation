import utils.NotationUtils;

import java.util.Arrays;
import java.util.List;


/**
 * Created by ugncry on 08.08.2016.
 */
public class InfixNotation {

    private List<String> inputExpression;

    public InfixNotation(String inputExpression) throws IncorrectExpressionException {
        this.inputExpression = validate(inputExpression);
    }

    private List<String> validate(String inputExpression) throws IncorrectExpressionException {

        List<String> validatedExpression = Arrays.asList(inputExpression.split(" "));
        validateSimplifiedExpression(validatedExpression);

        return validatedExpression;
    }

    public void validateSimplifiedExpression(List<String> exp) throws IncorrectExpressionException {
        if(exp.contains("(")){
            return;
        }
        for (int i = 0; i < exp.size() ; i++) {
            if(NotationUtils.isNumeric(exp.get(i))) {
                if(exp.size() == i + 1) {
                    break;
                } else if (exp.size() >= i + 3) {
                    if (!(NotationUtils.isOperand(exp.get(++i)) && NotationUtils.isNumeric(exp.get(i + 1)))) {
                        throw new IncorrectExpressionException();
                    }
                } else {
                    throw new IncorrectExpressionException();
                }
            } else {
                throw new IncorrectExpressionException();
            }
        }
    }

    public PostfixNotation toPostfixNotation() throws IncorrectExpressionException {
        return (PostfixNotation) new PostfixNotation(inputExpression).operate();
    }

    public PrefixNotation toPrefixNotation() throws IncorrectExpressionException {
        return (PrefixNotation) new PrefixNotation(inputExpression).operate();
    }

    public List<String> getInputExpression() {
        return inputExpression;
    }

    public void setInputExpression(String newExpression) throws IncorrectExpressionException {
        this.inputExpression = validate(newExpression);
    }
}
