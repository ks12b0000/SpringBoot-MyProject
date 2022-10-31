package com.example.myproject.service;



import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import com.example.myproject.utils.CookieService;
import com.example.myproject.utils.JwtService;
import com.example.myproject.web.dto.request.JoinReqDto;
import com.example.myproject.web.dto.request.LoginReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@Transactional
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtService jwtService;

    @Mock
    private CookieService cookieService;

    @Mock
    HttpServletResponse response;

    @Test
    public void join_Test() {
        // given
        JoinReqDto dto = new JoinReqDto();
        dto.setUsername("유저 아이디1");
        dto.setName("유저 이름1");
        dto.setEmail("유저 이메일1");
        dto.setPassword("유저 비밀번호1");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(password);

        User user = new User(dto.getUsername(), dto.getName(), dto.getEmail(), encPassword);

        when(userRepository.save(any())).thenReturn(user);
        // when
        User userEntity = userService.join(dto);

        // then
        assertThat(user.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(user.getName()).isEqualTo(userEntity.getName());
        assertThat(user.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(bCryptPasswordEncoder.matches(password, encPassword)).isEqualTo(true);

    }

    @Test
    public void join중복_Test() {
        // given
        JoinReqDto user = new JoinReqDto("junit수정123", "메타코딩수정6", "ks12b0000@gmail.com", "sadasdsad");
        userService.join(user);

        // stub
        when(userRepository.save(any())).thenReturn(user);

        // when
        JoinReqDto dto = new JoinReqDto();
        dto.setUsername("junit수정123");
        dto.setName("유저 이름1");
        dto.setEmail("유저 이메일1");
        dto.setPassword("유저 비밀번호1");

        // then
        assertThrows(RuntimeException.class, () -> userService.join(dto));

    }

    @Test
    public void login_Test() {
        // given
        JoinReqDto dto = new JoinReqDto();
        dto.setUsername("유저 아이디1");
        dto.setName("유저 이름1");
        dto.setEmail("유저 이메일1");
        dto.setPassword("유저 비밀번호1");
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String password = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(password);

        User user = new User(dto.getUsername(), dto.getName(), dto.getEmail(), encPassword);
        when(userRepository.save(any())).thenReturn(user);

        userService.join(dto);

        // when
        LoginReqDto loginReqDto = new LoginReqDto("유저 아이디1", "유저 비밀번호1", false);
        when(userRepository.findByUsername(loginReqDto.getUsername())).thenReturn(user);

        User login = userService.login(loginReqDto ,response);

        // then
        assertThat(user.getUsername()).isEqualTo(login.getUsername());
        assertThat(user.getPassword()).isEqualTo(login.getPassword());
    }
}