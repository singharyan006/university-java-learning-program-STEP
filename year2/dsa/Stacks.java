public class Stacks {
    private static boolean isOpen(char c) {
        return c == '(' || c == '[' || c == '{' || c == '<';
    }
    private static boolean isClose(char c) {
        return c == ')' || c == ']' || c == '}' || c == '>';
    }
    private static char matchOpen(char c) {
        switch (c) {
            case ')': return '(';
            case ']': return '[';
            case '}': return '{';
            case '>': return '<';
            default: return 0;
        }
    }
    private static char matchClose(char c) {
        switch (c) {
            case '(': return ')';
            case '[': return ']';
            case '{': return '}';
            case '<': return '>';
            default: return 0;
        }
    }
    public static String cleanBrackets(String s) {
        if (s == null || s.isEmpty()) return "";

        char[] arr = s.toCharArray();
        int n = arr.length;
        boolean[] valid = new boolean[n];

        int[] openStack = new int[n];
        int openTop = -1;

        int[] starStack = new int[n];
        int starTop = -1;

        for (int i = 0; i < n; i++) {
            char c = arr[i];

            if (!isOpen(c) && !isClose(c) && c != '*') {
                valid[i] = true;
                continue;
            }

            if (isOpen(c)) {
                openStack[++openTop] = i;
            } else if (c == '*') {
                boolean literal = (i > 0 && Character.isLetterOrDigit(arr[i - 1])) ||
                                  (i + 1 < n && Character.isLetterOrDigit(arr[i + 1]));
                if (literal) {
                    valid[i] = true;
                } else {
                    starStack[++starTop] = i;
                }
            } else if (isClose(c)) {
                if (openTop >= 0 && arr[openStack[openTop]] == matchOpen(c)) {
                    valid[i] = true;
                    valid[openStack[openTop--]] = true;
                } else if (starTop >= 0) {
                    int starIdx = starStack[starTop--];
                    arr[starIdx] = matchOpen(c);
                    valid[i] = true;
                    valid[starIdx] = true;
                }
            }
       }
        // close remaining opens using stars after them
        while (openTop >= 0 && starTop >= 0) {
            int openIdx = openStack[openTop];
            int starIdx = starStack[starTop];
            if (starIdx > openIdx) {
                valid[openIdx] = valid[starIdx] = true;
                arr[starIdx] = matchClose(arr[openIdx]);
                openTop--;
                starTop--;
            } else {
                openTop--;
            }
        }
        // build the final string for printing using string builder
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!valid[i]) continue;
            if (arr[i] != '*') sb.append(arr[i]); // remove stray *
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(cleanBrackets("The sum is (a[b*c] + d)"));
        System.out.println(cleanBrackets("<[*(])>"));
        System.out.println(cleanBrackets("hello*)("));
        System.out.println(cleanBrackets("(<a[>]b)"));
        System.out.println(cleanBrackets("a(b[c<d>])e"));
    }
}