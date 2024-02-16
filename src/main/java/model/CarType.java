package model;

public enum CarType {
    SUV(20),
    HATCHBACK(10);

    private int rate;


    CarType(int rate) {
    this.rate = rate;
    }

    public int getRate() {
        return this.rate;
    }

}
