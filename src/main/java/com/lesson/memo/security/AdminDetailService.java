package com.lesson.memo.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Service
public class AdminDetailService implements UserDetailsService{
	private final AdminRepository adminRepository;
	public AdminDetailService (AdminRepository adminRepository) {
		this.adminRepository=adminRepository;
	}
	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<Admin>admin=adminRepository.findByEmail(email);
		if(admin.isEmpty()) {
			throw new UsernameNotFoundException("メールアドレスが見つかりません");
		}
		UserDetails user=User.withUsername(admin.get().getEmail())
				.password(admin.get().getPassword())
				.roles("ADMIN")
				.build();
		return user;
	}
}
