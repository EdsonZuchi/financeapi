package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.User;

import java.util.List;

public class ListUserResponse {
    List<User> users = null;
    String error = null;

    public ListUserResponse(List<User> users, String error) {
        this.users = users;
        this.error = error;
    }

    public List<User> getUsers() {
        return users;
    }

    public String getError() {
        return error;
    }
}
