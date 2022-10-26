package com.example.myproject.web;


import com.example.myproject.service.UserService;
import com.example.myproject.web.dto.request.JoinReqDto;
import com.example.myproject.web.dto.request.LoginReqDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @CrossOrigin
    @PostMapping("/joinForm")
    public ResponseEntity<?> joinForm(@Valid @RequestBody JoinReqDto joinReqDto) {
        return new ResponseEntity<>(userService.join(joinReqDto), HttpStatus.CREATED);
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@Valid @RequestBody LoginReqDto loginReqDto) {
//        return new ResponseEntity<>(userService.login(loginReqDto), HttpStatus.OK);
//    }
}
