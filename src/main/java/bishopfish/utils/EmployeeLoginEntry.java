package bishopfish.utils;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class EmployeeLoginEntry extends DBEntry {
    // id set by DBEntry (id)
    private String saltedPassword;
    private String clearance;

    public EmployeeLoginEntry(String id, String saltedPassword, String clearance) {
        this(new ArrayList<String>() {{ add(id); add(saltedPassword); add(clearance); }});
    }

    // id must be first item in array list
    public EmployeeLoginEntry(ArrayList<String> values) {
        super(values);
        this.saltedPassword = this.values.get(1);
        this.clearance = this.values.get(2);
    }

    public String getSaltedPassword() {
        return saltedPassword;
    }

    public void setSaltedPassword(String saltedPassword) {
        this.saltedPassword = saltedPassword;
    }

    public String getClearance() {
        return clearance;
    }

    public void setClearance(String clearance) {
        this.clearance = clearance;
    }
}
