package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.SubsocialNtDto;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.repositories.SubsocialNtRepo;
import com.kaly7dev.socialntapp.coreapi.mappers.SubsocialNtMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SubsocialNtServiceImpl implements SubsocialNtService {
    private final SubsocialNtRepo subsocialNtRepo;
    private final SubsocialNtMapper subsocialNtMapper;
    @Override
    public SubsocialNtDto createSubsocialNt(SubsocialNtDto subsocialNtDto) {
        SubsocialNt subsocialNtSaved= subsocialNtRepo.save(subsocialNtMapper.mapToSubsocialNt(subsocialNtDto));
        subsocialNtDto.setId(subsocialNtSaved.getId());
        return subsocialNtDto;
    }

    @Override
    public List<SubsocialNtDto> getSubsocialNtList() {
        return subsocialNtRepo.findAll()
                .stream()
                .map(subsocialNtMapper::mapToDto)
                .toList();
    }
}
