package com.los.service;


import com.los.dto.request.MasterMenuRequest;
import com.los.dto.response.CommonResponse;
import com.los.dto.response.MasterMenuResponse;
import com.los.entity.MasterMenu;
import com.los.exception.FlowSequenceNotUniqueException;
import com.los.exception.ResourceNotFoundException;
import com.los.mapper.MasterMenuMapper;
import com.los.repository.MasterMenuRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;

@Service
@Validated
@RequiredArgsConstructor
public class MasterMenuService {

    private final MasterMenuRepository masterMenuRepository;

    private final MasterMenuMapper masterMenuMapper;

    public CommonResponse createMasterMenu(@Valid MasterMenuRequest request) {
        MasterMenu masterMenu = masterMenuMapper.toMasterMenu(request);
        masterMenu.setIsDeleted(Boolean.valueOf("false"));

        MasterMenu savedMasterMenu = masterMenuRepository.save(masterMenu);
        return new CommonResponse(savedMasterMenu.getId());
    }

    public MasterMenuResponse getMasterMenuById(Long id){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findById(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        MasterMenu masterMenu = optionalMasterMenu.get();
        return masterMenuMapper.toMasterMenuResponse(masterMenu);
    }

    public List<MasterMenuResponse> getAllMasterMenu(){
        List<MasterMenu> masterMenus = masterMenuRepository.findAll();
        return masterMenuMapper.toMasterMenuResponseList(masterMenus);
    }

    public CommonResponse updateMasterMenu(Long id, MasterMenuRequest request){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findById(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        MasterMenu masterMenu = optionalMasterMenu.get();
        Long currentFlowSequence = masterMenu.getFlowSequence();

        if (!request.getFlowSequence().equals(currentFlowSequence)){
            if (masterMenuRepository.existsByFlowSequence(request.getFlowSequence())){
                throw new FlowSequenceNotUniqueException("Flow sequence must be unique");
            }
        }
        masterMenuMapper.updateMasterMenu(request, masterMenu);

        MasterMenu updatedMasterMenu = masterMenuRepository.save(masterMenu);
        return new CommonResponse(updatedMasterMenu.getId());
    }

    public void deleteMasterMenu(Long id){
        Optional<MasterMenu> optionalMasterMenu = masterMenuRepository.findById(id);

        if (optionalMasterMenu.isEmpty()){
            throw new ResourceNotFoundException("Master menu not found with id : " + id);
        }

        masterMenuRepository.delete(optionalMasterMenu.get());
    }
}
