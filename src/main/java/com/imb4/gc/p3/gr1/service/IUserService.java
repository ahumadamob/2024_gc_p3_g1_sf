package com.imb4.gc.p3.gr1.service;

import java.util.List;
import com.imb4.gc.p3.gr1.entity.User;

public interface IUserService {
    List<User> getAll();
    User getById(Long id);
    User save(User user);
    void delete(Long id);
    boolean exists(Long id);
}