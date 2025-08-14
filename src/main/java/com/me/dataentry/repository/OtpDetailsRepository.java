package com.me.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.me.dataentry.modal.AnualFinance;
import com.me.dataentry.modal.OtpDetails;

public interface OtpDetailsRepository extends JpaRepository<OtpDetails, Integer>{
	Integer  removeByUserName(String userName);
	List<OtpDetails>  findAllByOtp(String otp);
}
