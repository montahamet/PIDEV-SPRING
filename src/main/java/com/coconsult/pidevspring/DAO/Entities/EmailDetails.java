package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.Entity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Setter
@Getter
public class EmailDetails implements Serializable {
    String to;
     String subject;
     String body;

}
