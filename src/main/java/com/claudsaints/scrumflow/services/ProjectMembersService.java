package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.dto.ProjectMemberDTO;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.repositories.ProjectMembersRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProjectMembersService {


    private ProjectMembersRepository repository;

    public ProjectMembers addMember(ProjectMembers member){
        return repository.save(member);
    }


    public ProjectMembers findByID(Long id){
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Não foi possível encontrar esse membro no projeto"));
    }

    public ProjectMemberDTO updateRole(Long id, ProjectMemberRole newRole){
        ProjectMembers member = findByID(id);

        member.setRole(newRole);

       repository.save(member);

       return new ProjectMemberDTO(member);
    }

    public void removeMember(Long id){
        repository.deleteById(id);
    }
}
