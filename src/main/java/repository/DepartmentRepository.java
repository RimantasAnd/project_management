package repository;

import domain.Department;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository extends Repository {
    public static final String importFile = "files\\departments.csv";
    private static final String selectAll = "SELECT * FROM SKYRIUS";
    final String insertStatement = "INSERT INTO SKYRIUS (PAVADINIMAS,VADOVAS_ASMENSKODAS ) VALUES (?,?);";

    public List<Department> findAll() throws SQLException {
        ResultSet result = findAllResults(selectAll);
        DepartmentMapper departmentMapper = new DepartmentMapper();
        List<Department> departments = new ArrayList<>();
        while (result.next()) {
            Department department = departmentMapper.fromResultSet(result);
            departments.add(department);
        }
        return departments;
    }

    public Integer updateDepartments(ArrayList<String> departments) {
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
            for (String department : departments) {
                String[] token = department.split(delimiter);
                preparedStatement.setString(1, token[0]);
                preparedStatement.setString(2, token[1]);
                preparedStatement.addBatch();
                if (++insertCount % maxBatchSize == 0) {
                    preparedStatement.executeBatch();
                }
            }
            int[] affectedRecords = preparedStatement.executeBatch();
            executedBatchSize = affectedRecords.length;
            if (executedBatchSize == departments.size()) {
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
    }//updateDepartments
}//class
