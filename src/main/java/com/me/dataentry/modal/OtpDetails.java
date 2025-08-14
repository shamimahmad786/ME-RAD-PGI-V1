package com.me.dataentry.modal;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Transactional
@Table(name = "PGI_RE_OTP")
public class OtpDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String userName;
	private String stateCode;
	private String mobileNumber;
	private String email;
	private String otp;
	private LocalDateTime  otpGerateTime;
	private LocalDateTime  otpExpiryTime;
}
