package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Bead;

import java.util.List;

public class ListBeadResponse {

    List<Bead> beads = null;
    String error = null;

    public ListBeadResponse(List<Bead> beads, String error) {
        this.beads = beads;
        this.error = error;
    }

    public List<Bead> getBeads() {
        return beads;
    }

    public void setBeads(List<Bead> beads) {
        this.beads = beads;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
