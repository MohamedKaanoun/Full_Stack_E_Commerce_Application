package com.backend.EcommerceProjectBackEnd.service.impl;

import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.User;
import com.backend.EcommerceProjectBackEnd.repository.UserRepository;
import com.backend.EcommerceProjectBackEnd.security.JwtService;
import com.backend.EcommerceProjectBackEnd.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImplementation.class);
    private UserRepository userRepository;
    private JwtService jwtService;

    @Override
    public User findUserById(Integer userId) throws UserException {
        return userRepository.findById(userId).orElseThrow(() ->
                new UserException("The user with this id : " + userId + " does not exist"));
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtService.extractUsername(jwt);
        logger.debug("Extracted email from JWT: {}", email);

        User user = userRepository.findByEmail(email);
        if (user != null) {
            return user;
        } else {
            throw new UserException("User with email " + email + " does not exist");
        }
    }
}

