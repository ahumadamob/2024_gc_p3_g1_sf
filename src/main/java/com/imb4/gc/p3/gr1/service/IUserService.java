package com.imb4.gc.p3.gr1.service;

import com.imb4.gc.p3.gr1.entity.User;
import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
