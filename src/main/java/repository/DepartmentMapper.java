package repository;

import domain.Department;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentMapper {
    protected Department fromResultSet(ResultSet resultSet) throws SQLException {
        return new Department(
                resultSet.getString("PAVADINIMAS"),
                resultSet.getString("VADOVAS_ASMENSKODAS")
        );
    }
}
