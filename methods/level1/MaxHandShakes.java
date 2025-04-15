import java.util.*;

public class MaxHandShakes{
    public static void handshakeCalc(int num){
        int result = (num*(num - 1))/2;
        System.out.print("The maximum handshakes possible is " +result);
    }

    public static void main(String[] args){
        handshakeCalc(2);
    }
}