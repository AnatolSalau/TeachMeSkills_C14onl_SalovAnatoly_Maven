package entity;

import enums.Gender;
import enums.Role;

import java.util.Objects;

public class User {
    private final String login;
    private String password;
    private Gender gender;
    private String description;
    private Role role;

    public User(String login, String password, String gender, String description, String role) {
        this.login = login;
        this.password = password;
        if (Gender.MAN.toString().equals(gender.toUpperCase())) {
            this.gender = Gender.MAN;
        } else {
            this.gender = Gender.WOMAN;
        }
        this.description = description;
        if (Role.ADMIN.toString().equals(role.toUpperCase())) {
            this.role = Role.ADMIN;
        } else {
            this.role = Role.USER;
        }
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", gender=" + gender +
                ", description='" + description + '\'' +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(login, user.login) && Objects.equals(password, user.password) && gender == user.gender && Objects.equals(description, user.description) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(login, password, gender, description, role);
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public Gender getGender() {
        return gender;
    }

    public String getDescription() {
        return description;
    }

    public Role getRole() {
        return role;
    }
}
