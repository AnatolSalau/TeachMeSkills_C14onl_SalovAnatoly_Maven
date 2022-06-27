import java.util.Objects;

public class Car {
    private final int ID;
    private String name;
    private String number;

    public Car(int ID, String name, String number) {
        this.ID = ID;
        this.name = name;
        this.number = number;
    }

    public int getID() {
        return ID;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "Car{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return ID == car.ID && Objects.equals(name, car.name) && Objects.equals(number, car.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ID, name, number);
    }
}
