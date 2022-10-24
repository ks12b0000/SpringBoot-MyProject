package com.example.myproject.service;



import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import com.example.myproject.web.dto.request.JoinReqDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

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

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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
        JoinReqDto dto = new JoinReqDto();
        dto.setUsername("유저 아이디1");
        dto.setName("유저 이름1");
        dto.setEmail("유저 이메일1");
        dto.setPassword("유저 비밀번호1");

        userService.join(dto);

        // when
        JoinReqDto dto2 = new JoinReqDto();
        dto2.setUsername("유저 아이디1");
        dto2.setName("유저 이름1");
        dto2.setEmail("유저 이메일1");
        dto2.setPassword("유저 비밀번호1");

        // then
        assertThrows(RuntimeException.class, () -> userService.join(dto2));

    }
}