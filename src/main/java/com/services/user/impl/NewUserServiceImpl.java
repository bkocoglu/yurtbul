package com.services.user.impl;

import com.dao.impl.GlobalDaoImpl;
import com.dao.impl.userDaoImpl;
import com.model.entities.User;
import com.services.user.NewUserService;

public class NewUserServiceImpl implements NewUserService {
    GlobalDaoImpl globalMethods = new GlobalDaoImpl();
    userDaoImpl userDao = new userDaoImpl();


    public boolean chackSystemName(String systemName) {
        return userDao.findBySystemName(systemName);
    }

    public boolean createNewUser(User user) {
        return globalMethods.save(user);
    }
}
