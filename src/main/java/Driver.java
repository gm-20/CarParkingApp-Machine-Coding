import model.CarType;
import model.User;
import service.CarServiceImpl;
import service.CarServices;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class Driver {





    public static void main(String[] args) {


        //Entry
       CarServices carServices = new CarServiceImpl();
       carServices.addCar(new User(1,CarType.HATCHBACK,new Timestamp(System.currentTimeMillis())));
//       carServices.removeCar(1);


       carServices.addCar(new User(2,CarType.SUV,new Timestamp(System.currentTimeMillis())));
//       carServices.removeCar(2);


        String input = "2024-02-12 18:00:00" ;
        java.sql.Timestamp ts = java.sql.Timestamp.valueOf( input ) ;

        carServices.addCar(new User(3,CarType.HATCHBACK,ts));

        carServices.removeCar(3);

    }



}
