import java.util.*;

public class MiniMarkDownBracketCleaner {

    public String cleanMarkdownBrackets(String s) {
        int n = s.length();
        char[] chars = s.toCharArray();
        boolean[] keep = new boolean[n];

        // Stacks for each bracket type
        Map<Character, Stack<Integer>> openers = new HashMap<>();
        openers.put('(', new Stack<>());
        openers.put('[', new Stack<>());
        openers.put('{', new Stack<>());
        openers.put('<', new Stack<>());

        Map<Character, Character> match = Map.of(
            ')', '(', ']', '[', '}', '{', '>', '<'
        );

        Stack<Integer> stars = new Stack<>();


        for (int i = 0; i < n; i++) {
            char c = chars[i];
            if (openers.containsKey(c)) {
                openers.get(c).push(i);
            } else if (c == '*') {
                stars.push(i);
            } else if (match.containsKey(c)) {
                char opener = match.get(c);
                Stack<Integer> stack = openers.get(opener);
                if (!stack.isEmpty()) {
                    keep[stack.pop()] = true;
                    keep[i] = true;
                } else if (!stars.isEmpty()) {
                    keep[stars.pop()] = true;
                    keep[i] = true;
                }
                
            } else {
                keep[i] = true; // letters and spaces
            }
        }

        // Second pass: match remaining openers with stars
        for (char opener : openers.keySet()) {
            Stack<Integer> stack = openers.get(opener);
            while (!stack.isEmpty() && !stars.isEmpty()) {
                int openIdx = stack.pop();
                int starIdx = stars.pop();
                keep[openIdx] = true;
                keep[starIdx] = true;
            }
        }

        // Build final string (include matched stars)
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (keep[i]) {
                sb.append(chars[i]);
            }
        }

        // Final validation
        if (!isValid(sb.toString())) return "";
        return sb.toString();
    }

    private boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> match = Map.of(
            ')', '(', ']', '[', '}', '{', '>', '<'
        );
        for (char c : s.toCharArray()) {
            if ("([{<*".indexOf(c) >= 0) {  // Include * as opener
                stack.push(c);
            } else if (match.containsKey(c)) {
                if (stack.isEmpty()) return false;
                char top = stack.pop();
                // Star matches any closer
                if (top == '*') continue;
                // Otherwise check exact match
                if (top != match.get(c)) return false;
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        MiniMarkDownBracketCleaner cleaner = new MiniMarkDownBracketCleaner();

        String input1 = "The sum is (a[b*c] + d)";
        String input2 = "<[*(])>";
        String input3 = "hello*)(";
        String input4 = "*{[a + b]*}c<d>";
        String input5 = "*]*[";

        System.out.println("Input: " + input1);
        System.out.println("Output: " + cleaner.cleanMarkdownBrackets(input1));
        System.out.println();

        System.out.println("Input: " + input2);
        System.out.println("Output: " + cleaner.cleanMarkdownBrackets(input2));
        System.out.println();

        System.out.println("Input: " + input3);
        System.out.println("Output: " + cleaner.cleanMarkdownBrackets(input3));
        System.out.println();

        System.out.println("Input: " + input4);
        System.out.println("Output: " + cleaner.cleanMarkdownBrackets(input4));
        System.out.println();

        System.out.println("Input: " + input5);
        System.out.println("Output: " + cleaner.cleanMarkdownBrackets(input5));
    }
}
