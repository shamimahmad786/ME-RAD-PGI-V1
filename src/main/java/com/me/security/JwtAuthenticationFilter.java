package com.me.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.example.demo.security.CustomDataEncriptor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.me.model.User;
import com.me.repository.UserRepository;
import com.me.service.UserDetailsServiceImpl;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	private UserRepository userRepository;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Override
	protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("called for decription");
		
		
		System.out.println("get url--->"+req.getRequestURI());
		
		if (req.getRequestURI().contains("api/pgi/getStateList") || req.getRequestURI().contains("api/main/getAllIndicator")  || req.getRequestURI().contains("/api/pgi/getColorJson")) {
			System.out.println("In if condition");
//			chain.doFilter(req, res);
		}
		
//		CustomDataEncriptor obj=new CustomDataEncriptor();
//		String header = req.getHeader(HEADER_STRING);
//		
//		System.out.println("Header--->"+header);
//		
//		String[] splited = header.split("\\s+");
//		header ="Bearer "+obj.decrypt("secret", splited[1]);
//		System.out.println("after decription---->"+header);
//		System.out.println("called filter first--->" + header);
//		System.out.println("get request url--->" + req.getRequestURI());
//		String username = null;
//		String authToken = null;
//		String authType=null;
////		authType=req.getHeader("authType");
//		
////		if(authType.equalsIgnoreCase("jwt") || true) {
//		if(true) {
//		
//		if (header != null && header.startsWith(TOKEN_PREFIX)) {
//			authToken = header.replace(TOKEN_PREFIX, "");
//			try {
//				System.out.println("ready to get user");
//				UserDetails userDetails = userDetailsService.loadUserByUsername(req.getHeader("userName"));
//				
//				System.out.println("authType--->"+authType);
//				System.out.println("user name--->" + userDetails);
//				// System.out.println("validate
//				// ---->"+jwtTokenUtil.validateToken(authToken,userDetails));
//				System.out.println("request url--->"+req.getRequestURI());
//				if (req.getRequestURI().contains("/school/") && !req.getRequestURI().contains("school/downloadDocument")) {
//					if (!jwtTokenUtil.validateToken(authToken, userDetails)
//							|| jwtTokenUtil.validateToken(authToken, userDetails) == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					}
//				}
//				if (req.getRequestURI().contains("/api/pgi/") && !req.getRequestURI().contains("/api/message/")) {
//					System.out.println("-----Authentication in user id------");
//					if (!jwtTokenUtil.validateToken(authToken, userDetails)
//							|| jwtTokenUtil.validateToken(authToken, userDetails) == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					}
//				}
//				username = jwtTokenUtil.getUsernameFromToken(authToken);
//			} catch (IllegalArgumentException e) {
//				// log.error("an error occured during getting username from token", e);
//				System.out.println("an error occured during getting username from token");
//			} catch (ExpiredJwtException e) {
//				// log.warn("the token is expired and not valid anymore", e);
//				System.out.println("the token is expired and not valid anymore");
//			} catch (SignatureException e) {
//				// log.error("Authentication Failed. Username or Password not valid.");
//				System.out.println("Authentication Failed. Username or Password not valid.");
//			} catch (Exception ex) {
//				System.out.println("in my exception");
//				ex.printStackTrace();
//			}
//		} else {
//			System.out.println("In else condition-->" + header);
//
//			try {
//				System.out.println("Else in user detailsss1");
//				System.out.println("test-->"+req.getHeader("username"));
//				System.out.println("test-->"+req.getHeader("firstname"));
//				UserDetails userDetails = userDetailsService.loadUserByUsername(req.getHeader("username"));
//				System.out.println("Else in user details1ssssssssss");
//				if (req.getRequestURI().contains("school") &&  !req.getRequestURI().contains("school/downloadDocument")) {
//
//					if (header == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					} else {
//						authToken = header.replace(TOKEN_PREFIX, "");
//					}
//					System.out.println("Else in user details2");
//					if (!jwtTokenUtil.validateToken(authToken, userDetails)
//							|| jwtTokenUtil.validateToken(authToken, userDetails) == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					}
//				}else if (req.getRequestURI().contains("/api/pgi/") && !req.getRequestURI().contains("/api/message/")) {
//
//					if (header == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					} else {
//						authToken = header.replace(TOKEN_PREFIX, "");
//					}
//					if (!jwtTokenUtil.validateToken(authToken, userDetails)
//							|| jwtTokenUtil.validateToken(authToken, userDetails) == null) {
//						res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
//					}
//				}
//				System.out.println("authToken-->"+authToken);
//				username = jwtTokenUtil.getUsernameFromToken(authToken);
//			} catch (IllegalArgumentException e) {
//				System.out.println("an error occured during getting username from token");
//			} catch (ExpiredJwtException e) {
//				System.out.println("the token is expired and not valid anymore");
//			} catch (SignatureException e) {
//				System.out.println("Authentication Failed. Username or Password not valid.");
//			} catch (Exception ex) {
//				System.out.println("in my exception");
//				ex.printStackTrace();
//			}
//		}
//		System.out.println("get authentication--->" + SecurityContextHolder.getContext().getAuthentication());
//		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//
//			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//			System.out.println("userDetails--->" + username);
//			if (jwtTokenUtil.validateToken(authToken, userDetails)) {
//				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
//						userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
//				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
//				// log.info("authenticated user " + username + ", setting security context");
//				SecurityContextHolder.getContext().setAuthentication(authentication);
//			}
//		}
//
//		}else if(authType.equalsIgnoreCase("Auth")) {
////			System.out.println("In auth ");
////			Map<String, Object> map=null;
////			ObjectMapper mapper = new ObjectMapper();
////			ResponseEntity<String> response = null;
////			RestTemplate restTemplate = new RestTemplate();
////			authToken = header.replace(TOKEN_PREFIX, "");
////			String credentials = "pgi:pin";
////			String encodedCredentials = new String(Base64.encodeBase64(credentials.getBytes()));
////			HttpHeaders headers = new HttpHeaders();
////			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
////			headers.add("Authorization", "Basic " + encodedCredentials);
////			headers.add("token", authToken);
////			headers.add("authcredential", credentials);
////			
////			HttpEntity<String> request = new HttpEntity<String>(headers);
//////			String access_token_url = "http://10.25.26.251:8090/meuser/api/userCradential/validateToken";
//////			http://' + "pgi.seshagun.gov.in"+'/UserService/api/userCradential
////			String access_token_url = "http://pgi.seshagun.gov.in/UserService/api/userCradential/validateToken";
////			try {
////			response = restTemplate.exchange(access_token_url, HttpMethod.POST, request, String.class);
////			System.out.println("In auth repose body--->"+response.getBody());
////			map = mapper.readValue(response.getBody(), Map.class);
////			if(map.get("status")=="0") {
////				res.sendError(HttpServletResponse.SC_UNAUTHORIZED);
////			}else if(map.get("status")=="1") {
////				chain.doFilter(req, res);
////			}
////			}catch(Exception ex) {
////				ex.printStackTrace();
////			}
//		}
//		System.out.println("in do chain");
//		
		chain.doFilter(req, res);
	}
}