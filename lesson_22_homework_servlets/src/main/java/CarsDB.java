import jakarta.servlet.http.HttpServlet;

import java.util.HashMap;
import java.util.Map;

public class CarsDB  {
    Map<String, Car> cars = new HashMap<>();

    public CarsDB() {
        //add default cars
        cars.put("1", new Car(1,"Mercedes", "011AA"));
        cars.put("2", new Car(2,"BMV", "022BB"));
        cars.put("3", new Car(3,"Audi", "033CC"));
        cars.put("4", new Car(4,"Volvo", "044DD"));
    }
}
