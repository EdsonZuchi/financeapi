package io.github.edsonzuchi.financeapi.controller;

import io.github.edsonzuchi.financeapi.response.ListLaunchResponse;
import io.github.edsonzuchi.financeapi.service.LaunchService;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/launch")
public class LaunchController {

    private final LaunchService launchService;

    public LaunchController(LaunchService launchService) {
        this.launchService = launchService;
    }

    @GetMapping("/{year}/{month}")
    public ListLaunchResponse findYearAndMonthLaunch(@PathVariable Integer year, @PathVariable Integer month){
        return launchService.findLaunches(year, month);
    }
}
