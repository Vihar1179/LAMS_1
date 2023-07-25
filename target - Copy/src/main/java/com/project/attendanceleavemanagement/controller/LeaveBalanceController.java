package com.project.attendanceleavemanagement.controller;

import com.project.attendanceleavemanagement.enums.LeaveTypeName;
import com.project.attendanceleavemanagement.payload.LeaveBalanceDTO;
import com.project.attendanceleavemanagement.service.LeaveBalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
public class LeaveBalanceController {


    @Autowired
    private LeaveBalanceService leaveBalanceService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCurrentUserLeaveBalance(@PathVariable Long userId) {
        LeaveBalanceDTO leaveBalance = leaveBalanceService.getLeaveBalanceByUserId(userId);
        if (leaveBalance != null) {
            return ResponseEntity.ok().body(leaveBalance);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leave balance not found for the current user.");
        }
    }


    @PostMapping("/reset/{userId}/{type}")
    public ResponseEntity resetBalance(@PathVariable("userId") Long userId, @PathVariable("type") LeaveTypeName type){
        return leaveBalanceService.reset(userId, type);
    }

}
