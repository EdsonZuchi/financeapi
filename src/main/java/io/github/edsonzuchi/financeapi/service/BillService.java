package io.github.edsonzuchi.financeapi.service;

import io.github.edsonzuchi.financeapi.orm.Bill;
import io.github.edsonzuchi.financeapi.orm.Launch;
import io.github.edsonzuchi.financeapi.orm.User;
import io.github.edsonzuchi.financeapi.repository.BillRepository;
import io.github.edsonzuchi.financeapi.repository.LaunchRepository;
import io.github.edsonzuchi.financeapi.repository.UserRepository;
import io.github.edsonzuchi.financeapi.response.BillResponse;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class BillService {

    private final BillRepository billRepository;
    private final UserRepository userRepository;
    private final LaunchRepository launchRepository;


    public BillService(BillRepository billRepository, UserRepository userRepository, LaunchRepository launchRepository) {
        this.billRepository = billRepository;
        this.userRepository = userRepository;
        this.launchRepository = launchRepository;
    }

    public BillResponse findId(Long id){
        return billRepository.findById(id).map(value -> new BillResponse(value, null))
                .orElseGet(() -> new BillResponse(null, "bill not found"));
    }

    public BillResponse deleteBill(Long id){
        Optional<Bill> billOptional = billRepository.findById(id);
        if(billOptional.isEmpty()){
            return new BillResponse(null, "bill not found");
        }
        Bill bill = billOptional.get();
        launchRepository.deleteAllLaunchOfBill(bill);
        billRepository.delete(bill);
        return new BillResponse(null,null);
    }

    public BillResponse newBill(Bill bill){
        try{
            validateBill(bill);
        }catch (Exception e){
            return new BillResponse(null, e.toString());
        }

        Optional<User> user = userRepository.findById(bill.getUser().getId());
        if(user.isPresent()){
            bill.setUser(user.get());
        }else{
            return new BillResponse(null, "user not found");
        }

        if(bill.getReferenceDate() == null){
            bill.setReferenceDate(LocalDate.now());
        }

        if(bill.getInstallments() == null || bill.getInstallments() == 0){
            bill.setInstallments(1);
        }

        bill = billRepository.save(bill);
        int numDias = 0;
        for(int repet = 1; repet <= bill.getInstallments(); repet++){
            Launch launch = new Launch();
            launch.setBill(bill);
            launch.setValue(bill.getValue()/ bill.getInstallments());
            launch.setInstallment(repet);
            launch.setReferenceDate(bill.getReferenceDate().plusDays(numDias));
            launchRepository.save(launch);
            numDias += 30;
        }

        return new BillResponse(bill, null);
    }

    public void validateBill(Bill bill){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Bill>> violations = validator.validate(bill);

        if (violations.isEmpty()) {
            return;
        }

        List<String> messages = new ArrayList<>();
        for (ConstraintViolation<Bill> violation: violations){
            messages.add(violation.getMessage());
        }
        throw new IllegalArgumentException(messages.toString());
    }


}
