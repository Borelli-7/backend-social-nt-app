package com.kaly7dev.socialntapp.controllers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kaly7dev.socialntapp.services.SubsocialNtService;
import com.kaly7dev.socialntapp.coreapi.dtos.SubsocialNtDto;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/subsocialnt")
public class SubsocialNtController {
    private final SubsocialNtService subsocialNtService;

    public ResponseEntity<SubsocialNtDto> createSubsocialNt(SubsocialNtDto subsocialNtDto){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(subsocialNtService.createSubsocialNt(subsocialNtDto));
    }
}
