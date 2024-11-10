package ca.gbc.userservice.service;

import ca.gbc.userservice.dto.UserRequest;
import ca.gbc.userservice.dto.UserResponse;
import ca.gbc.userservice.model.User;
import ca.gbc.userservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public List<UserResponse> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map( user -> UserResponse.builder()
                        .id(user.getId())
                        .name(user.getName())
                        .email(user.getEmail())
                        .role(user.getRole())
                        .userType(user.getUserType())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse getUser(String userId) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .userType(user.getUserType())
                .build();
    }

    @Override
    public boolean isStudent(Long userId) {
        return userRepository.findById(userId)
                .map(user -> "student".equalsIgnoreCase(user.getRole()))
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        User user = User.builder()
                .name(userRequest.name())
                .email(userRequest.email())
                .role(userRequest.role())
                .userType(userRequest.userType())
                .build();

        User savedUser = userRepository.save(user);

        return UserResponse.builder()
                .id(savedUser.getId())
                .name(savedUser.getName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .userType(savedUser.getUserType())
                .build();
    }

    @Override
    public String updateUser(String userId, UserRequest userRequest) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

        user.setName(userRequest.name());
        user.setEmail(userRequest.email());
        user.setRole(userRequest.role());
        user.setUserType(userRequest.userType());

        User updatedUser = userRepository.save(user);
        return String.valueOf(updatedUser.getId());
    }

    @Override
    public void deleteUser(String userId) {
        User user = userRepository.findById(Long.parseLong(userId))
                .orElseThrow(() -> new RuntimeException("User with ID " + userId + " not found"));

        userRepository.delete(user);
    }
}
