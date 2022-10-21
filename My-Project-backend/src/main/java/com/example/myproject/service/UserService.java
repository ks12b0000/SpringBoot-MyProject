package com.example.myproject.service;

import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import com.example.myproject.web.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    // 회원가입
    @Transactional
    public User join(UserDto dto) {

        String username = dto.getUsername();
        String name = dto.getName();
        String email = dto.getEmail();
        String password = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(password);

        if (checkUsernameDuplicate(username)) {
            throw new RuntimeException("중복된 아이디가 있습니다.");
        }

        User user = new User(username, name, email, encPassword);

        userRepository.save(user);

        return user;
    }

    // 아이디 중복확인
    public boolean checkUsernameDuplicate(String username){
        User user = userRepository.findByUsername(username);

        if(user == null){
            return false; // 중복 X
        } else {
            return true; // 중복 O
        }
    }
}
