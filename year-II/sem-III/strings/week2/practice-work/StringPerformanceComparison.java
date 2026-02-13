public class StringPerformanceComparison {

    public static void main(String[] args) {
        System.out.println("=== PERFORMANCE COMPARISON ===");

        // Test with String
        long startTime = System.nanoTime();
        String result1 = concatenateWithString(10000);
        long endTime = System.nanoTime();
        System.out.println("String concatenation time: " + (endTime - startTime) + " ns");

        // Test with StringBuilder
        startTime = System.nanoTime();
        String result2 = concatenateWithStringBuilder(10000);
        endTime = System.nanoTime();
        System.out.println("StringBuilder concatenation time: " + (endTime - startTime) + " ns");

        // Test with StringBuffer
        startTime = System.nanoTime();
        String result3 = concatenateWithStringBuffer(10000);
        endTime = System.nanoTime();
        System.out.println("StringBuffer concatenation time: " + (endTime - startTime) + " ns");

        demonstrateStringBuilderMethods();
        demonstrateThreadSafety();
        compareStringComparisonMethods();
        demonstrateMemoryEfficiency();
    }

    public static String concatenateWithString(int iterations) {
        String result = "";
        for (int i = 0; i < iterations; i++) {
            result += "Java" + i + " ";
        }
        return result;
    }

    public static String concatenateWithStringBuilder(int iterations) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java").append(i).append(" ");
        }
        return sb.toString();
    }

    public static String concatenateWithStringBuffer(int iterations) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < iterations; i++) {
            sb.append("Java").append(i).append(" ");
        }
        return sb.toString();
    }

    public static void demonstrateStringBuilderMethods() {
        System.out.println("\n=== STRINGBUILDER METHODS ===");
        StringBuilder sb = new StringBuilder("Hello World");

        sb.append("!");
        sb.insert(6, "Java ");
        sb.delete(0, 5);
        sb.deleteCharAt(0);
        sb.reverse();
        sb.replace(0, 4, "Code");
        sb.setCharAt(0, 'C');

        System.out.println("Modified StringBuilder: " + sb);
        System.out.println("Capacity: " + sb.capacity());
        sb.ensureCapacity(100);
        System.out.println("Capacity after ensureCapacity(100): " + sb.capacity());
        sb.trimToSize();
        System.out.println("Capacity after trimToSize(): " + sb.capacity());
    }

    public static void demonstrateThreadSafety() {
        System.out.println("\n=== THREAD SAFETY DEMO ===");
        StringBuffer buffer = new StringBuffer();
        StringBuilder builder = new StringBuilder();

        Runnable bufferTask = () -> {
            for (int i = 0; i < 1000; i++) {
                buffer.append("A");
            }
        };

        Runnable builderTask = () -> {
            for (int i = 0; i < 1000; i++) {
                builder.append("B");
            }
        };

        Thread t1 = new Thread(bufferTask);
        Thread t2 = new Thread(bufferTask);
        Thread t3 = new Thread(builderTask);
        Thread t4 = new Thread(builderTask);

        t1.start(); t2.start(); t3.start(); t4.start();

        try {
            t1.join(); t2.join(); t3.join(); t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("StringBuffer length (thread-safe): " + buffer.length());
        System.out.println("StringBuilder length (not thread-safe): " + builder.length());
    }

    public static void compareStringComparisonMethods() {
        System.out.println("\n=== STRING COMPARISON METHODS ===");
        String str1 = "Hello";
        String str2 = "Hello";
        String str3 = new String("Hello");

        System.out.println("str1 == str2: " + (str1 == str2)); // true
        System.out.println("str1 == str3: " + (str1 == str3)); // false
        System.out.println("str1.equals(str3): " + str1.equals(str3)); // true
        System.out.println("str1.equalsIgnoreCase(\"hello\"): " + str1.equalsIgnoreCase("hello")); // true
        System.out.println("str1.compareTo(\"Hello\"): " + str1.compareTo("Hello")); // 0
        System.out.println("str1.compareToIgnoreCase(\"hello\"): " + str1.compareToIgnoreCase("hello")); // 0
    }

    public static void demonstrateMemoryEfficiency() {
        System.out.println("\n=== MEMORY EFFICIENCY ===");

        String str1 = "Java";
        String str2 = "Java";
        String str3 = new String("Java");

        System.out.println("str1 == str2 (string pool): " + (str1 == str2)); // true
        System.out.println("str1 == str3 (new object): " + (str1 == str3)); // false

        StringBuilder sb = new StringBuilder();
        System.out.println("Initial capacity: " + sb.capacity());
        sb.append("Java".repeat(50));
        System.out.println("Capacity after appending: " + sb.capacity());
        sb.trimToSize();
        System.out.println("Capacity after trimToSize(): " + sb.capacity());
    }
}
