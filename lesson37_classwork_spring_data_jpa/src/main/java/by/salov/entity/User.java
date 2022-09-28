package by.salov.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@NoArgsConstructor
@Getter

@Entity
@Table(name = "users")
public class User {

    private static String STATIC_FIELD = "STATIC_FIELD";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer age;
    private boolean isActive;

    @Temporal(TemporalType.DATE)
    private Date birthday;

    @CreationTimestamp
    private Date created;
    @UpdateTimestamp
    private Date updated;
    @Version
    private Long version;
    private boolean isDeleted = false;

    SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy", Locale.ENGLISH);

    public User(String name, Integer age, String birthday) {
        this.name = name;
        this.age = age;
        try {
            this.birthday = formatter.parse(birthday );
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static String getStaticField() {
        return STATIC_FIELD;
    }

}
