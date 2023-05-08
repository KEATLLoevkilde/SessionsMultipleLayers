package com.example.sessionsmultiplelayers.controllers;

import com.example.sessionsmultiplelayers.models.Project;
import com.example.sessionsmultiplelayers.models.Subproject;
import com.example.sessionsmultiplelayers.models.User;
import com.example.sessionsmultiplelayers.repositories.ProjectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ProjectController {
    ProjectRepository projectRepository;

    public ProjectController(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }


    private boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }

    @GetMapping("/create/project")
    public String createProject(Model model){
        Project newProject = new Project();
        model.addAttribute("newProject", newProject);
        return "createProject";

    }

    @PostMapping(path = "/create/project")
    public String createProject(@ModelAttribute("newProject") Project newProject, HttpSession session) {
        User user = (User) session.getAttribute("user");
        newProject.setUserID(user.getUserID());

        projectRepository.createProject(newProject);
        return "redirect:/projects";
    }

    @GetMapping(path="projects")
    public String getProjects(HttpSession session, Model model) {
        if (isLoggedIn(session)) {
            List<Project> projects = projectRepository.getProjects((User) session.getAttribute("user"));
            model.addAttribute("projects", projects);
            return "projects";
        }
        return "redirect:/login";
    }

    @GetMapping("/subprojects")
    public String showSubprojects(@RequestParam() int projectId, HttpSession session, Model model) {
        // Retrieve and display subprojects for the given project ID
        Project currentProject = projectRepository.getProjectByID(projectId);
        if (currentProject != null) {
            List<Subproject> subprojects = projectRepository.getSubprojects(currentProject);
            model.addAttribute("subprojects", subprojects);

            // Set the session's current project to the selected project, so it can be used in other endpoints
            session.setAttribute("currentProject", currentProject);

            return "subprojects";

        } else {
            // handle error case
            return "redirect:/projects";
        }
    }
}
