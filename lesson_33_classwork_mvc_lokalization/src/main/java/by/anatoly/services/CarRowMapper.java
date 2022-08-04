package by.anatoly.services;

import by.anatoly.model.Car;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.sql.ResultSet;
import java.sql.SQLException;

//RowMapper - interface which create object from data of DB
@Service
@Qualifier(value = "carRowMapper")
public class CarRowMapper implements RowMapper<Car> {
    @Override
    public Car mapRow(ResultSet rs, int rowNum) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int age = rs.getInt("age");
        return new Car(id,name,age);
    }
}
