package repository;

import domain.Department;
import domain.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper {
    public Department fromResultSet(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getString("PAVADINIMAS"),
                resultSet.getString("VADOVAS_ASMENSKODAS")
        );
    }
}
