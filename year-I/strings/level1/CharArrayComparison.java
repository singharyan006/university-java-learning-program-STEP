import java.util.Scanner; 
 
public class CharArrayComparison { 
 
    public static char[] getCharsFromString(String text) { 
        int length = text.length(); 
        char[] result = new char[length]; 
        for (int i = 0; i < length; i++) { 
            result[i] = text.charAt(i); 
        } 
        return result; 
    } 
 
    public static boolean compareCharArrays(char[] arr1, char[] arr2) { 
        if (arr1.length != arr2.length) { 
            return false; 
        } 
 
        for (int i = 0; i < arr1.length; i++) { 
            if (arr1[i] != arr2[i]) { 
                return false; 
            } 
        } 
 
        return true; 
    } 
 
    public static void main(String[] args) { 
        Scanner scanner = new Scanner(System.in); 
 
        System.out.print("Enter a string: "); 
        String text = scanner.next(); 
 
        char[] customCharArray = getCharsFromString(text); 
        char[] builtInCharArray = text.toCharArray(); 
 
        boolean areEqual = compareCharArrays(customCharArray, 
builtInCharArray); 
 
        System.out.print("\nCharacters using custom method: "); 
        for (char c : customCharArray) { 
            System.out.print(c + " "); 
        } 
 
        System.out.print("\nCharacters using toCharArray(): "); 
        for (char c : builtInCharArray) { 
            System.out.print(c + " "); 
        } 
 
        System.out.println("\n\nComparison result: " + areEqual); 
 
        if (areEqual) { 
            System.out.println(" Both character arrays are the same."); 
        } else { 
            System.out.println(" Character arrays are different."); 
        } 
 
        scanner.close(); 
    } 
}