package com.claudsaints.scrumflow.config;

import com.claudsaints.scrumflow.entities.Project;
import com.claudsaints.scrumflow.entities.ProjectMembers;
import com.claudsaints.scrumflow.entities.User;
import com.claudsaints.scrumflow.repositories.ProjectMembersRepository;
import com.claudsaints.scrumflow.repositories.ProjectRepository;
import com.claudsaints.scrumflow.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMembersRepository projectMembersRepository;


    @Override
    public void run(String... args) throws Exception {
        User u1 = new User(null,"claudio","claudio@gmail.com","123456");
        User u2 = new User(null,"maria","maria@gmail.com","123456");
        User u3 = new User(null,"pedro","pedro@gmail.com","123456");

        userRepository.saveAll(Arrays.asList(u1,u2,u3));

        Project p1 = new Project(null,"scrumflow","a scrum + kanban app", u1, Instant.now());
        Project p2 = new Project(null,"virtual_shop","virtual website to buy things", u3, Instant.now());

        projectRepository.saveAll(Arrays.asList(p2,p1));

        ProjectMembers pm1 = new ProjectMembers(u2,p1,"product_owner",Instant.now());
        ProjectMembers pm2 = new ProjectMembers(u3,p1,"product_owner",Instant.now());

        projectMembersRepository.saveAll(Arrays.asList(pm1,pm2));



    }
}
