package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

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
    @JsonIgnore

    Project projectDoc;

}
