package com.example.questapp.business.concretes;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.business.requests.UpdateUserRequest;
import com.example.questapp.business.responses.user.CreateUserResponse;
import com.example.questapp.business.responses.user.GetAllUsersResponse;
import com.example.questapp.business.responses.user.GetUserByIdResponse;
import com.example.questapp.business.responses.user.UpdateUserResponse;
import com.example.questapp.core.utilities.mappers.ModelMapperService;
import com.example.questapp.dataAccess.UserRepository;
import com.example.questapp.entities.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserManager implements UserService {
    private ModelMapperService modelMapperService;
    private UserRepository userRepository;

    @Override
    public List<GetAllUsersResponse> getAll() {
        List<User> users = this.userRepository.findAll();
        List<GetAllUsersResponse> getAllUsersResponses = users.stream().map(user -> this.modelMapperService.forResponse().map(user, GetAllUsersResponse.class)).collect(Collectors.toList());
        return getAllUsersResponses;
    }

    @Override
    public GetUserByIdResponse getById(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();
        GetUserByIdResponse getUserByIdResponse = this.modelMapperService.forResponse().map(user, GetUserByIdResponse.class);
        return getUserByIdResponse;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userRepository.save(user);
        return this.modelMapperService.forResponse().map(user, CreateUserResponse.class);
    }

    @Override
    public UpdateUserResponse updateUser(UpdateUserRequest updateUserRequest, Long id) {
        this.userRepository.findById(id).orElseThrow();
        User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        return this.modelMapperService.forResponse().map(user, UpdateUserResponse.class);
    }

    @Override
    public Long deleteUser(Long id) {
        User user = this.userRepository.findById(id).orElseThrow();
        this.userRepository.deleteById(id);
        return user.getId();
    }

    public User getOneUserByUserName(String username) {
        return this.userRepository.findByUserName(username);
    }
}
