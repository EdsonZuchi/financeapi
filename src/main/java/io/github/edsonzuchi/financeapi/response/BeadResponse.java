package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Bead;

public class BeadResponse {

    Bead bead = null;
    String error = null;

    public BeadResponse(Bead bead, String error) {
        this.bead = bead;
        this.error = error;
    }

    public Bead getBead() {
        return bead;
    }

    public String getError() {
        return error;
    }
}
