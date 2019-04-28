package bishopfish.employees;

public class Department {
    String departmentID;
    String name;
    String type;

    public Department(String departmentID, String name, String type) {
        this.departmentID = departmentID;
        this.name = name;
        this.type = type;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String departmentID) {
        this.departmentID = departmentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public DepartmentEntry asEntry() {
        DepartmentEntry dep = new DepartmentEntry(departmentID,name,type);
        return dep;
    }

    @Override
    public String toString() {
        return name;
    }
}
