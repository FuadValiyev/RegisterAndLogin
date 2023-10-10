package com.app.PerfectRegisterAndLogin.repository;

import com.app.PerfectRegisterAndLogin.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface UserRepositoryInter extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
