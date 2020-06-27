package com.smartreal.api.controller;

import com.smartreal.api.model.User;
import com.smartreal.api.model.request.ChangePasswordRequest;
import com.smartreal.api.service.UserService;
import com.smartreal.api.util.JwtTokenUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private final JwtTokenUtil jwtTokenUtil;
    private final UserService userService;

    public UserController(JwtTokenUtil jwtTokenUtil, UserService userService) {
        this.jwtTokenUtil = jwtTokenUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public boolean register(@RequestBody User user) {
        userService.insertUser(user);

        return true;
    }

    @GetMapping
    public User getUserByUserName(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }

    @PutMapping
    public User changeUserInformation(@RequestBody User user) {
        return userService.updateUserInformation(user);
    }

    @PutMapping("/password")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordRequest changePasswordRequest) {
        userService.changePassword(changePasswordRequest);

        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }

    @GetMapping("/me")
    public User whoAmI(HttpServletRequest request) {
        String token = jwtTokenUtil.resolveToken(request);
        Authentication authentication = jwtTokenUtil.getAuthentication(token);
        Object userDetails = authentication.getPrincipal();

        if (userDetails instanceof UserDetails) {
            String username = ((UserDetails) userDetails).getUsername();
            return userService.getUserByUsername(username);
        } else {
            throw new RuntimeException("Wrong Token");
        }
    }
}
