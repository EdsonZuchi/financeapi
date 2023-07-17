package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.Bead;
import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.BeadRepository;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.BeadResponse;
import io.github.edsonzuchi.financeapi.response.ListBeadResponse;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class BeadService {

    private final BeadRepository beadRepository;
    private final UserRepository userRepository;

    public BeadService(BeadRepository beadRepository, UserRepository userRepository) {
        this.beadRepository = beadRepository;
        this.userRepository = userRepository;
    }

    public ListBeadResponse findBeads(Long userId, Integer year, Integer month){
        if(month < 1 || month > 12){
            return new ListBeadResponse(null, "month is not valid");
        }
        Optional<User> user = userRepository.findById(userId);
        if(user.isPresent()){
            return new ListBeadResponse(beadRepository.findByUserAndMonth(user.get(), year, month), null);
        }else{
            return new ListBeadResponse(null, "user not found");
        }
    }

    public BeadResponse findBead(Long id){
        Optional<Bead> bead = beadRepository.findById(id);
        if(bead.isPresent()){
            return new BeadResponse(bead.get(), null);
        }else{
            return new BeadResponse(null, "bead not found");
        }
    }

    public BeadResponse saveNewBead(Bead bead){
        if(bead == null){
            return new BeadResponse(null, "bead is null");
        }
        if(bead.getTitle() == null){
            return new BeadResponse(null, "title not informed");
        }
        if(bead.getTitle().trim().equals("")){
            return new BeadResponse(null, "title cannot be empty");
        }
        if(bead.getUser() == null){
            return new BeadResponse(null, "user is null");
        }
        if(bead.getUser().getId() == null){
            return new BeadResponse(null, "user id is null");
        }
        Optional<User> user = userRepository.findById(bead.getUser().getId());
        if(user.isPresent()){
            bead.setUser(user.get());
        }else{
            return new BeadResponse(null, "user is not found");
        }
        if(bead.getValue() == null){
            return new BeadResponse(null, "value is null");
        }
        if(bead.getValue() == 0){
            return new BeadResponse(null, "value cannot be zero");
        }
        if(bead.getInstallment() == null){
            bead.setInstallment(1);
        }
        if(bead.getInstallment() == 0){
            return new BeadResponse(null, "installment cannot is zero");
        }
        if(bead.getDescription() == null){
            bead.setDescription("");
        }
        if(bead.getReferenceDate() == null){
            return new BeadResponse(null, "date is null");
        }
        if(bead.getReferenceDate().isBefore(LocalDate.now())){
            return new BeadResponse(null, "date is before in now");
        }

        return new BeadResponse(beadRepository.save(bead), null);
    }

    public BeadResponse deleteBead(Long id){
        Optional<Bead> bead = beadRepository.findById(id);
        if(bead.isPresent()){
            beadRepository.delete(bead.get());
            return new BeadResponse(null, null);
        }else{
            return new BeadResponse(null, "bead is not found");
        }
    }
}