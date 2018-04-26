package com.services.user.impl;

import com.model.dao.global.impl.GlobalDaoImpl;
import com.model.dao.user.impl.UserDaoİmpl;
import com.model.entities.User;
import com.services.user.NewUserService;

public class NewUserServiceImpl implements NewUserService {
    GlobalDaoImpl globalMethods = new GlobalDaoImpl();
    UserDaoİmpl userDao = new UserDaoİmpl();


    public boolean chackSystemName(String systemName) {
        return userDao.findBySystemName(systemName);
    }

    public boolean createNewUser(User user) {
        return globalMethods.save(user);
    }
}
