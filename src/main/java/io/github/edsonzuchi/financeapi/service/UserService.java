package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.ListUserResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.constraints.NotBlank;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    public UserResponse checksUser(String username, String birthday) {
        if(birthday.isBlank()){
            return new UserResponse(null, "Birthday is blank");
        }
        LocalDate dateBirthday = LocalDate.parse(birthday);
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isEmpty()) {
            return new UserResponse(null, "Username not found");
        }
        User user = userOptional.get();

        if (!dateBirthday.equals(LocalDate.parse(user.getBirthday()))) {
            return new UserResponse(null, "Birthday is not compatible");
        }
        return new UserResponse(user, null);
    }

    public UserResponse saveNewUser(User user){
        try {
            validateUser(user);
        }catch (Exception e){
            return new UserResponse(null, e.toString());
        }
        user.setUsername(generateUsername(user.getName()));
        if(userRepository.findByEmail(user.getEmail()).isPresent()){
            return new UserResponse(null, "E-mail is present in database");
        }
        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return new UserResponse(null, "Username is present in database");
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
