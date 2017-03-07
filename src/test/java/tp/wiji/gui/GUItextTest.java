package tp.wiji.gui;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import tjp.wiji.gui.GUItext;

@RunWith(JukitoRunner.class)
public class GUItextTest {
    @Test
    public void testAncillaryConstructor() {
        GUItext dut = new GUItext("test", true);
        assertEquals(dut.toString(), "test");
        assertTrue(dut.isAncillary());
    }
    
    @Test
    public void testInsertChar() {
        GUItext dut = new GUItext("123");
        dut.insertChar(':', 1);
        assertEquals(dut.toString(), "1:23");
    }
    
    @Test
    public void testRemoveAllEmpty() { 
        GUItext dut = new GUItext("");
        dut.removeAll(':');
        assertEquals(dut.toString(), "");
    }
    
    @Test
    public void testRemoveAllTrivial() { 
        GUItext dut = new GUItext("123");
        dut.removeAll(':');
        assertEquals(dut.toString(), "123");
    }
    
    @Test
    public void testRemoveAll() { 
        GUItext dut = new GUItext("1:23");
        dut.removeAll(':');
        assertEquals(dut.toString(), "123");
    }
    
    @Test
    public void testRemoveAllTwo() { 
        GUItext dut = new GUItext("1:23:45");
        dut.removeAll(':');
        assertEquals(dut.toString(), "12345");
    }
}
