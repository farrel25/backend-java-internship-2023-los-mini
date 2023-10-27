package com.los.controller;

import com.los.dto.request.MasterMenuRequest;
import com.los.dto.response.CommonResponse;
import com.los.service.MasterMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/master-menu")
@RequiredArgsConstructor
public class MasterMenuController {
    private final MasterMenuService masterMenuService;

    @PostMapping
    public ResponseEntity<CommonResponse> createMasterMenu(@RequestBody MasterMenuRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(masterMenuService.createMasterMenu(request));
    }
}
