import java.util.*;

public class SimpleInterest{
    public static void simpleInterest(double principal, double rate, double time){
        double si =  (principal*rate*time)/100;
        System.out.print("The Simple Interest is " +si+", for Principal "+principal+", Rate of Interest "+rate+", and Time "+time);
    }

    public static void main(String[] args){
        double principal = 10000;
        double rate = 10;
        double time = 2.0;

        simpleInterest(principal,rate,time);
    }
}