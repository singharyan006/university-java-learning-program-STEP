public class PenDistribution{
    public static void main(String[] args){

        //Declared the variables
        int pen = 14, stud = 3;


        //Calculating the pen per student 
        int penPerStudent = pen / stud;
        
        //Caculating the remaining pens
        int remainingPen = pen % stud;
        
        System.out.println();   // Printing new line 


        //Printing the output
        System.out.print("The Pen per Student is "+penPerStudent +" and the remaining no distributed pen is "+remainingPen);

    }
}