

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import domain.Department;
import domain.Employee;
import domain.Project;
import repository.DepartmentRepository;
import repository.EmployeeRepository;
import repository.ProjectRepository;
import service.DepartmentService;
import service.EmployeeService;
import service.ProjectService;

public class Main {

    public static EmployeeService employeeService;
    public static ProjectService projectService;
    public static EmployeeRepository employeeRepository;
    public static ProjectRepository projectRepository;
    private static DepartmentService departmentService;

    public static void main(String[] args) throws SQLException, IOException {
        projectRepository = new ProjectRepository();
        employeeRepository = new EmployeeRepository();
        projectService = new ProjectService();
        employeeService = new EmployeeService(employeeRepository, projectRepository);
        departmentService = new DepartmentService(new DepartmentRepository());
        printEmployeeFullNames();
        printEmployeesForProject();
        printAllDepartments();

        String inputCSVString;
        System.out.println("Ivesti naują darbuotoja per kablelį");
        System.out.println("asmens kodas,vardas,pavarde,dirba nuo,gimimo metai,pareigos,skyrius,projekto id");
        Scanner myScanner = new Scanner(System.in);
        inputCSVString = myScanner.nextLine();
        Employee employee = EmployeeRepository.fromCSV(inputCSVString);
        System.out.println(employee);
        Integer i= insertEmployeeFromCSV(employee);
        System.out.println("Iterpta darbuotojų : "+i);
        System.out.println("Kuri darbuotoją redaguosim ");
        System.out.println("Asmens kodas   : ");
        String asmKodas = myScanner.nextLine();
        System.out.println("Į kuriame projekte dirbs : ");
        Integer projectid =myScanner.nextInt();

        i=pushEmployeeInNewProject(asmKodas,projectid);
        System.out.println("Pakeista darbuotojų : "+i);

       Integer insertedProjects = updateProjectsDB();
        System.out.println("Į projektų  DB įterpta "+insertedProjects+" eilučių");

        Integer insertedDepartments = updateDeprtmentsDB();
        System.out.println("Į projektų  DB įterpta "+insertedDepartments+" eilučių");


    }//Main


    private static Integer updateProjectsDB() throws IOException, SQLException {
        ArrayList<String> projects = retrieveProjects();
        for (String project: projects){
            System.out.println(project);
        }
        return ProjectService.updateProjectsSQL(projects);
    }
    private static Integer updateDeprtmentsDB() throws IOException, SQLException {
        ArrayList<String> departments = retrieveDepartments();
        for (String department: departments){
            System.out.println(department);
        }
        return DepartmentService.updateDepartmentsSQL(departments);
    }


    private static ArrayList<String> retrieveProjects() throws IOException {
        return ProjectService.getAllImports();
    }

    private static ArrayList<String> retrieveDepartments() throws IOException {
        return DepartmentService.getAllImports();
    }

    private static Integer pushEmployeeInNewProject(String asmKodas,Integer projectid) throws SQLException {
        return employeeService.updateEmployeeProjectID(asmKodas,projectid);
    }

    private static Integer insertEmployeeFromCSV(Employee employee) throws SQLException {

         return employeeService.push2DB(employee);
    }


    private static void printEmployeeFullNames() throws SQLException {
        List<Employee> employees = employeeService.getAllEmployees();

        for (Employee e : employees) {
            System.out.println(e.getName() + " " + e.getLastName());
        }
    }

    private static void printAllProjects() throws SQLException {
        List<Project> projects = projectService.getAllProjects();

        System.out.println(projects);
    }
    private static void printAllDepartments() throws SQLException {
        List<Department> departments = departmentService.getAllDepartments();

        System.out.println(departments);
    }

    private static void printEmployeesForProject() throws SQLException {
        Map<Project, List<Employee>> employeesForProject = employeeService.getEmployeesForProject();

        Set<Project> mapKeys = employeesForProject.keySet();

        for (Project project : mapKeys) {
            System.out.println("= " + project.getName() + " =");

            List<Employee> employeeList = employeesForProject.get(project);

            int i = 1;
            for (Employee employee : employeeList) {
                System.out.println(i + ". " + employee.getName() + " " + employee.getLastName());
                i++;
            }
        }
    }


}
