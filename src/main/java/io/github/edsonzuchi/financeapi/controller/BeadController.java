package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.service.BeadService;
import io.github.edsonzuchi.financeapi.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bead")
public class BeadController {

    private final BeadService beadService;

    public BeadController(BeadService userService) {
        this.beadService = userService;
    }

    @GetMapping("/{id}")
    public BeadResponse visibleId(@PathVariable Long id){
        return beadService.findId(id);
    }
}
