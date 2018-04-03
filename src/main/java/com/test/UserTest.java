package com.test;

import com.model.entities.User;

public class UserTest {
    public static void main(String[] args){
        User user = new User("bilal", "kocoglu", "deneme@deneme.com", "asdfasdf");
        HibernateExample.save(user);
    }
}
