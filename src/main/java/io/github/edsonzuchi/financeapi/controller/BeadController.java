package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.response.ListBeadResponse;
import io.github.edsonzuchi.financeapi.service.BeadService;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bead")
public class BeadController {

    private final BeadService beadService;

    public BeadController(BeadService beadService) {
        this.beadService = beadService;
    }

    @GetMapping("/{userId}/{year}/{month}")
    public ListBeadResponse findBeads(@PathVariable Long userId, @PathVariable Integer year, @PathVariable Integer month){
        return beadService.findBeads(userId, year, month);
    }

    @GetMapping("/{id}")
    public BeadResponse findBead(@PathVariable Long id){
        return beadService.findBead(id);
    }

    @PostMapping
    public BeadResponse newBead(@RequestBody Bead bead){
        return beadService.saveNewBead(bead);
    }

    @DeleteMapping("/{id}")
    public BeadResponse deleteBead(@PathVariable Long id){
        return beadService.deleteBead(id);
    }
}
