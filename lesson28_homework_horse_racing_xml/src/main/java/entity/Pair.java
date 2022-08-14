package entity;

import java.util.Objects;

public class Pair {
    private final int number;
    private final Horse horse;
    private  final Rider rider;
    private double speed;

    public Pair(int number, Horse horse, Rider rider) {
        this.number = number;
        this.horse = horse;
        this.rider = rider;
        this.speed = horse.getSpeed() * rider.getLevel();
    }

    public int getNumber() {
        return number;
    }

    public Horse getHorse() {
        return horse;
    }

    public Rider getRider() {
        return rider;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair pair = (Pair) o;
        return number == pair.number && Objects.equals(horse, pair.horse) && Objects.equals(rider, pair.rider);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, horse, rider);
    }
}
