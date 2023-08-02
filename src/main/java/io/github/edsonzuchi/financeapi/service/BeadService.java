package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.orm.Launch;
import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.BeadRepository;
import io.github.edsonzuchi.financeapi.repository.LaunchRepository;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BeadService {

    private final BeadRepository beadRepository;
    private final UserRepository userRepository;
    private final LaunchRepository launchRepository;


    public BeadService(BeadRepository beadRepository, UserRepository userRepository, LaunchRepository launchRepository) {
        this.beadRepository = beadRepository;
        this.userRepository = userRepository;
        this.launchRepository = launchRepository;
    }

    public BeadResponse findId(Long id){
        return beadRepository.findById(id).map(value -> new BeadResponse(value, null))
                .orElseGet(() -> new BeadResponse(null, "bead not found"));
    }

    public BeadResponse deleteBead(Long id){
        Optional<Bead> beadOptional = beadRepository.findById(id);
        if(beadOptional.isEmpty()){
            return new BeadResponse(null, "bead not found");
        }
        Bead bead = beadOptional.get();
        launchRepository.deleteAllLaunchOfBead(bead);
        beadRepository.delete(bead);
        return new BeadResponse(null,null);
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
            bead.setReferenceDate(LocalDate.now());
        }

        if(bead.getInstallments() == null || bead.getInstallments() == 0){
            bead.setInstallments(1);
        }

        bead = beadRepository.save(bead);
        int numDias = 0;
        for(int repet = 1; repet <= bead.getInstallments(); repet++){
            Launch launch = new Launch();
            launch.setBead(bead);
            launch.setValue(bead.getValue()/ bead.getInstallments());
            launch.setInstallment(repet);
            launch.setReferenceDate(bead.getReferenceDate().plusDays(numDias));
            launchRepository.save(launch);
            numDias += 30;
        }

        return new BeadResponse(bead, null);
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
