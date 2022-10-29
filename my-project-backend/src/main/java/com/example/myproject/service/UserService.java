package com.example.myproject.service;

import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import com.example.myproject.utils.CookieService;
import com.example.myproject.utils.JwtService;
import com.example.myproject.utils.SHA256;
import com.example.myproject.web.dto.request.JoinReqDto;
import com.example.myproject.web.dto.request.LoginReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final JwtService jwtService;

    private final CookieService cookieService;

    // 회원가입
    @Transactional
    public User join(JoinReqDto dto) {

        String username = dto.getUsername();
        String name = dto.getName();
        String email = dto.getEmail();
        String password = dto.getPassword();

        if (checkUsernameDuplicate(username)) {
            throw new RuntimeException("중복된 아이디가 있습니다.");
        }

        //password = SHA256.encrypt(password);
        String encPassword = bCryptPasswordEncoder.encode(password);

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

    // 로그인
    @Transactional
    public User login(LoginReqDto dto, HttpServletResponse response) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        boolean isAutoLogin = dto.getAutoLogin();

        User user = userRepository.findByUsername(username);

        Boolean pw = bCryptPasswordEncoder.matches(password, user.getPassword());

        if(user == null || !pw){
            throw new RuntimeException("아이디 비밀번호를 확인해주세요.");
        }

        String accessToken = jwtService.createAccessToken(username);
        String refreshToken = jwtService.createRefreshToken(username);

        // 쿠키 발급
        Cookie accessCookie = cookieService.createAccessCookie(accessToken, isAutoLogin);
        Cookie refreshCookie = cookieService.createRefreshCookie(refreshToken, isAutoLogin);

        response.addCookie(accessCookie);
        response.addCookie(refreshCookie);

        return user;
    }
}
