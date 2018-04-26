package com.model.dao.user;

import com.model.entities.User;

public interface UserDao {
    public boolean findBySystemName(String systemName);

    public User logCheck(String username, String password);
}
