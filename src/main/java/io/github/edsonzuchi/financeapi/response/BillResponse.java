package io.github.edsonzuchi.financeapi.response;

import io.github.edsonzuchi.financeapi.orm.Bill;

public class BillResponse {

    Bill bill = null;
    String error = null;

    public BillResponse(Bill bill, String error) {
        this.bill = bill;
        this.error = error;
    }

    public Bill getBill() {
        return bill;
    }

    public String getError() {
        return error;
    }
}
