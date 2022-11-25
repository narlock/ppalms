import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class GuiTests {

    @Test
    void guiTestInitialCenterPanelBackgroundColor() {
        Gui gui = new Gui(true);
        assertEquals(Color.RED, gui.getCenterPanel().getBackground());
    }

    @Test
    void guiTestClickButton() {
        Gui gui = new Gui(true);
        gui.getMyButton().doClick();
        assertEquals(Color.YELLOW, gui.getCenterPanel().getBackground());
    }

    @Test
    void guiTestClickButtonTwice() {
        Gui gui = new Gui(true);
        gui.getMyButton().doClick();
        gui.getMyButton().doClick();
        assertEquals(Color.RED, gui.getCenterPanel().getBackground());
    }

    @Test
    void ensureJPanelComponent() {
        Gui gui = new Gui(true);
        assertEquals(true, gui.getCenterPanel() instanceof JPanel);
    }

//    @Test
//    void ensureJPanelCdomponent() {
//        Gui gui = new Gui(true);
//        gui.setViewStrategy(new Strategy);
//        //I can test the new strategy
//        assertEquals(true, gui.getCenterPanel() instanceof NewStrategy);
//    }

}
