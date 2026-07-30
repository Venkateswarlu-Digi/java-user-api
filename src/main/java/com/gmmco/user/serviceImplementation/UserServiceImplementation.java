package com.gmmco.user.serviceImplementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gmmco.user.model.User;
import com.gmmco.user.repository.UserRepository;
import com.gmmco.user.service.UserService;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUser(String empId, String email) {

        if (empId != null) {
            return userRepository.findByEmpId(empId)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        if (email != null && !email.isBlank()) {
            return userRepository.findByEmail(email)
                    .orElseThrow(() -> new RuntimeException("User not found"));
        }

        throw new RuntimeException("Please provide either id or email");
    }
}
