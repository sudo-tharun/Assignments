package calculator;

//File to calculate the equation with parentheses and multiple operands
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class CalMulti {

    public static int calc(String s) {
        if (s == null || s.length() < 1) return Integer.MIN_VALUE;
        return evalSuffix(inToSuffix(s));
    }

    public static int rank(Character op) {
        switch (op) {
            case '+': return 1;
            case '-': return 1;
            case '*': return 2;
            case '/': return 2;
            case '%': return 2;
            case '^': return 3;
            default: return 0; 
        }
    }

    public static List<Object> inToSuffix(String s) {
        Stack<Character> opStack = new Stack<>();
        List<Object> suffix = new LinkedList<>();
        int num = 0;
        boolean numCached = false;
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (Character.isDigit(c)) {
                num = num * 10 + (c - '0');
                numCached = true;
            }
            else {
                if (numCached) {
                    suffix.add(num);
                    num = 0;
                    numCached = false;
                }
                if (c == ' ' || c == '\t') continue;
                if (c == '(') opStack.push('(');
                else if (c == ')') {
                    while (opStack.peek() != '(') suffix.add(opStack.pop()); //op in () should be added first
                    opStack.pop();
                }
                else {
                    while (!opStack.isEmpty() && rank(c) <= rank(opStack.peek())) suffix.add(opStack.pop());
                    opStack.push(c);
                }
            }
        }
        if (numCached) suffix.add(num);
        while (!opStack.isEmpty()) suffix.add(opStack.pop());
        return suffix;
    }

    public static int evalSuffix(List<Object> suffix) {
        Stack<Integer> numStack = new Stack<>();
        int num1 = 0;
        int num2 = 0;
        for (Object o : suffix) {
            if (o instanceof Character) {
                char op = (Character)o;
                num2 = numStack.pop();
                num1 = numStack.pop();
                switch (op) {
                    case '+': numStack.push(num1 + num2); break;
                    case '-': numStack.push(num1 - num2); break;
                    case '*': numStack.push(num1 * num2); break;
                    case '/': numStack.push(num1 / num2); break;
                    case '%': numStack.push(num1 % num2); break;
                    case '^': numStack.push((int)Math.pow((double)num1, (double)num2)); break;
                }
            }
            else numStack.push((Integer)o);
        }
        return numStack.pop();
    }
}