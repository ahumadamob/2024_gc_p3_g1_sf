package com.imb4.gc.p3.gr1.service.jpa;

import com.imb4.gc.p3.gr1.entity.User;
import com.imb4.gc.p3.gr1.repository.UserRepository;
import com.imb4.gc.p3.gr1.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb4.gc.p3.gr1.exception.UserNotFoundException;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id " + id));
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteById(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("Cannot delete. User not found with id " + id);
        }
        userRepository.deleteById(id);
    }
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}