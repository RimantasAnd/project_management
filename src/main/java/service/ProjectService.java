package service;

import java.util.List;

import domain.Project;
import repository.ProjectRepository;

public class ProjectService {

    public List<Project> getAllProjects() {
        ProjectRepository projectRepository = new ProjectRepository();
        return projectRepository.findAll();
    }
}
