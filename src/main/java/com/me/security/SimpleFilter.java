package com.me.security;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//import com.example.demo.model.PgiAudit;
//import com.example.demo.repository.PgiAuditRepository;

@Component
public class SimpleFilter implements Filter {
   
//	@Autowired
//	PgiAuditRepository pgiAuditRepository;
	
	@Override
   public void destroy() {}
   
//	@Autowired
//	PgiAuditRepository pgiAuditRepository1;

   @Override
   public void doFilter
      (ServletRequest request,final ServletResponse response, FilterChain filterchain) 
      throws IOException, ServletException {
//	   PgiAudit pObj=new PgiAudit();
	   
	   
	   System.out.println("calleded--->");
	   
	    HttpServletResponse res = (HttpServletResponse) response;
	    res.addHeader("X-XSS-Protection", "1; mode=block");
	    res.addHeader("X-Content-Type-Options", "nosniff");
	    res.addHeader("Content-Security-Policy", "default-src 'self'");
	    res.setHeader("Strict-Transport-Security", "max-age=7776000");
	    res.setHeader("Access-Control-Allow-Credentials", "true");
	    res.setHeader("X-Frame-Options", "SAMEORIGIN");
	    res.setHeader("Access-Control-Allow-Methods", "POST, PUT, GET");
	    

	   
	  
	   
	   ServletContext context = ((HttpServletRequest) request).getSession().getServletContext();
	   
	   HttpServletRequest req = (HttpServletRequest) request;
	   
	  System.out.println("Private key--->"+context.getAttribute("_private_key"));
	  System.out.println("Public key---->"+context.getAttribute("_public_key"));
	   
//	   if(context.getAttribute("_private_key") ==null) {
//		   KeyPair keyPair = RSAUtils.generateKeyPair();
//	       PrivateKey privateKey = keyPair.getPrivate();
//	       System.out.println("privateKey---->"+privateKey);
//	       context.setAttribute("_private_key", privateKey);
//	       context.setAttribute("_public_key", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
////	       System.out.println("privateKey---->"+privateKey);
////	       Map<String, Object> publicKeyMap = new HashMap<>();
////	       publicKeyMap.put("publicKey", Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
//	       System.out.println("Public key at filter------------------------>"+Base64.encodeBase64String(keyPair.getPublic().getEncoded()));
//	   }
	   System.out.println("request url----->"+req.getRequestURI());
	   
	   String[] values = req.getRequestURI().substring(1).split("/");
	   
	   System.out.println("first string---->"+values[values.length-1]);
	   
//	   final CopyPrintWriter writer = new CopyPrintWriter(response.getWriter());
	   filterchain.doFilter(request,response);
//	   System.out.println(response.getWriter());
	   int status = ((HttpServletResponse) response).getStatus();
	   System.out.println("status---->"+status);
	   
//	   if(values[values.length-1].equalsIgnoreCase("sign-in") || values[values.length-1].equalsIgnoreCase("sign-in") || values[values.length-1].equalsIgnoreCase("sign-out") || values[values.length-1].equalsIgnoreCase("sign-out")) {
//		   pObj.setAction(values[values.length-1]);
//		   pObj.setActionCode(String.valueOf(status));
//		   pObj.setIpAdreess(req.getRemoteAddr());
//		   pgiAuditRepository.save(pObj);
//	   }
//	   filterchain.doFilter(request, new HttpServletResponseWrapper((HttpServletResponse) response) {
//	        @Override public PrintWriter getWriter() {
//	            return writer;
//	        }
//	    });
//	   System.out.println("writer--->"+writer.getCopy());
   }

   @Override
   public void init(FilterConfig filterconfig) throws ServletException {
	   
	   
	   
   }
}