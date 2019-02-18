import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

public class TestPA2 {
    @Test
    public void testPut() {
        Map<String, Integer> job = new HashMap<String, Integer>();
        job.put("1", 123);
        for (String a : job.keySet()) {
            assertEquals(a, "1");
            int b = job.get("1");
            assertEquals(b, 123);
        }
    }

    @Test
    public void testGet() {
        Map<String, Integer> job = new HashMap<String, Integer>();
        job.put("1", 123);
        for (String a : job.keySet()) {
            int b = job.get(a);
            assertEquals(b, 123);
        }

    }

    @Test
    public void testContain() {
        Map<String, Integer> job = new HashMap<String, Integer>();
        job.put("1", 123);
        boolean b = job.containsKey("1");
        assertEquals(true, b);
    }

    public void testKeySet() {
        Map<String, Integer> job = new HashMap<String, Integer>();
        job.put("1", 123);
        job.put("2", 456);
        Set<String> a = new HashSet<String>();
        a.add("1");
        a.add("2");
        assertEquals(a, job.keySet());
    }

}
