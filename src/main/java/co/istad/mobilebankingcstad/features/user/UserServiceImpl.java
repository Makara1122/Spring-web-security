package co.istad.mobilebankingcstad.features.user;


import co.istad.mobilebankingcstad.domain.Role;
import co.istad.mobilebankingcstad.domain.User;
import co.istad.mobilebankingcstad.features.roles.RoleRepository;
import co.istad.mobilebankingcstad.features.user.dto.UserRequest;
import co.istad.mobilebankingcstad.features.user.dto.UserResponse;
import co.istad.mobilebankingcstad.features.user.dto.UserUpdateRequest;
import co.istad.mobilebankingcstad.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Struct;
import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {



    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserMapper userMapper;

//    @Override
//    public UserResponse updateUserById(String id, UserUpdateRequest userUpdateRequest) {
//        var updateUser = userRepository.findById(id)
//                .orElseThrow(() ->
//                        new NoSuchElementException("There is no user with id = " + id));
//        userMapper.updateUserFromRequest(updateUser, userUpdateRequest);
//
//
//        return null;
//    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
//        check if username and email already exist

        if (userRepository.existsByUsername(userRequest.username())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Username already exists");
        }
        if (userRepository.existsByEmail(userRequest.email())){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Email already exists");
        }
        System.out.println(userRequest);

        //userRepository.save(userRequest);
        Set<Role> roles = new HashSet<>();
        for (var role : userRequest.roles()) {
            var roleObj = roleRepository.findByName(role)
                    .orElseThrow(
                            () -> new NoSuchElementException(
                                    "Role: <" + role + "> could not found!"
                            )
                    );
            roles.add(roleObj);
        }
        User newUser = userMapper.requestToUser(userRequest);
        newUser.setIsBlocked(false);
        newUser.setIsDeleted(false);
        newUser.setRoles(roles);
        userRepository.save(newUser);
        return userMapper.toUserResponse(newUser);
    }

    @Override
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream().map(userMapper::toUserResponse).toList();
    }

    @Override
    public UserResponse getUserById(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("There is no user with id = " + id));
        return userMapper.toUserResponse(user);
    }

    @Override
    public void deleteUserById(String id) {
        var user = userRepository.findById(id)
                .orElseThrow(() ->
                        new NoSuchElementException("There is no user with id = " + id));
        userRepository.delete(user);

    }

    @Override
    public UserResponse updateUserById(String id, UserUpdateRequest userRequest) {
        var updateUser = userRepository.findById(id).
                orElseThrow(() -> new NoSuchElementException("There is no user with = " + id));
        userMapper.updateUserFromRequest(updateUser, userRequest);

        userRepository.save(updateUser);

        return userMapper.toUserResponse(updateUser);
    }

    @Override
    public UserResponse disableUser(String id) {
       int affectedRow =  userRepository.updateBlockedStatusById(id,true);
        if(affectedRow > 0){
            return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(null));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User wit id = " + id + " not found!");
        }

    }

    @Override
    public UserResponse enableUser(String id) {
        int affectRow = userRepository.updateBlockedStatusById(id,false);
        if (affectRow > 0 ){
            return userMapper.toUserResponse(userRepository.findById(id).orElseThrow(null));
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with id = " + id + " not found!");
        }

    }

}
