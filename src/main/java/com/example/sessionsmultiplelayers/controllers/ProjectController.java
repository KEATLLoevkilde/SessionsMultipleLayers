package com.example.sessionsmultiplelayers.controllers;

import com.example.sessionsmultiplelayers.models.Project;
import com.example.sessionsmultiplelayers.models.User;
import com.example.sessionsmultiplelayers.repositories.ProjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProjectController {
    ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @GetMapping("/create/project")
    public String createUser(Model model){
        Project newProject = new Project();
        model.addAttribute("newProject", newProject);
        return "createProject";

    }

    @PostMapping(path = "/create/project")
    public String createUserSubmit(@ModelAttribute("newProject") Project newProject, HttpSession session) {
        User user = (User) session.getAttribute("user");
        newProject.setUserID(user.getUserID());

        projectRepository.createProject(newProject);
        return "redirect:/projects";
    }
}
