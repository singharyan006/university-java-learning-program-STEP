import java.util.*;

public class RoundCalc{
    public static void roundCalc(int side1, int side2, int side3){
        int perimeter = side1 +side2 + side3;
        double totalDIstance = 5000.0;

        double rounds =  perimeter /totalDIstance;;

        System.out.print("The number of round that can be made are "+rounds);
    }

    public static void main(String[] args){
        roundCalc(3,4,5);
    }
}