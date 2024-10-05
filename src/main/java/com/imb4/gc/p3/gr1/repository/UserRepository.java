package com.imb4.gc.p3.gr1.repository;
import com.imb4.gc.p3.gr1.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}