import java.util.*;

public class BiggestFactor {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int biggestFactor = 0;

        System.out.print("Enter the number : ");
        int num = sc.nextInt();

        for (int i = 1; i < num + 1; i++){
            if(num % i == 0 && i != num){
                biggestFactor = i;
            }
        }
        System.out.printf("The biggest factor of %d is %d",num,biggestFactor);

        }
    }
