package com.services.user.impl;

import com.model.dao.global.GlobalMethods;
import com.model.dao.global.impl.GlobalMethodsImpl;
import com.model.entities.User;
import com.services.user.NewUserService;

public class NewUserServiceImpl implements NewUserService {
    GlobalMethodsImpl globalMethods = new GlobalMethodsImpl();

    public boolean createNewUser(User user) {
        return globalMethods.save(user);
    }
}
