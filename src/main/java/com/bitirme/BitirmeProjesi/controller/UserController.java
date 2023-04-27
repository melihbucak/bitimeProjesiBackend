package com.bitirme.BitirmeProjesi.controller;

import com.bitirme.BitirmeProjesi.entity.User;
import com.bitirme.BitirmeProjesi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/saveUser")
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        User user1 = userService.createUser(user);
        return new ResponseEntity<>(user1, HttpStatus.CREATED);
    }

    @PostMapping("/checkLogin")
    public ResponseEntity checkLogin(@RequestBody User user) {
        ResponseEntity user1 = userService.checkLogin(user);
        return new ResponseEntity<>(user1, HttpStatus.ACCEPTED);
    }

}
