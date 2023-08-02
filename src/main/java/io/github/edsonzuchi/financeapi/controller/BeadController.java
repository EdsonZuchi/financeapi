package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.service.BeadService;
import io.github.edsonzuchi.financeapi.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bead")
public class BeadController {

    private final BeadService beadService;

    public BeadController(BeadService userService) {
        this.beadService = userService;
    }

    @GetMapping("/{id}")
    public BeadResponse visualizeBead(@PathVariable Long id){
        return beadService.findId(id);
    }

    @PostMapping
    public BeadResponse newBead(@RequestBody Bead bead){
        return beadService.newBead(bead);
    }

    @DeleteMapping("/{id}")
    public BeadResponse deleteBead(@PathVariable Long id){
        return beadService.deleteBead(id);
    }
}
