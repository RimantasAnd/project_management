package repository;

import domain.Department;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentRepository extends Repository{
    public List<Department> findAll(){
        DepartmentMapper departmentMapper = new DepartmentMapper();

        try {
            createConnection();
            List<Department> departments = new ArrayList<>();
            PreparedStatement findDeprtmentsStatement = connection.prepareStatement("SELECT * FROM SKYRIUS");
            ResultSet result = findDeprtmentsStatement.executeQuery();
            while (result.next()) {
                Department department = departmentMapper.fromResultSet(result);
                System.out.println(department);
                departments.add(department);
            }
            return departments;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db!");
        }



    }
}
