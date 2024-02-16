package service;

import exception.ParkingLimitExceeded;
import exception.UserNotFoundException;
import jdk.nashorn.internal.runtime.logging.Logger;
import model.CarType;
import model.User;

import java.util.HashMap;



@Logger
public class CarServiceImpl implements CarServices{

    //Constants
    static private int SUV_LIMIT = 2;
    static private int HB_LIMIT = 1;


    //Database
    HashMap<CarType,Integer> countofCar = new HashMap<>();

    HashMap<Integer, User> userDB = new HashMap<>();

    public CarServiceImpl() {
        countofCar.put(CarType.HATCHBACK,0);
        countofCar.put(CarType.SUV,0);
    }


    @Override
    public boolean addCar(User user) {

        if (user == null) {
            throw new NullPointerException("Null User not allowed");
        }

        //Check for limit
        if (user.getType().equals(CarType.SUV)) {

            //LimitCheck
            if (countofCar.get(user.getType()) < SUV_LIMIT) {

                userDB.put(user.getId(),user);
                countofCar.put(user.getType(),countofCar.get(user.getType()) + 1);
                System.out.println("Entry Done for UserID " + user.getId());
                return true;

            } else {
                throw new ParkingLimitExceeded();
            }


        } else {
            //LimitCheck

            if (countofCar.get(user.getType()) < HB_LIMIT) {

                userDB.put(user.getId(),user);

                countofCar.put(CarType.HATCHBACK,countofCar.get(CarType.HATCHBACK) + 1);

                System.out.println("Entry Done for UserID: " + user.getId());

                return true;

            } else if (countofCar.get(CarType.SUV) < SUV_LIMIT) {

                user.setIsOverrided(true);

                userDB.put(user.getId(),user);

                countofCar.put(CarType.SUV,countofCar.get(CarType.SUV) + 1);

                System.out.println("Entry Done for UserID: " + user.getId() + " in SUV Parking");

                return true;

            } else {
                throw new ParkingLimitExceeded();
            }
        }

    }

    private long generateBill(User user, long timeDiff) {

        if (timeDiff == 0) {
            return user.getType().getRate();
        } else {
            return timeDiff * user.getType().getRate();
        }
    }

    @Override
    public void removeCar(int userId) {

        if (userDB.get(userId) == null) {
            System.out.println("User not Found " + userId);
            throw new UserNotFoundException(String.format("UserId : {} not found",userId));
        } else {

            User user = userDB.get(userId);

            long timeInHours = (System.currentTimeMillis() - user.getEntryTime().getTime()) / 1000 / 60 / 60;

            long billAmount = generateBill(user,timeInHours);

            System.out.println("Bill Generated for user: Rs " + billAmount);
            userDB.remove(userId);

            //Check for overRiden Car
            if (user.getIsOverrided()) {
                countofCar.put(CarType.SUV, countofCar.get(CarType.SUV) - 1);
            } else {
                countofCar.put(user.getType(), countofCar.get(user.getType()) - 1);
            }

            System.out.println("Vehicle Exited with userID: " + userId);
        }
    }
}
