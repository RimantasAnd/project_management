package service;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Project;
import repository.ProjectRepository;

public class ProjectService {

    public List<Project> getAllProjects() throws SQLException {
        ProjectRepository projectRepository = new ProjectRepository();
        return projectRepository.findAll();
    }

    public  static ArrayList<String> getAllImports() throws IOException {
        ProjectRepository projectRepository = new ProjectRepository();
        return projectRepository.readFileLines(ProjectRepository.importFile);
    }

    public static Integer updateProjectsSQL(ArrayList<String> projects) throws SQLException {
        ProjectRepository projectRepository = new ProjectRepository();
        return projectRepository.updateProjects(projects);
    }

}//class
