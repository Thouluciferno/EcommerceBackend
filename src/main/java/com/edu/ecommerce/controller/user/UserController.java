package com.edu.ecommerce.controller.user;

import java.util.List;

import com.edu.ecommerce.dto.response.AuthenticationResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.edu.ecommerce.dto.request.ApiResponse;
import com.edu.ecommerce.dto.request.User.UserCreationRequest;
import com.edu.ecommerce.dto.request.User.UserUpdateRequest;
import com.edu.ecommerce.dto.response.User.UserResponse;
import com.edu.ecommerce.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class UserController {

     UserService userService;

    @PostMapping
    ApiResponse<AuthenticationResponse> createUser(@RequestBody @Valid UserCreationRequest request) {
        log.info("controller: create user: {}", request);
        var result = userService.createUser(request);

        return ApiResponse.<AuthenticationResponse>builder()
                .result(result)
                .build();
    }

//    get user by authetication


//    @PutMapping("/{id}")
//    UserResponse updateUser(@PathVariable("id") Long id, @RequestBody @Valid UserUpdateRequest request) {
//
//        return userService.updateUser(id, request);
//    }

//    @GetMapping("/{id}")
//    UserResponse getUserById(@PathVariable("id") Long id) {
//        return userService.getUserById(id);
//    }

    //    delete

//    @DeleteMapping("/{id}")
//    void deleteUser(@PathVariable("id") Long id) {
//        userService.deleteUser(id);
//    }

    //    myInfo

    @GetMapping("/myInfo")
    ApiResponse<UserResponse> getMyInfo() {

        return ApiResponse.<UserResponse>builder()
                .result(userService.getMyInfo())
                .build();
    }

//    update user

    @PutMapping()
    ApiResponse<UserResponse> updateUser(@RequestBody @Valid UserUpdateRequest request) {
        return ApiResponse.<UserResponse>builder()
                .result(userService.updateUser(request))
                .build();
    }

    //    getUser

    @GetMapping
    ApiResponse<List<UserResponse>> getUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        log.info("username: {}", authentication.getName());
        authentication.getAuthorities().forEach(r -> log.info("role: {}", r.getAuthority()));

        return ApiResponse.<List<UserResponse>>builder()
                .result(userService.getUser())
                .build();
    }
}
