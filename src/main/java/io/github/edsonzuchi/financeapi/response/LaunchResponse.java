package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Launch;

public class LaunchResponse {

    Launch launch = null;
    String error = null;

    public LaunchResponse(Launch launch, String error) {
        this.launch = launch;
        this.error = error;
    }

    public Launch getLaunch() {
        return launch;
    }

    public String getError() {
        return error;
    }
}
