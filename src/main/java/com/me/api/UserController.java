package com.me.api;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.me.dto.UserDto;
import com.me.dto.UserPasswordDto;
import com.me.service.UserServiceImp;
import com.me.util.ApiPaths;

import javassist.NotFoundException;

@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@CrossOrigin
public class UserController {

	@Autowired
	private UserServiceImp userServiceImp;

	@GetMapping("/{username}")
	public ResponseEntity<UserDto> findByUserName(@PathVariable(name = "username", required = true) String username)
			throws NotFoundException {
		return ResponseEntity.ok(userServiceImp.findByUserName(username));
	}

	@PutMapping("/{username}")
	public ResponseEntity<Boolean> updateUser(@PathVariable(name = "username", required = true) String username,
			@Valid @RequestBody UserDto userDto) throws NotFoundException {
		return ResponseEntity.ok(userServiceImp.update(username, userDto));
	}
	
	@PatchMapping("/change-password")
	public ResponseEntity<Boolean> signUp(@RequestBody UserPasswordDto userPasswordDto) throws NotFoundException {

		Boolean result = userServiceImp.changePassword(userPasswordDto);
		return ResponseEntity.ok(result);
	}
}
