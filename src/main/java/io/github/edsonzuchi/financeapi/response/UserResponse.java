package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.User;

public class UserResponse {

    User user = null;
    String error = null;

    public UserResponse(User user, String error) {
        this.user = user;
        this.error = error;
    }

    public User getUser() {
        return user;
    }

    public String getError() {
        return error;
    }
}
