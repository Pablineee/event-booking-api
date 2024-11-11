package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserRequest;
import ca.gbc.userservice.dto.UserResponse;

import java.util.List;

public interface UserService {
    List<UserResponse> getAllUsers();
    UserResponse getUser(String userId);
    boolean isStudent(Long userId);
    boolean isStaff(Long userId);
    UserResponse createUser(UserRequest userRequest);
    String updateUser(String userId, UserRequest userRequest);
    void deleteUser(String userId);
}
