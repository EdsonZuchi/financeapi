package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.repository.LaunchRepository;
import io.github.edsonzuchi.financeapi.response.ListLaunchResponse;
import org.springframework.stereotype.Service;

@Service
public class LaunchService {

    private final LaunchRepository launchRepository;

    public LaunchService(LaunchRepository launchRepository) {
        this.launchRepository = launchRepository;
    }

    public ListLaunchResponse findLaunches(Integer year, Integer month){
        if(month < 1 || month > 12){
            return new ListLaunchResponse(null, "Month invalid");
        }

        return new ListLaunchResponse(launchRepository.findByYearAndMonth(year, month), null);
    }
}
