package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.ListUserResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import org.springframework.stereotype.Service;

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
        if(user != null){
            if(user.getEmail() == null){
                return new UserResponse(null, "email is null");
            }
            if(user.getName() == null){
                return new UserResponse(null, "name is null");
            }
            if(user.getName().trim().equals("")){
                return new UserResponse(null, "uninformed user name");
            }
            if(user.getEmail().trim().equals("")){
                return new UserResponse(null, "uninformed user email");
            }
            user.setUsername(geraUsername(user.getName()));
            return new UserResponse(userRepository.save(user), null);
        }else{
            return new UserResponse(null, "uninformed user");
        }
    }

    private String geraUsername(String name){
        String[] partsOfName = name.split(" ");
        String username = partsOfName[0].trim()+"."+partsOfName[partsOfName.length - 1].trim();
        if(username.length() > 30){
            username = username.substring(0, 30);
        }
        return username.toLowerCase();
    }
}
