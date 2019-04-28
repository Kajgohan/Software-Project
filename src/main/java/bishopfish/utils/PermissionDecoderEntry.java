package bishopfish.utils;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class PermissionDecoderEntry extends DBEntry {
    // id set by DBEntry (id)
    private String featureId;

    public PermissionDecoderEntry(String id, String serviceRequestType) {
        this(new ArrayList<String>() {{ add(id); add(serviceRequestType); }});
    }

    // id must be first item in array list
    public PermissionDecoderEntry(ArrayList<String> values) {
        super(values);
        this.featureId = this.values.get(1);
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }
}
