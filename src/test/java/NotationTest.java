import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by ugncry on 08.08.2016.
 */
public class NotationTest {


    public NotationTest() throws IncorrectExpressionException {
    }

    @Test
    public void basicTest() throws IncorrectExpressionException {
        InfixNotation infixNotation = new InfixNotation("0");
        PostfixNotation postfixNotation = infixNotation.toPostfixNotation();
        PrefixNotation prefixNotation = infixNotation.toPrefixNotation();
        assertTrue(postfixNotation.getResultOutput().equals(prefixNotation.getResultOutput()));
    }

    @Test
    public void passValidationTest() throws IncorrectExpressionException {
        InfixNotation infixNotation = new InfixNotation("-4");
        InfixNotation infixNotation2 = new InfixNotation("0 + 0");
        InfixNotation infixNotation3 = new InfixNotation("( -9 - 9 - 9 )");
        infixNotation.setInputExpression("( 4 + ( -4 - ( 2 ^ 0 ) ) )");
        infixNotation.setInputExpression("-0 + -0");
        infixNotation.setInputExpression("1 - 5 ^ 4 + 9 - 5");
    }

    @Test(expected = IncorrectExpressionException.class)
    public void incorrectExpressionsTest() throws IncorrectExpressionException {
        InfixNotation infixNotation = new InfixNotation("-4-");
    }

    @Test
    public void convertToPostfixTest() throws IncorrectExpressionException {
        InfixNotation infixNotation = new InfixNotation("-5 + 4 * ( 2 - 6 )");
        assertEquals(infixNotation.toPostfixNotation().getResultOutput(),"-5 4 2 6 - * +");

        infixNotation.setInputExpression("5 + 6 - 4 ^ 2 / 7 * -3");
        assertEquals(infixNotation.toPostfixNotation().getResultOutput(),"5 6 + 4 2 ^ 7 / -3 * -");

        infixNotation.setInputExpression("( 21 + -4 ) / ( 3 - ( 2 * 6 ) )");
        assertEquals(infixNotation.toPostfixNotation().getResultOutput(),"21 -4 + 3 2 6 * - /");
    }

    @Test
    public void convertToPrefixTest() throws IncorrectExpressionException {
        InfixNotation infixNotation = new InfixNotation("7 * 20 - 4 + 6");
        assertEquals(infixNotation.toPrefixNotation().getResultOutput(),"+ - * 7 20 4 6");

        infixNotation.setInputExpression("5 + 6 - 4 ^ 2 / 7 * -3");
        assertEquals(infixNotation.toPrefixNotation().getResultOutput(),"- + 5 6 * / ^ 4 2 7 -3");

        infixNotation.setInputExpression("7 * ( 20 - 4 ) + 6");
        assertEquals(infixNotation.toPrefixNotation().getResultOutput(),"+ * 7 - 20 4 6");
    }

    @Test
    public void compareResults() throws IOException, IncorrectExpressionException {
        Map<String, String> map = jsonToMap();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            assertEquals(new InfixNotation(entry.getKey()).toPostfixNotation().getResult(), entry.getValue());
            assertEquals(new InfixNotation(entry.getKey()).toPrefixNotation().getResult(), entry.getValue());
        }
    }

    private Map<String, String> jsonToMap() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(
                new File("src/main/resources/correct-expressions.json"), new TypeReference<Map<String, String>>() {});
    }


}
