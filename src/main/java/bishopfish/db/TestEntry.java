package bishopfish.db;

import java.util.ArrayList;

public class TestEntry extends DBEntry {
    // id set by DBEntry (id)
    private String name;
    private String value;

    public TestEntry(String id, String name, String value) {
        this(new ArrayList<String>() {{ add(id); add(name); add(value); }});
    }

    // id must be first item in array list
    public TestEntry(ArrayList<String> values) {
        super(values);
        this.name = this.values.get(1);
        this.value = this.values.get(2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}