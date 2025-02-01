package com.playground.form;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class SignupForm { 
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 50, groups = ValidGroup2.class)
	private String userId;
	
	@NotBlank(groups = ValidGroup1.class) 
	@Length(min = 6, max = 100, groups = ValidGroup2.class)
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	private String password;
	
	@NotBlank(groups = ValidGroup1.class)
	@Length(min = 1, max = 50, groups = ValidGroup2.class)
	private String userName;

	@NotBlank(groups = ValidGroup1.class)
	@Email(groups = ValidGroup2.class)
	@Length(min = 1, max = 50, groups = ValidGroup2.class)
	private String mailAddress;
}
