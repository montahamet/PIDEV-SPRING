package com.coconsult.pidevspring.Security.payload.request;

import com.coconsult.pidevspring.DAO.Entities.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
 
public class ForgotPasswordRequest {



    String email;


 

}
