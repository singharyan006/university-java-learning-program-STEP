class MovieTicket {
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;

    // Default constructor
    public MovieTicket() {
        this("Unknown", "Generic Theatre", -1, 0.0);
    }

    // Constructor with movie name
    public MovieTicket(String movieName) {
        this(movieName, "Generic Theatre", -1, 200.0);
    }

    // Constructor with movie name and seat number
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, "PVR", seatNumber, 200.0);
    }

    // Full constructor
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

    // Method to print ticket details
    public void printTicket() {
        System.out.println("🎟️ Movie Ticket");
        System.out.println("Movie: " + movieName);
        System.out.println("Theatre: " + theatreName);
        System.out.println("Seat No: " + seatNumber);
        System.out.println("Price: ₹" + price);
        System.out.println("-------------------------");
    }
}

public class MovieTicketBookingSystem {
    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();
        MovieTicket t2 = new MovieTicket("Inception");
        MovieTicket t3 = new MovieTicket("Interstellar", 12);
        MovieTicket t4 = new MovieTicket("Tenet", "IMAX", 5, 350.0);

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}
