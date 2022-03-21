package service;

import domain.Department;
import repository.DepartmentRepository;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentService {
    private DepartmentRepository departmentRepository;

    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;

    }

    public List<Department> getAllDepartments() throws SQLException {
        return departmentRepository.findAll();
    }

    public static ArrayList<String> getAllImports() throws IOException {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        return departmentRepository.readFileLines(DepartmentRepository.importFile);
    }

    public static Integer updateDepartmentsSQL(ArrayList<String> departments) throws SQLException {
        DepartmentRepository departmentRepository = new DepartmentRepository();
        return departmentRepository.updateDepartments(departments);
    }

}
