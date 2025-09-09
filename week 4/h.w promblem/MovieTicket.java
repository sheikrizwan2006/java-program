public class MovieTicket {
    private String movieName;
    private String theatreName;
    private int seatNumber;
    private double price;

     1. Default constructor → assigns Unknown movie
    public MovieTicket() {
        this(Unknown);
    }

     2. Constructor with movie name → assigns default price = 200
    public MovieTicket(String movieName) {
        this(movieName, Unknown Theatre, 0, 200.0);
    }

     3. Constructor with movie name and seat number → assigns default theatre PVR
    public MovieTicket(String movieName, int seatNumber) {
        this(movieName, PVR, seatNumber, 200.0);
    }

     4. Full constructor → sets all details
    public MovieTicket(String movieName, String theatreName, int seatNumber, double price) {
        this.movieName = movieName;
        this.theatreName = theatreName;
        this.seatNumber = seatNumber;
        this.price = price;
    }

     Method to print ticket details
    public void printTicket() {
        System.out.println(Movie Ticket Details);
        System.out.println(Movie Name  + movieName);
        System.out.println(Theatre  + theatreName);
        System.out.println(Seat Number  + seatNumber);
        System.out.println(Price ₹ + price);
        System.out.println(------------------------);
    }

     Main method to create and print tickets
    public static void main(String[] args) {
        MovieTicket t1 = new MovieTicket();   Unknown movie
        MovieTicket t2 = new MovieTicket(Inception);   Default price 200
        MovieTicket t3 = new MovieTicket(Avengers, 45);   Default theatre PVR
        MovieTicket t4 = new MovieTicket(Interstellar, IMAX, 12, 350.5);   Full details

        t1.printTicket();
        t2.printTicket();
        t3.printTicket();
        t4.printTicket();
    }
}