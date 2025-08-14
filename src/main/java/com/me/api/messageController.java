package com.me.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.me.bean.MessageBean;
import com.me.bean.StaticReportBean;
import com.me.dataentry.modal.OtpDetails;
import com.me.dataentry.repository.OtpDetailsRepository;
import com.me.model.User;
import com.me.repository.UserRepository;
import com.me.util.ApiPaths;

import java.net.URLConnection;
import java.net.URLEncoder;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;


import java.io.BufferedReader;
import java.io.IOException;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.transaction.Transactional;


@RestController
@RequestMapping(ApiPaths.MessageCtrl.CTRL)
@CrossOrigin(origins = {"https://demopgi.udiseplus.gov.in","http://10.25.26.251:4200","http://10.25.26.251:8086","http://demo.sdmis.gov.in","http://localhost:4200","http://pgi.seshagun.gov.in","https://pgi.udiseplus.gov.in"}, allowedHeaders = "*",allowCredentials = "true")
public class messageController {
	@Autowired
	UserRepository userRepository;
	@Autowired
	OtpDetailsRepository otpDetailsRepository;
	
	@RequestMapping(value = "/sentMessage", method = RequestMethod.POST)
	@Transactional
	public String sentMessage(@RequestBody MessageBean msObj) throws JsonProcessingException {	
	      String messageString = "";
	      System.out.println("In sent message");
          System.out.println(msObj.getUserId());
	      
          try
          {
              String https_url = "https://smsgw.sms.gov.in/failsafe/HttpLink?username=shagun.sms&pin=P%26j6@tRb";
                              URL url;
                              String messageval="";
                              try {
                            	 List<Object> otp= optSave(msObj.getUserId());
                                      SSLContext ssl_ctx = SSLContext.getInstance("TLS");
                                      TrustManager[] trust_mgr = get_trust_mgr();
                                      ssl_ctx.init(null, // key manager
                                                      trust_mgr, // trust manager
                                                      new SecureRandom()); // random number generator
                                      HttpsURLConnection.setDefaultSSLSocketFactory(ssl_ctx
                                                      .getSocketFactory());
//                                 String q="&message="+msObj.getMessage()+otp.get(0)+"&mnumber=91"+otp.get(1)+"&signature=SCHEDU";
                                      
//                                      String message="Dear "+msObj.getUserId()+msObj.getMessage()+" OTP for Approving the data is "+otp.get(0)+" (valid for 3 min). For any query reach us as per the details available on the website.";
                                      String message="Dear "+msObj.getUserId()+" OTP for Approving the data is "+otp.get(0)+" (valid for 3 min). For any query reach us as per the details available on the website.";
//                                      String message="Dear "+msObj.getUserId()+"," +" \nOTP for Approving the data is "+otp.get(0)+" (valid for 3 min). For any query reach us at: Phone: 011-23381570 and WhatsApp at 99710-38827";
                                      System.out.println("shamim--->"+message);
                                      
                                      String q="&message="+message+"&mnumber=91"+otp.get(1)+"&signature=UDISEP&dlt_entity_id=1101607010000029348&dlt_template_id=1107161768570242528";
                                      url = new URL(https_url);
                                      HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                                  con.setDoOutput (true);
                                  OutputStreamWriter wr = new OutputStreamWriter (con.getOutputStream());
                                  String line2=wr.toString();
                                  wr.write(q);
                                  wr.flush();
                               messageval=print_content(con);

                              } catch (MalformedURLException e) {
//                                      e.printStackTrace();
                            	  System.out.println(e);
                              
                              } catch (IOException e) {
                                      e.printStackTrace();
                              } catch (NoSuchAlgorithmException e) {
                                      e.printStackTrace();
                              } catch (KeyManagementException e) {
                                      e.printStackTrace();
                              }
                              
                              
                             
              messageString=messageval+"sms successfull";
                 
          }
          catch(NullPointerException e)
          {
              messageString=e.toString();
              e.printStackTrace();
                  //////System.out.print("Message String in case of Customer Reg not found");
          }
          catch(Exception e)
          {
                  messageString=e.toString();
                  e.printStackTrace();
                  //////System.out.print("Message String in case of Customer Reg not found");
                  
          }
        
          return messageString;
	}
	
	
	@RequestMapping(value = "/checkOtp", method = RequestMethod.POST)
	public int checkOtp(@RequestBody String otp) throws JsonProcessingException {	
//		System.out.println("otp------>"+otp);
		List<OtpDetails> list=  otpDetailsRepository.findAllByOtp(otp);
		System.out.println("otp length----->"+list.size());
		return list.size();
	}
	
	
    private  TrustManager[] get_trust_mgr() {
        TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
                public X509Certificate[] getAcceptedIssuers() {
                        return null;
                }

                public void checkClientTrusted(X509Certificate[] certs, String t) {
                }

                public void checkServerTrusted(X509Certificate[] certs, String t) {
                }
        } };
        return certs;
}
public  String print_content(HttpsURLConnection con) {
      String msg="";
          if (con != null) {

                  try {

                          //////System.out.print("****** Content of the URL ********");
                          BufferedReader br = new BufferedReader(new InputStreamReader(
                                          con.getInputStream()));

                          String input;

                          while ((input = br.readLine()) != null) {
                                  ////////System.out.print(input);
                                msg=msg+input;
                          }
                          br.close();

                  } catch (IOException e) {
                          e.printStackTrace();
                  }
          }
          return msg;
  }

public  List<Object> optSave(String userId) {
	List<Object> lst=new ArrayList<Object>();
	int random_int = (int)(Math.random() * (9999 - 1000 + 1) + 1000);
    System.out.println(random_int);
    
    try {
    	otpDetailsRepository.removeByUserName(userId);
    }catch(Exception ex) {
    	ex.printStackTrace();
    	System.out.println("delete by user");
    }
    
	OtpDetails otpDetails=new OtpDetails();
	try {
	List<User> uObj=userRepository.getByUsername(userId);
	otpDetails.setUserName(uObj.get(0).getUsername());
	otpDetails.setStateCode(uObj.get(0).getParamValue());
	otpDetails.setMobileNumber(uObj.get(0).getMobileNo());
	otpDetails.setEmail(uObj.get(0).getEmail());
	otpDetails.setOtp(String.valueOf(random_int));
	otpDetails.setOtpGerateTime(java.time.LocalDateTime.now());
	otpDetails.setOtpExpiryTime(java.time.LocalDateTime.now().plusMinutes(3));

	otpDetailsRepository.save(otpDetails);
lst.add(random_int);
lst.add(uObj.get(0).getMobileNo());
	}catch(Exception ex) {
		ex.printStackTrace();
	}
	return lst;
}
	
}
