package com.los.controller;

import com.los.dto.request.MasterMenuRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.MasterMenuResponse;
import com.los.service.MasterMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/v1/master-menu")
@RequiredArgsConstructor
public class MasterMenuController {
    private final MasterMenuService masterMenuService;

    @PostMapping
    public ResponseEntity<CommonResponse> createMasterMenu(@RequestBody MasterMenuRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(masterMenuService.createMasterMenu(request));
    }

    @GetMapping(path = "/{menuId}")
    public ResponseEntity<MasterMenuResponse> getMasterMenuById(@PathVariable(name = "menuId") Long id){
        return ResponseEntity.ok(masterMenuService.getMasterMenuById(id));
    }

    @GetMapping
    public ResponseEntity<List<MasterMenuResponse>> getAllMasterMenu(){
        return ResponseEntity.ok(masterMenuService.getAllMasterMenu());
    }

    @PutMapping(path = "/{menuId}")
    public ResponseEntity<CommonResponse> updateMasterMenu(@PathVariable(name = "menuId") Long id,@RequestBody MasterMenuRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(masterMenuService.updateMasterMenu(id, request));
    }

    @DeleteMapping(path = "/{menuId}")
    public void deleteMasterMenu(@PathVariable(name = "menuId") Long id){
       masterMenuService.deleteMasterMenu(id);
    }
}
