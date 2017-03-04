package tp.wiji.gui;

import org.jukito.JukitoRunner;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

import tjp.wiji.drawing.BitmapContext;
import tjp.wiji.drawing.Color;
import tjp.wiji.gui.ScreenTextCollection;

@RunWith(JukitoRunner.class)
public class ScreenTextCollectionTest {
    // Integration
    @Test
    public void testBuilder() {
        ScreenTextCollection dut = ScreenTextCollection.newBuilder()
                .bitmapContext(mock(BitmapContext.class))
                .color(Color.RED)
                .initialItem(" SUS ")
                .centered()
                .y(11)
                .build();
        assertNotNull(dut);
        assertTrue(dut.isCentered());
    }
    
    // Integration
    @Test
    public void testBuilderChoiceList() {
        ScreenTextCollection dut = ScreenTextCollection.newBuilder()
                .bitmapContext(mock(BitmapContext.class))
                .inactiveColor(Color.BLACK)
                .activeColor(Color.BLUE)
                .centered()
                .y(15)
                .build();
        assertNotNull(dut);
        assertTrue(dut.isCentered());
    }
}
