package bishopfish.db;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class DBEntry implements Comparable, Cloneable {
    private final static String badChars = "[';=-]";
    protected String id;
    protected ArrayList<String> values;   // all values with id as the first. rest should be in order of the database

    // id must be the first item
    public DBEntry(ArrayList<String> values) {
        this.values = new ArrayList<>();
        for (String s : values) {
            this.values.add(sanitize(s));
        }
        this.id = this.values.get(0);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<String> getValues() {
        return values;
    }

    public ArrayList<String> toStringArrayList() {
        return values;
    }

    protected String sanitize(String input) {
        Pattern r = Pattern.compile(badChars);
        Matcher m = r.matcher(input);
        return m.replaceAll("");
    }

    // 0 for the same, < and > work by id (string) sort, -100 for same id but different values
    // only can compare two Entries of the same type or else there is risk of index out of bounds
    @Override
    public int compareTo(Object o) {
        boolean fieldsSame = true;
        // check if the entries have the same values for each field
        for (int i = 0; i < this.values.size(); i++) {
            if (!this.values.get(i).equals(((DBEntry) o).values.get(i))) {
                fieldsSame = false;
            }
        }
        if (fieldsSame) {
            return this.getId().compareTo(((DBEntry) o).getId());
        }
        return -100;
    }


}
