package com.example.myproject.domain;

import com.example.myproject.domain.user.User;
import com.example.myproject.domain.user.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void join_Test() {
        // given
        String username = "유저 아이디1";
        String name = "유저 이름1";
        String email = "유저 이메일1";
        String password = "유저 비밀번호1";
        User user = new User(username, name, email, password);

        // when
        User userPS = userRepository.save(user);

        // then
        assertThat(username).isEqualTo(userPS.getUsername());
        assertThat(name).isEqualTo(userPS.getName());
        assertThat(email).isEqualTo(userPS.getEmail());
        assertThat(password).isEqualTo(userPS.getPassword());

    }

    @Test
    public void join중복_Test() {
        // given
        String username = "유저 아이디1";
        String name = "유저 이름1";
        String email = "유저 이메일1";
        String password = "유저 비밀번호1";
        User user = new User(username, name, email, password);

        String username2 = "유저 아이디1";
        String name2 = "유저 이름1";
        String email2 = "유저 이메일1";
        String password2 = "유저 비밀번호1";
        User user2 = new User(username2, name2, email2, password2);

        // when
        User userPS = userRepository.save(user);

        // then
        assertThrows(RuntimeException.class, () -> userRepository.save(user2));

    }
}
