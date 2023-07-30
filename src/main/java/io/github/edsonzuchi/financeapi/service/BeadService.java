package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.repository.BeadRepository;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import org.springframework.stereotype.Service;

@Service
public class BeadService {

    private final BeadRepository beadRepository;

    public BeadService(BeadRepository beadRepository) {
        this.beadRepository = beadRepository;
    }

    public BeadResponse findId(Long id){
        return beadRepository.findById(id).map(value -> new BeadResponse(value, null))
                .orElseGet(() -> new BeadResponse(null, "bead not found"));
    }


}
