package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;


@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		private  Long id;
	
	@NotBlank(message="入力必須です")
	@Column(length=255, nullable=false)
		private String last_name;
	@NotBlank(message="入力必須です")
	@Column(length=255,nullable=false)
		private String first_name;
	@Email
	@Column(length=255 ,unique=true,nullable=false)
	@NotBlank(message="入力必須です")
		private String email;
	@NotBlank(message="入力必須です")
		private String password;
	@CreationTimestamp
		private LocalDateTime created_at;
	@UpdateTimestamp
		private LocalDateTime updated_at;
	
}
