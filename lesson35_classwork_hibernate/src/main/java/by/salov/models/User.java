package by.salov.models;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

//Mark our class as entity for DB
@Entity
//Table name in which save our class
@Table(name = "users_one")
public class User {
    //javax.persistence.Id - you must mark one field as primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //indicate the name of colomn in DB
    @Column(name = "username")
    private String login;
    private String password;
    //EnumType.STRING - save field in DB as string
    //EnumType.ORDINAL - save field in DB as serial number
    @Enumerated(EnumType.STRING)
    private Gender gender;
    //Save object as fields in DataBase
    @Embedded
    private Adress adress;
    //indicate type date which we save in database
    @Temporal(TemporalType.DATE)
    private java.util.Date date;
    //Hibernate automatically set date of creating
    @CreationTimestamp
    private java.util.Date dateOfCreating;
    //Hibernate automatically set date of updating
    @UpdateTimestamp
    private java.util.Date dateOfUpdating;
    //Hibernate automatically set quantity od updates
    @Version
    private int version;

    //In javax.persistence.Entity you must create non argument constructor
    public User() {
    }

    public User(String login, String password, Gender gender, Adress adress) {
        this.login = login;
        this.password = password;
        this.gender = gender;
        this.adress = adress;
        this.date = new Date();
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", adress=" + adress +
                ", date=" + date +
                ", dateOfCreating=" + dateOfCreating +
                ", dateOfUpdating=" + dateOfUpdating +
                ", version=" + version +
                '}';
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
