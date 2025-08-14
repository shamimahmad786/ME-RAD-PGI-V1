package com.me.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.model.UserRoleMst;
import com.me.repository.UserRoleMstRepository;

@Service
public class UserRoleMstServiceImpl {

	@Autowired
	private UserRoleMstRepository userRoleMstRepository;

	public List<UserRoleMst> getRoleList(Long roleId) {
		List<UserRoleMst> roleList = Collections.EMPTY_LIST;
		try {
			if (roleId != null && roleId != 0) {
				Optional<UserRoleMst> userRoleMst = userRoleMstRepository.findById(roleId);
				if (userRoleMst.isPresent())
					roleList = Arrays.asList(userRoleMst.get());
			} else {
				roleList = (List<UserRoleMst>) userRoleMstRepository.findAll();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return roleList;
	}

}
