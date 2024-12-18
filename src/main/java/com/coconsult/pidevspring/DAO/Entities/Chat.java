package com.coconsult.pidevspring.DAO.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Chat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long chatId ;
    String name ;
    LocalDateTime createdAt;
    @ManyToMany(mappedBy="chats", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private Set<User> users;

    public Chat(String name) {
        this.name = name;
    }
}
