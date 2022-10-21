package com.example.myproject.service;



import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import com.example.myproject.web.dto.UserDto;
import org.assertj.core.api.Assertions;
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
        UserDto dto = new UserDto();
        dto.setUsername("유저 아이디1");
        dto.setName("유저 이름1");
        dto.setEmail("유저 이메일1");
        dto.setPassword("유저 비밀번호1");
        String password = dto.getPassword();
        String encPassword = bCryptPasswordEncoder.encode(password);

        User user = new User(dto.getUsername(), dto.getName(), dto.getEmail(), encPassword);  // 이 객체 주소랑

        when(userRepository.save(any())).thenReturn(user);

        // when
        User userEntity = userService.join(dto); // 이 객체 주소랑 주소값이 다름
        // dto 만들 때 request랑 response dto를 두개 만들 것
        // request -> 프론트에서 넘겨줄 json 데이터
        // response -> 백엔드 처리 후 프론트로 넘겨줄 json 데이터

        // then
//        assertThat(user).isEqualTo(userEntity);

        assertThat(user.getUsername()).isEqualTo(userEntity.getUsername());
        assertThat(user.getName()).isEqualTo(userEntity.getName());
        assertThat(user.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(user.getPassword()).isEqualTo(userEntity.getPassword());

    }

//    @Test
//    public void join중복_Test() {
//        // given
//        UserDto dto = new UserDto();
//        dto.setUsername("유저 아이디1");
//        dto.setName("유저 이름1");
//        dto.setEmail("유저 이메일1");
//        dto.setPassword("유저 비밀번호1");
//        String password = dto.getPassword();
//        String encPassword = bCryptPasswordEncoder.encode(password);
//        User user = new User(dto.getUsername(), dto.getName(), dto.getEmail(), encPassword);  // 이 객체 주소랑
//
//        when(userRepository.save(any())).thenReturn(user);
//        userService.join(dto);
//
//        UserDto dto2 = new UserDto();
//        dto2.setUsername("유저 아이디1");
//        dto2.setName("유저 이름1");
//        dto2.setEmail("유저 이메일1");
//        dto2.setPassword("유저 비밀번호1");
//        String password2 = dto.getPassword();
//        String encPassword2 = bCryptPasswordEncoder.encode(password2);
//        User user2 = new User(dto2.getUsername(), dto2.getName(), dto2.getEmail(), encPassword2);  // 이 객체 주소랑
//        when(userRepository.save(any())).thenReturn(user2);
//
//        // when
//
//        // then
//        assertThrows(RuntimeException.class);
//    }
}