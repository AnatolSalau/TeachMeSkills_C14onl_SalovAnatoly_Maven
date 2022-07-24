package model;

import java.util.ArrayList;
import java.util.List;

public class DataBase {
    private static List<User> users = new ArrayList<>();

    public void add(User user) {
        boolean alreadyExist = users.stream()
                .anyMatch(oldUser -> oldUser.getUsername().equals(user.getUsername()));
        if (alreadyExist) {
            System.out.println(String.format("User %s is exist", user.getUsername()));
            throw new IllegalArgumentException();
        } else {
            users.add(user);
            System.out.println(String.format("User %s was added to database",user.getUsername()));
        }
    }
}

