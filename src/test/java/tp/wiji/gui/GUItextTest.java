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
}
