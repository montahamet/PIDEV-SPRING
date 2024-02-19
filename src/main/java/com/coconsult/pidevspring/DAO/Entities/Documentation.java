package com.coconsult.pidevspring.DAO.Entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Documentation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long doc_id;
    String rapport;
    String specficationDoc;
    @OneToOne(mappedBy = "document")
    Project projectDoc;

}
