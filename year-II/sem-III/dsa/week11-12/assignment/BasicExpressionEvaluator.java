import java.util.Scanner;

class StackString {
    String[] arr = new String[200];
    int top = -1;
    void push(String x) { arr[++top] = x; }
    String pop() { return arr[top--]; }
    String peek() { return arr[top]; }
    boolean empty() { return top == -1; }
}

class StackInt {
    int[] arr = new int[200];
    int top = -1;
    void push(int x) { arr[++top] = x; }
    int pop() { return arr[top--]; }
    boolean empty() { return top == -1; }
}

public class BasicExpressionEvaluator {
    static int prec(char op) {
        if (op == '+' || op == '-') return 1;
        if (op == '*' || op == '/') return 2;
        return 0;
    }

    static String infixToPostfix(String expr) {
        StackString stack = new StackString();
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (Character.isWhitespace(c)) continue;
            if (Character.isDigit(c)) {
                String num = "";
                while (i < expr.length() && Character.isDigit(expr.charAt(i))) {
                    num += expr.charAt(i);
                    i++;
                }
                i--;
                output.append(num).append(" ");
            } else if (c == '(') {
                stack.push("(");
            } else if (c == ')') {
                while (!stack.empty() && !stack.peek().equals("(")) {
                    output.append(stack.pop()).append(" ");
                }
                if (!stack.empty()) stack.pop();
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.empty() && prec(stack.peek().charAt(0)) >= prec(c)) {
                    output.append(stack.pop()).append(" ");
                }
                stack.push(String.valueOf(c));
            }
        }
        while (!stack.empty()) {
            output.append(stack.pop()).append(" ");
        }
        return output.toString().trim();
    }

    static int evaluatePostfix(String postfix) {
        StackInt stack = new StackInt();
        String[] tokens = postfix.split("\\s+");
        for (String token : tokens) {
            if (token.equals("+") || token.equals("-") || token.equals("*") || token.equals("/")) {
                int b = stack.pop();
                int a = stack.pop();
                int res = 0;
                if (token.equals("+")) res = a + b;
                else if (token.equals("-")) res = a - b;
                else if (token.equals("*")) res = a * b;
                else if (token.equals("/")) res = a / b;
                stack.push(res);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an arithmetic expression (e.g. 3+4*2): ");
        String expr = sc.nextLine();
        String postfix = infixToPostfix(expr);
        int value = evaluatePostfix(postfix);
        System.out.println("Postfix: " + postfix);
        System.out.println("Value: " + value);
    }
}
