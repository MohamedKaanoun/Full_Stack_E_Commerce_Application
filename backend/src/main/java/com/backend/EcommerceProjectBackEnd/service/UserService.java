package com.backend.EcommerceProjectBackEnd.service;

import com.backend.EcommerceProjectBackEnd.exception.UserException;
import com.backend.EcommerceProjectBackEnd.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {
    public User findUserById(Integer userid) throws Exception;

    public User findUserProfileByJwt(String jwt) throws UserException;

}
