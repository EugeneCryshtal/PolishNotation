package utils;

/**
 * Created by ugncry on 08.08.2016.
 */

public class NotationUtils {

    public static Boolean isOperand(String s) {
        if ("()+*^-/".contains(s)) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Boolean isNumeric(String s) {
        if (s.matches("-?\\d+(\\.\\d+)?")) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    public static Byte getPriority(String s) {
        switch (s.charAt(0)) {
            case '(':
                return 0;
            case ')':
                return 1;
            case '+':
            case '-':
                return 2;
            case '*':
            case '/':
                return 3;
            case '^':
                return 4;
            default:
                return 5;
        }
    }

    public static Double manipulate(String operand, Double operator1, Double operator2) {
        switch (operand) {
            case "+":
                return operator1 + operator2;
            case "-":
                return operator1 - operator2;
            case "*":
                return operator1 * operator2;
            case "/":
                return operator1 / operator2;
            case "^":
                return Math.pow(operator1, operator2);
            default:
                return null;
        }
    }



}
