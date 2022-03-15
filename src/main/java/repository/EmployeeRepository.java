package repository;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Employee;

public class EmployeeRepository extends Repository {

    public List<Employee> findAll() {
        try {
            EmployeeMapper employeeMapper = new EmployeeMapper();

            createConnection();
            List<Employee> employees = new ArrayList<>();
            PreparedStatement findEmployeesStatement = connection.prepareStatement("SELECT * FROM DARBUOTOJAS");
            ResultSet result = findEmployeesStatement.executeQuery();


            while (result.next()) {
                Employee employee = employeeMapper.fromResultSet(result);
                employees.add(employee);
            }

            return employees;
        } catch (SQLException e) {
            throw new RuntimeException("Unable to connect to db!");
        }
    }

    public void pushToDB(Employee e) throws SQLException {
        createConnection();
        PreparedStatement insertEmployeeStatement = connection.prepareStatement("SELECT * FROM DARBUOTOJAS");
        insertEmployeeStatement.setString(1, e.getId());
        insertEmployeeStatement.setString(2, e.getName());
        insertEmployeeStatement.setString(3, e.getLastName());
        insertEmployeeStatement.setDate(4, Date.valueOf(e.getStartedAt()));
        insertEmployeeStatement.setDate(5, Date.valueOf(e.getBirthDate()));
        insertEmployeeStatement.setString(6, String.valueOf(e.getOccupation()));
        insertEmployeeStatement.setInt(7, e.getDepartment());




        //INSERT INTO DARBUOTOJAS (ASMENSKODAS ,VARDAS ,PAVARDE ,DIRBANUO ,GIMIMOMETAI ,PAREIGOS ,SKYRIUS_PAVADINIMAS ,PROJEKTAS_ID )
        // VALUES ('39922334459','Kestutis','Didysys', '2011-12-17', '1986-09-10','Testuotojas','Java',3);
    }//pushToDB

}
