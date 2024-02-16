package service;

import model.User;

public interface CarServices {


    boolean addCar(User user);

    void removeCar(int userId);

}
