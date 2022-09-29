package by.salov.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDTO {

    private static String STATIC_FIELD = User.getStaticField();

    private String name;
    private int age;
    private String birthday;


    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", birthday='" + birthday + '\'' +
                '}';
    }
    public static String getStaticField() {
        return STATIC_FIELD;
    }
}
