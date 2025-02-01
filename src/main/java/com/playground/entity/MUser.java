package com.playground.entity;

import lombok.Data;

@Data
public class MUser {
	private String userId;
    private String password;
    private String userName;
    private String mailAddress;
    private String role;
}
