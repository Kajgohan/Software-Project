package bishopfish.db;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;


public class TestDBBuffer {

    @Test
    public void addDBMakeNewAdd(){
        DBUpdater dbu = new DBUpdater(DBMI.TestDBBuffer.value);
        dbu.dropTable();
        assertTrue(dbu.createTable());
        ArrayList<String> one = new ArrayList<String>() {{ add("1"); add("ONEONE"); add("ONE"); }};
        assertEquals(1, dbu.addEntry(one));
        assertEquals(one, dbu.getEntry(one.get(0)));
        ArrayList<String> two = new ArrayList<String>() {{ add("2"); add("TWOTWO"); add("TWO"); }};
        assertEquals(1, dbu.addEntry(two));
        assertEquals(two, dbu.getEntry(two.get(0)));

        DBBuffer<TestEntry> dbb = new DBBuffer<>(DBMI.TestDBBuffer.value);
        assertEquals(one, dbb.getBufferHashMap().get("1").toStringArrayList());

    }

}
