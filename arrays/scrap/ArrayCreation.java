import java.util.*;

public class ArrayCreation{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr1 = new int[10];//declared an 1d array
        int total = 0;//variable to store the sum of all numbers entered

        int count = 0;//Counter variable for while loop
        while(true){

            System.out.print("\nEnter the number :");
            arr1[count] = sc.nextInt();

            if (arr1[count] < 0 || arr1[count] == 0){break;}    
            count++;
        }

        for ( int i = 0; i < 10; i++){
            total+=arr1[i];
        }

        System.out.print("The Total of number entered is "+total);

        sc.close();
    }
}