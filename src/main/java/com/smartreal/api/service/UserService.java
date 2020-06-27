package com.smartreal.api.service;

import com.smartreal.api.enums.ErrorType;
import com.smartreal.api.exception.SmartRealRuntimeException;
import com.smartreal.api.mapper.UserMapper;
import com.smartreal.api.model.User;
import com.smartreal.api.model.request.ChangePasswordRequest;
import org.springframework.lang.NonNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class UserService {
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }

    public void changePassword(ChangePasswordRequest changePassword) {
        userMapper.changePasswordByUser(changePassword.getUsername(), passwordEncoder.encode(changePassword.getNewPassword()));
    }

    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Object principal = authentication.getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return this.getUserByUsername(username);
        } else {
            throw new RuntimeException("Wrong User! Need to Login");
        }
    }

    @Transactional
    public void insertUser(User user) {
        User userByUsername = userMapper.getUserByUsername(user.getUsername());
        User userByPhone = userMapper.getUserByPhone(user.getPhone());
        User userByEmail = userMapper.getUserByEmail(user.getEmail());
        if (Objects.nonNull(userByUsername)) {
            throw new RuntimeException("Username Exist! Try other one!");
        }

        if (Objects.nonNull(userByPhone)) {
            throw new RuntimeException("Phone Exist! Try other one!");
        }

        if (Objects.nonNull(userByEmail)) {
            throw new RuntimeException("Email Exist! Try other one!");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        userMapper.insertUser(user);
    }

    @Transactional
    public User updateUserInformation(@NonNull User user) {
        if (Objects.isNull(user.getId())) {
            throw new SmartRealRuntimeException(ErrorType.BAD_REQUEST);
        }

        User userByUsername = userMapper.getUserByUsername(user.getUsername());
        if (Objects.nonNull(userByUsername) && !user.getId().equals(userByUsername.getId())) {
            throw new SmartRealRuntimeException(ErrorType.BAD_REQUEST, "Username Exist! Try other one!");
        }
        User userByPhone = userMapper.getUserByPhone(user.getPhone());
        if (Objects.nonNull(userByPhone) && !user.getId().equals(userByPhone.getId())) {
            throw new SmartRealRuntimeException(ErrorType.BAD_REQUEST, "Phone Exist! Try other one!");
        }
        User userByEmail = userMapper.getUserByEmail(user.getEmail());
        if (Objects.nonNull(userByEmail) && !user.getId().equals(userByEmail.getId())) {
            throw new SmartRealRuntimeException(ErrorType.BAD_REQUEST, "Email Exist! Try other one!");
        }

        userMapper.updateUser(user);

        return userMapper.getUserById(user.getId());
    }

    private User getUserByPhone(String phone) {
        return userMapper.getUserByPhone(phone);
    }
}
