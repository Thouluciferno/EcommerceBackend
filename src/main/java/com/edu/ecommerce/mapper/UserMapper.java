package com.edu.ecommerce.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.edu.ecommerce.dto.request.User.UserCreationRequest;
import com.edu.ecommerce.dto.request.User.UserUpdateRequest;
import com.edu.ecommerce.dto.response.User.UserResponse;
import com.edu.ecommerce.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toUser(UserCreationRequest request);

    UserResponse toUserResponse(User user);

    @Mapping(target = "roles", ignore = true)
    void updateUser(@MappingTarget User user, UserUpdateRequest request);

    List<UserResponse> toUserResponseList(List<User> all);
}
