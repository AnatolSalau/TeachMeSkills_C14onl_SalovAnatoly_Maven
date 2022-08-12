package by.salov.models;

public class Adress {
    private String city;
    private int home;

    public Adress() {
    }

    public Adress(String city, int home) {
        this.city = city;
        this.home = home;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getHome() {
        return home;
    }

    public void setHome(int home) {
        this.home = home;
    }
}
