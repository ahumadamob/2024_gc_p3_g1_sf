package com.imb4.gc.p3.gr1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb4.gc.p3.gr1.entity.User;
import com.imb4.gc.p3.gr1.service.IUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private IUserService userService;

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        if (user == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(user);
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return userService.save(user);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userDetails) {
        if (!userService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        userDetails.setId(id);
        User updatedUser = userService.save(userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!userService.exists(id)) {
            return ResponseEntity.notFound().build();
        }
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }
}