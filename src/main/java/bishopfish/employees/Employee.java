package bishopfish.employees;

public class Employee {
    private String id;
    private String name;
    private Department department;
    private String email;
    private String phone;

    public Employee(String id, String name, Department department, String email, String phone) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.email = email;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public EmployeeEntry asEntry(){
        EmployeeEntry emp = new EmployeeEntry(id, name, department.getDepartmentID(), email, phone);
        return emp;
    }

    @Override
    public String toString() {
        return name;
    }
}
