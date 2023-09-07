package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.orm.Bill;
import io.github.edsonzuchi.financeapi.response.BillResponse;
import io.github.edsonzuchi.financeapi.service.BillService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bill")
public class BillController {

    private final BillService billService;

    public BillController(BillService userService) {
        this.billService = userService;
    }

    @GetMapping("/{id}")
    public BillResponse visualizeBill(@PathVariable Long id){
        return billService.findId(id);
    }

    @PostMapping
    public BillResponse newBill(@RequestBody Bill bill){
        return billService.newBill(bill);
    }

    @DeleteMapping("/{id}")
    public BillResponse deleteBill(@PathVariable Long id){
        return billService.deleteBill(id);
    }
}
