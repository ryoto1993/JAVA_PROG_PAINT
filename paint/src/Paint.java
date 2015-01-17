/*import java.awt.*;
import java.awt.event.*;
*/
import javax.swing.*;

public class paint extends JFrame {
    public static void main(String[] args) {
        new paint();
    }

    public paint() {
        // set look and feel
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (ClassNotFoundException ignored) {
        } catch (InstantiationException e) {
        } catch (IllegalAccessException e) {
        } catch (UnsupportedLookAndFeelException e) {
        }
        SwingUtilities.updateComponentTreeUI(this);

        // set frame
        this.setSize(300, 230);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Simple Paint");
        this.setVisible(true);
    }
}
