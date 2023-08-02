package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Launch;

import java.util.List;

public class ListLaunchResponse {

    List<Launch> launches = null;
    String error = null;

    public ListLaunchResponse(List<Launch> launches, String error) {
        this.launches = launches;
        this.error = error;
    }

    public List<Launch> getLaunches() {
        return launches;
    }

    public String getError() {
        return error;
    }
}
