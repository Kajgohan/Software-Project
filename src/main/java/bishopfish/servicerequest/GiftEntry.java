package bishopfish.servicerequest;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class GiftEntry extends DBEntry {
    // id set by DBEntry (id)
    private String name;
    private String desc;

    public GiftEntry(String id, String name, String desc) {
        this(new ArrayList<String>() {{ add(id); add(name); add(desc); }});
    }

    // id must be first item in array list
    public GiftEntry(ArrayList<String> values) {
        super(values);
        this.name = this.values.get(1);
        this.desc = this.values.get(2);
    }

}
