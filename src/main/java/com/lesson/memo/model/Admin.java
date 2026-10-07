package com.lesson.memo.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;


@Entity
@Data
public class Admin {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
		private  Long id;
	
	@NotNull
	@Column(length=255, nullable=false)
		private String last_name;
	@NotNull
	@Column(length=255,nullable=false)
		private String first_name;
	@Email
	@Column(length=255 ,unique=true,nullable=false)
	@NotNull
		private String email;
	@NotNull
	@Column(length=255 , nullable=false)
		private String password;
	@DateTimeFormat
		private LocalDateTime created_at;
	@DateTimeFormat
		private LocalDateTime updated_at;
	
}
