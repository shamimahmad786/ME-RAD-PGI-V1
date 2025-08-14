package com.me.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.me.model.UserRoleMst;

@Repository
public interface UserRoleMstRepository extends JpaRepository<UserRoleMst, Long> {
}
