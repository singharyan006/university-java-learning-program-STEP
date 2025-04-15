import java.util.*;

public class NaturalSum{
    public static int sumCalc(int limit){
        int sum = 0;
        for (int i = 1; i <= limit; i++){
            sum +=i;
        }
        return sum;
    }

    public static void main(String[] args){
        System.out.print("Enter the limit :");
        int limit = sc.nextInt();

        System.out.print(sumCalc(limit));
    }
}