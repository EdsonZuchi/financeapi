package io.github.edsonzuchi.financeapi.response;

public class ValueResponse {

    Double value = null;
    String error = null;

    public ValueResponse(Double value, String error) {
        this.value = value;
        this.error = error;
    }

    public Double getValue() {
        return value;
    }

    public String getError() {
        return error;
    }
}
