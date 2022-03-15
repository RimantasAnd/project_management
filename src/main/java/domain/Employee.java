package domain;

import java.sql.Date;
import java.time.LocalDate;

public class Employee {
    private String id;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public OccupationType getOccupation() {
        return occupation;
    }

    public DepartmentType getDepartment() {
        return department;
    }

    private String name;
    private String lastName;
    private LocalDate startedAt;
    private LocalDate birthDate;
    private OccupationType occupation;
    private DepartmentType department;
    private Integer projectId;

    public Employee(String id, String name, String lastName, LocalDate startedAt, LocalDate birthDate,
                    OccupationType occupation, DepartmentType department, Integer projectId) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.startedAt = startedAt;
        this.birthDate = birthDate;
        this.occupation = occupation;
        this.department = department;
        this.projectId = projectId;
    }


    public Integer getProjectId() {
        return projectId;
    }

    @Override
    public String toString() {
        return "Employee{" +
            "id='" + id + '\'' +
            ", name='" + name + '\'' +
            ", lastName='" + lastName + '\'' +
            ", startedAt=" + startedAt +
            ", birthDate=" + birthDate +
            ", occupation=" + occupation +
            ", department=" + department +
            ", projectId=" + projectId +
            '}';
    }
}
