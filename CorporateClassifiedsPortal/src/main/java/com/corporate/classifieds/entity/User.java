package com.corporate.classifieds.entity;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private int empid;
	private String username;
	private String email;
	private String firstname;
	private String lastname;
	private String password;
}
