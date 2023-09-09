package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.RequestDTO;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Model.User;
import com.example.musouqsystem.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;

    @GetMapping("/get-marketer-request")
    public ResponseEntity marketerViewAllHisRequest(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(requestService.marketerViewAllHisRequest(user.getId()));
    }

    @GetMapping("/get-supplier-request")
    public ResponseEntity supplierViewAllHisRequest(@AuthenticationPrincipal User user) {
        return ResponseEntity.status(200).body(requestService.supplierViewAllHisRequest(user.getId()));
    }

    @PostMapping("/send-request")
    public ResponseEntity marketerSendRequestTo(@AuthenticationPrincipal User user, @RequestBody @Valid RequestDTO requestDTO) {
        requestService.marketerSendRequest(user.getId(),requestDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your request is sent"));
    }

    @PutMapping("/update-request/{request_id}")
    public ResponseEntity marketerUpdateRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id, @RequestBody @Valid RequestDTO requestDTO) {
        requestService.marketerUpdateRequest(user.getId(), request_id, requestDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your request is updated"));
    }

    @DeleteMapping("/delete-request/{request_id}")
    public ResponseEntity marketerDeleteRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
        requestService.marketerDeleteRequest(user.getId(),request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your request is deleted"));
    }

    @PutMapping("/accept-request/{request_id}")
    public ResponseEntity supplierAcceptRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {
        requestService.supplierAcceptRequest(user.getId(), request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your are accept the request"));
    }

    @PutMapping("/reject-request/{request_id}")
    public ResponseEntity supplierRejectRequest(@AuthenticationPrincipal User user, @PathVariable Integer request_id) {

        requestService.supplierRejectRequest(user.getId(), request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your are reject the request"));
    }

}