package com.dao;

import com.model.entities.User;

public interface UserDao {
    public boolean findBySystemName(String systemName);

    public User logCheck(String username, String password);
}
