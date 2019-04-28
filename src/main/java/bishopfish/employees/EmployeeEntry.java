package bishopfish.employees;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class EmployeeEntry extends DBEntry {
    //id set by DBEntry (employeeID)
    private String name;
    private String departmentID;
    private String email;
    private String phone;

    public EmployeeEntry(String employeeID, String name, String departmentID, String email, String phone) {
        this(new ArrayList<String>() {{ add(employeeID); add(name); add(departmentID); add(email); add(phone); }});
    }

    public EmployeeEntry(ArrayList<String> values){
        super(values);
        this.name = this.values.get(1);
        this.departmentID = this.values.get(2);
        this.email = this.values.get(3);
        this.phone = this.values.get(4);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartmentID() {
        return departmentID;
    }

    public void setDepartmentID(String department) {
        this.departmentID = department;
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

    @Override
    public String toString() {
        return this.name;
    }
}
