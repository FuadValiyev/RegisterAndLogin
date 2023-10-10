package com.app.PerfectRegisterAndLogin.repository;

import com.app.PerfectRegisterAndLogin.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

//@Repository
public interface RoleRepositoryInter extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
