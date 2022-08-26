package by.salov.entity;

import java.util.Objects;

public class Horse {
    private final String name;
    private double speed;

    public Horse(String name, double speed) {
        this.name = name;
        this.speed = speed;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Horse horse = (Horse) o;
        return Double.compare(horse.speed, speed) == 0 && Objects.equals(name, horse.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, speed);
    }

    public String getName() {
        return name;
    }

    public double getSpeed() {
        return speed;
    }

    @Override
    public String toString() {
        return "Horse{" +
                "name='" + name + '\'' +
                ", speed=" + speed +
                '}';
    }
}
