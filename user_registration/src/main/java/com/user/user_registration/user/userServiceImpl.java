package com.user.user_registration.user;

import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class userServiceImpl implements userService {

    private final userRepository userRepository;

    public userServiceImpl(userRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public userModel saveUser(userModel user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<userModel> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public Optional<userModel> findUserById(Long id) {
        return userRepository.findById(id);
    }

    public void  deleteUserById(Long id) {
         userRepository.deleteById(id);
    }

 
}
