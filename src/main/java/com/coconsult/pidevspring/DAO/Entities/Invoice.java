package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Invoice implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long invoice_id;
    LocalDate issueDateinvoice;
    String item;
    String invoiceDescription;
    int quantity;
    double unitPrice;
    double totalAmount;
    String paymentMethods;
    @Enumerated(EnumType.STRING)
    PaymentStatus paymentStatus;
    @JsonIgnore
    @ManyToOne
    Project projetInvoice;
}
