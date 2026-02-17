package com.company.user_service.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SmsService {

    private static final String API_KEY = "d443f5c3-c24f-11f0-a6b2-0200cd936042";

    public void sendOtpSms(String phone, String otp) {
        try {
            // 2Factor send your own OTP
            String url = "https://2factor.in/API/V1/" 
                    + API_KEY 
                    + "/SMS/" 
                    + phone 
                    + "/" 
                    + otp 
                    + "?voice=false";

            RestTemplate rest = new RestTemplate();
            rest.getForObject(url, String.class);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to send SMS OTP");
        }
    }
}
