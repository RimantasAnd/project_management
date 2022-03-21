package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import domain.DepartmentType;
import domain.Employee;


import static repository.EmployeeMapper.toDepartmentType;
import static repository.EmployeeMapper.toOccupationType;

public class EmployeeRepository extends Repository {
    private static final String selectAll="SELECT * FROM DARBUOTOJAS";

    public List<Employee> findAll() throws SQLException {
        ResultSet result=findAllResults(selectAll);
        List<Employee> employees = new ArrayList<>();
        EmployeeMapper employeeMapper = new EmployeeMapper();
            while (result.next()) {
                Employee employee = employeeMapper.fromResultSet(result);
                employees.add(employee);
            }
            return employees;
    }

    public Integer pushToDB(Employee e) throws SQLException {
        createConnection();
        PreparedStatement insertEmployeeStatement = connectionSQL.prepareStatement
                ("INSERT INTO DARBUOTOJAS (ASMENSKODAS ,VARDAS ,PAVARDE ,DIRBANUO ,GIMIMOMETAI ,PAREIGOS ,SKYRIUS_PAVADINIMAS ,PROJEKTAS_ID)" +
                "VALUES (?,?,?,?,?,?,?,?)");
        insertEmployeeStatement.setString(1, e.getId());
        insertEmployeeStatement.setString(2, e.getName());
        insertEmployeeStatement.setString(3, e.getLastName());
        insertEmployeeStatement.setDate(4, Date.valueOf(e.getStartedAt()));
        insertEmployeeStatement.setDate(5, Date.valueOf(e.getBirthDate()));
        insertEmployeeStatement.setString(6, String.valueOf(e.getOccupation()));
        insertEmployeeStatement.setString(7, departmentTypetoString(e.getDepartment()));
        insertEmployeeStatement.setInt(8, e.getProjectId());
        return insertEmployeeStatement.executeUpdate();
    }//pushToDB

    private String departmentTypetoString(DepartmentType dpType) {
        switch (dpType) {
            case QA -> {
                return "Testavimo";
            }
            case JAVA -> {
                return "Java";
            }
            case C_SHARP -> {
                return "C#";
            }
            default -> {
                return null;
            }
        }
    }

    public static Employee fromCSV(String inputCSVString) {
        String[] token = inputCSVString.split(delimiter);
        String id = token[0];
        String name = token[1];
        String lastname = token[2];
        LocalDate startedAt = LocalDate.parse(token[3]);
        LocalDate birthDate = LocalDate.parse(token[4]);
        String occupation = token[5];
        String department = token[6];
        Integer projektasId = Integer.parseInt(token[7]);
        Employee employee = new Employee(id, name, lastname, startedAt, birthDate, toOccupationType(occupation), toDepartmentType(department), projektasId);
        return employee;
    }

    public Integer updateProjectId(String primKey,Integer projectId) throws SQLException {
        createConnection();
        PreparedStatement updateEmployeesStatement = connectionSQL.prepareStatement
                ("UPDATE DARBUOTOJAS SET PROJEKTAS_ID=? WHERE ASMENSKODAS =?");
        updateEmployeesStatement.setInt(1,projectId);
        updateEmployeesStatement.setString(2, primKey);
        return updateEmployeesStatement.executeUpdate();
    }
}
