package bishopfish.db;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;


public class TestDBEntry {

    @BeforeEach
    public void setUp() {
        DBUpdater dbu = new DBUpdater(DBMI.TestDBBuffer.value);
        dbu.dropTable();
    }



    @Test
    public void testSanitize(){
        TestEntry test = new TestEntry("jac;;;k", "is fine", "out'of;;ideas");
        assertEquals("jack", test.getId());
        assertEquals("is fine", test.getName());
        assertEquals("outofideas", test.getValue());



    }

    @Test
    public void testsrBufferDOTaddToSRBuffer(){
        DBBuffer<TestEntry> dbb = new DBBuffer<>(DBMI.TestDBBuffer.value);
        TestEntry one = new TestEntry("1","Jack", "Needs Help");
        TestEntry two = new TestEntry("2","Jack", "Needs Help");
        TestEntry three = new TestEntry("3","Jack", "Needs Help");
        TestEntry four = new TestEntry("4","Jack", "Needs Help");
        dbb.add(one);
        dbb.add(two);
        dbb.add(three);
        dbb.add(four);


        HashMap<String, TestEntry> hm = new HashMap<>();
        hm.put(one.getId(), one);
        hm.put(two.getId(), two);
        hm.put(three.getId(), three);
        hm.put(four.getId(), four);

        assertEquals(hm, dbb.getBufferHashMap());
    }

    @Test
    public void testerBufferDOTgetSRBufferObservableList(){
        DBBuffer<TestEntry> dbb = new DBBuffer<>(DBMI.TestDBBuffer.value);
        TestEntry test1 = new TestEntry("1","Jack", "Needs Help");
        TestEntry test2 = new TestEntry("2","Jack", "Needs Help");
        TestEntry test3 = new TestEntry("3","Jack", "Needs Help");
        TestEntry test4 = new TestEntry("4","Jack", "Needs Help");
        dbb.add(test1);
        dbb.add(test2);
        dbb.add(test3);
        dbb.add(test4);

        assertFalse(dbb.getBufferObservableList().isEmpty());
    }



}
