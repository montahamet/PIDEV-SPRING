    package com.coconsult.pidevspring.DAO.Entities;

    import com.fasterxml.jackson.annotation.JsonInclude;
    import jakarta.persistence.*;
    import lombok.*;
    import lombok.experimental.FieldDefaults;

    import java.io.Serializable;
    import java.time.LocalDateTime;

    @Entity
    @Getter
    @Setter    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    @FieldDefaults(level = AccessLevel.PRIVATE)
    public class RegistrationEvent implements Serializable {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        Long registrationE_id;

        @Enumerated(EnumType.STRING)
        Status registrationEvent_status;

        @JsonInclude(JsonInclude.Include.NON_NULL)
        boolean locked = false;
        LocalDateTime registration_date;

        @ManyToOne
        User user;

        @ManyToOne
        Event event;



        @PrePersist
        protected void onCreate() {
            registration_date = LocalDateTime.now();
        }
    }