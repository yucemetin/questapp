package com.example.questapp.business.abstracts;

import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.business.requests.UpdateUserRequest;
import com.example.questapp.business.responses.GetAllUsersResponse;
import com.example.questapp.business.responses.GetUserByIdResponse;

import java.util.List;

public interface UserService {

    List<GetAllUsersResponse> getAll();

    GetUserByIdResponse getById(Long id);

    Long createUser(CreateUserRequest createUserRequest);

    Long updateUser(UpdateUserRequest updateUserRequest);

    Long deleteUser(Long id);

}
