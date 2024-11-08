package com.edu.ecommerce.service;

import java.util.HashSet;
import java.util.List;

import com.edu.ecommerce.dto.request.ApiResponse;
import com.edu.ecommerce.dto.response.AuthenticationResponse;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.edu.ecommerce.dto.request.User.UserCreationRequest;
import com.edu.ecommerce.dto.request.User.UserUpdateRequest;
import com.edu.ecommerce.dto.response.User.UserResponse;
import com.edu.ecommerce.entity.User;
import com.edu.ecommerce.enums.Role;
import com.edu.ecommerce.exception.AppException;
import com.edu.ecommerce.exception.ErrorCode;
import com.edu.ecommerce.mapper.UserMapper;
import com.edu.ecommerce.repository.RoleRepository;
import com.edu.ecommerce.repository.UserRepository;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    UserMapper userMapper;
    PasswordEncoder passwordEncoder;

    AuthenticationService authenticationService;


    //    @PreAuthorize("hasRole('CREATE_DATA')")
    public AuthenticationResponse createUser(UserCreationRequest userCreationRequest) {

        if (userRepository.existsByUsername(userCreationRequest.getUsername())) {
            throw new AppException(ErrorCode.USER_EXISTED);
        }

        User user = userMapper.toUser(userCreationRequest);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        user.setEmail(userCreationRequest.getEmail());

        user.setNumberPhone(userCreationRequest.getNumberPhone());

        HashSet<String> roles = new HashSet<>();
        roles.add(Role.USER.name());

        user.setRoles(null);

//        save user and get user
        User savedUser = userRepository.save(user);


        return authenticationService.authenticate(savedUser.getUsername(), userCreationRequest.getPassword());
    }

    //    update User
    public UserResponse updateUser( UserUpdateRequest request) {

        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        log.info("username: {}", name);
        log.info("user: {}", user);

        log.info("email: {}", user.getEmail());
        log.info("numberPhone: {}", user.getNumberPhone());
        user.setNumberPhone(request.getNumberPhone());
        user.setEmail(request.getEmail());

        return userMapper.toUserResponse(userRepository.save(user));

    }

    //    getMyInf

    public UserResponse getMyInfo() {
        var context = SecurityContextHolder.getContext();
        String name = context.getAuthentication().getName();

        User user = userRepository.findByUsername(name).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND));

        log.info("id: {}", user.getId());
        log.info("user: {}", user.getUsername());
        log.info("username: {}", context.getAuthentication().getName());
        log.info("password: {}", context.getAuthentication().getCredentials());
        log.info("role: {}", context.getAuthentication().getAuthorities());

        log.info("email: {}", user.getEmail());
        log.info("numberPhone: {}", user.getNumberPhone());


        return userMapper.toUserResponse(user);
    }

    //    get List User

    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public List<User> getListUser() {
        log.info(
                "Authorized user: {}",
                SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findAll();
    }

    @PostAuthorize("returnObject.username == authentication.name")
    public UserResponse getUserById(Long id) {

        log.info("Fetching user by id: {}", id);
        return userMapper.toUserResponse(
                userRepository.findById(id).orElseThrow(() -> new AppException(ErrorCode.USER_NOT_FOUND)));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @PreAuthorize("hasAuthority('CREATE_DATA')")
    public List<UserResponse> getUser() {

        log.info(
                "Authorized user: {}",
                SecurityContextHolder.getContext().getAuthentication().getName());

        log.info(
                "Fetching user by id: {}",
                SecurityContextHolder.getContext().getAuthentication().getAuthorities());

        return userMapper.toUserResponseList(userRepository.findAll());
    }


}
