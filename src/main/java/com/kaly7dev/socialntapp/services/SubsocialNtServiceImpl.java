package com.kaly7dev.socialntapp.services;

import com.kaly7dev.socialntapp.coreapi.dtos.SubsocialNtDto;
import com.kaly7dev.socialntapp.coreapi.exceptions.SubsocialNtNotFoundException;
import com.kaly7dev.socialntapp.coreapi.mappers.SubsocialNtMapper;
import com.kaly7dev.socialntapp.entities.SubsocialNt;
import com.kaly7dev.socialntapp.repositories.SubsocialNtRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class SubsocialNtServiceImpl implements SubsocialNtService {
    private final SubsocialNtRepo subsocialNtRepo;
    private final SubsocialNtMapper subsocialNtMapper;
    @Override
    @Transactional
    public SubsocialNtDto createSubsocialNt(SubsocialNtDto subsocialNtDto) {
        SubsocialNt subsocialNtSaved= subsocialNtRepo.save(subsocialNtMapper.mapToSubsocialNt(subsocialNtDto));
        subsocialNtDto.setId(subsocialNtSaved.getId());
        return subsocialNtDto;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubsocialNtDto> getSubsocialNtList() {
        return subsocialNtRepo.findAll()
                .stream()
                .map(subsocialNtMapper::mapToDto)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public SubsocialNtDto getSubsocialNt(Long id) {
         return subsocialNtMapper.mapToDto( subsocialNtRepo.findById(id)
                 .orElseThrow(()-> new SubsocialNtNotFoundException("No SubSocialNt found with ID: "+id.toString())));
    }
}
