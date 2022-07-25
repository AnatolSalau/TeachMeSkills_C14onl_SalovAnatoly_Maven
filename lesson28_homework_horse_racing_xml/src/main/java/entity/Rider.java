package entity;

import java.util.Objects;

public class Rider {
    private final String name;
    private final int lavel;

    public Rider(String name, int lavel) {
        this.name = name;
        this.lavel = lavel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rider rider = (Rider) o;
        return Double.compare(rider.lavel, lavel) == 0 && Objects.equals(name, rider.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lavel);
    }

    public String getName() {
        return name;
    }

    public int getLavel() {
        return lavel;
    }
}
