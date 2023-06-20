package com.user.user_registration.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class userController {

    private final userService user_Service;

    @Autowired
    public userController(userService user_Service) {
        this.user_Service = user_Service;   
    }

    @PostMapping("/createUser")
    public ResponseEntity<String> createUser(@RequestBody userModel user) {
        
        if(user_Service.existsByEmail(user.getEmail())) {
            System.out.print("email already exists");
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Email already exists");
        }
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // Encode the password
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        user_Service.saveUser(user);
        return ResponseEntity.ok("user created successfully");
    }
    
    @DeleteMapping("/deleteUser/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
    user_Service.deleteUserById(id);
    return ResponseEntity.ok().build();
}


    @GetMapping
    public ResponseEntity<Iterable<userModel>> getAllUsers() {
        return ResponseEntity.ok(user_Service.getAllUsers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<userModel> getUserById(@PathVariable Long id) {
        Optional<userModel> fetchedUser = user_Service.findUserById(id);
        return fetchedUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


}
