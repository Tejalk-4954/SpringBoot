package com.pgfinder.pgfinder_backend.service;

import java.io.File;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.pgfinder.pgfinder_backend.Entity.User;
import com.pgfinder.pgfinder_backend.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(User user, MultipartFile aadharFile) throws IOException {
        // Mock Aadhar verification: Assume file is valid and extract gender (in reality, use OCR/API)
        if (!aadharFile.isEmpty() && user.getGender().equals("Female")) { // Mock check
            String path = "src/main/resources/static/aadhar/" + user.getId() + ".jpg";
            aadharFile.transferTo(new File(path));
            user.setAadharPath(path);
            return userRepository.save(user);
        }
        throw new RuntimeException("Invalid Aadhar or gender not female");
    }

    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password) && user.getGender().equals("Female")) {
            return user;
        }
        return null;
    }
}