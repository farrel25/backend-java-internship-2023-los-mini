package com.los.mapper;

import com.los.dto.request.MasterMenuRequest;
import com.los.dto.response.MasterMenuResponse;
import com.los.entity.MasterMenu;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Mapper(componentModel = "spring")
public interface MasterMenuMapper {
    MasterMenuResponse toMasterMenuResponse (MasterMenu masterMenu);
    List<MasterMenuResponse> toMasterMenuResponseList (List<MasterMenu> masterMenus);
    MasterMenu toMasterMenu (MasterMenuRequest request);
    void updateMasterMenu(MasterMenuRequest request, @MappingTarget MasterMenu masterMenu);
}
