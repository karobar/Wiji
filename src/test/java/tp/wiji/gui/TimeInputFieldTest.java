package tp.wiji.gui;
import static org.junit.Assert.assertEquals;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import tjp.wiji.drawing.Color;
import tjp.wiji.gui.TimeInputField;

@RunWith(JukitoRunner.class)
public class TimeInputFieldTest {

    @Test
    public void testPush() {
        TimeInputField dut = new TimeInputField(Color.BLACK, Color.BLUE, 8, "sus", Color.BLACK);
        assertEquals(dut.toString(), "");
        dut.push('1');
        assertEquals(dut.toString(), "1");
        dut.push('2');
        assertEquals(dut.toString(), "12");
        dut.push('3');
        assertEquals(dut.toString(), "1:23");
        dut.push('4');
        assertEquals(dut.toString(), "12:34");
        dut.push('5');
        assertEquals(dut.toString(), "1:23:45");
        dut.push('6');
        assertEquals(dut.toString(), "12:34:56");
        
        dut.push('X');
        assertEquals(dut.toString(), "12:34:56");
        
        dut.pop();
        assertEquals(dut.toString(), "1:23:45");
        dut.pop();
        assertEquals(dut.toString(), "12:34");
        dut.pop();
        assertEquals(dut.toString(), "1:23");
        dut.pop();
        assertEquals(dut.toString(), "12");
        dut.pop();
        assertEquals(dut.toString(), "1");
        dut.pop();
        assertEquals(dut.toString(), "");
        dut.pop();
        assertEquals(dut.toString(), "");
    }
}
