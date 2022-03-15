package service;

import domain.Department;
import domain.Employee;
import repository.DepartmentRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;

import java.util.List;

public class DepartmentService {
    private DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;

    }


    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

}
