package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.ListUserResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public ListUserResponse findAllUsers(){
        return new ListUserResponse(userRepository.findAll(), null);
    }

    public UserResponse findByIdUser(Long id){
        return userRepository.findById(id).map(value -> new UserResponse(value, null))
                                          .orElseGet(() -> new UserResponse(null, "user not found"));
    }

    public UserResponse saveNewUser(User user){
        try {
            validateUser(user);
        }catch (Exception e){
            return new UserResponse(null, e.toString());
        }
        user.setUsername(generateUsername(user.getName()));
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return new UserResponse(null, "e-mail is present in database");
        }
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new UserResponse(null, "username is present in database");
        }
        return new UserResponse(userRepository.save(user), null);
    }

    private String generateUsername(String name){
        String[] partsOfName = name.split(" ");
        String username = partsOfName[0].trim()+"."+partsOfName[partsOfName.length - 1].trim();
        if(username.length() > 30){
            username = username.substring(0, 30);
        }
        return username.toLowerCase();
    }

    private void validateUser(User user){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<User>> violations = validator.validate(user);

        if (violations.isEmpty()) {
            return;
        }

        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<User> violation: violations){
            messages.add(violation.getMessage());
        }
        throw new IllegalArgumentException(messages.toString());
    }
}
