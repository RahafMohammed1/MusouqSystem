package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.RequestDTO;
import com.example.musouqsystem.Model.Request;
import com.example.musouqsystem.Service.RequestService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/request")
public class RequestController {
    private final RequestService requestService;
    @GetMapping("/get-marketer-request/{marketer_id}")
    public ResponseEntity marketerViewAllHisRequest(@PathVariable Integer marketer_id)
    {
        return ResponseEntity.status(200).body( requestService.marketerViewAllHisRequest(marketer_id));
    }
    @GetMapping("/get-supplier-request/{supplier_id}")
    public ResponseEntity supplierViewAllHisRequest(@PathVariable Integer supplier_id)
    {
        return ResponseEntity.status(200).body(requestService.supplierViewAllHisRequest(supplier_id));
    }

    @PostMapping("/send-request/{marketer_id}")
    public ResponseEntity marketerSendRequestTo(@PathVariable Integer marketer_id, @RequestBody @Valid RequestDTO requestDTO)
    {
        requestService.marketerSendRequest(marketer_id,requestDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your request is sent"));
    }

    @PutMapping("/update-request/{marketer_id}/{request_id}")
    public ResponseEntity marketerUpdateRequest(@PathVariable Integer marketer_id,@PathVariable Integer request_id,@RequestBody @Valid RequestDTO requestDTO)
    {
        requestService.marketerUpdateRequest(marketer_id, request_id, requestDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your request is updated"));
    }

    @DeleteMapping("/delete-request/{marketer_id}/{request_id}")
    public ResponseEntity marketerDeleteRequest (@PathVariable Integer marketer_id,@PathVariable Integer request_id)
    {
        requestService.marketerDeleteRequest(marketer_id, request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your request is deleted"));
    }
    @PutMapping("/accept-request/{supplier_id}/{request_id}")
    public ResponseEntity supplierAcceptRequest(@PathVariable Integer supplier_id,@PathVariable Integer request_id){
        requestService.supplierAcceptRequest(supplier_id, request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your are accept the request"));
    }
    @PutMapping("/reject-request/{supplier_id}/{request_id}")
    public ResponseEntity supplierRejectRequest(Integer supplier_id,Integer request_id){

        requestService.supplierRejectRequest(supplier_id, request_id);
        return ResponseEntity.status(200).body(new ApiResponse("your are reject the request"));
    }
    }
