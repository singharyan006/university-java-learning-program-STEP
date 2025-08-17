import java.util.*;

public class OddEvenNumbers{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the limit : ");
        int num = sc.nextInt();

        System.out.print("Even Numbers\n");
        for (int i=0; i< num; i+=2){
            System.out.println(i);
        }

        System.out.print("Odd Numbers\n");
        for(int i = 1; i<num; i+=2){
            System.out.println(i);
        }

        sc.close();
    }
}     