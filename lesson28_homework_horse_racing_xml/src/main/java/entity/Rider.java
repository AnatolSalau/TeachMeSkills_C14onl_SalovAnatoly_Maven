package entity;

import java.util.Objects;

public class Rider {
    private final String name;
    private double speed;

    public Rider(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rider rider = (Rider) o;
        return Double.compare(rider.speed, speed) == 0 && Objects.equals(name, rider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speed);
    }
}
