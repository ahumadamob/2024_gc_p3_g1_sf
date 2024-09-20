package com.imb4.gc.p3.gr1.service;

import com.imb4.gc.p3.gr1.entity.User;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
}
