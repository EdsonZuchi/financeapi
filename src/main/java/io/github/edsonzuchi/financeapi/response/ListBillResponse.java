package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Bill;

import java.util.List;

public class ListBillResponse {

    List<Bill> bills = null;
    String error = null;

    public ListBillResponse(List<Bill> bills, String error) {
        this.bills = bills;
        this.error = error;
    }

    public List<Bill> getBills() {
        return bills;
    }

    public String getError() {
        return error;
    }
}
