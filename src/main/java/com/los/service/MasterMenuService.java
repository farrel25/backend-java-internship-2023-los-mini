package com.los.service;


import com.los.dto.request.MasterMenuRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.MasterMenuResponse;
import com.los.entity.MasterMenu;
import com.los.exception.ResourceNotFoundException;
import com.los.repository.MasterMenuRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MasterMenuService {

    private MasterMenuRepository masterMenuRepository;

    public CommonResponse createMasterMenu(MasterMenuRequest request) {
        MasterMenu masterMenu = new MasterMenu();
        masterMenu.setName(request.getName());
        masterMenu.setFlowSequence(request.getFlowSequence());
        masterMenu.setIsActive(request.getIsActive());
        masterMenu.setIsDeleted(request.getIsDeleted());

        MasterMenu savedMasterMenu = masterMenuRepository.save(masterMenu);
        return new CommonResponse(savedMasterMenu.getId());
    }

    public MasterMenuResponse getMasterMenuById(Long id){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findById(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        MasterMenuResponse masterMenuResponse = new MasterMenuResponse();
        masterMenuResponse.setId(optionalMasterMenu.get().getId());
        masterMenuResponse.setName(optionalMasterMenu.get().getName());
        masterMenuResponse.setFlowSequence(optionalMasterMenu.get().getFlowSequence());
        masterMenuResponse.setIsActive(optionalMasterMenu.get().getIsActive());
        masterMenuResponse.setIsDeleted(optionalMasterMenu.get().getIsDeleted());

        return masterMenuResponse;
    }

    public List<MasterMenuResponse> getAllMasterMenu(){
        List<MasterMenu> masterMenus = masterMenuRepository.findAll();

        List<MasterMenuResponse> masterMenuResponses = new ArrayList<>();

        for (MasterMenu masterMenu : masterMenus){
            MasterMenuResponse masterMenuResponse = new MasterMenuResponse();
            masterMenuResponse.setId(masterMenu.getId());
            masterMenuResponse.setName(masterMenu.getName());
            masterMenuResponse.setFlowSequence(masterMenu.getFlowSequence());
            masterMenuResponse.setIsActive(masterMenu.getIsActive());
            masterMenuResponse.setIsDeleted(masterMenu.getIsDeleted());

            masterMenuResponses.add(masterMenuResponse);
        }

        return masterMenuResponses;
    }

    public CommonResponse updateMasterMenu(Long id, MasterMenuRequest request){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findByIdAndIsDeletedFalse(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        optionalMasterMenu.get().setName(optionalMasterMenu.get().getName());
        optionalMasterMenu.get().setFlowSequence(optionalMasterMenu.get().getFlowSequence());
        optionalMasterMenu.get().setIsActive(optionalMasterMenu.get().getIsActive());
        optionalMasterMenu.get().setIsDeleted(optionalMasterMenu.get().getIsDeleted());

        MasterMenu updatedMasterMenu = masterMenuRepository.save(optionalMasterMenu.get());
        return new CommonResponse(updatedMasterMenu.getId());
    }

    public void deleteMasterMenu(Long id){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findByIdAndIsDeletedFalse(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        optionalMasterMenu.get().setIsDeleted(true);
        masterMenuRepository.save(optionalMasterMenu.get());
    }
}
