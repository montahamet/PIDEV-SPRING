package com.coconsult.pidevspring.Security.payload.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
 
public class NewPasswordRequest {



    String token;
    String password ;
    String email ;


 

}
