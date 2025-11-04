public class ProfitCalculator{
    public static void main(String [] args){
        

        //Decalring the varibles with some values
        int costPrice = 129;
        int sellingPrice = 191;

        //Calculating the profit
        int profit = sellingPrice - costPrice;
        
        //Calculating the profit percentage
        int profitPercentage = (profit / costPrice) * 100; 

        System.out.println();//Printing new line 

        //Printing the Selling and Cost Price
        System.out.println("The CostPrice is INR "+costPrice+" and the Selling Price is INR "+sellingPrice);
        
        //Printing the output
        System.out.println("The profit made is "+profit +" and the profit percentage is " +profitPercentage);

        System.out.println();//Printing new line 
    }
}