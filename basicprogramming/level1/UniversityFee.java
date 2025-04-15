public class UniversityFee{
    public static void main(String[] args){

        //Decalring the variables with given data
        int fee = 125000; 
        double discountPercent = 10.0;


        // Calculating the dicount 
        double discount = (discountPercent/100) * fee;

        // Calculating the Discounted Fee
        double newFee = fee - discount;

        // Printing the Output
        System.out.print("The discount amount is INR " +discount+ " and the discount fee is "+newFee); 
    }
}