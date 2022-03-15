package service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.Employee;
import domain.Project;
import repository.EmployeeRepository;
import repository.ProjectRepository;

public class EmployeeService {

    private EmployeeRepository employeeRepository;
    private ProjectRepository projectRepository;

    public EmployeeService(
        EmployeeRepository employeeRepository,
        ProjectRepository projectRepository
    ) {
        this.employeeRepository = employeeRepository;
        this.projectRepository = projectRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public Map<Project, List<Employee>> getEmployeesForProject() {
        List<Employee> employees = employeeRepository.findAll();
        List<Project> projects = projectRepository.findAll();

        HashMap<Project, List<Employee>> employeeProject = new HashMap<>();

        for (Project project : projects) {
            List<Employee> projectEmployees = new ArrayList<>();
            for (Employee employee : employees) {
                if (employee.getProjectId() == project.getId()) {
                    projectEmployees.add(employee);
                }
            }
            employeeProject.put(project, projectEmployees);
        }

        return employeeProject;
    }
}
