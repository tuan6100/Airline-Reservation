package vn.edu.hust.bookingmanagement.domain.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import vn.edu.hust.bookingmanagement.domain.model.enumeration.CustomerGender;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;

@Getter
@NoArgsConstructor
public class CustomerEntity {

    private Long customerId;
    private String name;
    private int age;
    private CustomerGender gender;
    private String nation;

    public CustomerEntity(Long customerId, String name, LocalDateTime dob,
                          String gender, String nation) {
        this.customerId = customerId;
        this.name = name;
        this.nation = nation;
        this.gender = CustomerGender.valueOf(gender.toUpperCase());
        this.age = Period.between(dob.toLocalDate(), LocalDate.now()).getYears();
    }
}
