package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Contract implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long contract_id;
    long numContract;
    LocalDate signatureDate;
    @Enumerated(EnumType.STRING)
    ContractStatus contractStatus;
    LocalDate startDateContract;
    LocalDate endDateContract;
    String paymentMethods;
    @OneToOne(cascade = CascadeType.ALL,mappedBy = "contract")
    Project projetContract;


}
