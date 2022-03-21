package repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import domain.Project;

public class ProjectRepository extends Repository {
    final String insertStatement = "INSERT INTO PROJEKTAS (PAVADINIMAS) VALUES (?);";
    public static final String importFile = "files\\projects.txt";
    private static final String selectAll = "SELECT * FROM projektas";

    public List<Project> findAll() throws SQLException {
        ResultSet result = findAllResults(selectAll);
        ProjectMapper projectMapper = new ProjectMapper();
        List<Project> projectList = new ArrayList<>();
        while (result.next()) {
            Project project = projectMapper.fromResultSet(result);
            projectList.add(project);
        }
        return projectList;
    }

    public Integer updateProjects(ArrayList<String> projects) {
        Integer executedBatchSize = 0;
        PreparedStatement preparedStatement = null;
        try {
            createConnection();
            preparedStatement = connectionSQL.prepareStatement(insertStatement);
            connectionSQL.setAutoCommit(false);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            int insertCount = 0;
            for (String project : projects) {
                preparedStatement.setString(1, project);
                preparedStatement.addBatch();
                if (++insertCount % maxBatchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }
            int[] affectedRecords = preparedStatement.executeBatch();
            executedBatchSize = affectedRecords.length;
            if (executedBatchSize == projects.size()) {
                preparedStatement.executeBatch();
            }
        } catch (SQLException e) {
            if (executedBatchSize == 0) {
                System.out.println("Probably unique records already exist");
            }
            System.out.println("SQL Transaction rollback");
            connectionSQL.rollback(); // in case of errors rollback database
        } finally {
            try {
                preparedStatement.close();
                connectionSQL.commit();
                connectionSQL.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return executedBatchSize;
        }
    }//updateProjects
}//class
