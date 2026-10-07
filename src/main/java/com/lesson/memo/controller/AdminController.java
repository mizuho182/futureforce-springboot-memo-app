package com.lesson.memo.controller;

import jakarta.validation.Valid;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.lesson.memo.model.Admin;
import com.lesson.memo.repository.AdminRepository;

@Controller
public class AdminController {
		private AdminRepository adminRepository;
		private final BCryptPasswordEncoder passwordEncoder;
		public AdminController(
			AdminRepository adminRepository,
			BCryptPasswordEncoder passwordEncoder) {
			this.adminRepository=adminRepository;
			this.passwordEncoder=passwordEncoder;
				
		}
		
	@GetMapping("/admin/signup")
		public String sign(Model model) {
		model.addAttribute("admin",new Admin());
		return "admin-signup";
	}
	
	@PostMapping("/admin/signup")
		public String signup( @ModelAttribute @Valid Admin admin,BindingResult result) { 
				if(result.hasErrors()) {
					return "admin-signup";
				}
				if(adminRepository.findByEmail(admin.getEmail()).isPresent()) {
					result.rejectValue(
							"email",
							"double",
							"このメールアドレスは登録済みです");
					return "admin-signup";
				}
			admin.setPassword(passwordEncoder.encode(admin.getPassword()));
			adminRepository.save(admin);
			return "redirect:/admin/signin";
	}
	@GetMapping("/admin/signin")
	public String signin() {
		return "admin-signin";
	}
	
}

