package com.model.dao.global;

import java.util.List;

public interface GlobalMethods {
    public boolean save(Object entity);

    public boolean delete(Object entity);

    public <T> List<T> getAllData(Class<T> entity);
}
