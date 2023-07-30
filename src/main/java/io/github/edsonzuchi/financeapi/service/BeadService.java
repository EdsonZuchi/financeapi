package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.BeadRepository;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.response.UserResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class BeadService {

    private final BeadRepository beadRepository;
    private final UserRepository userRepository;

    public BeadService(BeadRepository beadRepository, UserRepository userRepository) {
        this.beadRepository = beadRepository;
        this.userRepository = userRepository;
    }

    public BeadResponse findId(Long id){
        return beadRepository.findById(id).map(value -> new BeadResponse(value, null))
                .orElseGet(() -> new BeadResponse(null, "bead not found"));
    }

    public BeadResponse newBead(Bead bead){
        try{
            validateBead(bead);
        }catch (Exception e){
            return new BeadResponse(null, e.toString());
        }

        Optional<User> user = userRepository.findById(bead.getUser().getId());
        if(user.isPresent()){
            bead.setUser(user.get());
        }else{
            return new BeadResponse(null, "user not found");
        }

        if(bead.getReferenceDate() == null){
            bead.setReferenceDate(new Date());
        }

        if(bead.getInstallments() == null || bead.getInstallments() == 0){
            bead.setInstallments(1);
        }

        return new BeadResponse(beadRepository.save(bead), null);
    }

    public void validateBead(Bead bead){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Bead>> violations = validator.validate(bead);

        if (violations.isEmpty()) {
            return;
        }

        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<Bead> violation: violations){
            messages.add(violation.getMessage());
        }
        throw new IllegalArgumentException(messages.toString());
    }


}
