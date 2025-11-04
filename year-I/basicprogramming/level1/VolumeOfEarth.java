public class VolumeOfEarth{
    public static void main(String[] args){
        
        // Declaring the variables with given values  
        int radiusInKm = 6378;
        double miles = 1.6;

        // Converting the distanc in miles
        double radiusInMiles = radiusInKm * miles;

        // Calculating the volume of earth in cubic km
        double volumeInKm = 4.0/3 * 3.14 * Math.pow(radiusInKm,3);

        // Calculating the volume of earth in cubic miles
        double volumeInMiles = 4.0/3 * 3.14 * Math.pow(radiusInMiles,3);

        //Displaying the output
        System.out.print("Volume of earth in km^3 "+volumeInKm+ " in miles^3 "+volumeInMiles);
    }
}