package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Project;

public class ProjectRepository extends Repository {

    public List<Project> findAll() {
        try {
            ProjectMapper projectMapper = new ProjectMapper();
            createConnection();

            List<Project> projectList = new ArrayList<>();

            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM projektas");
            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                Project project = projectMapper.fromResultSet(result);
                projectList.add(project);
            }

            return projectList;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db!");
        }
    }
}
