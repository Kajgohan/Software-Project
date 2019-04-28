package bishopfish.servicerequest;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class SimpleEntry extends DBEntry {
    // id set by DBEntry (id)
    private String name;

    public SimpleEntry(String id, String name, String desc) {
        this(new ArrayList<String>() {{ add(id); add(name); }});
    }

    // id must be first item in array list
    public SimpleEntry(ArrayList<String> values) {
        super(values);
        this.name = this.values.get(1);
    }
}
