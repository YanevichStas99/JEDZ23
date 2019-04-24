package com.akadatsky.DZ23;



import java.util.List;

public interface Storage {
    void removeAll();

    void removeUserByAge(int age);

    void removeUserByName(String name);

    void addUser(User user);

    void updateUser(User user);

    User getUserByName(String name);

    List<User> getAllUsers();
}
