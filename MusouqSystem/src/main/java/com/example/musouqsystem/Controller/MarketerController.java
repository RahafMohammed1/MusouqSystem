package com.example.musouqsystem.Controller;

import com.example.musouqsystem.Api.ApiResponse;
import com.example.musouqsystem.DTO.MarketerDTO;
import com.example.musouqsystem.Model.Marketer;
import com.example.musouqsystem.Service.MarketerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/marketer")
@RequiredArgsConstructor
public class MarketerController {
private final MarketerService marketerService;

    @GetMapping("/get-all-marketer")
    public ResponseEntity shopperGetAllMarketer()
    {
        return ResponseEntity.status(200).body( marketerService.shopperGetAllMarketer());
    }

    @PostMapping("/complete-profile")
    public ResponseEntity completeProfile(@RequestBody @Valid Marketer marketer)
    {
        marketerService.completeProfile(marketer);
        return ResponseEntity.status(200).body(new ApiResponse("your profile is completed"));
    }

    @PutMapping("/update-profile/{marketer_id}")
    public ResponseEntity updateProfile(@PathVariable Integer marketer_id,@RequestBody @Valid MarketerDTO marketerDTO)
    {
        marketerService.updateProfile(marketer_id,marketerDTO);
        return ResponseEntity.status(200).body(new ApiResponse("your profile is updated"));
    }

    @DeleteMapping("/delete-profile/{marketer_id}")
    public ResponseEntity deleteProfile(@PathVariable Integer marketer_id)
    {
        marketerService.deleteProfile(marketer_id);
        return ResponseEntity.status(200).body(new ApiResponse("your profile is deleted"));
    }

}
