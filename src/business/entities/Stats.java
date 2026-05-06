package business.entities;

public class Stats {
    private long time;
    private double coffeCount;

    public Stats (long time, double coffeCount){
        this.time = time;
        this.coffeCount = coffeCount;
    }

    public double getCoffeCount() {
        return coffeCount;
    }

    public long getTime() {
        return time;
    }

    public void setCoffeCount(double coffeCount) {
        this.coffeCount = coffeCount;
    }

    public void setTime(long time) {
        this.time = time;
    }
}
