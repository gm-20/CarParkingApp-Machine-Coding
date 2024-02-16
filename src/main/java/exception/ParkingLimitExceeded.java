package exception;

public class ParkingLimitExceeded extends RuntimeException{

    public ParkingLimitExceeded() {
        super("Limit Exceed for Parking");
    }

}
