package com.lucas.ClubManager.modules.clubs.useCases;

import com.lucas.ClubManager.modules.clubs.dto.ClubDto;
import com.lucas.ClubManager.modules.clubs.dto.ClubResponse;
import com.lucas.ClubManager.modules.clubs.entities.ClubEntity;
import com.lucas.ClubManager.modules.clubs.repositories.ClubRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListAllClubsUseCase {


    private ClubRepository clubRepository;
    @Autowired
    public ListAllClubsUseCase(ClubRepository clubRepository){
        this.clubRepository=clubRepository;
    }
    public ClubResponse execute(int pageNo,int pageSize){
        try{
            Pageable pageable = PageRequest.of(pageNo,pageSize);
            Page<ClubEntity> clubs = this.clubRepository.findAll(pageable);
            List<ClubEntity> listOfClubs = clubs.getContent();
            List<ClubDto>content = listOfClubs.stream().map(c->mapToDTO(c)).collect(Collectors.toList());

            ClubResponse clubResponse = new ClubResponse();
            clubResponse.setContent(content);
            clubResponse.setPageNo(clubs.getNumber());
            clubResponse.setPageSize(clubs.getSize());
            clubResponse.setTotalElements(clubs.getTotalElements());
            clubResponse.setTotalPages(clubs.getTotalPages());
            clubResponse.setLast(clubs.isLast());

            return clubResponse;

        }catch (Exception e){
            System.err.println("Error listing clubs: " + e.getMessage());

            return null;
        }
    }
    private ClubDto mapToDTO(ClubEntity clubEntity){
        ClubDto clubDTO = new ClubDto();
        clubDTO.setName(clubEntity.getName());


        return clubDTO;
    }
}
