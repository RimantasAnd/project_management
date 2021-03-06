package domain;

public class Department {
    private String name;
    private String id;

    public Department(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}//class
