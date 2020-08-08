package com.softserve.edu;

import com.softserve.edu.model.Marathon;
import com.softserve.edu.model.Role;
import com.softserve.edu.model.Sprint;
import com.softserve.edu.model.User;
import com.softserve.edu.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.time.LocalDate;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class Application implements CommandLineRunner {

    private final UserService userService;
    private final MarathonService marathonService;
    private final SprintService sprintService;
    private final TaskService taskService;
    private final ProgressService progressService;
    private final RoleService roleService;

    public Application(UserService userService, MarathonService marathonService,
                       SprintService sprintService, TaskService taskService,
                       ProgressService progressService, RoleService roleService) {
        this.userService = userService;
        this.marathonService = marathonService;
        this.sprintService = sprintService;
        this.taskService = taskService;
        this.progressService = progressService;
        this.roleService = roleService;
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Running Spring Boot Application");
        Marathon marathon = new Marathon();
        marathon.setTitle("Marathon1");
        marathonService.createOrUpdate(marathon);
        Marathon marathon2 = new Marathon();
        marathon2.setTitle("Marathon2");
        marathonService.createOrUpdate(marathon2);
        for (int i = 0; i < 2; i++) {
            Sprint sprint = new Sprint();
            sprint.setTitle("Sprint" + i);
            sprint.setStartDate(LocalDate.now());
            sprint.setEndDate(LocalDate.now().plusMonths(3));
            sprintService.updateSprint(sprint);
            sprintService.addSprintToMarathon(sprint, marathon);
            Sprint sprint2 = new Sprint();
            sprint2.setTitle("Sprint" + (i + 2));
            sprint2.setStartDate(LocalDate.now());
            sprint2.setEndDate(LocalDate.now().plusMonths(6));
            sprintService.updateSprint(sprint2);
            sprintService.addSprintToMarathon(sprint2, marathon2);
        }
        Role mentorRole = new Role("ROLE_MENTOR");
        roleService.createOrUpdate(mentorRole);
        Role studentRole = new Role("ROLE_STUDENT");
        roleService.createOrUpdate(studentRole);
        for (int i = 0; i < 2; i++) {
            User mentor = new User();
            mentor.setEmail("mentor" + i + "@email.com");
            mentor.setFirstName("MentorName" + i);
            mentor.setLastName("MentorSurname" + i);
            mentor.setPassword("123456");
            mentor.setRole(mentorRole);
            mentor = userService.createOrUpdateUser(mentor);
            userService.addUserToMarathon(mentor, marathon);

            User trainee = new User();
            trainee.setEmail("trainee" + i + "@email.com");
            trainee.setFirstName("TraineeName" + i);
            trainee.setLastName("TraineeSurname" + i);
            trainee.setPassword("123456");
            trainee.setRole(studentRole);
            userService.createOrUpdateUser(trainee);
            userService.addUserToMarathon(trainee, marathon);
        }
    }
}