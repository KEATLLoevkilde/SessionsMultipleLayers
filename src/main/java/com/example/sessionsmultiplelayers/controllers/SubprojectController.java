package com.example.sessionsmultiplelayers.controllers;

import com.example.sessionsmultiplelayers.models.Project;
import com.example.sessionsmultiplelayers.models.Subproject;
import com.example.sessionsmultiplelayers.models.User;
import com.example.sessionsmultiplelayers.repositories.SubprojectRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SubprojectController {
    private SubprojectRepository subprojectRepository;

    public SubprojectController(SubprojectRepository subprojectRepository) {
        this.subprojectRepository = subprojectRepository;
    }

    @GetMapping("/create/subproject")
    public String createSubproject(Model model){
        model.addAttribute("newSubproject", new Subproject());
        return "createSubproject";

    }

    @PostMapping(path = "/create/subproject")
    public String createSubproject(@ModelAttribute("newSubproject") Subproject newSubproject, HttpSession session) {
        Project project = (Project) session.getAttribute("project");
        newSubproject.setProjectID(project.getProjectID());

        subprojectRepository.createSubproject(newSubproject);
        return "redirect:/subprojects";
    }
}
