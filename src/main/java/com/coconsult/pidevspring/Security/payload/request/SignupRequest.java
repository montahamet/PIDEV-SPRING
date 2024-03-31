package com.coconsult.pidevspring.Security.payload.request;

import java.time.LocalDate;
import java.util.Set;

import com.coconsult.pidevspring.DAO.Entities.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
 
public class SignupRequest {


    String email;
    String firstname;
    String lastname;
    String password;
    String Adresse;
    LocalDate birthdate ;
    Integer phonenumber ;
    @Enumerated(EnumType.STRING)
    Gender gender;
    private Set<String> role;
  

 

 

 

}
