package com.coconsult.pidevspring.Services;

import com.coconsult.pidevspring.DAO.Entities.ActionLog;
import com.coconsult.pidevspring.DAO.Repository.ActionLogRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ActionLogService {

    private final ActionLogRepository actionLogRepository;

    public List<ActionLog> getAllActionLogs() {
        return actionLogRepository.findAll();
    }
    public List<ActionLog> getActionLogsForEntity(Long entityId) {
        return actionLogRepository.findActionLogByEntityId(entityId);
    }

    public ActionLog createActionLog(ActionLog actionLog) {
        actionLog.setTimestamp(LocalDateTime.now());
        return actionLogRepository.save(actionLog);
    }

    public List<Long> getAllEntityIds() {
        return actionLogRepository.findAll(Sort.by("entityId")).stream()
                .map(ActionLog::getEntityId)
                .distinct()
                .collect(Collectors.toList());
    }


    public List<Long> checkEntityInactivityForDay() {
        List<ActionLog> lastActions = actionLogRepository.findLastActionsOfEachEntity();
        List<Long> inactiveEntityIds = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now();

        for (ActionLog lastAction : lastActions) {
            LocalDateTime lastActionTime = lastAction.getTimestamp();
            long hoursBetween = ChronoUnit.HOURS.between(lastActionTime, currentTime);

            if (hoursBetween >= 24) {
                inactiveEntityIds.add(lastAction.getEntityId());
            }
        }

        return inactiveEntityIds;
    }

    public List<Long> checkEntityInactivityForMinute() {
        List<ActionLog> lastActions = actionLogRepository.findLastActionsOfEachEntity();
        List<Long> inactiveEntityIds = new ArrayList<>();

        LocalDateTime currentTime = LocalDateTime.now();

        for (ActionLog lastAction : lastActions) {
            LocalDateTime lastActionTime = lastAction.getTimestamp();
            long minutesBetween = ChronoUnit.MINUTES.between(lastActionTime, currentTime);

            if (minutesBetween >= 1) {
                inactiveEntityIds.add(lastAction.getEntityId());
            }
        }

        return inactiveEntityIds;
    }
}
