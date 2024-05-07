package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Table(name="CONTRACT_INFO_BATCH")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ContractEmployment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="contract_id")
    Integer contract_id;
    @Column(name="candidateName")
    String candidateName;
    @Column(name="contractType")
    String contractType;
    @Column(name="startDate")
    String startDate;
    @Column(name="endDate")
    String endDate;
    @Column(name="employmentType")
    String employmentType;
    @Column(name="jobTitle")
    String jobTitle;
    @Column(name="salary")
    Double salary;
    @Column(name="terms")
    String terms;

//    public enum ContractType {
//        CDI, // Contrat à durée indéterminée
//        CDD, // Contrat à durée déterminée
//        CDD_OBJET_DEFINI, // CDD à objet défini
//        TRAVAIL_TEMPORAIRE, // Contrat de travail temporaire
//        TRAVAIL_PARTIEL, // Contrat de travail à temps partiel
//        TRAVAIL_INTERMITTENT, // Contrat de travail intermittent
//        TRAVAIL_SAISSONNIER // Contrat de travail saisonnier
//    }
//
//    // Enum for employment type
//    public enum EmploymentType {
//        FULL_TIME,
//        PART_TIME,
//        TEMPORARY
//    }
}
