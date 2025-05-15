package com.claudsaints.scrumflow.services;

import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.repositories.ProjectMembersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectMembersService {

    @Autowired
    private ProjectMembersRepository repository;

    public ProjectMembers addMember(ProjectMembers member){
        return repository.save(member);
    }

//    public List<ProjectMembers> findAll(){
//        return  repository.findAll();
//    }
//
//    public ProjectMembers updateRole(){
//
//    }
//
//    public void removeMember(){
//
//    }
}
