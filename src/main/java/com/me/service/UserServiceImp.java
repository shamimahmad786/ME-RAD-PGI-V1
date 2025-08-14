package com.me.service;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.me.bean.CustumResponse;
import com.me.dto.RegistirationRequest;
import com.me.dto.UserDto;
import com.me.dto.UserPasswordDto;
import com.me.model.User;
import com.me.repository.UserRepository;

import javassist.NotFoundException;

@Service
public class UserServiceImp {

	private final AuthenticationManager authenticationManager;

	private final ModelMapper modelMapper;

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	CustumResponse custumResponse;

	public UserServiceImp(ModelMapper modelMapper, UserRepository userRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder, AuthenticationManager authenticationManager) {
		super();
		this.modelMapper = modelMapper;
		this.userRepository = userRepository;
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
		this.authenticationManager = authenticationManager;
	}

	public List<UserDto> getAll() throws NotFoundException {
		List<User> users = userRepository.findAll();

		if (users.size() < 1) {
			throw new NotFoundException("Project don't already exist");
		}

		UserDto[] userdto = modelMapper.map(users, UserDto[].class);
		return Arrays.asList(userdto);
	}

	@Transactional
	public CustumResponse register(RegistirationRequest registirationRequest){
try {
		List<User> userList = userRepository.findByEmail(registirationRequest.getEmail());
System.out.println(registirationRequest.getUsername());
Optional<User> userList1=null;

if(registirationRequest.getId() !=null) {
   userList1 = userRepository.findById(registirationRequest.getId());
}
if(userList1 ==null) {
//if(! (userList1.get().getId() >0 )) {
		if (userRepository.findByEmail(registirationRequest.getEmail()).size() > 0) {			
			custumResponse.setStatus(0);
			custumResponse.setMessage("Duplicate email id");
		return  custumResponse;
//		throw new Exception("User exist with this : " + registirationRequest.getEmail());
		}
//}
}else {
	if(!(userList1.get().getEmail().equalsIgnoreCase(registirationRequest.getEmail()))){
	if (userRepository.findByEmail(registirationRequest.getEmail()).size() > 0) {		
		custumResponse.setStatus(0);
		custumResponse.setMessage("Duplicate email id");
	return  custumResponse;
	}
}
}
if(userList1 ==null) {

//		if(! (userList1.get().getId()>0)) {
			if (userRepository.getByUsername(registirationRequest.getUsername()).size() > 0) {
				custumResponse.setStatus(0);
				custumResponse.setMessage("Duplicate user name");
				return  custumResponse;
//				throw new Exception("User exist with this name called : " + registirationRequest.getUsername());
//			}	
		}
}else {
//	if(! (userList1.get().getId()>0)) {
	if(!(userList1.get().getUsername().equalsIgnoreCase(registirationRequest.getUsername()))){
		if (userRepository.getByUsername(registirationRequest.getUsername()).size() > 0) {
			custumResponse.setStatus(0);
			custumResponse.setMessage("Duplicate user name");
			return  custumResponse;
//			throw new Exception("User exist with this name called : " + registirationRequest.getUsername());
//		}	
	}
	}
}
		User user = new User();
		registirationRequest.getId();
		if(registirationRequest.getId() !=null) {
			System.out.println("in if condition for updte");
			user.setId(registirationRequest.getId());
			custumResponse.setMessage("user update successfully");
		}else {
			custumResponse.setMessage("user add successfully");
		}
		user.setRealPassword(registirationRequest.getPassword());
		System.out.println(registirationRequest.getPassword());
		registirationRequest.setPassword(bCryptPasswordEncoder.encode(registirationRequest.getPassword()));
		// user = modelMapper.map(registirationRequest, User.class);
		user.setUsername(registirationRequest.getUsername());
		user.setEmail(registirationRequest.getEmail());
		user.setMobileNo(registirationRequest.getMobileNo());
		user.setFirstname(registirationRequest.getFirstname());
		user.setLastname(registirationRequest.getLastname());
		user.setPassword(registirationRequest.getPassword());
		user.setRoleId(registirationRequest.getRoleId());
		user.setCreatedby(registirationRequest.getCreatedBy());
		user.setCreationTime(Calendar.getInstance().getTime());
		user.setModificationTime(Calendar.getInstance().getTime());
		user.setParamName(registirationRequest.getParamName());
		user.setParamValue(registirationRequest.getParamValue());
		user.setStatus("A");
		user.setStateId(registirationRequest.getStateId());
		user.setStateName(registirationRequest.getStateName());
		user.setDistrictName(registirationRequest.getDistrictName());
		user.setGroupId(registirationRequest.getGroupId());
		user.setBlockName(registirationRequest.getBlockName());
		userRepository.save(user);
		custumResponse.setStatus(1);
		
		
}catch(Exception ex) {
	ex.printStackTrace();
	custumResponse.setStatus(1);
	custumResponse.setMessage(ex.getMessage());
	return custumResponse;
}
return custumResponse;
	}

	
	@Transactional
	public Boolean deleteUser(RegistirationRequest registirationRequest){
try {
	userRepository.deleteById(registirationRequest.getId());
}catch(Exception ex) {
	return false;
	}
return true;
}
	
	public UserDto findByUserName(String username) throws NotFoundException {
		try {

			User user = userRepository.findByUsername(username);
			UserDto userDto = modelMapper.map(user, UserDto.class);
			return userDto;

		} catch (Exception e) {

			throw new NotFoundException("User dosen't exist with this name called : " + username);
		}
	}

	public Boolean update(String username, @Valid UserDto userDto) throws NotFoundException {

		List<User> userlist = userRepository.getByUsername(username);

		if (userlist.size() < 0) {
			throw new NotFoundException("User dosen't exist with this name called : " + username);
		}

		User user = modelMapper.map(userDto, User.class);
		user.setId(userlist.get(0).getId());
		user.setRealPassword(userlist.get(0).getRealPassword());
		user.setPassword(userlist.get(0).getPassword());
		userRepository.save(user);
		return true;
	}

	public Boolean changePassword(UserPasswordDto userPasswordDto) throws NotFoundException {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!userPasswordDto.getUsername().equals(auth.getName())) {
			return false;
		}

		List<User> userlist = userRepository.getByUsername(userPasswordDto.getUsername());

		if (userlist.size() < 0) {
			throw new NotFoundException("User dosen't exist with this name called : " + userPasswordDto.getUsername());
		}

		boolean control = bCryptPasswordEncoder.matches(userPasswordDto.getPassword(), userlist.get(0).getPassword());

		if (!control) {
			throw new NotFoundException("Mevcut şifreniz yanlıştır.");
		}

		userlist.get(0).setRealPassword(userPasswordDto.getNewpassword());
		userlist.get(0).setPassword(bCryptPasswordEncoder.encode(userPasswordDto.getNewpassword()));
		userRepository.save(userlist.get(0));
		return true;

	}

	public List<User> getUserList(String search) {
		List<User> userList = Collections.emptyList();
		try {
			if(search.equalsIgnoreCase("A")) {
			userList = userRepository.findAll();
			}else if(Integer.parseInt(search)>0) {
//				userList = userRepository.findBy
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userList;
	}
	
	public Optional<User> getUserById(String search) {
		return userRepository.findById(Long.parseLong(search));
	}
	
}
