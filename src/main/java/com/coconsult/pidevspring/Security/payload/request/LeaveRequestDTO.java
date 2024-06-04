package com.coconsult.pidevspring.Security.payload.request;

import com.coconsult.pidevspring.DAO.Entities.Event;
import com.coconsult.pidevspring.DAO.Entities.TrainingSession;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeaveRequestDTO {
    private String firstname;
    private String lastname;
    private Integer leaveCredit;
List<TrainingSession> trainingSession ;
private long thismounth ;
private long thisyear ;


    private List<Event> event ;



}
