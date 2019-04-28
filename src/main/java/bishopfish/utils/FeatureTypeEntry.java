package bishopfish.utils;

import bishopfish.db.DBEntry;

import java.util.ArrayList;

public class FeatureTypeEntry extends DBEntry implements Comparable {
    // id set by DBEntry (id)
    private String featureName;
    private String permissionType;
    private String featureType;


    public FeatureTypeEntry(String id, String serviceReqestId, String permissionType, String featureType) {
        this(new ArrayList<String>() {{ add(id); add(serviceReqestId); add(permissionType); add(featureType); }});
    }

    // id must be first item in array list
    public FeatureTypeEntry(ArrayList<String> values) {
        super(values);
        this.featureName = this.values.get(1);
        this.permissionType = this.values.get(2);
        this.featureType = this.values.get(3);

    }

    @Override
    public String toString() {
        return featureName;
    }

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }

    public String getFeatureType() {
        return featureType;
    }

    public void setFeatureType(String featureType) {
        this.featureType = featureType;
    }

    public int compareTo(Object o) {
        return featureName.compareTo(((FeatureTypeEntry) o).getFeatureName());
    }
}
