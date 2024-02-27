package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class Quote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long quote_id ;
    private LocalDate issuanceDate;
    private String description;
    private Integer quantity;
    private Double UnitPrice;
    private Double TotalAmount;


    @JsonIgnore
    @ManyToOne
    private ProjectOffer projectofferquote;

}
