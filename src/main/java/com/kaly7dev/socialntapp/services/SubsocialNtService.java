package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.SubsocialNtDto;

import java.util.List;

public interface SubsocialNtService {
    SubsocialNtDto createSubsocialNt(SubsocialNtDto subsocialNtDto);
    List<SubsocialNtDto> getSubsocialNtList();
    SubsocialNtDto getSubsocialNt(Long id);
}
