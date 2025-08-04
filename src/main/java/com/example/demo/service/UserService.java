package com.example.demo.service;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO createUser(UserDTO userDTO) {
        logger.debug("Received request to create user with name: {}", userDTO.getName());
        try {
            User user = userMapper.toEntity(userDTO);
            User saved = userRepository.save(user);
            logger.info("User created successfully with id: {}", saved.getId());
            return userMapper.toDTO(saved);
        } catch (Exception e) {
            logger.error("Error creating user", e);
            throw e; // rethrow or handle accordingly
        }
    }


    public List<UserDTO> getAllUsers() {
        return userMapper.toDTOs(userRepository.findAll());
    }
}
