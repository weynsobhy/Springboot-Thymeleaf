package com.es.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.es.model.User;
import com.es.repository.UserRepository;


@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	
	@Override
	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			return null;
		}
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> grantedAuths = new ArrayList<GrantedAuthority>();	
		return new org.springframework.security.core.userdetails.User(
				user.getUsername(), 
				user.getPassword(),
				user.getEnabled(),
				accountNonExpired,
				credentialsNonExpired,
				accountNonLocked,grantedAuths
				);
	}

}
