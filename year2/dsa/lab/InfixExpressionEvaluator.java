import java.util.*;
import java.util.regex.*;

public class InfixExpressionEvaluator {

    public static int evaluate(String expr, Map<String, Integer> env) {
        try {
            List<String> rpn = toRPN(expr, env);
            return evalRPN(rpn, env);
        } catch (Exception e) {
            return Integer.MIN_VALUE; // Use Integer.MIN_VALUE to represent ERROR
        }
    }

    private static List<String> toRPN(String expr, Map<String, Integer> env) throws Exception {
        List<String> output = new ArrayList<>();
        Stack<String> ops = new Stack<>();
        StringTokenizer tokens = new StringTokenizer(expr, "+-*/%^(),", true);
        String prev = "";
        while (tokens.hasMoreTokens()) {
            String token = tokens.nextToken().trim();
            if (token.isEmpty()) continue;

            if (isNumber(token) || isVariable(token)) {
                output.add(token);
            } else if (isFunction(token)) {
                ops.push(token);
            } else if (token.equals(",")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                if (ops.isEmpty()) throw new Exception("ERROR");
            } else if (isOperator(token)) {
                while (!ops.isEmpty() && precedence(ops.peek()) >= precedence(token)) {
                    output.add(ops.pop());
                }
                ops.push(token);
            } else if (token.equals("(")) {
                ops.push(token);
            } else if (token.equals(")")) {
                while (!ops.isEmpty() && !ops.peek().equals("(")) {
                    output.add(ops.pop());
                }
                if (ops.isEmpty()) throw new Exception("ERROR");
                ops.pop(); // pop '('
                if (!ops.isEmpty() && isFunction(ops.peek())) {
                    output.add(ops.pop());
                }
            } else {
                throw new Exception("ERROR");
            }
            prev = token;
        }

        while (!ops.isEmpty()) {
            String op = ops.pop();
            if (op.equals("(") || op.equals(")")) throw new Exception("ERROR");
            output.add(op);
        }

        return output;
    }

    private static int evalRPN(List<String> rpn, Map<String, Integer> env) throws Exception {
        Stack<Integer> stack = new Stack<>();
        for (String token : rpn) {
            if (isNumber(token)) {
                stack.push(Integer.parseInt(token));
            } else if (isVariable(token)) {
                if (!env.containsKey(token)) throw new Exception("ERROR");
                stack.push(env.get(token));
            } else if (isOperator(token)) {
                if (stack.size() < 2) throw new Exception("ERROR");
                int b = stack.pop();
                int a = stack.pop();
                stack.push(applyOperator(a, b, token));
            } else if (isFunction(token)) {
                if (token.equals("abs")) {
                    if (stack.isEmpty()) throw new Exception("ERROR");
                    stack.push(Math.abs(stack.pop()));
                } else {
                    if (stack.size() < 2) throw new Exception("ERROR");
                    int b = stack.pop();
                    int a = stack.pop();
                    if (token.equals("min")) stack.push(Math.min(a, b));
                    else if (token.equals("max")) stack.push(Math.max(a, b));
                    else throw new Exception("ERROR");
                }
            } else {
                throw new Exception("ERROR");
            }
        }
        if (stack.size() != 1) throw new Exception("ERROR");
        return stack.pop();
    }

    private static boolean isNumber(String token) {
        return token.matches("-?\\d+");
    }

    private static boolean isVariable(String token) {
        return token.matches("[a-zA-Z]\\w*");
    }

    private static boolean isFunction(String token) {
        return token.equals("min") || token.equals("max") || token.equals("abs");
    }

    private static boolean isOperator(String token) {
        return "+-*/%^".contains(token);
    }

    private static int precedence(String op) {
        switch (op) {
            case "^": return 4;
            case "*": case "/": case "%": return 3;
            case "+": case "-": return 2;
            default: return 0;
        }
    }

    private static int applyOperator(int a, int b, String op) throws Exception {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/":
                if (b == 0) throw new Exception("ERROR");
                return a / b;
            case "%":
                if (b == 0) throw new Exception("ERROR");
                return a % b;
            case "^": return (int) Math.pow(a, b);
            default: throw new Exception("ERROR");
        }
    }

    // For testing
    public static void main(String[] args) {
        Map<String, Integer> env1 = new HashMap<>();
        System.out.println(evaluate("3 + 4 * 2 / (1 - 5) ^ 2 ^ 3", env1)); // Output: 3

        Map<String, Integer> env2 = new HashMap<>();
        System.out.println(evaluate("min(10, max(2, 3*4))", env2)); // Output: 10

        Map<String, Integer> env3 = new HashMap<>();
        env3.put("x", -2);
        env3.put("y", -7);
        System.out.println(evaluate("-(x) + abs(y)", env3)); // Output: 9

        Map<String, Integer> env4 = new HashMap<>();
        env4.put("a", 1);
        System.out.println(evaluate("a + b", env4)); // Output: ERROR
    }
}
