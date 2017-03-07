package tp.wiji.gui;

import static org.junit.Assert.assertEquals;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import tjp.wiji.gui.Timer;

@RunWith(JukitoRunner.class)
public class TimerTest {
    @Test
    public void testSetTime() {
        Timer dut = new Timer("0:00");
        dut.setTime(0);
        assertEquals(dut.toString(), "0:00");
        dut.setTime(10);
        assertEquals(dut.toString(), "0:10");
        
        dut.setTime(60);
        assertEquals(dut.toString(), "1:00");
        dut.setTime(61);
        assertEquals(dut.toString(), "1:01");
        dut.setTime(70);
        assertEquals(dut.toString(), "1:10");
        
        dut.setTime(600);
        assertEquals(dut.toString(), "10:00");
        dut.setTime(601);
        assertEquals(dut.toString(), "10:01");
        dut.setTime(610);
        assertEquals(dut.toString(), "10:10");
        dut.setTime(660);
        assertEquals(dut.toString(), "11:00");
        
        dut.setTime(3600);
        assertEquals(dut.toString(), "1:00:00");
        dut.setTime(3601);
        assertEquals(dut.toString(), "1:00:01");
        dut.setTime(3610);
        assertEquals(dut.toString(), "1:00:10");
        dut.setTime(3660);
        assertEquals(dut.toString(), "1:01:00");
        
        dut.setTime(36000);
        assertEquals(dut.toString(), "10:00:00");
        dut.setTime(36001);
        assertEquals(dut.toString(), "10:00:01");
        dut.setTime(36060);
        assertEquals(dut.toString(), "10:01:00");
    }
}
