package model;

import java.sql.Timestamp;

public class User {


    int id;
    CarType type;
    Timestamp entryTime;
    boolean isOverrided;


    public User(int id, CarType type, Timestamp entryTime) {
        this.id = id;
        this.type = type;
        this.entryTime = entryTime;
        this.isOverrided = false;
    }

    public void setIsOverrided(boolean isOverrided) {
        this.isOverrided = isOverrided;
    }

    public int getId() {
        return this.id;
    }

    public CarType getType() {
        return this.type;
    }


    public Timestamp getEntryTime() {
        return this.entryTime;
    }

    public boolean getIsOverrided() {
        return this.isOverrided;
    }

}
