package com.claudsaints.scrumflow.controllers;


import com.claudsaints.scrumflow.services.ProjectListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/projects/lists")
public class ProjectListController {
    @Autowired
    private ProjectListService service;




}
