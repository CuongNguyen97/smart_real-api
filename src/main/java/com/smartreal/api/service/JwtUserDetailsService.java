package com.smartreal.api.service;

import com.smartreal.api.mapper.UserMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;

@Service
public class JwtUserDetailsService implements UserDetailsService {
	private final UserMapper userMapper;

	public JwtUserDetailsService(UserMapper userMapper) {
		this.userMapper = userMapper;
	}

	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		com.smartreal.api.model.User userByUsername = userMapper.getUserByUsername(username);

		if (Objects.isNull(userByUsername)) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}

		ArrayList<GrantedAuthority> objects = new ArrayList<>();
		objects.add(new SimpleGrantedAuthority("ROLE_USER"));

		return User.builder()
				.username(username)
				.password(userByUsername.getPassword())
				.authorities("ROLE_USER")
				.accountExpired(false)
				.accountLocked(false)
				.credentialsExpired(false)
				.disabled(false)
				.build();
    }
}