package com.imb4.gc.p3.gr1.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.entity.User;
import com.imb4.gc.p3.gr1.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(Long id) {
        return userRepository.existsById(id);
    }
}