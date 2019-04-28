package bishopfish.employees;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class DepartmentEntry extends DBEntry {
    //ID set by departmentID
    String name;
    String type;

    public DepartmentEntry(String ID, String name, String type) {
        this(new ArrayList<String>() {{ add(ID); add(name); add(type); }});
    }

    public DepartmentEntry(ArrayList<String> values) {
        super(values);
        this.name = values.get(1);
        this.type = values.get(2);
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

    @Override
    public String toString() {
        return this.name;
    }
}
