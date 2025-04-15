import java.util.*;

public class MultiplicationTable {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        int[] multArr = new int [10];

        for (int i = 0; i < multArr.length; i++){
            multArr[i] = num * (i+1); 
        }

        for (int i = 0; i <=multArr.length; i++){
            System.out.println(num +" * "+ (i+1)+" = "+multArr[i]);
        }

        sc.close();
    }
}