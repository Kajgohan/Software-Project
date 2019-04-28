package bishopfish.db;

import bishopfish.servicerequest.ServiceRequest;
import bishopfish.servicerequest.ServiceRequestEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBBuffer<T extends DBEntry> {

    private Constructor<? extends T> ctor;
    public HashMap<String, T> bufferMap;
    private DBUpdater dbu;
    //String dbConnString = "jdbc:derby:serviceRequestDB;create=true";
//    String tableName = "serviceRequest";
//    String primaryKey = "ticketNum";
//    String colString = "TICKETNUM VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), REQUESTTYPE VARCHAR(20), TICKETCONTENT VARCHAR(500)";

    //"jdbc:derby:serviceRequestDB;create=true", "serviceRequest", "ticketNum", "TICKETNUM VARCHAR(8) NOT NULL PRIMARY KEY, NAME VARCHAR(255), REQUESTTYPE VARCHAR(20), TICKETCONTENT VARCHAR(500)"

    private static final Logger LOGGER = Logger.getLogger(DBBuffer.class.getName());


    private DBBuffer(Class<? extends T> entryType, DBMI.DBInfo info) {
        try {
            this.ctor = entryType.getConstructor(ArrayList.class);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        dbu = new DBUpdater(info);
        dbu.createTable();
        arrToEntry(dbu.getHashMap());
    }

    public DBBuffer(DBMI.DBInfo info) {
        this( (Class<? extends T>) info.getEntry(), info);
    }

    private void arrToEntry(HashMap<String, ArrayList<String>> dbOutput) {
        bufferMap = new HashMap<>();
        for (HashMap.Entry<String, ArrayList<String>> entry : dbOutput.entrySet()) {
            try {
                bufferMap.put(entry.getKey(), ctor.newInstance(entry.getValue()));
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }

    public void add(T toBeAdded){
        dbu.addEntry(toBeAdded.toStringArrayList());
        bufferMap.put(toBeAdded.getId(), toBeAdded);
    }

    public void addR(T toBeAdded){
        ArrayList<String> al = toBeAdded.toStringArrayList();
        al.remove(0);
        Object[] results = dbu.addEntryR(al);
        toBeAdded.setId((String) results[1]);
        bufferMap.put(toBeAdded.getId(), toBeAdded);
    }

    public void remove(String id){
        dbu.removeEntry(id);
        bufferMap.remove(id);
    }

    public T get(String id) {
        return bufferMap.get(id);
    }

    public HashMap<String, T> getAll() {
        return this.bufferMap;
    }

    public void update(String id, ArrayList<String> toUpdate){

        dbu.updateEntry(id, toUpdate);
        ArrayList<String> oldEntry = bufferMap.get(id).toStringArrayList();
        //when updating with the same key, it overwrites the old value
        for (int i = 0; i < toUpdate.size(); i++) {
            if(toUpdate.get(i) != null){
                oldEntry.set(i + 1, toUpdate.get(i));
            }
        }
        try {
            bufferMap.put(id, ctor.newInstance(oldEntry));
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }

    /**
     * update from two entries
     * @param oldEntry the old entry before changes
     * @param newEntry the new entry of the same row but with new values (same id)
     */
    public void update(T oldEntry, T newEntry){
        String id = oldEntry.getId();
        if (id.compareTo(newEntry.getId()) != 0) {
            LOGGER.log(Level.SEVERE, "Cannot update entries with mismatching id's");
            return;
        }
        ArrayList<String> diff = new ArrayList<>();

        String tmpDiff;
        // loop though all feilds besides id. add new value to diff if different, else null
        for (int i = 1; i < newEntry.getValues().size(); i++) {
            tmpDiff = null;
            if (newEntry.getValues().get(i).equals(oldEntry.getValues().get(i))) {
                tmpDiff = newEntry.getValues().get(i);
            }
            diff.add(tmpDiff);
        }

        bufferMap.put(id, newEntry);
    }

    public void close() {
        dbu.close();
    }

    public void open() {
        dbu.open();
    }

    public HashMap<String, T> getBufferHashMap(){
        return bufferMap;
    }

    public ObservableList<T> getBufferObservableList(){
        ObservableList<T> observableSRList = FXCollections.observableArrayList();
        for(HashMap.Entry<String, T> entry : bufferMap.entrySet()){
            observableSRList.add(entry.getValue());
        }
        return observableSRList;
    }


    // TODO: Get rid of
    @Deprecated
    public ObservableList<String> getBufferObservableListId(){
        ObservableList<String> observableSRList = FXCollections.observableArrayList();
        for(HashMap.Entry<String, T> entry : bufferMap.entrySet()){
            observableSRList.add(entry.getValue().getId());
        }
        return observableSRList;
    }


    public interface OlFromEntryLambda {
        DBEntry getFromEntry(DBEntry dbe);
    }

    public ObservableList<DBEntry> getOlOfel(OlFromEntryLambda filter){
        ObservableList<DBEntry> observableSRList = FXCollections.observableArrayList();
        for(HashMap.Entry<String, T> entry : this.bufferMap.entrySet()){
            DBEntry filterdValue = filter.getFromEntry(entry.getValue());
            if (filterdValue != null) {
                observableSRList.add(filterdValue);
            }
        }
        return observableSRList;
    }



}
