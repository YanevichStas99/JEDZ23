package com.akadatsky;

import com.akadatsky.DZ23.HibernateStorage;
import com.akadatsky.DZ23.User;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        HibernateStorage hibernateStorage = new HibernateStorage();
        hibernateStorage.removeAll();
        User user1 = new User("Alex", 30);
        User user2 = new User("Ben", 10);
        User user3 = new User("Daryl",35);
        hibernateStorage.addUser(user1);
        hibernateStorage.addUser(user2);
        hibernateStorage.addUser(user3);
        user2.setAge(20);
        hibernateStorage.updateUser(user2);
        User user =hibernateStorage.getUserByName("Ben");
        System.out.println(user);
        hibernateStorage.removeUserByAge(20);
        List<User> users = hibernateStorage.getAllUsers();
        System.out.println(users);
        hibernateStorage.removeAll();
        users=hibernateStorage.getAllUsers();
        System.out.println(users);
        hibernateStorage.addUser(user1);
        hibernateStorage.addUser(user2);
        hibernateStorage.addUser(user3);
        hibernateStorage.removeUserByName("Alex");
        System.out.println(hibernateStorage.getAllUsers());

        hibernateStorage.close();
    }

}
