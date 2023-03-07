package com.example.questapp.business.concretes;

import com.example.questapp.business.abstracts.UserService;
import com.example.questapp.business.requests.CreateUserRequest;
import com.example.questapp.business.requests.UpdateUserRequest;
import com.example.questapp.business.responses.GetAllUsersResponse;
import com.example.questapp.business.responses.GetUserByIdResponse;
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
    public Long createUser(CreateUserRequest createUserRequest) {
        User user = this.modelMapperService.forRequest().map(createUserRequest, User.class);
        this.userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long updateUser(UpdateUserRequest updateUserRequest) {
        User user = this.modelMapperService.forRequest().map(updateUserRequest, User.class);
        this.userRepository.save(user);
        return user.getId();
    }

    @Override
    public Long deleteUser(Long id) {
        this.userRepository.deleteById(id);
        return id;
    }
}
