package com.company.user_service.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.company.user_service.dto.CandidateRegisterRequest;
import com.company.user_service.service.CandidateService;

@RestController
@RequestMapping("/api/candidates")
public class CandidateController {

    private final CandidateService candidateService;

    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    // Step 1: Send OTPs to email + phone
    @PostMapping("/send-otp/email")
    public ResponseEntity<String> sendEmailOtps(@RequestParam String email) {
        candidateService.sendEmailOtps(email);
        return ResponseEntity.ok("OTP sent to email"+email);
    }
    
    @PostMapping("/send-otp/phone")
    public ResponseEntity<String> sendPhoneOtps( @RequestParam String phone) {
        candidateService.sendPhoneOtps( phone);
        return ResponseEntity.ok("OTP sent to phone no "+phone);
        
    }

    // Step 2: Register candidate after OTP verification
//    @PostMapping("/register")
//    public ResponseEntity<String> registerCandidate(@RequestParam String fullName,
//                                                    @RequestParam String email,
//                                                    @RequestParam String phone,
//                                                    @RequestParam String password,
//                                                    @RequestParam String emailOtp,
//                                                    @RequestParam String phoneOtp) {
//        candidateService.registerCandidate(fullName, email, phone, password, emailOtp, phoneOtp);
//        return ResponseEntity.ok("Candidate registered successfully");
//    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerCandidate(@RequestBody CandidateRegisterRequest request) {
        candidateService.registerCandidate(
            request.getFullName(),
            request.getEmail(),
            request.getPhone(),
            request.getPassword(),
            request.getEmailOtp(),
            request.getPhoneOtp()
        );
        return ResponseEntity.ok("Candidate registered successfully");
    }
}
//}
