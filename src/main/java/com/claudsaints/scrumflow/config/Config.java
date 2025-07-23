package com.claudsaints.scrumflow.config;

import com.claudsaints.scrumflow.entities.*;
import com.claudsaints.scrumflow.entities.enums.ProjectMemberRole;
import com.claudsaints.scrumflow.entities.enums.RoleName;
import com.claudsaints.scrumflow.repositories.*;
import com.claudsaints.scrumflow.security.config.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class Config implements CommandLineRunner {
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private ProjectMembersRepository projectMembersRepository;

    @Autowired
    private ListRepository projectListRepository;

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private SectionRepository sectionRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private SprintRepository sprintRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;


    @Override
    public void run(String... args) throws Exception {

        Role adm = new Role(null, RoleName.ROLE_ADMINISTRATOR);
        Role simple = new Role(null, RoleName.ROLE_CUSTOMER);


        User u1 = new User(null, "claudio", "claudio@gmail.com", securityConfiguration.passwordEncoder().encode("123456"));
        u1.setRoles(Arrays.asList(adm, simple));

        User u2 = new User(null, "maria", "maria@gmail.com", "123456");

        User u3 = new User(null, "pedro", "pedro@gmail.com", "123456");

        userRepository.saveAll(Arrays.asList(u1, u2, u3));

        Project p1 = new Project(null, "scrumflow", "a scrum + kanban app", u1, Instant.now());
        Project p2 = new Project(null, "virtual_shop", "virtual website to buy things", u1, Instant.now());

        projectRepository.saveAll(Arrays.asList(p2, p1));
        ProjectMembers pm0 = new ProjectMembers(u1, p1, ProjectMemberRole.MEMBER, Instant.now());
        ProjectMembers pm1 = new ProjectMembers(u2, p1, ProjectMemberRole.OWNER, Instant.now());
        ProjectMembers pm2 = new ProjectMembers(u3, p1, ProjectMemberRole.ADMIN, Instant.now());
        ProjectMembers pm3 = new ProjectMembers(u1, p2, ProjectMemberRole.ADMIN, Instant.now());

        projectMembersRepository.saveAll(Arrays.asList(pm0, pm1, pm2, pm3));

        Section s1 = new Section(null, "Sprint 1", "Prototipagem", p1);
        Section s2 = new Section(null, "Sprint 2", "Desenvolvimento", p1);

        this.sectionRepository.saveAll(Arrays.asList(s1, s2));

        //TODO: verificar a geração de posições automatica na inicialização de um nova lista?

        ProjectList l1 = new ProjectList(null, s1, "To Do", 0, Instant.now());
        ProjectList l2 = new ProjectList(null, s1, "Doing", 1, Instant.now());
        ProjectList l3 = new ProjectList(null, s1, "Done", 2, Instant.now());

        projectListRepository.saveAll(Arrays.asList(l1, l2, l3));

        Card c1 = new Card(null, l1, "criar entidades", "criar novas entidades com framework spring", Instant.now(), null, "user story", 5);
        Card c2 = new Card(null, l1, "criar repositories", "criar novos repositories para salvar dados", Instant.now(), null, "user story", 2);

        cardRepository.saveAll(Arrays.asList(c1, c2));


        Label lb1 = new Label(null, p1, "edit", "#fffff");
        Label lb2 = new Label(null, p2, "importante", "#ff0000");

        labelRepository.saveAll(Arrays.asList(lb1, lb2));


        Sprint sp1 = new Sprint(null, p1, "one", null, null, "create backend");

    }
}
