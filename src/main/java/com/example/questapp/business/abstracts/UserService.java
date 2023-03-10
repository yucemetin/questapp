package com.example.questapp.business.abstracts;

import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.business.requests.UpdateUserRequest;
import com.example.questapp.business.responses.user.CreateUserResponse;
import com.example.questapp.business.responses.user.GetAllUsersResponse;
import com.example.questapp.business.responses.user.GetUserByIdResponse;
import com.example.questapp.business.responses.user.UpdateUserResponse;

import java.util.List;

public interface UserService {

    List<GetAllUsersResponse> getAll();

    GetUserByIdResponse getById(Long id);

    CreateUserResponse createUser(CreateUserRequest createUserRequest);

    UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest, Long id);

    Long deleteUser(Long id);

}
