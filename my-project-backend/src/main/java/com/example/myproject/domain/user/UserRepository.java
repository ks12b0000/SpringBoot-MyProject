package com.example.myproject.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    // 아이디와 패스워드로 일치하는 유저를 찾는다.
    User findByUsernameAndPassword(String username, String password);
}
