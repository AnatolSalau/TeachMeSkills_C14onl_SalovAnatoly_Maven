package by.salov.entity;

import java.util.Objects;

public class Rider {
    private final String name;
    private final int level;

    public Rider(String name, int level) {
        this.name = name;
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rider rider = (Rider) o;
        return Double.compare(rider.level, level) == 0 && Objects.equals(name, rider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, level);
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public String toString() {
        return "Rider{" +
                "name='" + name + '\'' +
                ", level=" + level +
                '}';
    }
}
