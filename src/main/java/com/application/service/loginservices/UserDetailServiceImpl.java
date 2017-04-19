package com.application.service.loginservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.application.model.User;
import com.application.service.UserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User currUser = userRepo.findByUsername(username);
		
		if (currUser == null) {
			throw new UsernameNotFoundException("Username not found");
		}
				
		UserDetails user = new org.springframework.security.core.userdetails.User(username, currUser.getPassword(),
				AuthorityUtils.createAuthorityList(currUser.getRole()));
		return user;
	}

}
