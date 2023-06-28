package com.user.user_registration.user;
import java.util.Optional;

public interface userService {
    userModel saveUser(userModel user);
    Iterable<userModel> getAllUsers();
    Optional<userModel> findUserById(Long id);
    void deleteUserById(Long id);
}
