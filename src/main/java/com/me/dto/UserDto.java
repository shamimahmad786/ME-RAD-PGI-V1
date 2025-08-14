package com.me.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Data
public class UserDto {

	private String username;

	private String firstname;

	private String lastname;

	@NotNull
	private String email;

}
