package com.coconsult.pidevspring.RestControllers;

import com.coconsult.pidevspring.DAO.Entities.ActionLog;
import com.coconsult.pidevspring.Services.ActionLogService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")
@RequestMapping("/actionlogs")
public class ActionLogController {

    private final ActionLogService actionLogService;

    @GetMapping("/entityIds")
    public ResponseEntity<List<Long>> getAllEntityIds() {
        List<Long> entityIds = actionLogService.getAllEntityIds();
        return ResponseEntity.ok(entityIds);
    }

    @GetMapping("/one/{entityId}")
    public ResponseEntity<List<ActionLog>> getActionLogsForEntity(@PathVariable Long entityId) {
        List<ActionLog> actionLogs = actionLogService.getActionLogsForEntity(entityId);
        return ResponseEntity.ok(actionLogs);
    }

    @GetMapping("/all")
    public List<ActionLog> getAllActionLogs() {
        return actionLogService.getAllActionLogs();
    }


    @PostMapping("/create")
    public ActionLog createActionLog(@RequestBody ActionLog actionLog) {
        return actionLogService.createActionLog(actionLog);
    }

    @GetMapping("/check-inactivity")
    public ResponseEntity<List<Long>> checkEntityInactivityForDay() {
        List<Long> inactiveEntityIds = actionLogService.checkEntityInactivityForDay();
        return ResponseEntity.ok(inactiveEntityIds);
    }

    @GetMapping("/check-inactivity-minute")
    public ResponseEntity<List<Long>> checkEntityInactivityForMinute() {
        List<Long> inactiveEntityIds = actionLogService.checkEntityInactivityForMinute();
        return ResponseEntity.ok(inactiveEntityIds);
    }

}

